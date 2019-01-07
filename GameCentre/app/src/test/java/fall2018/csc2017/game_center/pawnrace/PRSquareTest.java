package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.Test;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;

public class PRSquareTest {

    private PRSquare[][] squares;

    @Before
    public void setup() {
        squares = new PRSquare[PRBoard.NUM_ROW_COL][PRBoard.NUM_ROW_COL];
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                squares[i][j] = new PRSquare(i, j);
                squares[i][j].setOccupier(PRColor.NONE);
                if (j == 1) {
                    squares[i][j].setOccupier(PRColor.WHITE);
                } else if (j == 6) {
                    squares[i][j].setOccupier(PRColor.BLACK);
                }
            }
        }
    }

    @Test
    public void getBackground() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                if (j == 1 && ((i + 1) % 2 == (j + 1) % 2)) {
                    assertEquals(squares[i][j].getBackground(), R.drawable.pawngame_black_white);
                } else if (j == 1) {
                    assertEquals(squares[i][j].getBackground(), R.drawable.pawngame_white_white);
                } else if (j == 6  && ((i + 1) % 2 == (j + 1) % 2)) {
                    assertEquals(squares[i][j].getBackground(), R.drawable.pawngame_black_black);
                } else if (j == 6) {
                    assertEquals(squares[i][j].getBackground(), R.drawable.pawngame_white_black);
                } else if (((i + 1) % 2 == (j + 1) % 2)) {
                    assertEquals(squares[i][j].getBackground(), R.drawable.pawngame_black_empty);
                } else {
                    assertEquals(squares[i][j].getBackground(), R.drawable.pawngame_white_empty);
                }
            }
        }
    }

    @Test
    public void getX() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                assertEquals(i, squares[i][j].getX());
            }
        }
    }

    @Test
    public void getY() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                assertEquals(j, squares[i][j].getY());
            }
        }
    }

    @Test
    public void occupierGetterSetter() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                squares[i][j].setOccupier(PRColor.NONE);
                assertEquals(PRColor.NONE, squares[i][j].occupiedBy());
                squares[i][j].setOccupier(PRColor.WHITE);
                assertEquals(PRColor.WHITE, squares[i][j].occupiedBy());
            }
        }
    }

    @Test
    public void hashCodeTest() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                assertEquals((new PRSquare(i, j)).hashCode(), squares[i][j].hashCode());
            }
        }
    }

    @Test
    public void equalsTest() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                squares[i][j].setOccupier(PRColor.NONE);
                PRSquare temp = new PRSquare(i, j);
                temp.setOccupier(PRColor.NONE);
                assertEquals(temp, squares[i][j]);
                temp.setOccupier(PRColor.WHITE);
                assertNotEquals(temp, squares[i][j]);
                assertNotEquals(temp, squares[j][i]);
                assertNotEquals(temp, new Object());
            }
        }
    }
}