package fall2018.csc2017.game_center.slidingtiles;

/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Custom GridView modified for gesture detection - taken from A2 source code
 */
public class TileGestureDetectGridView extends GridView {

    /**
     * Minimum swipe distance for swipe to be detected
     */
    public static final int SWIPE_MIN_DISTANCE = 100;

    /**
     * Velocity for swipe to be processed
     */
    public static final int SWIPE_THRESHOLD_VELOCITY = 100;

    /**
     * The gesture detector, used for detecting swipes and taps
     */
    private GestureDetector gDetector;

    /**
     * Movement controller in the MVC model - processes swipes and taps with regards to the tile
     * game
     */
    private TileMovementController mController;

    /**
     * Stores whether or not the list fling is confirmed
     */
    private boolean mFlingConfirmed = false;

    /**
     * X coordinate of the tap
     */
    private float mTouchX;

    /**
     * Y coordinate of the tap
     */
    private float mTouchY;

    /**
     * Initializes a TileGestureDetectGridView
     *
     * @param context the parent activity
     */
    public TileGestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    /**
     * Initializes a TileGestureDetectGridView
     *
     * @param context the parent activity
     * @param attrs the set of attributes for the GridView
     */
    public TileGestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Initializes a TileGestureDetectGridView
     *
     * @param context the parent activity
     * @param attrs the set of attributes for the GridView
     * @param defStyleAttr style attribute reference
     */
    public TileGestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Initializes the grid view parameters and adds a gesture detector to it
     *
     * @param context the parent activity
     */
    private void init(final Context context) {
        mController = new TileMovementController();
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = TileGestureDetectGridView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processTapMovement(context, position);
                return true;
            }

            /**
             * Added gesture detection to implement undo functionality with a left swipe
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE &&
                        Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mController.processSwipe(context);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        gDetector.onTouchEvent(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mFlingConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            mTouchX = ev.getX();
            mTouchY = ev.getY();
        } else {

            if (mFlingConfirmed) {
                return true;
            }

            float dX = (Math.abs(ev.getX() - mTouchX));
            float dY = (Math.abs(ev.getY() - mTouchY));
            if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                mFlingConfirmed = true;
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }


    /**
     * Calls the gesture detection when the user interacts with the grid view
     * Suppresses accessibility warning as accessibility services are not supported at this time
     *
     * @param ev gesture event to process
     * @return whether the gesture or click was processed
     */
    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

    /**
     * Sets the board manager and controller
     *
     * @param boardManager board manager to be stored
     */
    public void setBoardManager(TileBoardManager boardManager) {
        mController.setBoardManager(boardManager);
    }
}
