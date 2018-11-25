package fall2018.csc2017.game_center.slidingtiles;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

/**
 * The sliding tiles board.
 */
public class TileBoard extends Observable implements Serializable, Iterable<Tile> {
    /**
     * Default number of rows and columns
     */
    static final int DEFAULT_ROW_COL = 4;

    /**
     * The number of rows and columns
     */
    private int numRowCol;

    /**
     * The tiles on the board in row-major order
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRowCol * numCols
     *
     * @param tiles     the tiles for the board
     * @param numRowCol number of rows and columns for the board
     */
    TileBoard(List<Tile> tiles, int numRowCol) {
        this.numRowCol = numRowCol;
        this.tiles = new Tile[numRowCol][numRowCol];
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != numRowCol; row++) {
            for (int col = 0; col != numRowCol; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return numRowCol * numRowCol;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }



    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile temp = tiles[row2][col2];
        tiles[row2][col2] = tiles[row1][col1];
        tiles[row1][col1] = temp;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "TileBoard{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Return an iterator for all the tiles on the board
     *
     * @return an iterator for all the tiles on the board
     */
    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new Iterator<Tile>() {

            /**
             * Index of current iteration
             */
            private int currIndex = 0;

            /**
             * Return whether the iterator has a next element
             *
             * @return whether the iterator has a next element
             */
            @Override
            public boolean hasNext() {
                return currIndex < numTiles();
            }

            /**
             * Return the next tile in the board in row-major order
             *
             * @return the next tile in the board in row-major order
             */
            @Override
            public Tile next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Tile result = getTile(currIndex / numRowCol, currIndex % numRowCol);
                currIndex++;
                return result;
            }

        };
    }

    /**
     * Return the number of rows/columns
     *
     * @return number of rows/columns
     */
    int getNumRowCol() {
        return numRowCol;
    }
}
