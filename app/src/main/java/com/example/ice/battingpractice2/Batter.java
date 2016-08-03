package com.example.ice.battingpractice2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class Batter  {
	private int[] mBatter;

	private Bitmap img; // the image of pitcher

 	private int coordX = 0; // the x coordinate at the canvas
 	private int coordY = 0; // the y coordinate at the canvas

	private int width = 0;
	private int height = 0;
	private int id;              // gives every bal
	private static int count    = 1;
	private boolean goRight     = true;
 	private boolean goDown      = true;
	private boolean rightHanded = true;
	private int MAX_BATTER = 16;
	private int frame = 0;

 
	public Batter() {


		mBatter = new int[35];
		mBatter[0] = R.drawable.hit1;
		mBatter[1] = R.drawable.hit2;
		mBatter[2] = R.drawable.hit3s;
		mBatter[3] = R.drawable.hit5s;
		mBatter[4] = R.drawable.hit7s;
		mBatter[5] = R.drawable.hit9s;
		mBatter[6] = R.drawable.hit11s;
		mBatter[7] = R.drawable.hit13s;
		mBatter[8] = R.drawable.hit15s;
		mBatter[9] = R.drawable.hit17;
		mBatter[10] = R.drawable.hit19;
		mBatter[11] = R.drawable.hit21;
		mBatter[12] = R.drawable.hit23;
		mBatter[13] = R.drawable.hit25;
		mBatter[14] = R.drawable.hit27;
		mBatter[15] = R.drawable.hit29;
		mBatter[16] = R.drawable.hit30;
	}

	public int next() {
		frame++;
		if (frame >= MAX_BATTER)
			frame = MAX_BATTER;
		return mBatter[frame];
	}


	public void begin() {
		frame = 0;
	}
	void setX(int newValue) {
        coordX = newValue;
    }
	
	public int getX() {
		return coordX;
	}

	void setY(int newValue) {
        coordY = newValue;
   }


	public int getY() {
		return coordY;
	}
	
	public int getFrame() {
		return frame;
	}

	void setWidth(int newValue) {
		width = newValue;
	}
	void setHeight(int newValue) {
		height = newValue;
	}





	public Drawable getDrawable(Context c) {
		Drawable ball = c.getResources().getDrawable(this.next());
		ball.setBounds((int) this.getX(), (int) this.getY(), this.getX() + width, this.getY() + height);
		return ball;
	}

	public void moveBatter(int goX, int goY) {
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
	
}

