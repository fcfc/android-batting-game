package com.example.ice.battingpractice2;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.ice.battingpractice2.BattingView.BattingThread;

/**
 * <ul>
 * <li>animating by calling invalidate() from draw()
 * <li>loading and drawing resources
 * <li>handling onPause() in an animation
 * </ul>
 */
public class BattingPractice extends Activity  implements View.OnClickListener,  OnGestureListener, OnDoubleTapListener {
    private static final int MENU_YANKEE = 1;
    private static final int MENU_HARD = 2;
    private static final int MENU_MEDIUM = 3;
    private static final int MENU_SOUNDOFF = 4;
    private static final int MENU_SOUNDON = 5;
    private static final int MENU_START = 6;
    private static final int MENU_STOP = 7;
    private static final int MENU_FENWAY = 8;
    private static final int MENU_WRIGLEY = 9;
    private static final int MENU_SFPARK = 10;
    private static final int MENU_ANGELS = 11;
    private static final int MENU_BLANK = 12;

    /** A handle to the thread that's actually running the animation. */
    private BattingThread mBattingThread;

    /** A handle to the View in which the game is running. */
    private BattingView mBattingView;    /** A handle to the View in which the game is running. */

    // the kick button
       private Button   mButton01;
       private Button   mButton02;
       private Button   mButtonUp;
       private Button   mButtonDown;
       private Button   mButtonLeft;
       private Button   mButtonRight;
       private TextView mTimerView;
       private TextView mDistanceView;
       AnimationDrawable batAnimation;
 

    /**
     * Invoked during init to give the Activity a chance to set up its Menu.
     * 
     * @param menu the Menu to which entries may be added
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_START, 0, R.string.menu_start);
        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_SOUNDOFF, 0, R.string.menu_soundoff);
        menu.add(0, MENU_YANKEE, 0, R.string.yankee);
        menu.add(0, MENU_MEDIUM, 0, R.string.polo);
        menu.add(0, MENU_HARD, 0, R.string.tiger);
        menu.add(0, MENU_FENWAY, 0, R.string.fenway);
        menu.add(0, MENU_WRIGLEY, 0, R.string.wrigley);
        menu.add(0, MENU_SFPARK, 0, R.string.sfpark);
        menu.add(0, MENU_ANGELS, 0, R.string.angels);

        menu.add(0, MENU_BLANK, 0, R.string.blank);


        return true;
    }

    /**
     * Invoked when the user selects an item from the Menu.
     * 
     * @param item the Menu entry which was selected
     * @return true if the Menu item was ok, false otherwise
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_START:
                mBattingThread.doStart();
                return true;
            case MENU_STOP:
                mBattingThread.setNewBackground("ys");
                return true;
            case MENU_SOUNDOFF:
                mBattingThread.setSound("off");
                return true;
            case MENU_SOUNDON:
                mBattingThread.setSound("on");
                return true;
            case MENU_YANKEE:
                mBattingThread.setNewBackground("ys");
                return true;
            case MENU_MEDIUM:
                mBattingThread.setNewBackground("polo");
                return true;
            case MENU_HARD:
                mBattingThread.setNewBackground("tiger");
                return true;
            case MENU_WRIGLEY:
                mBattingThread.setNewBackground("wrigley");
                return true;       
            case MENU_FENWAY:
                mBattingThread.setNewBackground("fenway");
                return true;
            case  MENU_SFPARK:
                mBattingThread.setNewBackground("sfpark");
                return true;
            case MENU_ANGELS:
                mBattingThread.setNewBackground("angels");
                return true;
            case MENU_BLANK:
                    mBattingThread.setNewBackground("blank");
                    return true;
        }

        return false;
    }

    /** * Invoked when the Activity is created.* 
     * @param savedInstanceState a Bundle containing state saved from a previous execution, or null if this is a new execution  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);               // tell system to use the layout defined in our XML file

        mBattingView = (BattingView) findViewById(R.id.batting);        // get handles to the FieldgoalView from XML,
        mBattingThread = mBattingView.getThread();

        mBattingView.setTextView(mDistanceView);
        
        mButton01 = (Button)findViewById(R.id.Button01);
        mButton01.setOnClickListener(this);

        mButton02 = (Button)findViewById(R.id.Button02);
        mButton02.setOnClickListener(this);
       
        mButtonLeft = (Button)findViewById(R.id.left);
        mButtonLeft.setOnClickListener(this);
        mButtonRight = (Button)findViewById(R.id.right);
        mButtonRight.setOnClickListener(this);
        
        // get handles to the BattingView from XML, and its Thread
//        mBattingView.setTextView((TextView) findViewById(R.id.in1v));

        if (savedInstanceState == null) {
            mBattingThread.setState(BattingThread.STATE_READY);               // new game
        } else {
            mBattingThread.restoreState(savedInstanceState);                          // we are being restored: resume a previous game
        }

//        mBattingView.setBackgroundResource(R.drawable.batter);
//        rocketAnimation = (AnimationDrawable) mBattingView.getBackground();

    }

    /**
     * Invoked when the Activity loses user focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        mBattingView.getThread().pause(); // pause game when Activity pauses
    }

    /**
     * Notification that something is about to happen, to give the Activity a
     * chance to save state.
     * 
     * @param outState a Bundle into which this Activity should save its state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
        super.onSaveInstanceState(outState);
        mBattingThread.saveState(outState);
    }
    
    /* Button Handler */
    public void onClick(View v) {
        // this is the first screen
    	mButton01.setVisibility(View.INVISIBLE);
        mButton02.setVisibility(View.INVISIBLE);
    	
   if (mButton01.equals(v)) {
        mButton01.setVisibility(View.VISIBLE);
       mBattingThread.setState(BattingThread.STATE_READY);
    }
     else if (mButton02.equals(v)) {
           mButton01.setVisibility(View.VISIBLE);
           mBattingThread.setState(BattingThread.STATE_PAUSED);
     }    
   
     if (mButtonLeft.equals(v)) {
         mBattingThread.setState(BattingThread.STATE_START_BATTER);

     }    
     else if (mButtonRight.equals(v)) {
        mBattingThread.setState(BattingThread.STATE_START_PITCHER);
    }
}
 /*   
    public boolean onTouchEvent(MotionEvent event) {
    	  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    	         mBattingThread.setState(BattingThread.STATE_BATTING);
    	         double x = event.getX();
    	         double y = event.getY();
  //  	         String aString = Double.toString(x);
  //  	         String bString = Double.toString(y);
  //  	         mDistanceView.setText(aString+" "+bString);
  //  	         mDistanceView.setVisibility(View.VISIBLE);


    	    return true;
    	  }
    	  return super.onTouchEvent(event);
    	}
*/
	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) 
	{
	//	speech.speak("tap tap");
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) 
	{
	//	speech.speak("tap");
		return false;
	}

	@Override
	public boolean onDown(MotionEvent arg0) 
	{
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) 
	{
        mBattingThread.setNewBackground("wrigley");
        return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) 
	{
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) 
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) 
	{
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) 
	{
		return false;
	}
}

