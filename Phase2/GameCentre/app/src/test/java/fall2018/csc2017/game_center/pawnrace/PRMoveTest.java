package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PRMoveTest {

    private PRMove move1;
    private PRMove move2;
    private PRMove move3;
    private PRMove move4;

    @Before
    public void setup() {
        move1 = new PRMove(new PRSquare(1, 1), new PRSquare(1, 3),
                false, false);
        move2 = new PRMove(new PRSquare(1, 3), new PRSquare(2, 4),
                true, false);
        move3 = new PRMove(new PRSquare(1,4), new PRSquare(2, 5),
                true, true);
        move4 = new PRMove(new PRSquare(1, 1), new PRSquare(1, 3),
                false, false);
    }

    @Test
    public void getFrom() {
        assertEquals(new PRSquare(1, 1), move1.getFrom());
        assertEquals(new PRSquare(1, 3), move2.getFrom());
        assertEquals(new PRSquare(1, 4), move3.getFrom());
    }

    @Test
    public void getTo() {
        assertEquals(new PRSquare(1, 3), move1.getTo());
        assertEquals(new PRSquare(2, 4), move2.getTo());
        assertEquals(new PRSquare(2, 5), move3.getTo());
    }

    @Test
    public void isCapture() {
        assertFalse(move1.isCapture());
        assertTrue(move2.isCapture());
        assertTrue(move3.isCapture());
    }

    @Test
    public void isEnPassantCapture() {
        assertFalse(move1.isEnPassantCapture());
        assertFalse(move2.isEnPassantCapture());
        assertTrue(move3.isEnPassantCapture());
    }

    @Test
    public void hashCodeTest() {
        assertEquals(move1.hashCode(), move4.hashCode());
    }

    @Test
    public void equalsTest() {
        assertEquals(move1, move4);
        assertNotEquals(move2, move4);
        assertNotEquals(move1, new Object());
    }
}