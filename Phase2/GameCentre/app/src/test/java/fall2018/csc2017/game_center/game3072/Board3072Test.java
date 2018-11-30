package fall2018.csc2017.game_center.game3072;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class Board3072Test {

    @Mock
    private Board3072 board1;

    @Test
    public void getBoard() {
        assertEquals(board1.getBoard().length, 0);

    }

    @Test
    public void isFinished() {
    }

    @Test
    public void getScore() {
    }
}