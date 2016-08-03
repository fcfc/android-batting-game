package com.example.ice.battingpractice2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Scorecard  {
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
