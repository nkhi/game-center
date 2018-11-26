package fall2018.csc2017.game_center.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;

public class TileBoardManagerTest {
    /**
     * Solvable boardManager for testing.
     */
    private TileBoardManager boardManagers;

    /**
     * Unsolvable boardManager for testing.
     */
    private TileBoardManager boardManageru;

    /**
     * List of the solvable board in list format
     */
    private List<Tile> tiles;

    /**
     * List of the unsolvable board in list format
     */
    private List<Tile> tileu;

    /**
     * Make a solved board
     */
    @Before
    public void setup(){
        // Make solved board
        tiles = make4Tiles(4);
        TileBoard board = new TileBoard(tiles, 4);
        boardManagers = new TileBoardManager(board, 4, -1);

        //Make Unsolved board
        tileu = make4Tileu();
        board = new TileBoard(tiles, 4);
        boardManageru = new TileBoardManager(board, 4, -1);
    }

    /**
     * Solvable board in list format for testing
     * @param rowcol the size of the sides of a board.
     */
    private List<Tile> make4Tiles(int rowcol) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = rowcol * rowcol;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(numTiles, R.drawable.tile_25));

        return tiles;
    }

    /**
     * Unsolvable 4X4 board in list format for testing
     */
    private List <Tile> make4Tileu() {
        List<Tile> tiles = make4Tiles(4);
        Collections.swap(tiles, 10, 11);
        return  tiles;
    }

    @After
    public void tearDown(){
        tiles.clear();
        tileu.clear();
        boardManagers = null;
        boardManageru = null;
    }

    @Test
    public void testParameter(){
        TileBoard board= new TileBoard(tiles,4);
        TileBoardManager boardManager = new TileBoardManager(4,-1);
        assertTrue(boardManager.isSolvable(tiles, 4));
    }

    @Test
    public void testGetBoard() {
        int row;
        int col;
        for (int i = 0; i < 16; i++){
            row = i/4;
            col = i%4;
            assertEquals(i+1, boardManagers.getBoard().getTile(row, col).getId());
        }
    }

    @Test
    public void testPuzzleSolved() {
        assertTrue(boardManagers.isSolvable(tiles, 4));
        assertFalse(boardManageru.isSolvable(tileu, 4));
    }

    @Test
    public void testIsValidTap() {
        assertTrue(boardManagers.isValidTap(11));
        assertFalse(boardManagers.isValidTap(15));
        assertFalse(boardManagers.isValidTap(10));
    }

    @Test
    public void testIsSolvable() {
        assertTrue(boardManagers.isSolvable(tiles, 4));
        assertFalse(boardManageru.isSolvable(tileu, 4));
        tiles = make4Tiles(3);
        TileBoard board = new TileBoard(tiles, 3);
        boardManagers = new TileBoardManager(board, 3, -1);
        assertTrue(boardManagers.isSolvable(tiles, 3));
    }

    @Test
    public void testTouchMove() {
        boardManagers.touchMove(14);
        assertEquals(16, boardManagers.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManagers.getBoard().getTile(3, 3).getId());
    }

    @Test
    public void testUndo() {
        boardManagers.touchMove(14);
        assertEquals(16, boardManagers.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManagers.getBoard().getTile(3, 3).getId());
        boardManagers.undo();
        assertEquals(15, boardManagers.getBoard().getTile(3,2).getId());
        assertEquals(16, boardManagers.getBoard().getTile(3,3).getId());
    }

    @Test
    public void testHasUndo() {
        assertFalse(boardManagers.hasUndo());
        boardManagers.touchMove(14);
        assertTrue(boardManagers.hasUndo());
    }

    @Test
    public void testGetScore() {
        assertEquals(200, boardManagers.getScore());
        boardManagers.touchMove(14);
        assertEquals(0, boardManagers.getScore());
        boardManagers.touchMove(15);
        assertEquals(198, boardManagers.getScore());
    }
}