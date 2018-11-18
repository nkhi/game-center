package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.Observable;

/**
 * The pawn race chess board
 */
class Board extends Observable implements Serializable {

    /**
     * The number of rows and columns
     */
    final static int NUM_ROW_COL = 8;

    /**
     * The chess board
     */
    private Square[][] board;

    /**
     * A new pawn race chess board
     * Precondition: len(board) == NUM_ROW_COL * NUM_ROW_COL
     *
     * @param board the board for the board
     */
    Board(Square[][] board) {
        this.board = board;
    }

    /**
     * Return the square on the board given by the rank and file
     *
     * @param x file of the square
     * @param y rank of the square
     * @return Square on the board at that position
     */
    Square getSquare(int x, int y) {
        return board[x][y];
    }

    /**
     * Applies a move on the board
     * Precondition: move is a valid move
     *
     * @param move the move to be made
     */
    void applyMove(Move move) {
        Color c = move.getFrom().occupiedBy();
        move.getFrom().setOccupier(Color.NONE);
        move.getTo().setOccupier(c);
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();
        if (move.isEnPassantCapture()) {
            if (c == Color.WHITE) {
                board[toX][toY - 1].setOccupier(Color.NONE);
            } else {
                board[toX][toY + 1].setOccupier(Color.NONE);
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
    void unapplyMove(Move move) {
        Color c = move.getTo().occupiedBy();
        move.getFrom().setOccupier(c);
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();
        if (move.isEnPassantCapture()) {
            board[toX][toY].setOccupier(Color.NONE);
            if (c == Color.WHITE) {
                board[toX][toY - 1].setOccupier(Color.BLACK);
            } else {
                board[toX][toY + 1].setOccupier(Color.WHITE);
            }
        } else if (move.isCapture()) {
            if (c == Color.WHITE) {
                move.getTo().setOccupier(Color.BLACK);
            } else {
                move.getTo().setOccupier(Color.WHITE);
            }
        } else {
            move.getTo().setOccupier(Color.NONE);
        }
    }

}
