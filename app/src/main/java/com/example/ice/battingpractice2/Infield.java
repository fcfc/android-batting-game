package com.example.ice.battingpractice2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class Infield  {
    public boolean firstbase = true;
    public boolean secondbase = true;
    public boolean thirdbase = true;
    public boolean home = false;
  
    Paint vPaint;
    Paint tPaint;
    Paint wPaint;
    int plateX = 140;
    int plateY = 500;
    int currentY = 420;
    int textX = 30;
 
    String atBat = "AT BAT: ";
    String batter = "CRIMI: ";
    String avg = ".374";
    String ballString = "BALL: ";
    String strikeString = "STRIKE: ";
    String outString = "OUT: ";
    int balls = 0;
    int strikes = 0;
    int outs = 0;

    
    public void init() {

        vPaint = new Paint();
        vPaint.setColor(0xaa888888);
        vPaint.setStrokeWidth(2);
        tPaint = new Paint();
        tPaint.setColor(Color.RED);
        tPaint.setTextSize(15);
        wPaint = new Paint();
        wPaint.setColor(Color.BLUE);
        wPaint.setTextSize(15);

    }
        public void draw(Canvas canvas) {
//      canvas.drawColor(Color.BLACK);
        Path path = new Path();
        vPaint = new Paint();
        vPaint.setColor(Color.GREEN);
		path.moveTo(plateX,plateY);
		path.lineTo(plateX+40, plateY-40);// draw vertical blue line
		path.lineTo(plateX, plateY-80);// draw vertical blue line
		path.lineTo(plateX-40, plateY-40);
		path.close();
		canvas.drawPath(path, vPaint);
		vPaint.setColor(Color.WHITE);
		RectF rectF = new RectF(plateX, 00, plateX, 60);
		canvas.drawArc(rectF, -180.0f, 180.0f, true, vPaint);
		textX = plateX - 100;
		canvas.drawText(atBat, textX, currentY, vPaint);
		canvas.drawText(batter, textX, currentY+20, vPaint);
		canvas.drawText(avg, textX, currentY+40, vPaint);
		canvas.drawText(ballString + " " + Integer.toString(balls), textX, currentY+60, vPaint);
		canvas.drawText(strikeString + " " + Integer.toString(strikes), textX, currentY+80, vPaint);
		canvas.drawText(outString + " " + Integer.toString(outs), textX, currentY+100, vPaint);
		if (firstbase)
			canvas.drawCircle((float)plateX+40, 464f, 4, tPaint);
		if (secondbase)
			canvas.drawCircle((float)plateX, 424f, 4, tPaint);
		if (thirdbase)
			canvas.drawCircle((float)plateX - 40, 464f, 4, tPaint);

    }

        public void setFirstBase(boolean b) {
            firstbase = b;
        }
        
        public void setSecondBase(boolean b) {
        	secondbase = b;
        }
        
        public void setThirdBase(boolean b) {
        	thirdbase = b;
        }
        
        public void setBalls(int i) {
        	balls = i;
        }        
        
        public void setStrikes(int i) {
        	strikes = i;
        }
        
        public void setOuts(int i) {
        	outs = i;
        }       

        public void advanceRunners(int i) {

        	if (thirdbase) {
        		thirdbase = false;
        	}
        	if (secondbase)  {
        		secondbase = false;
        		thirdbase = true;
        	}
        	if (firstbase) {
        		firstbase = false;
        		secondbase = true;
        	}
        	firstbase = true;
        }

    public void setX(int value) { plateX = value;}
    public void setY(int value) { plateY = value;}

}
