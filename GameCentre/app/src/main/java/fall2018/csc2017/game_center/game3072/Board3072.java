package fall2018.csc2017.game_center.game3072;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fall2018.csc2017.game_center.Scoreable;

/**
 * The board for the 3072 game.
 */
public class Board3072 extends Observable implements Scoreable {

    /**
     * Number of rows and columns in the game
     */
    public static final int NUM_ROW_COL = 4;

    /**
     * Probability that a new random number will be a large number
     */
    private static final float PROB_LARGE_NUM = 0.1f;

    /**
     * Number of initial numbers generated
     */
    private static final int NUM_INIT_CARDS = 3;

    /**
     * The array of "cards" that make up the board
     */
    private Card3072[][] board = new Card3072[NUM_ROW_COL][NUM_ROW_COL];

    /**
     * A list of all the empty card locations
     */
    private List<Point> emptyPoints = new ArrayList<>();

    /**
     * Return the board as a 2D array
     *
     * @return the board as a 2D array of cards
     */
    public Card3072[][] getBoard() {
        return board;
    }

    /**
     * Adds a random number (either 3 or 6) to a random unoccupied location on the board
     */
    void addRandomNum() {

        emptyPoints.clear();

        for (int y = 0; y < NUM_ROW_COL; y++) {
            for (int x = 0; x < NUM_ROW_COL; x++) {
                if (board[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }

        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        board[p.x][p.y].setNum(Math.random() >= PROB_LARGE_NUM ? Card3072.BASE_NUM :
                Card3072.BASE_NUM * 2);
    }

    /**
     * Initializes the board
     */
    void startGame() {
        for (int y = 0; y < NUM_ROW_COL; y++) {
            for (int x = 0; x < NUM_ROW_COL; x++) {
                board[x][y].setNum(0);
            }
        }

        for (int i = 0; i < NUM_INIT_CARDS; i++) {
            addRandomNum();
        }
    }

    /**
     * Processes an upwards swipe gesture
     *
     * @return true iff the swipe moved a card around
     */
    boolean gestureUp() {
        boolean move = false;

        for (int x = 0; x < NUM_ROW_COL; x++) {
            for (int y = 0; y < NUM_ROW_COL; y++) {

                for (int y1 = y + 1; y1 < NUM_ROW_COL; y1++) {
                    if (board[x][y1].getNum() > 0) {

                        if (board[x][y].getNum() <= 0 || board[x][y].equals(board[x][y1])) {
                            if (board[x][y].getNum() <= 0) {
                                board[x][y].setNum(board[x][y1].getNum());
                                board[x][y1].setNum(0);
                                y--;
                            } else if (board[x][y].equals(board[x][y1])) {
                                board[x][y].setNum(board[x][y].getNum() * 2);
                                board[x][y1].setNum(0);
                            }
                            move = true;

                        }
                        break;

                    }
                }
            }
        }
        setChanged();
        notifyObservers();
        return move;
    }

    /**
     * Processes an down swipe gesture
     *
     * @return true iff the swipe moved a card around
     */
    boolean gestureDown() {
        boolean move = false;

        for (int x = 0; x < NUM_ROW_COL; x++) {
            for (int y = NUM_ROW_COL - 1; y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (board[x][y1].getNum() > 0) {

                        if (board[x][y].getNum() <= 0 || board[x][y].equals(board[x][y1])) {
                            if (board[x][y].getNum() <= 0) {
                                board[x][y].setNum(board[x][y1].getNum());
                                board[x][y1].setNum(0);
                                y++;
                            } else if (board[x][y].equals(board[x][y1])) {
                                board[x][y].setNum(board[x][y].getNum() * 2);
                                board[x][y1].setNum(0);
                            }
                            move = true;

                        }
                        break;

                    }
                }
            }
        }
        setChanged();
        notifyObservers();
        return move;
    }

    /**
     * Processes an left swipe gesture
     *
     * @return true iff the swipe moved a card around
     */
    boolean gestureLeft() {
        boolean move = false;

        for (int y = 0; y < NUM_ROW_COL; y++) {
            for (int x = 0; x < NUM_ROW_COL; x++) {

                for (int x1 = x + 1; x1 < NUM_ROW_COL; x1++) {
                    if (board[x1][y].getNum() > 0) {

                        if (board[x][y].getNum() <= 0 || board[x][y].equals(board[x1][y])) {
                            if (board[x][y].getNum() <= 0) {
                                board[x][y].setNum(board[x1][y].getNum());
                                board[x1][y].setNum(0);
                                x--;
                            } else if (board[x][y].equals(board[x1][y])) {
                                board[x][y].setNum(board[x][y].getNum() * 2);
                                board[x1][y].setNum(0);
                            }
                            move = true;

                        }
                        break;

                    }
                }
            }
        }
        setChanged();
        notifyObservers();
        return move;
    }

    /**
     * Processes an right swipe gesture
     *
     * @return true iff the swipe moved a card around
     */
    boolean gestureRight() {
        boolean move = false;

        for (int y = 0; y < NUM_ROW_COL; y++) {
            for (int x = NUM_ROW_COL - 1; x >= 0; x--) {

                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (board[x1][y].getNum() > 0) {

                        if (board[x][y].getNum() <= 0 || board[x][y].equals(board[x1][y])) {

                            if (board[x][y].getNum() <= 0) {
                                board[x][y].setNum(board[x1][y].getNum());
                                board[x1][y].setNum(0);
                                x++;
                            } else if (board[x][y].equals(board[x1][y])) {
                                board[x][y].setNum(board[x][y].getNum() * 2);
                                board[x1][y].setNum(0);
                            }
                            move = true;
                        }
                        break;

                    }
                }
            }
        }
        setChanged();
        notifyObservers();
        return move;
    }

    /**
     * Return whether the game is finished (i.e. whether there remains a valid move)
     *
     * @return true iff there are no more valid moves
     */
    boolean isFinished() {

        for (int y = 0; y < NUM_ROW_COL; y++) {
            for (int x = 0; x < NUM_ROW_COL; x++) {
                if (board[x][y].getNum() == 0 ||
                        (x > 0 && board[x][y].equals(board[x - 1][y])) ||
                        (x < NUM_ROW_COL - 1 && board[x][y].equals(board[x + 1][y])) ||
                        (y > 0 && board[x][y].equals(board[x][y - 1])) ||
                        (y < NUM_ROW_COL - 1 && board[x][y].equals(board[x][y + 1]))) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Return the score as a sum of all card values on the board
     *
     * @return the score as a sum of all card values on the board
     */
    @Override
    public int getScore() {
        int score = 0;
        for (int x = 0; x < NUM_ROW_COL; x++) {
            for (int y = 0; y < NUM_ROW_COL; y++) {
                score += board[x][y].getNum();
            }
        }
        return score;
    }
}