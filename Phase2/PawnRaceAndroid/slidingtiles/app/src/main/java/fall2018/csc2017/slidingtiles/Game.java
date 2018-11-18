package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

class Game implements Serializable {

    /**
     * White player's starting rank
     */
    static final int WHITE_STARTING_RANK = 1;

    /**
     * Black player's starting rank
     */
    static final int BLACK_STARTING_RANK = 6;

    /**
     * Maximum number of moves
     */
    private static final int MAX_MOVES = 300;

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * An array of all the moves made
     */
    private Move[] moves;

    /**
     * The number of moves made
     */
    private int index;

    /**
     * The color of the current player to move on the board
     */
    private Color currentPlayer;

    /**
     * Initializes a game and board with the white and black gaps
     *
     * @param whiteGap file of the white gap
     * @param blackGap file of the black gap
     */
    Game(int whiteGap, int blackGap) {
        Square[][] board = new Square[Board.NUM_ROW_COL][Board.NUM_ROW_COL];
        for (int i = 0; i < Board.NUM_ROW_COL; i++) {
            for (int j = 0; j < Board.NUM_ROW_COL; j++) {
                board[i][j] = new Square(i, j);
                board[i][j].setOccupier(Color.NONE);
                if (j == WHITE_STARTING_RANK && i != whiteGap) {
                    board[i][j].setOccupier(Color.WHITE);
                } else if (j == BLACK_STARTING_RANK && i != blackGap) {
                    board[i][j].setOccupier(Color.BLACK);
                }
            }
        }
        this.board = new Board(board);
        moves = new Move[MAX_MOVES];
        index = 0;
        currentPlayer = Color.WHITE;
    }

    /**
     * Return the color of the current player
     *
     * @return the color of the current player
     */
    Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Return the current board
     *
     * @return the current board
     */
    Board getBoard() {
        return board;
    }

    /**
     * Return whether a player has won (does not account for stalemates)
     *
     * @return whether a player has won
     */
    boolean isFinished() {
        return (lastLine(Color.WHITE) || lastLine(Color.BLACK) || noColor(Color.BLACK) ||
                noColor(Color.WHITE));
    }

    /**
     * Return whether a player has at least one pawn
     *
     * @param c color of player to check
     * @return if a player has at least one pawn
     */
    private boolean noColor(Color c) {
        for (int i = 0; i < Board.NUM_ROW_COL; i++) {
            for (int j = 0; j < Board.NUM_ROW_COL; j++) {
                if (board.getSquare(i, j).occupiedBy() == c) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return whether a player has a pawn on the last rank
     *
     * @param c color of player to check
     * @return if a player has a pawn on the last rank
     */
    private boolean lastLine(Color c) {
        int a = c == Color.WHITE ? (Board.NUM_ROW_COL - 1) : 0;
        for (int i = 0; i < Board.NUM_ROW_COL; i++) {
            if (board.getSquare(i, a).occupiedBy() == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the last move made
     *
     * @return the last move made (null if no moves have been made)
     */
    Move getLastMove() {
        if (index == 0) {
            return null;
        } else {
            return moves[index];
        }
    }

    /**
     * Indexes the move in the moves list, makes the move, and changes current player
     * Precondition: move must be valid
     *
     * @param move move to be made
     */
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

    /**
     * Un-indexes the last move, switches the players, and undo's the last move made
     */
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

    /**
     * Return the result of the game
     * Precondition: game has ended
     *
     * @return color of winning player (returns none if stalemate)
     */
    Color getGameResult() {
        if (noColor(Color.WHITE) || lastLine(Color.WHITE)) {
            return Color.WHITE;
        } else if (noColor(Color.BLACK) || lastLine(Color.BLACK)) {
            return Color.BLACK;
        } else {
            return Color.NONE;
        }
    }

}
