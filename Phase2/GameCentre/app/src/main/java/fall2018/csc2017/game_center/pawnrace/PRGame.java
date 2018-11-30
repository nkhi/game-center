package fall2018.csc2017.game_center.pawnrace;

import java.io.Serializable;

/**
 * The game class of Pawn Race - contains the board and move functionality
 */
class PRGame implements Serializable {

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
    private PRBoard board;

    /**
     * An array of all the moves made
     */
    private PRMove[] moves;

    /**
     * The number of moves made
     */
    private int index;

    /**
     * The color of the current player to move on the board
     */
    private PRColor currentPlayer;

    /**
     * Initializes a game and board with the white and black gaps
     *
     * @param whiteGap file of the white gap
     * @param blackGap file of the black gap
     */
    PRGame(int whiteGap, int blackGap) {
        PRSquare[][] board = new PRSquare[PRBoard.NUM_ROW_COL][PRBoard.NUM_ROW_COL];
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                board[i][j] = new PRSquare(i, j);
                board[i][j].setOccupier(PRColor.NONE);
                if (j == WHITE_STARTING_RANK && i != whiteGap) {
                    board[i][j].setOccupier(PRColor.WHITE);
                } else if (j == BLACK_STARTING_RANK && i != blackGap) {
                    board[i][j].setOccupier(PRColor.BLACK);
                }
            }
        }
        this.board = new PRBoard(board);
        moves = new PRMove[MAX_MOVES];
        index = 0;
        currentPlayer = PRColor.WHITE;
    }

    /**
     * Return the color of the current player
     *
     * @return the color of the current player
     */
    PRColor getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Return the current board
     *
     * @return the current board
     */
    PRBoard getBoard() {
        return board;
    }

    /**
     * Return whether a player has won (does not account for stalemates)
     *
     * @return whether a player has won
     */
    boolean isFinished() {
        return (lastLine(PRColor.WHITE) || lastLine(PRColor.BLACK) || noColor(PRColor.BLACK) ||
                noColor(PRColor.WHITE));
    }

    /**
     * Return the number of moves made
     *
     * @return the number of moves made
     */
    int getNumMovesMade() {
        return index;
    }

    /**
     * Return whether a player has at least one pawn
     *
     * @param c color of player to check
     * @return if a player has at least one pawn
     */
    private boolean noColor(PRColor c) {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
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
    private boolean lastLine(PRColor c) {
        int a = c == PRColor.WHITE ? (PRBoard.NUM_ROW_COL - 1) : 0;
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
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
    PRMove getLastMove() {
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
    void applyMove(PRMove move) {
        if (currentPlayer == PRColor.WHITE) {
            currentPlayer = PRColor.BLACK;
        } else {
            currentPlayer = PRColor.WHITE;
        }
        index++;
        moves[index] = move;
        board.applyMove(move);
    }

    /**
     * Un-indexes the last move, switches the players, and undo's the last move made
     */
    void unapplyMove() {
        PRMove move = getLastMove();
        if (currentPlayer == PRColor.WHITE) {
            currentPlayer = PRColor.BLACK;
        } else {
            currentPlayer = PRColor.WHITE;
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
    PRColor getGameResult() {
        if (noColor(PRColor.WHITE) || lastLine(PRColor.WHITE)) {
            return PRColor.WHITE;
        } else if (noColor(PRColor.BLACK) || lastLine(PRColor.BLACK)) {
            return PRColor.BLACK;
        } else {
            return PRColor.NONE;
        }
    }

}
