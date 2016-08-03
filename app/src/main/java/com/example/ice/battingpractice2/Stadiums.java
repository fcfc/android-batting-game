
package com.example.ice.battingpractice2;


	import android.content.Context;
	import android.graphics.Bitmap;
	import android.graphics.BitmapFactory;

	public class Stadiums  {
	 private Bitmap img; // the image of pitcher
	 private int coordX = 0; // the x coordinate at the canvas
	 private int coordY = 0; // the y coordinate at the canvas
	 private int id; // gives every ball his own id, for now not necessary
	 private static int count = 1;
	 private boolean goRight = true;
	 private boolean goDown = true;
	 
		public Stadiums(Context context, int drawable) {

			BitmapFactory.Options opts = new BitmapFactory.Options();
	        opts.inJustDecodeBounds = true;
	        img = BitmapFactory.decodeResource(context.getResources(), drawable); 
	        id=count;
			count++;

		}
		

		
	}

