package fall2018.csc2017.game_center.game3072;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class MovementController3072Test {

    @Mock
    private Board3072 b3072;
    private MovementController3072 mc3072;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setBoard() {
        mc3072.setBoard(b3072);
        assertEquals(b3072, mc3072);
    }

    @Test
    public void swipeRight() {
    }

    @Test
    public void swipeLeft() {
    }

    @Test
    public void swipeUp() {
    }

    @Test
    public void swipeDown() {
    }
}