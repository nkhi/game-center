package fall2018.csc2017.game_center.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TileBoardTest {

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testNumTiles() {
        int exp_default = 16;
        int actual_default = t1.numTiles();

        assertEquals(exp_default, actual_default);
        assertEquals(exp_mod, actual_mod);
    }

    @Test
    public void getTile() {
    }

    @Test
    public void swapTiles() {
    }

    @Test
    public void iterator() {
    }

    @Test
    public void getNumRowCol() {
    }
}