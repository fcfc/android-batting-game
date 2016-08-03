package com.example.ice.battingpractice2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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


class BattingView extends SurfaceView implements SurfaceHolder.Callback {
		protected Crosshair crosshair1;
		private int mCrosshairX = 160;
		
	    class BattingThread extends Thread {

			public static final int STATE_INIT = 1;
			public static final int STATE_PAUSED = 2;
			public static final int STATE_READY = 3;
			public static final int STATE_PITCHING = 4;
			public static final int STATE_BATTING = 5;
			public static final int STATE_RUNNING = 6;
			public static final int STATE_PITCHTHROWN = 7;
			public static final int STATE_PITCHCAUGHT = 8;
			public static final int STATE_WIN = 9;
			public static final int STATE_UPDATEFIELD = 10;
            public static final int STATE_START_PITCHER = 11;
            public static final int STATE_START_BATTER = 12;


            /* * State-tracking constants */
			public static final int UP = 1;
			public static final int DOWN = 2;
			public static final int LEFT = 3;
			public static final int RIGHT = 4;
	        /*
	         * Goal condition constants */
	         public static final int IS_PITCH = 1;
	         public static final int IS_HIT   = 2;

	         public static final int PITCH_X = 580;
	         public static final int PITCH_Y = 140;
	         public static final int XBOUNDING = 20;

	         /* Pitch Type */
			 public static final int PITCH_FASTBALL = 1;
			 public static final int PITCH_CURVEBALL = 2;
			 public static final int PITCH_SLIDER = 3;
			 public static final int PITCH_CHANGE = 4;
			 public static final int PITCH_KNUCKLE = 5;
			 public static final int PITCH_SCREWBALL = 5;
			 public static final int MAX_PITCHER = 53;

/* pitch location */
			 public static final int LOCATION_N = 1;
			 public static final int LOCATION_NE = 2;
			 public static final int LOCATION_E = 3;
			 public static final int LOCATION_SE = 4;
			 public static final int LOCATION_S = 5;
			 public static final int LOCATION_SW = 6;
			 public static final int LOCATION_W = 7;
			 public static final int LOCATION_NW = 8;

			 

			 
	         private int XBATTER = 100;
	         private int YBATTER = 120;

	         private int XPITCHER = 640;   // 240
	         private int YPITCHER = 220;    // 80
			 /* GAME constants ()
	         */
	        public static final int UI_BAR_HEIGHT = 10; // height of the bar(s)
	        private static final String KEY_DIFFICULTY = "mDifficulty";
	        private static final String KEY_DX = "mDX";
	        private static final String KEY_DY = "mDY";

	        private static final String KEY_HEADING = "mHeading";
	        private static final String KEY_WINS = "mWinsInARow";



	        /* Member (state) fields */
	        
	        public Bitmap mBackgroundImage;  // The drawable to use as the background of the animation canvas
			public int drawable = 0;
			public int newDrawable = 0;

			private int mCanvasHeight = 1;    // Current height of the surface/canvas.
	        private int mCanvasWidth = 1;    //Current width of the surface/canvas.

	        private boolean soundEnabled = true;

	        
	     // the batter animation
			Batter myBatter = new Batter();

			Pitcher myPitcher = new Pitcher();
			Ball myBaseball = new Ball();
            Fielder my1B   = new Fielder();
            Fielder my2B   = new Fielder();
            Fielder mySS   = new Fielder();
            Fielder my3B   = new Fielder();
            Fielder myLF   = new Fielder();
            Fielder myCF   = new Fielder();
            Fielder myRF   = new Fielder();

            private Scoreboard mScoreboard;
            private StrikeZone mStrikeZone;
            private Infield mInfield;

	        /** Message handler used by thread to interact with TextView */
	        private Handler mHandler;


	        private int mBatterHeight;
	        private int mBatterWidth;
	        private int mPitcherHeight;
	        private int mPitcherWidth;

