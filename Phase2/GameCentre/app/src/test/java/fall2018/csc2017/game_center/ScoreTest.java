package fall2018.csc2017.game_center;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.game_center.slidingtiles.Tile;
import fall2018.csc2017.game_center.slidingtiles.TileBoard;
import fall2018.csc2017.game_center.slidingtiles.TileBoardManager;
import fall2018.csc2017.game_center.slidingtiles.TileBoardManagerTest;

import static org.junit.Assert.*;

public class ScoreTest {
    private Score score;
    private String user;
    private TileBoardManager tileBoardManager;

    @Before
    public void setUp(){
        user = "testuser";
        tileBoardManager = new TileBoardManager();
        score =  new Score(user, tileBoardManager);
    }

    @After
    public void tearDown(){
        score = null;
        user = null;
        tileBoardManager = null;
    }

    @Test
    public void testGetScore() {
        assertEquals(0, score.getScore());
    }

    @Test
    public void testGetUsername() {
        assertEquals(user, score.getUsername());
    }

    @Test
    public void compareTo() {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = 16;
        for (int tileNum = 0; tileNum != numTiles - 1; tileNum++) {
            tiles.add(new Tile(tileNum + 1, tileNum));
        }
        tiles.add(new Tile(numTiles, R.drawable.tile_25));

        Score compareScore = new Score("testuser2", new TileBoardManager(new TileBoard(tiles,4), 4,-1));
        assertEquals(-200, score.compareTo(compareScore));
    }

    @Test
    public void equals() {
    }
}