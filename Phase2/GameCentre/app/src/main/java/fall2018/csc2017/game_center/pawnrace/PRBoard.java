package fall2018.csc2017.game_center.pawnrace;

import java.io.Serializable;
import java.util.Observable;

/**
 * The pawn race chess board
 */
class PRBoard extends Observable implements Serializable {

    /**
     * The number of rows and columns
     */
    final static int NUM_ROW_COL = 8;

    /**
     * The chess board
     */
    private PRSquare[][] board;

    /**
     * A new pawn race chess board
     * Precondition: len(board) == NUM_ROW_COL * NUM_ROW_COL
     *
     * @param board the board for the board
     */
    PRBoard(PRSquare[][] board) {
        this.board = board;
    }

    /**
     * Return the square on the board given by the rank and file
     *
     * @param x file of the square
     * @param y rank of the square
     * @return PRSquare on the board at that position
     */
    PRSquare getSquare(int x, int y) {
        return board[x][y];
    }

    /**
     * Applies a move on the board
     * Precondition: move is a valid move
     *
     * @param move the move to be made
     */
    void applyMove(PRMove move) {
        PRColor c = move.getFrom().occupiedBy();
        move.getFrom().setOccupier(PRColor.NONE);
        move.getTo().setOccupier(c);
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();
        if (move.isEnPassantCapture()) {
            if (c == PRColor.WHITE) {
                board[toX][toY - 1].setOccupier(PRColor.NONE);
            } else {
                board[toX][toY + 1].setOccupier(PRColor.NONE);
            }
        }
        setChanged();
    }

    /**
     * Undos a move on the board
     * Precondition: move is the last move made on the board
     *
     * @param move move to be undo'd
     */
    void unapplyMove(PRMove move) {
        PRColor c = move.getTo().occupiedBy();
        move.getFrom().setOccupier(c);
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();
        if (move.isEnPassantCapture()) {
            board[toX][toY].setOccupier(PRColor.NONE);
            if (c == PRColor.WHITE) {
                board[toX][toY - 1].setOccupier(PRColor.BLACK);
            } else {
                board[toX][toY + 1].setOccupier(PRColor.WHITE);
            }
        } else if (move.isCapture()) {
            if (c == PRColor.WHITE) {
                move.getTo().setOccupier(PRColor.BLACK);
            } else {
                move.getTo().setOccupier(PRColor.WHITE);
            }
        } else {
            move.getTo().setOccupier(PRColor.NONE);
        }
        setChanged();
    }

}
