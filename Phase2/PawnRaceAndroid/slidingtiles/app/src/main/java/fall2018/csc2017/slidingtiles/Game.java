package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

class Game implements Serializable {

    private static final int MAX_MOVES = 1000;

    /**
     * The board being managed.
     */
    private Board board;

    private Move[] moves;
    private int index;
    private Color currentPlayer;

    Game(int whiteGap, int blackGap) {
        Square[][] board = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j);
                board[i][j].setOccupier(Color.NONE);
                if (j == 1 && i != whiteGap) {
                    board[i][j].setOccupier(Color.WHITE);
                } else if (j == 6 && i != blackGap) {
                    board[i][j].setOccupier(Color.BLACK);
                }
            }
        }
        this.board = new Board(board);
        moves = new Move[MAX_MOVES];
        index = 0;
        currentPlayer = Color.WHITE;
    }

    Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    boolean isFinished() {
        return (lastLine(Color.WHITE) || lastLine(Color.BLACK) || noColor(Color.BLACK) ||
                noColor(Color.WHITE));
    }

    private boolean noColor(Color c) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.getSquare(i, j).occupiedBy() == c) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean lastLine(Color c) {
        int a = c == Color.WHITE ? 7 : 0;
        for (int i = 0; i < 8; i++) {
            if (board.getSquare(i, a).occupiedBy() == c) {
                return true;
            }
        }
        return false;
    }

    Move getLastMove() {
        if (index == 0) {
            return null;
        } else {
            return moves[index];
        }
    }

    void applyMove(Move move) {
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else {
            currentPlayer = Color.WHITE;
        }
        index++;
        moves[index] = move;
        board.applyMove(move);
    }

    void unapplyMove() {
        Move move = getLastMove();
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else {
            currentPlayer = Color.WHITE;
        }
        moves[index] = null;
        index--;
        board.unapplyMove(move);
    }

    Color getGameResult() {
        if (noColor(Color.WHITE) || lastLine(Color.WHITE)) {
            return Color.WHITE;
        }
        else if (noColor(Color.BLACK) || lastLine(Color.BLACK)) {
            return Color.BLACK;
        }
        else {
            return Color.NONE;
        }
    }

//    /**
//     * Return whether any of the four surrounding tiles is the blank tile.
//     *
//     * @param position the tile to check
//     * @return whether the tile at position is surrounded by a blank tile
//     */
//    boolean isValidTap(int position) {
//
//        int row = position / Board.NUM_COLS;
//        int col = position % Board.NUM_COLS;
//        // Are any of the 4 the blank tile?
//        Square above = row == 0 ? null : board.getTile(row - 1, col);
//        Square below = row == Board.NUM_ROWS - 1 ? null : board.getTile(row + 1, col);
//        Square left = col == 0 ? null : board.getTile(row, col - 1);
//        Square right = col == Board.NUM_COLS - 1 ? null : board.getTile(row, col + 1);
//        return (below != null && below.getId() == blankId)
//                || (above != null && above.getId() == blankId)
//                || (left != null && left.getId() == blankId)
//                || (right != null && right.getId() == blankId);
//    }
//
//    /**
//     * Process a touch at position in the board, swapping tiles as appropriate.
//     *
//     * @param position the position
//     */
//    void touchMove(int position) {
//        if (isValidTap(position)) {
//            TilePosition tile = new TilePosition(position);
//            board.swapTiles(tile.getRow(), tile.getCol(), getSwapRow(tile), getSwapCol(tile));
//        }
//    }

}