	        private Paint mLinePaint;
			private long mLastTime;        // elapsed time between frames

	        private int mMode;             // game state
            private int mGameMode;             // game state


            private int pitchMode;
	        /** Indicate whether the surface has been created & is ready to draw */
	        private boolean mRun = false;

	        private SurfaceHolder mSurfaceHolder;    // surface manager object handle

	        private double mDX;        // main object center X
	        private double mDY;        // main object center Y

            private int yBall = 150;              // 150
            private int xBall = 750;        // 150
            private int dxBall = -24;
            private int dyBall = -16;
            private int xBounding = XBOUNDING;
            
            private int RELEASE_FRAME = 49;

 

            /* game statuses */
            int pitchcount = 1;
            private int inning = 1;
            
            private int pitchType = 1;
            private int pitchSpeed = 90;
            private int pitchLocation = 3;
            
            private boolean strikeZoneOn = true;



            public BattingThread(SurfaceHolder surfaceHolder, Context context, Handler handler) {
	            // get handles to some important objects
	            mSurfaceHolder = surfaceHolder;
	            mHandler = handler;
	            mContext = context;

	            Resources res = context.getResources();	                          // cache handles to our key sprites & other drawables

//	            mBackgroundImage = BitmapFactory.decodeResource(res,R.drawable.polo2);
				if (mBackgroundImage == null)
	                mBackgroundImage = BitmapFactory.decodeResource(res,R.drawable.tiger);

	            // Set scaled images
	            mPitcherWidth = context.getResources().getDrawable(R.drawable.p1).getIntrinsicWidth() /2;
	            mPitcherHeight = context.getResources().getDrawable(R.drawable.p1).getIntrinsicHeight() /2;
	            mBatterWidth = (int)(context.getResources().getDrawable(R.drawable.hit2).getIntrinsicWidth() * 1.5);
	            mBatterHeight = (int)(context.getResources().getDrawable(R.drawable.hit2).getIntrinsicHeight() * 1.5);

                myBatter.setWidth(mBatterWidth);
                myBatter.setHeight(mBatterHeight);
                myBatter.setX(XBATTER);
                myBatter.setY(YBATTER);

                myPitcher.setWidth(mPitcherWidth);
                myPitcher.setHeight(mPitcherHeight);
                myPitcher.setX(XPITCHER);
                myPitcher.setY(YPITCHER);



                my1B.setWidth(mPitcherWidth / 2);
                my1B.setHeight(mPitcherHeight / 2);
                my1B.setX(1200);
                my1B.setY(305);
                my2B.setWidth(mPitcherWidth / 2);
                my2B.setHeight(mPitcherHeight / 2);
                my2B.setX(840);
                my2B.setY(305);
                mySS.setWidth(mPitcherWidth / 2);
                mySS.setHeight(mPitcherHeight / 2);
                mySS.setX(440);
                mySS.setY(305);
                my3B.setWidth(mPitcherWidth / 2);
                my3B.setHeight(mPitcherHeight / 2);
                my3B.setX(100);
                my3B.setY(305);
                myLF.setWidth(mPitcherWidth / 4);
                myLF.setHeight(mPitcherHeight / 4);
                myLF.setX(275);
                myLF.setY(340);
                myCF.setWidth(mPitcherWidth / 4);
                myCF.setHeight(mPitcherHeight / 4);
                myCF.setX(600);
                myCF.setY(340);
                myRF.setWidth(mPitcherWidth / 4);
                myRF.setHeight(mPitcherHeight / 4);
                myRF.setX(1100);
                myRF.setY(335);
	            mScoreboard = new Scoreboard();
	            mScoreboard.init();
	            
	            mInfield = new Infield();
	            mInfield.init();
                mStrikeZone = new StrikeZone();
                mStrikeZone.init();

		        crosshair1 = new Crosshair(context,R.drawable.crosshair);

	            // initial show-up (not yet playing)
	            mDX = 0;
	            mDY = 0;
	        }

