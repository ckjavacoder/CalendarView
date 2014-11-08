package com.youloft.calendar.calendarview;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by javen on 14-11-8.
 */
public class DrawParam {

    public Paint framePaint;

    public Paint festivalPaint;

    public Paint lunarPaint;

    public DrawParam() {
        framePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        framePaint.setColor(Color.RED);
        framePaint.setStyle(Paint.Style.STROKE);


        festivalPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        festivalPaint.setTextSize(50);
        festivalPaint.setColor(Color.BLACK);
        festivalPaint.setTextAlign(Paint.Align.CENTER);
        festivalPaint.setFakeBoldText(true);
        festivalPaint.setTypeface(Typeface.MONOSPACE);

    }


    public void updateState(DayCell dayCell) {
        festivalPaint.setColor(dayCell.isChecked() ? Color.WHITE : Color.BLACK);
        festivalPaint.setTextSize(dayCell.width/3f);
        framePaint.setStyle(!dayCell.isChecked() ? Paint.Style.STROKE : Paint.Style.FILL);

    }
}
