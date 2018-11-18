package fall2018.csc2017.slidingtiles;

import java.util.Observable;

import java.io.Serializable;

/**
 * The sliding board board.
 */
public class Board extends Observable implements Serializable {

    /**
     * The number of rows.
     */
    final static int NUM_ROWS = 8;

    /**
     * The number of rows.
     */
    final static int NUM_COLS = 8;

    /**
     * The board on the board in row-major order.
     */
    private Square[][] board;

    /**
     * A new board of board in row-major order.
     * Precondition: len(board) == NUM_ROWS * NUM_COLS
     *
     * @param board the board for the board
     */
    Board(Square[][] board) {
        this.board = board;
    }

    public void setSquare(int x, int y, Color c) {
        board[x][y].setOccupier(c);
    }

    public Square getSquare(int x, int y) {
        return board[x][y];
    }

    public void applyMove(Move move) {
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

    public void unapplyMove(Move move) {
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

    public void display() {
        System.out.println("  A B C D E F G H");
        for (int row = 7; row >= 0; row--) {
            int x = row + 1;
            System.out.print(x + " ");
            for (int col = 0; col <= 7; col++) {
                if (board[col][row].occupiedBy() == Color.WHITE) {
                    System.out.print("W ");
                } else if (board[col][row].occupiedBy() == Color.BLACK) {
                    System.out.print("B ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println(x + "");
        }
        System.out.println("  A B C D E F G H");
    }

}
