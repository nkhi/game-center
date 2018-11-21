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
    TileBoardManager boardManagers;

    /**
     * Unsolvable boardManager for testing.
     */
    TileBoardManager boardManageru;

    /**
     * Make a solved board
     */
    private void setupCorrects(){
        List<Tile> tiles = make4Tiles();
        TileBoard board = new TileBoard(tiles, 4);
        boardManagers = new TileBoardManager(board, 4, -1);
    }

    private void setupCorrectu(){
        List<Tile> tiles = make4Tileu();
        TileBoard board = new TileBoard(tiles, 4);
        boardManageru = new TileBoardManager(board, 4, -1);
    }
    /**
     * Solvable 4X4 board in list format for testing
     */
    private List<Tile> make4Tiles() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getBoard() {
    }

    @Test
    public void puzzleSolved() {
    }

    @Test
    public void isValidTap() {
    }

    @Test
    public void isSolvable() {
        setupCorrects();
        setupCorrectu();
        assertEquals(true, boardManagers.isSolvable());
        assertEquals(false, boardManageru.isSolvable());
    }

    @Test
    public void getInversion() {
    }

    @Test
    public void touchMove() {
    }

    @Test
    public void undo() {
    }

    @Test
    public void hasUndo() {
    }

    @Test
    public void getScore() {
    }
}