package fall2018.csc2017.game_center.slidingtiles;

import org.junit.Test;
import org.junit.Before;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TileBoardTest {

    private TileBoard board;

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
    public void getNumRowCol(){
        double num = Math.sqrt(board.numTiles());
        assertEquals(num, board.getNumRowCol());
    }
}