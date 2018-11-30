package fall2018.csc2017.game_center.slidingtiles;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TileBoardTest {
    public TileBoard board;
    public List<Tile> tileList = new ArrayList<>();
    int numRowCol = 4;
    int numTiles = numRowCol * numRowCol;

    /**
     * Sets up a board with a list of numTiles Tiles and a board size of numRowCol by numRowCol.
     */
    @Before
    public void setup() {
        for (int x = 0; x != numTiles; x++) {
            this.tileList.add(new Tile(x));
        }
        this.board = new TileBoard(tileList, numRowCol);
    }

    /**
     * Checks if the getter return value for a board initialized with a known number of Tiles is correct.
     */
    @Test
    public void numTiles() { assertEquals(this.board.numTiles(), numTiles); }

    /**
     * Compares every Tile in a board to the output accessed through (row,col) form.
     */
    @Test
    public void getTile() {
        Tile[][] tilesList = board.getTiles();
        for (int row = 0; row != numRowCol; row++) {
            for (int col = 0; col != numRowCol; col++) {
                assertEquals(tilesList[row][col], this.board.getTile(row, col));
            }
        }
    }

    /**
     * Generates four random ints no larger than numRowCol and uses those numbers
     * as row and column values to swap tiles at valid board locations. Compares tiles at
     * positions before and after function execution.
     */
    @Test
    public void swapTiles() {
        Tile[][] original = board.getTiles();
        Random random = new Random();
        int randomRow = random.nextInt(numRowCol);
        int randomCol = random.nextInt(numRowCol);
        int randomRow2 = random.nextInt(numRowCol);
        int randomCol2 = random.nextInt(numRowCol);

        Tile first = original[randomRow][randomCol];
        Tile second = original[randomRow2][randomCol2];
        board.swapTiles(randomRow, randomCol, randomRow2, randomCol2);

        assertEquals(first, board.getTile(randomRow2, randomCol2));
        assertEquals(second, board.getTile(randomRow, randomCol));
    }

    /**
     * Checks if a non-empty collection returns True to hasNext().
     * Checks if an empty collection returns False.
    */
    @Test
    public void hasNext() {
        Iterator<Tile> iter = tileList.iterator();
        assertTrue(iter.hasNext());

        List<Tile> emptyTileList = new ArrayList<>();
        Iterator<Tile> iter2 = emptyTileList.iterator();
        assertFalse(iter2.hasNext());
    }

    /**
     * Checks if method returns correct Tile object from a non-empty collection of Tiles.
     */
    @Test
    public void next() {
        Iterator<Tile> iter = tileList.iterator();
        for (int i = 0; i != numTiles; i++) {
            assertEquals(tileList.get(i), iter.next());
        }
    }

    /**
     * Checks if the getter return value for a board initialized with a known numRowCol is correct.
     */
    @Test
    public void getNumRowCol(){
        assertEquals(numRowCol, board.getNumRowCol());
    }

    /**
     * Checks if tile list returned matches expected list.
     */
    @Test
    public void getTiles() {
    }
}