	        /**  * Starts the game, setting parameters for the current difficulty.  */
	        public void doStart() {
	            synchronized (mSurfaceHolder) {
	                mDY = Math.random();
	                mDX = Math.random();

	                mLastTime = System.currentTimeMillis() + 100;
	                setState(STATE_RUNNING);
	            }
	        }

	        
	        public void setSound(String string) {
	            if (string.equals("off"))  {
                   soundEnabled = false;
	            } else if (string.equals("on"))  {
	               soundEnabled = true;
	            } 
	        }	        
	        
	        public void setNewBackground(String string) {
	            Resources res = mContext.getResources();
				mBackgroundImage.eraseColor(Color.TRANSPARENT);
                newDrawable = R.drawable.polo2;
	            if (string.equals("polo"))  {
		            newDrawable = R.drawable.polo2;
	            } else if (string.equals("ys"))  {
	 	            newDrawable = R.drawable.ys;
	 	        } else if (string.equals("tiger"))  {
	 	 	        newDrawable = R.drawable.tiger;
	 	 	    } else if (string.equals("wrigley"))  {
	 	 	 	    newDrawable = R.drawable.wrigley;
	 	 	 	} else if (string.equals("fenway"))  {
	 	 	 	 	newDrawable = R.drawable.fenway;
	 	 	 	} else if (string.equals("sfpark"))  {
	 	 	 	 	newDrawable = R.drawable.sfpark;
				} else if (string.equals("angels"))  {
					newDrawable = R.drawable.angels;
	            } else if (string.equals("blank")) {
	 	           newDrawable = R.drawable.wrigley2;
	            }

				mBackgroundImage = BitmapFactory.decodeResource(res,newDrawable);

	            mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, mCanvasWidth, mCanvasHeight, true);  	 // don't forget to resize the background image
				invalidate();
	        }

	        /** * Pauses the physics update & animation. */
	        public void pause() {
	            synchronized (mSurfaceHolder) {
	                if (mMode == STATE_RUNNING) setState(STATE_PAUSED);
	            }
	        }

	        /**
	         * @param savedState Bundle containing the game state
	         */
	        public synchronized void restoreState(Bundle savedState) {
	            synchronized (mSurfaceHolder) {
	                setState(STATE_PAUSED);

	                mDX = savedState.getDouble(KEY_DX);
	                mDY = savedState.getDouble(KEY_DY);

	            }
	        }

	        @Override
	        public void run() {
	            while (mRun) {
	                Canvas c = null;
	                try {
	                    c = mSurfaceHolder.lockCanvas(null);
	                    synchronized (mSurfaceHolder) {
	                        if (mMode == STATE_RUNNING) updatePhysics();
	                        if (mMode != STATE_PAUSED) doDraw(c);
	                    }
	                } finally {    	// do this in a finally so that if an exception is thrown the Surface is not in an inconsistent state
	                    if (c != null) {
	                        mSurfaceHolder.unlockCanvasAndPost(c);
	                    }
	                }
	            }
	        }

	        /** * Dump game state to the provided Bundle. */
	        public Bundle saveState(Bundle map) {
	            synchronized (mSurfaceHolder) {
	                if (map != null) {
	                    map.putDouble(KEY_DX, Double.valueOf(mDX));
	                    map.putDouble(KEY_DY, Double.valueOf(mDY));
	                }
	            }
	            return map;
	        }


	        public void setRunning(boolean b) {
	            mRun = b;
	        }

	        /** * Sets the game state mode. */
	        public void setState(int mode) {
	            synchronized (mSurfaceHolder) {
	                setState(mode, null);
	            }
	        }

	        public void setState(int mode, CharSequence message) {
	            synchronized (mSurfaceHolder) {
	                mGameMode = mode;
	            }
	        }
	        
	        public int getCurrentState()
	        {
	        	return mMode;
	        }

