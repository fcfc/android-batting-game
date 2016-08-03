package com.example.ice.battingpractice2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Ball {
	private int[] mBall;

	private Bitmap img; // the image of pitcher

 private int coordX = 0; // the x coordinate at the canvas
 private int coordY = 0; // the y coordinate at the canvas
 private int id;              // gives every bal
 private static int count    = 1;
 private boolean goRight     = true;
 private boolean goDown      = true;
 private boolean rightHanded = true;
	private int MAX_BALL = 8;
	private int frame = 0;

	private int width = 100;
	private int height = 100;

	public Ball() {


		mBall = new int[11];
		mBall[0] = R.drawable.bb1;
		mBall[1] = R.drawable.bb2;
		mBall[2] = R.drawable.bb3;
		mBall[3] = R.drawable.bb4;
		mBall[4] = R.drawable.bb5;
		mBall[5] = R.drawable.bb6;
		mBall[6] = R.drawable.bb7;
		mBall[7] = R.drawable.bb8;
		mBall[8] = R.drawable.bb9;
	}

	public int next() {
		frame++;
		if (frame >= MAX_BALL)
			frame = MAX_BALL;
		return mBall[frame];
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
	
	public int getID() {
		return id;
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

