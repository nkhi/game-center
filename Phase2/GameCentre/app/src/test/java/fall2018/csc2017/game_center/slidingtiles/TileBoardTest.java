package fall2018.csc2017.game_center.slidingtiles;

import org.junit.Test;
import org.junit.Before;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TileBoardTest {

    private TileBoard board;

    @Before
    public void setup() {
        //Tile[][] tiles = new Tile[TileBoard.DEFAULT_ROW_COL][TileBoard.DEFAULT_ROW_COL];
        //for (int row = 0; row != TileBoard.DEFAULT_ROW_COL; row++) {
            //for (int col = 0; col != TileBoard.DEFAULT_ROW_COL; col++) {
                //tiles[row][col] = new Tile(row, col);
            //}
        //}
        this.board = new TileBoard(tiles, board.getNumRowCol());
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
                assertEquals(i, board.getTile(i, j));
            }
        }
    }

    @Test
    public void swapTiles() {
    }

    @Test
    public void getNumRowCol() {
    }
}