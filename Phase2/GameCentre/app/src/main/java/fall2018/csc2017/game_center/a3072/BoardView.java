package fall2018.csc2017.game_center.a3072;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.game_center.Score;
import fall2018.csc2017.game_center.R;


public class BoardView extends GridLayout {

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initBoard();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initBoard();
    }

    public BoardView(Context context) {
        super(context);

        initBoard();
    }

    private void initBoard() {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        addCards();
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
                            if (offsetX < -5 ) {
                                if(Move.swipeLeft(getBoard())) {
                                    addRandomNum();
                                    checkComplete();
                                }
                            }   else if (offsetX > 5) {
                                if(Move.swipeRight(getBoard())) {
                                    addRandomNum();
                                    checkComplete();
                                }
                            }
                        } else {
                            if (offsetY < -5) {
                                if(Move.swipeUp(getBoard())) {
                                    addRandomNum();
                                    checkComplete();
                                }
                            }   else if (offsetY > 5) {
                                if(Move.swipeDown(getBoard())) {
                                    addRandomNum();
                                    checkComplete();
                                }
                            }
                        }

                        break;
                }
                return true;
            }
        });
    }

    public Card[][] getBoard() {
        return board;
    }

    public void addCards(){

        Card c;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, c.cardWidth, c.cardWidth);
                board[x][y] = c;
            }
        }
        startGame();
    }

    protected void startGame() {

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                board[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();
        addRandomNum();
    }

    public void addRandomNum(){

        emptyPoints.clear();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (board[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        board[p.x][p.y].setNum(Math.random()>=0.1?3:6);
    }

    private void checkComplete(){

        boolean complete = true;

        ALL:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (board[x][y].getNum()==0||
                        (x>0&&board[x][y].equals(board[x-1][y]))||
                        (x<4-1&&board[x][y].equals(board[x+1][y]))||
                        (y>0&&board[x][y].equals(board[x][y-1]))||
                        (y<4-1&&board[x][y].equals(board[x][y+1]))) {

                    complete = false;
                    break ALL;
                }
            }
        }

        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("GameOver").
                    setMessage("Good Luck next game!").
                    setPositiveButton("Show LeaderBoard", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Do something here to show the Scoreboard.
                        }
                    }).show();
        }

    }
    private Card[][] board = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();
}