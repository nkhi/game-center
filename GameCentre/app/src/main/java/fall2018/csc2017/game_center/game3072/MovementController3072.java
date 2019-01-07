package fall2018.csc2017.game_center.game3072;

/**
 * Movement controller for the 3072 game
 */
class MovementController3072 {

    /**
     * Board to control
     */
    private Board3072 board;

    /**
     * Sets the board to control
     *
     * @param board board to control
     */
    void setBoard(Board3072 board) {
        this.board = board;
    }

    /**
     * Processes a right swipe
     */
    void swipeRight() {
        if (board.gestureRight()) {
            board.addRandomNum();
        }
    }

    /**
     * Processes a left swipe
     */
    void swipeLeft() {
        if (board.gestureLeft()) {
            board.addRandomNum();
        }
    }

    /**
     * Processes an up swipe
     */
    void swipeUp() {
        if (board.gestureUp()) {
            board.addRandomNum();
        }
    }

    /**
     * Processes a down swipe
     */
    void swipeDown() {
        if (board.gestureDown()) {
            board.addRandomNum();
        }
    }

}
