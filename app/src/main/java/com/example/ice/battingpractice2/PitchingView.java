/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */


package com.example.ice.battingpractice2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;


	class PitchingView extends SurfaceView implements SurfaceHolder.Callback {
		private Crosshair crosshair1;
		private int mCrosshairX = 160;
		
	    class PitchingThread extends Thread {

            
            
            public PitchingThread(SurfaceHolder surfaceHolder, Context context, Handler handler) {
	            // get handles to some important objects
	        }

	        /**  * Starts the game, setting parameters for the current difficulty.  */
	        
	        public void setNewBackground(String string) {
	        }

	        /** * Pauses the physics update & animation. */
	        public void pause() {

	        }

	        /**
	         * Restores game state from the indicated Bundle. Typically called when
	         * the Activity is being restored after having been previously destroyed.
	         * 
	         * @param savedState Bundle containing the game state
	         */
	        public synchronized void restoreState(Bundle savedState) {
	        }

	        @Override
	        public void run() {
	        }

	        /** * Dump game state to the provided Bundle. */
	        public Bundle saveState(Bundle map) {
	            return map;
	        }


	         /*  * @param b true to run thread, false to shut down  */
	        public void setRunning(boolean b) {
	        }

	        /** * Sets the game state mode. */
	        public void setState(int mode) {
	        }

	        public void setState(int mode, CharSequence message) {

	        }
	        
	        public int getCurrentState()
	        {
	        	return 1;
	        }

	        /* Callback invoked when the surface dimensions change. */
	        public void setSurfaceSize(int width, int height) {
	        }

	        public void unpause() {
	        }

	        /** * Handles a key-down event.  */
	        boolean doKeyDown(int keyCode, KeyEvent msg) {
	        	return true;
	        }

	        /**  * Handles a key-up event. */
	        boolean doKeyUp(int keyCode, KeyEvent msg) {
	            boolean handled = false;


	            return handled;
	        }

	        /*** Draws objects and background to the provided Canvas. Operations on the Canvas accumulate so this clears the screen***/
	        private void doDraw(Canvas canvas) {

	        }

	        /**
	         *  Does not invalidate(). Called at the start of draw().
	         * Detects the end-of-game and sets the UI to the next state.
	         */
	        private void updatePhysics() {
	            long now = System.currentTimeMillis();


	            // figure speeds for the end of the period
	        }

	    
	        private void detectCollision()
	        {
	    
	        }
  
	         private void playSound(int resid)
	         {
	      	    
	         }
	    }
	    
	    
	    /** Handle to the application context, used to e.g. fetch Drawables. */
	    private Context mContext;
	    private TextView mStatusText;
	    private PitchingThread thread;	    /** The thread that actually draws the animation */


	    public PitchingView(Context context, AttributeSet attrs) {
	        super(context, attrs);

	        // register our interest in hearing about changes to our surface
	        SurfaceHolder holder = getHolder();
	        holder.addCallback(this);

	        // create thread only; it's started in surfaceCreated()
	        thread = new PitchingThread(holder, context, new Handler() {
	            @Override
	            public void handleMessage(Message m) {
	                mStatusText.setVisibility(m.getData().getInt("viz"));
	                mStatusText.setText(m.getData().getString("text"));
	            }
	        });

	        setFocusable(true); // make sure we get key events
	    }

	    /**
	     * Fetches the animation thread corresponding to this LunarView.
	     * 
	     * @return the animation thread
	     */
	    public PitchingThread getThread() {
	        return thread;
	    }


	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent msg) {
	        return thread.doKeyDown(keyCode, msg);
	    }

	    @Override
	    public boolean onKeyUp(int keyCode, KeyEvent msg) {
	        return thread.doKeyUp(keyCode, msg);
	    }

	    /**
	     * Standard window-focus override. Notice focus lost so we can pause on
	     * focus lost. e.g. user switches to take a call.
	     */
	    @Override
	    public void onWindowFocusChanged(boolean hasWindowFocus) {
	        if (!hasWindowFocus) thread.pause();
	    }

	    public void setTextView(TextView textView) {
	        mStatusText = textView;
	    }

	    /* Callback invoked when the surface dimensions change. */
	    public void surfaceChanged(SurfaceHolder holder, int format, int width,  int height) {
	        thread.setSurfaceSize(width, height);
	    }

	    /* * Callback invoked when the Surface has been created and is ready to be used. */
	    public void surfaceCreated(SurfaceHolder holder) {
	        // start the thread here so that we don't busy-wait in run()
	        // waiting for the surface to be created
	        thread.setRunning(true);
	        thread.start();
	    }

	    /*
	     * Callback invoked when the Surface has been destroyed and must no longer
	     * be touched. WARNING: after this method returns, the Surface/Canvas must
	     * never be touched again!
	     */
	    public void surfaceDestroyed(SurfaceHolder holder) {
	        // we have to tell thread to shut down & wait for it to finish, or else
	        // it might touch the Surface after we return and explode
	        boolean retry = true;
	        thread.setRunning(false);
	        while (retry) {
	            try {
	                thread.join();
	                retry = false;
	            } catch (InterruptedException e) {
	            }
	        }
	    }
	    
		// events when touching the screen
		 
	    public boolean onTouchEvent(MotionEvent event)  {
	        int eventaction = event.getAction();
	 
	        int X = (int)event.getX();
	        int Y = (int)event.getY();
	 
	        switch (eventaction ) {
	 
	        case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on a ball
//	                for (Crosshair ball : colorballs) {
	                        // check all the bounds of the ball
	                if (X > crosshair1.getX() && X < crosshair1.getX()+50 && Y > crosshair1.getY() && Y < crosshair1.getY()+50){
	                        int balID = crosshair1.getID();
	              }
	             break;
	 
	        case MotionEvent.ACTION_MOVE:   // touch drag with the ball
	                // move the balls the same as the finger
	            crosshair1.setX(X-25);
	            crosshair1.setY(Y-25);          
	             break;
	 
	        case MotionEvent.ACTION_UP:
	                mCrosshairX = crosshair1.getX();
	             break;
	 
	        }
	 
	        // redraw the canvas
	        invalidate();
	        return true;
	    }
	}
