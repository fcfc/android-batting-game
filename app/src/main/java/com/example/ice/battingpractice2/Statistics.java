package com.example.ice.battingpractice2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Statistics  {
    public int inning = 0;
    public int visitors = 0;
    public int home = 0;
    public int ball = 0;
    public int strike = 0;
    public int out = 0;
    public int pitchSpeed = 0;
    Paint vPaint;
    Paint tPaint;
    Paint wPaint;

    
    public void init() {

        vPaint = new Paint();
        vPaint.setColor(0xaa888888);
        vPaint.setStrokeWidth(2);
        tPaint = new Paint();
        tPaint.setColor(Color.RED);
        tPaint.setTextSize(15);
        wPaint = new Paint();
        wPaint.setColor(Color.WHITE);
        wPaint.setTextSize(15);

    }
        public void draw(Canvas canvas) {
//      canvas.drawColor(Color.BLACK);
        Path path = new Path();
		path.moveTo(0, 0);
		path.lineTo(480, 0);// draw vertical blue line
		path.lineTo(480, 50);// draw vertical blue line
		path.lineTo(0, 50);
		path.close();
		canvas.drawPath(path, vPaint);		
		canvas.drawText("BALL", 220, 15, tPaint);
		canvas.drawText("STRIKE", 220, 35, tPaint);

		canvas.drawText(Integer.toString(ball), 300, 15, wPaint);
		canvas.drawText(Integer.toString(strike), 300, 35, wPaint);	
    }

        public void setBall(int i) {
            ball = i;
        }
        
        public void setStrike(int i) {
        	strike = i;
        }
        
        public void setOut(int i) {
        	out = i;
        }
}
