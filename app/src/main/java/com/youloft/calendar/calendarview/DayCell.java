package com.youloft.calendar.calendarview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by javen on 14-11-8.
 */
public class DayCell implements IDayCell {

    private Rect mFrameRect = new Rect();

    private Rect mHintRect = new Rect();


    public int width;
    private int height;


    private int x;
    private int y;
    private int row;
    private int col;


    public DayCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void setFrame(int x, int y, int w, int h) {
        mFrameRect.set(0, 0, w, h);
        mHintRect.set(x, y, x + w, y + h);
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public Rect getFrame() {
        return mFrameRect;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void onDraw(Canvas canvas, DrawParam param) {
        canvas.save();
        canvas.translate(x, y);
        param.updateState(this);
        canvas.drawRect(mFrameRect, param.framePaint);
        canvas.drawText("30", width / 2, height / 3, param.festivalPaint);
        canvas.restore();
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public boolean contains(int x, int y) {
        return mHintRect.contains(x,y);
    }


    private boolean isChecked = false;
    @Override
    public void setChecked(boolean b) {
        isChecked = b;
    }

    public boolean  isChecked(){
        return isChecked;
    }


}
