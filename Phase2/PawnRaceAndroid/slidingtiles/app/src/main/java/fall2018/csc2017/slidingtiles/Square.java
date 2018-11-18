package fall2018.csc2017.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A Square in a sliding tiles puzzle.
 */
public class Square implements Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    private int x;
    private int y;
    private Color c;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        updateBackgroundID();
        return background;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Color occupiedBy() {
        return c;
    }

    void setOccupier(Color color) {
        c = color;
    }

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void updateBackgroundID() {
        if ((x + 1) % 2 == (y + 1) % 2) {
            switch(c) {
                case BLACK: background = R.drawable.pawngame_black_black; break;
                case WHITE: background = R.drawable.pawngame_black_white; break;
                default: background = R.drawable.pawngame_black_empty;
            }
        } else {
            switch(c) {
                case BLACK: background = R.drawable.pawngame_white_black; break;
                case WHITE: background = R.drawable.pawngame_white_white; break;
                default: background = R.drawable.pawngame_white_empty;
            }
        }
    }

    @Override
    public int hashCode() {
        return x + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Square)) {
            return false;
        }
        return (x == ((Square) obj).x && y == ((Square) obj).y && c == ((Square) obj).c);
    }
}
