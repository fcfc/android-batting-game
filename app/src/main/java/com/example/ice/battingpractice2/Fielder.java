
package com.example.ice.battingpractice2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Fielder {
	 private Bitmap img;     // the image of pitcher
	 private int coordX = 0; // the x coordinate at the canvas
	 private int coordY = 0; // the y coordinate at the canvas
	 private int mFielder[];         // gives every ball his own id, for now not necessary
	 private static int count = 1;
	 private boolean goRight = true;
	 private boolean goDown = true;
	 private boolean rightHanded = true;
    private int MAX_PITCHER = 107;
    private int frame = 0;

    private int width = 200;
    private int height = 200;

		public Fielder() {
            mFielder = new int[4];
            mFielder[0] = R.drawable.p1;
            }

    public int next() {
        return mFielder[frame];
    }

    public void begin() {
        frame = 0;
    }

    public int getFrame() {
			return frame;
		}
		
		void setX(int newValue) {
	        coordX = newValue;
	    }
		
		public int getX() {
			return coordX;
		}

		void setHeight(int newValue) {
	        height = newValue;
	   }
		
		public int getHeight() {
			return height;
		}

    void setWidth(int newValue) {
        width = newValue;
    }

    public int getWidth() {
        return width;
    }

    void setY(int newValue) {
        coordY = newValue;
    }

    public int getY() {
        return coordY;
    }



    public Drawable getDrawable(Context c) {
        Drawable ball = c.getResources().getDrawable(this.next());
        ball.setBounds((int) this.getX(), (int) this.getY(), this.getX() + width, this.getY() + height);
        return ball;
    }
		
		public Bitmap getBitmap() {
			return img;
		}
		
		public void movePitcher(int goX, int goY) {
			// check the borders, and set the direction if a border has reached
			if (coordX > 270){
				goRight = false;
			}
			if (coordX < 0){
				goRight = true;
			}
			if (coordY > 400){
				goDown = false;
			}
			if (coordY < 0){
				goDown = true;
			}
			// move the x and y 
			if (goRight){
				coordX += goX;
			}else
			{
				coordX -= goX;
			}
			if (goDown){
				coordY += goY;
			}else
			{
				coordY -= goY;
			}
			
		}
		public void update() {
		}

		
	}

