package com.youloft.calendar.calendarview;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by javen on 14-11-8.
 */
public interface IDayCell {

    void setFrame(int x, int y, int w, int h);

    Rect getFrame();

    int getX();

    int getY();

    void onDraw(Canvas canvas, DrawParam param);

    int getRow();

    int getCol();

    boolean contains(int x, int y);

    void setChecked(boolean b);
}
