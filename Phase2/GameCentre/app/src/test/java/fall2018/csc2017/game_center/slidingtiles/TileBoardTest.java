package fall2018.csc2017.game_center.slidingtiles;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;

public class TileBoardTest {
    private TileBoard board;
    private List<Tile> tiles;

    @Before
    public void setup() {
        int numRowCol = this.board.getNumRowCol();
        this.tiles = new ArrayList<Tile>();
        final int numTiles = numRowCol * numRowCol;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum));
        }
        tiles.add(new Tile(numTiles, R.drawable.tile_25));
        this.board = new TileBoard(tiles, numRowCol);
    }

    @Test
    public void numTiles() {
        for (int i = 0; i < board.getNumRowCol(); i++) {
            for (int j = 0; j < board.getNumRowCol(); j++) {
                assertEquals(i*j, board.numTiles());
            }
        }
    }

    @Test
    public void getTile() {
        for (int i = 0; i < board.getNumRowCol(); i++) {
            for (int j = 0; j < board.getNumRowCol(); j++) {
                assertNotNull(board.getTile(i, j));
            }
        }
    }

    @Test
    public void swapTiles() {

    }

    @Test
    public void iterator() {
        // should return a new Tile Iterator when called
    }

    @Test
    public void iteratorHasNext() {
        // create new iterator with elements
        // next() a few times leaving some iterables remaining
        // hasnext should be true
    }

    @Test
    public void iteratorNext() {
        // iterator with some elements
        // should be true at start
        // empty the iterator list, hasnext should be false
    }


    @Test
    public void getNumRowCol(){
        double num = Math.sqrt(board.numTiles());
        assertEquals(num, board.getNumRowCol());
    }
}

//    @Test
//    public void NumTiles() {
//        int exp_default = 16;
//        int actual_default = t1.numTiles();
//        // int exp_mod = 25
//        // int actual_mod = t2.numTiles();
//        // Need t2 to have numRowCol set to 5 in setup
//
//        assertEquals(exp_default, actual_default);
//        assertEquals(exp_mod, actual_mod);
//    }
//
//    @Test
//    public void getTile() {
//        // set up t1 with non randomized Tiles
//        // get randomized row and col ints within bounds of numRowCol
//        // getTile from t1 with arbitrary row col values
//    }
//
//    @Test
//    public void swapTiles() {
//        // get two Tiles from t1
//        // swapTiles on those two tiles
//        // check that new tiles are in swapped positions
//
//    }
//
//    @Test
//    public void iterator() {
//        // should return a new Tile Iterator when called
//    }
//
//    @Test
//    public void iteratorHasNext() {
//        // create new iterator with elements
//        // next() a few times leaving some iterables remaining
//        // hasnext should be true
//    }
//
//    @Test
//    public void iteratorNext() {
//        // iterator with some elements
//        // should be true at start
//        // empty the iterator list, hasnext should be false
//    }
//
//    @Test
//    public void getNumRowCol() {
//        // getNumRowCol on default board size should be 4
//        // getNumRowCol on modified board should be 5
//        //
//    }
//}