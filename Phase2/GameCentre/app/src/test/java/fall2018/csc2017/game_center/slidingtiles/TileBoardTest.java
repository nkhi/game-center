package fall2018.csc2017.game_center.slidingtiles;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TileBoardTest {
    private TileBoard board;
    private List<Tile> tileList = new ArrayList<>();
    int numRowCol = 4;
    int numTiles = numRowCol * numRowCol;

    @Before
    public void setup() {
        for (int x = 0; x != numTiles; x++) {
            this.tileList.add(new Tile(x));
        }
        this.board = new TileBoard(tileList, numRowCol);
    }

    /**
     * Checks if the getter return value for a board initialized with a known numRowCol is correct.
     */
    @Test
    public void numTiles() {
        assertEquals(this.board.numTiles(), numTiles);
    }

    @Test
    public void getTile() {
        Tile[][] tilesList = board.getTiles();
        for (int row = 0; row != numRowCol; row++) {
            for (int col = 0; col != numRowCol; col++) {
                assertEquals(tilesList[row][col], this.board.getTile(row, col));
            }
        }
    }

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

    @Test
    public void iterator() {
        Iterator<Tile> iter = tileList.iterator();
        assertEquals(0, iter.)
    }

    @Test
    public void hasNext() {

    }

    @Test
    public void iteratorNext() {
        // iterator with some elements
        // should be true at start
        // empty the iterator list, hasnext should be false
    }


    @Test
    public void getNumRowCol(){
        assertEquals(numRowCol, board.getNumRowCol());
    }

    @Test
    public void getTiles() {
    }
}