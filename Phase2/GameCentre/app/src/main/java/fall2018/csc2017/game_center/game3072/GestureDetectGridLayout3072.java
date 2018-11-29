package fall2018.csc2017.game_center.game3072;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GestureDetectGridLayout3072 extends GridLayout {

    private static final int OFFSET_CONSTANT = 5;

    private MovementController3072 mController;

    public GestureDetectGridLayout3072(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBoard();
    }

    public GestureDetectGridLayout3072(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBoard();
    }

    public GestureDetectGridLayout3072(Context context) {
        super(context);
        initBoard();
    }

    private void initBoard() {
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

    void setBoard(Board3072 board) {
        mController.setBoard(board);
    }

}