	        /* Callback invoked when the surface dimensions change. */
	        public void setSurfaceSize(int width, int height) {
	            synchronized (mSurfaceHolder) {
	                mCanvasWidth = width;
	                mCanvasHeight = height;
	                mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, width, height, true);  	 // don't forget to resize the background image

	            }
	        }

	        public void unpause() {
	            synchronized (mSurfaceHolder) {
	                mLastTime = System.currentTimeMillis() + 100;
	            }
	            setState(STATE_RUNNING);
	        }

	        /** * Handles a key-down event.  */
	        boolean doKeyDown(int keyCode, KeyEvent msg) {
	            synchronized (mSurfaceHolder) {
	                boolean okStart = false;
	                if (keyCode == KeyEvent.KEYCODE_DPAD_UP)   okStart = true;
	                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) okStart = true;
	                if (keyCode == KeyEvent.KEYCODE_S)         okStart = true;

	                boolean center = (keyCode == KeyEvent.KEYCODE_DPAD_UP);

	                if (okStart && (mMode == STATE_READY || mMode == STATE_WIN)) {
	                    doStart();         	                                 // ready-to-start -> start
	                    return true;
	                } else if (mMode == STATE_PAUSED && okStart) {	         // paused -> running
	                    unpause();
	                    return true;
	                } else if (mMode == STATE_RUNNING) {
	                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_SPACE) {	         // center/space -> fire
	                        return true;
	                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_Q) {    	 // left/q -> left
	                        return true;
	                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_W) {	     // right/w -> right
	                        return true;
	                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {	                                         // up -> pause
	                        pause();
	                        return true;
	                    }
	                }

	                return false;
	            }
	        }

	        /**  * Handles a key-up event. */
	        boolean doKeyUp(int keyCode, KeyEvent msg) {
	            boolean handled = false;

	            synchronized (mSurfaceHolder) {
	                if (mMode == STATE_RUNNING) {
	                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER
	                            || keyCode == KeyEvent.KEYCODE_SPACE) {
	                        handled = true;
	                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT
	                            || keyCode == KeyEvent.KEYCODE_Q
	                            || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
	                            || keyCode == KeyEvent.KEYCODE_W) {
	                        handled = true;
	                    }
	                }
	            }

	            return handled;
	        }

	        /*** Draws objects and background to the provided Canvas. ***/
	        private void doDraw(Canvas canvas) {
				Resources res = mContext.getResources();
				if (newDrawable != drawable) {
					drawable = newDrawable;
					mBackgroundImage = BitmapFactory.decodeResource(res,drawable);
					mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, mCanvasWidth, mCanvasHeight, true);
				}

		        canvas.drawBitmap(mBackgroundImage, 0, 0, null);

//                mScoreboard.draw(canvas);
                mInfield.draw(canvas);

                // Draw the strike zone
                if (strikeZoneOn) {
                    mStrikeZone.draw(canvas);
                }


                switch (mGameMode) {
                    case STATE_READY:
                        pitchMode = 0;
                        break;

                    case STATE_START_PITCHER:
                        myPitcher.begin();
                        mGameMode = STATE_PITCHING;
                    case STATE_PITCHING:
                        if (myPitcher.getFrame() == RELEASE_FRAME) {
                            pitchMode = IS_PITCH;
                            xBall = PITCH_X;
                            yBall = PITCH_Y;
                            xBounding = XBOUNDING;
                            mGameMode = STATE_PITCHTHROWN;
                        }

                        myPitcher.next();
                        break;
                    case STATE_PITCHTHROWN:
                        myPitcher.next();
                        myBaseball.next();
                        break;
                    case STATE_START_BATTER:
                        myBatter.begin();
                        mGameMode = STATE_BATTING;

                    case STATE_BATTING:
                        myBatter.next();

                        // play sound
                        if (myBatter.getFrame() == 7) {      // contact point
                            pitchMode = 0;        // ball isnt pitched anymore
                            playSound(R.raw.homerun);

                            dxBall = (int) (Math.random() * -40) + 15;
                            dyBall = (int) (Math.random() * -50) + 5;
                            mInfield.setBalls(dxBall);
                            mInfield.setStrikes(dyBall);
                            mInfield.advanceRunners(0);
                            pitchMode = IS_HIT;
                        }


                        break;
                    case STATE_PITCHCAUGHT:
                        mGameMode = STATE_READY;
                        break;
                    default:


                }
