package fall2018.csc2017.game_center.game3072;

class MovementController3072 {

    private Board3072 board;

    void setBoard(Board3072 board) {
        this.board = board;
    }

    void swipeRight() {
        if (board.gestureRight()) {
            board.addRandomNum();
        }
    }

    void swipeLeft() {
        if (board.gestureLeft()) {
            board.addRandomNum();
        }
    }

    void swipeUp() {
        if (board.gestureUp()) {
            board.addRandomNum();
        }
    }

    void swipeDown() {
        if (board.gestureDown()) {
            board.addRandomNum();
        }
    }

}
