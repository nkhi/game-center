package fall2018.csc2017.game_center.game3072;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Custom grid layout for the 3072 game
 */
public class GestureDetectGridLayout3072 extends GridLayout {

    /**
     * Swipe offset constant
     */
    private static final int OFFSET_CONSTANT = 5;

    /**
     * Movement controller for the 3072 game
     */
    private MovementController3072 mController;

    /**
     * Initializes a GestureDetectGridLayout3072
     *
     * @param context the parent activity
     * @param attrs the set of attributes for the GridView
     * @param defStyleAttr style attribute reference
     */
    public GestureDetectGridLayout3072(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGridLayout();
    }

    /**
     * Initializes a GestureDetectGridLayout3072
     *
     * @param context the parent activity
     * @param attrs the set of attributes for the GridView
     */
    public GestureDetectGridLayout3072(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGridLayout();
    }

    /**
     * Initializes a GestureDetectGridLayout3072
     *
     * @param context the parent activity
     */
    public GestureDetectGridLayout3072(Context context) {
        super(context);
        initGridLayout();
    }

    /**
     * Initializes the grid layout with parameters and a listener
     */
    private void initGridLayout() {
        mController = new MovementController3072();
        setOnTouchListener(new View.OnTouchListener() {

            float startX, startY, offsetX, offsetY;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -OFFSET_CONSTANT) {
                                mController.swipeLeft();
                            } else if (offsetX > OFFSET_CONSTANT) {
                                mController.swipeRight();
                            }
                        } else {
                            if (offsetY < -OFFSET_CONSTANT) {
                                mController.swipeUp();
                            } else if (offsetY > OFFSET_CONSTANT) {
                                mController.swipeDown();
                            }
                        }

                        break;
                }
                return true;
            }
        });
    }

    /**
     * Sets the board for the movement controller
     *
     * @param board board to be set
     */
    void setBoard(Board3072 board) {
        mController.setBoard(board);
    }

}