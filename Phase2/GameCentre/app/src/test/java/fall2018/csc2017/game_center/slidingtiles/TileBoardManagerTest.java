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
    public void setupSolvedBoard(){
        tiles = make4Tiles();
        TileBoard board = new TileBoard(tiles, 4);
        boardManagers = new TileBoardManager(board, 4, -1);
    }

    /**
     * Make an unsolved board
     */
    @Before
    public void setupUnsolvedBoard(){
        tileu = make4Tileu();
        TileBoard board = new TileBoard(tiles, 4);
        boardManageru = new TileBoardManager(board, 4, -1);
    }
    /**
     * Solvable 4X4 board in list format for testing
     */
    private List<Tile> make4Tiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 16;
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
        List<Tile> tiles = make4Tiles();
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
    public void testgetBoard() {
        int row;
        int col;
        for (int i = 0; i < 16; i++){
            row = i/4;
            col = i%4;
            assertEquals(i+1, boardManagers.getBoard().getTile(row, col).getId());
        }
    }

    @Test
    public void testpuzzleSolved() {
        assertTrue(boardManagers.isSolvable(tiles, 4));
        assertFalse(boardManageru.isSolvable(tileu, 4));
    }

    @Test
    public void testisValidTap() {
        assertTrue(boardManagers.isValidTap(11));
        assertFalse(boardManagers.isValidTap(15));
        assertFalse(boardManagers.isValidTap(10));
    }

    @Test
    public void testisSolvable() {
        assertTrue(boardManagers.isSolvable(tiles, 4));
        assertFalse(boardManageru.isSolvable(tileu, 4));
    }

    @Test
    public void testtouchMove() {
        boardManagers.touchMove(14);
        assertEquals(16, boardManagers.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManagers.getBoard().getTile(3, 3).getId());
    }

    @Test
    public void testundo() {
        boardManagers.touchMove(14);
        assertEquals(16, boardManagers.getBoard().getTile(3, 2).getId());
        assertEquals(15, boardManagers.getBoard().getTile(3, 3).getId());
        boardManagers.undo();
        assertEquals(15, boardManagers.getBoard().getTile(3,2).getId());
        assertEquals(16, boardManagers.getBoard().getTile(3,3).getId());
    }

    @Test
    public void testhasUndo() {
        assertFalse(boardManagers.hasUndo());
        boardManagers.touchMove(14);
        assertTrue(boardManagers.hasUndo());
    }

    @Test
    public void testgetScore() {
        assertEquals(200, boardManagers.getScore());
        boardManagers.touchMove(14);
        boardManagers.touchMove(15);
        assertEquals(198, boardManagers.getScore());
    }
}