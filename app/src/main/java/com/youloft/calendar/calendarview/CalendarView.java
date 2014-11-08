package com.youloft.calendar.calendarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by javen on 14-11-8.
 */
public class CalendarView extends View {

    private IDayCell[] mChilds = new IDayCell[42];

    private DrawParam mDrawParam = null;


    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSloup = ViewConfiguration.get(context).getScaledTouchSlop();
        mDrawParam = new DrawParam();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int newHeight = Math.round(width / 7f) * 6;
        setMeasuredDimension(width, newHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        inflateLayout();
    }

    /**
     * 填充布局
     */
    private void inflateLayout() {
        int itemWidth = getWidth() / 7;
        int itemHeight = getHeight() / 6;
        int extraWidth = getWidth() % 7;
        int extra = 0;
        int row = 0;
        int col = 0;
        int x = 0;
        int w, h;
        for (int i = 0; i < mChilds.length; i++) {
            row = i / 7;
            col = i % 7;
            if (col == 0) {
                x = 0;
            }
            if (mChilds[i] == null) {
                mChilds[i] = makeChildLayout(row, col);
            }
            extra = (7 - col) < extraWidth ? 1 : 0;
            w = extra + itemWidth;
            h = itemHeight;
            System.out.println("x:" + x + " y:" + row * itemHeight);
            mChilds[i].setFrame(x, row * itemHeight, w, h);
            x += w;
        }
    }

    /**
     * 产生子项布局
     *
     * @param row
     * @param col
     * @return
     */
    private IDayCell makeChildLayout(int row, int col) {
        return new DayCell(row, col);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < 42; i++) {
            final IDayCell cell = mChilds[i];
            if (cell != null)
                cell.onDraw(canvas, mDrawParam);
        }
    }


    private float downx, downy;

    private int mTouchMode = 0;

    private int mTouchSloup;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                mTouchMode = 1;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(event.getX() - downx) > mTouchSloup || Math.abs(event.getY() - downy) > mTouchSloup) {
                    mTouchMode = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mTouchMode == 1) {
                    onItemClick(event);
                }

        }

        return true;

    }


    private Rect mItemTouchRect = new Rect();


    private int mSelected = -1;

    /**
     * 点击
     *
     * @param event
     */
    private void onItemClick(MotionEvent event) {
        int tx = Math.round(event.getX());
        int ty = Math.round(event.getY());
        for (int i = 0; i < 42; i++) {
            if (mChilds[i] != null && mChilds[i].contains(tx, ty)) {
                if (i == mSelected) {
                    return;
                }
                if (mSelected != -1 && mSelected < 42) {
                    mChilds[mSelected].setChecked(false);
                }
                mSelected = i;
                performItemClick(i);
                break;
            }
        }
    }

    /**
     * 某一项点击
     *
     * @param i
     */
    private void performItemClick(int i) {

        mChilds[i].setChecked(true);

        postInvalidate();


        System.out.println("item[" + i + "] has clicked");

    }
}
