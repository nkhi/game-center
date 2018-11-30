package fall2018.csc2017.game_center.slidingtiles;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;

public class TileTest {
    private Tile t1;
    private Tile t2;
    private int background1;
    private int background2;
    private ArrayList<Integer> background;

    @Before
    public void setup() {
        int bg = 0;
        int bg2 = 2;
        background = new ArrayList<>();
        background.add(R.drawable.tile_1);
        background.add(R.drawable.tile_3);

        Tile tile = new Tile(bg);
        Tile tile2 = new Tile(bg2);
        this.t1 = tile;
        this.t2 = tile2;
        this.background1 = bg;
        this.background2 = bg2;
    }

    @After
    public void teardown() {
        t1 = null;
        t2 = null;
        background1 = 0;
        background2 = 0;
        background = null;
    }

    @Test
    public void testGetBackground() {
        assertEquals(background.get(1), (Integer)t2.getBackground());
        assertEquals(background.get(0), (Integer)t1.getBackground());
    }

    @Test
    public void testGetId() {
        assertEquals(background1+1, t1.getId());
        assertEquals(background2+1, t2.getId());
    }

    @Test
    public void testCompareTo() {
        assertEquals(background2-background1,t1.compareTo(t2) );
    }
}