//	            canvas.restore();

                Context c = getContext();

                my1B.getDrawable(c).draw(canvas);
                my2B.getDrawable(c).draw(canvas);
                mySS.getDrawable(c).draw(canvas);
                my3B.getDrawable(c).draw(canvas);
                myLF.getDrawable(c).draw(canvas);
                myCF.getDrawable(c).draw(canvas);
                myRF.getDrawable(c).draw(canvas);
                myBatter.getDrawable(c).draw(canvas);
                myPitcher.getDrawable(c).draw(canvas);

                if (pitchMode == IS_PITCH) {

                    myBaseball.next();
                    myBaseball.getDrawable(c).draw(canvas);

                    myBaseball.setY(myBaseball.getY() + 8);

                    if (myBaseball.getY() > 350) {
                        pitchMode = 0;
                        mGameMode = STATE_PITCHCAUGHT;
                    }
                }
                if (pitchMode == IS_HIT) {
                    myBaseball.next();
                    myBaseball.getDrawable(c).draw(canvas);
                    xBall = xBall + dxBall;
                    yBall = yBall + dyBall;
                }

                 }
	        }

	        /**
	         *  Does not invalidate(). Called at the start of draw().
	         */
	        private void updatePhysics() {
	            long now = System.currentTimeMillis();
	        }

	    
	        private void detectCollision()
	        {
	    
	        }
  
	         private void playSound(int resid)
	         {
                /*
	      	     if (soundEnabled == true)   {
                     MediaPlayer mp = MediaPlayer.create(getContext(), resid);   
                     mp.start();
                     mp.setOnCompletionListener(new OnCompletionListener() {

                     @Override
                     public void onCompletion(MediaPlayer mp) {
                       // TODO Auto-generated method stub
                         mp.release();
                     }

                     });
	      	     }*/
	         }
	    
	    
	    /** Handle to the application context, used to e.g. fetch Drawables. */
	    private Context mContext;
	    private TextView mStatusText;
	    private BattingThread thread;	    /** The thread that actually draws the animation */


	    public BattingView(Context context, AttributeSet attrs) {
	        super(context, attrs);

	        // register our interest in hearing about changes to our surface
	        SurfaceHolder holder = getHolder();
	        holder.addCallback(this);

	        // create thread only; it's started in surfaceCreated()
	        thread = new BattingThread(holder, context, new Handler() {
	            @Override
	            public void handleMessage(Message m) {
	                mStatusText.setText(m.getData().getString("text"));
	            }
	        });

	        setFocusable(true); // make sure we get key events
	    }

	    /**
	     * @return the animation thread
	     */
	    public BattingThread getThread() {
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



	    @Override
	    public void onWindowFocusChanged(boolean hasWindowFocus) {
	        if (!hasWindowFocus) thread.pause();
	    }

	    public void setTextView(TextView textView) {
	        mStatusText = textView;
	    }

	    /* SURFACE CALLBACKS. */
	    public void surfaceChanged(SurfaceHolder holder, int format, int width,  int height) {
	        thread.setSurfaceSize(width, height);
	    }

	    public void surfaceCreated(SurfaceHolder holder) {
	        thread.setRunning(true);
	        thread.start();
	    }

	    public void surfaceDestroyed(SurfaceHolder holder) {
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

	    /* TOUCH EVENTS. */
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
	 
	        invalidate();
	        return true;
	    }
	}
