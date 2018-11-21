package fall2018.csc2017.game_center.pawnrace;

import java.io.Serializable;

import fall2018.csc2017.game_center.R;

/**
 * A PRSquare in a pawn race chess board
 */
public class PRSquare implements Serializable {

    /**
     * The background id to find the square image.
     */
    private int background;

    /**
     * Row/file of the square
     */
    private int x;

    /**
     * Col/rank of the square
     */
    private int y;

    /**
     * PRColor occupying the square
     */
    private PRColor c;

    /**
     * Initializes the square with the row and col
     *
     * @param x row of square
     * @param y col of square
     */
    PRSquare(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates and returns the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        updateBackgroundID();
        return background;
    }

    /**
     * Return row of square
     *
     * @return row of square
     */
    int getX() {
        return x;
    }

    /**
     * Return col of square
     *
     * @return col of square
     */
    int getY() {
        return y;
    }

    /**
     * Return color occupied by square
     *
     * @return color occupied by square
     */
    PRColor occupiedBy() {
        return c;
    }

    /**
     * Sets the occupier of the square
     *
     * @param color color to be set
     */
    void setOccupier(PRColor color) {
        c = color;
    }

    /**
     * Updates the background ID based on the occupant and coordinates (to determine light/dark
     * square)
     */
    private void updateBackgroundID() {
        if ((x + 1) % 2 == (y + 1) % 2) {
            switch (c) {
                case BLACK:
                    background = R.drawable.pawngame_black_black;
                    break;
                case WHITE:
                    background = R.drawable.pawngame_black_white;
                    break;
                default:
                    background = R.drawable.pawngame_black_empty;
            }
        } else {
            switch (c) {
                case BLACK:
                    background = R.drawable.pawngame_white_black;
                    break;
                case WHITE:
                    background = R.drawable.pawngame_white_white;
                    break;
                default:
                    background = R.drawable.pawngame_white_empty;
            }
        }
    }

    @Override
    public int hashCode() {
        return x + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PRSquare)) {
            return false;
        }
        return (x == ((PRSquare) obj).x && y == ((PRSquare) obj).y && c == ((PRSquare) obj).c);
    }
}
