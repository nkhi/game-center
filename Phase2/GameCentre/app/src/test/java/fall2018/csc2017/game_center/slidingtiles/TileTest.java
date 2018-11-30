package fall2018.csc2017.game_center.slidingtiles;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;

public class TileTest {
    private Tile t1;
    private Tile t2;
    private int backgroung1;
    private int background2;
    private ArrayList backround;

    @Before
    public void setup() {
        int bg = 1;
        int bg2 = 3;
        ArrayList backgroundid = new ArrayList<>();

        backgroundid.add(R.drawable.tile_1);
        backgroundid.add(R.drawable.tile_3);

        Tile tile = new Tile(bg);
        Tile tile2 = new Tile(bg2);
        //Tile ctile = new Tile(2, 10);
        //Tile ctile2 = new Tile(4, 3);
        this.t1 = tile;
        this.t2 = tile2;
        this.backgroung1 = bg;
        this.background2 = bg2;
        this.backround = backgroundid;



    }

    @Test
    public void getBackground() {
        assertEquals(backround.get(1), t2.getBackground());
        assertEquals(backround.get(0), t1.getBackground());
    }

    @Test
    public void getId() {
        assertEquals(backgroung1+1, t1.getId());
        assertEquals(background2+1, t2.getId());
    }

    @Test
    public void compareTo() {
        assertEquals(background2-backgroung1,t2.compareTo(t1) );

    }
}