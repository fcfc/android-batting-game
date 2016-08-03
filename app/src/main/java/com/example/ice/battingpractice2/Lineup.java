
package com.example.ice.battingpractice2;


	import android.content.Context;
	import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;

	public class Lineup  {
	 private Bitmap img; // the image of pitcher
	 private int coordX = 0; // the x coordinate at the canvas
	 private int coordY = 0; // the y coordinate at the canvas
	 
		public Lineup(Context context, int drawable) {

			BitmapFactory.Options opts = new BitmapFactory.Options();
	        opts.inJustDecodeBounds = true;
	        img = BitmapFactory.decodeResource(context.getResources(), drawable); 

		}
		
		

		
	}

