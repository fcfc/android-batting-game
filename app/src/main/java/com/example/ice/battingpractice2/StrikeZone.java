package com.example.ice.battingpractice2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class StrikeZone {

    public boolean home = false;
  
    Paint mLinePaint;

    int plateX = 80;
    int plateY = 500;
    /* strike zone */
    public int szlx = 650;
    public int szly = 280;
    public int szrx = 750;
    public int szry = 400;

    
    public void init() {

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setARGB(255, 0, 255, 0);

    }
        public void draw(Canvas canvas) {
            DashPathEffect dashPath = new DashPathEffect(new float[]{2, 2}, 1);
            mLinePaint.setPathEffect(dashPath);
            mLinePaint.setStrokeWidth(2);
            canvas.drawLine(szlx, szly, szrx, szly, mLinePaint);    // vert
            canvas.drawLine(szlx, szry, szrx, szry, mLinePaint);    // vert
            canvas.drawLine(szlx, szly, szlx, szry, mLinePaint);
            canvas.drawLine(szrx, szly, szrx, szry, mLinePaint);

    }

    public void setX(int value) { plateX = value;}
    public void setY(int value) { plateY = value;}

}
