package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PRBoardTest {

    private PRBoard board;

    @Before
    public void setup() {
        PRSquare[][] board = new PRSquare[PRBoard.NUM_ROW_COL][PRBoard.NUM_ROW_COL];
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                board[i][j] = new PRSquare(i, j);
                board[i][j].setOccupier(PRColor.NONE);
                if (j == PRGame.WHITE_STARTING_RANK) {
                    board[i][j].setOccupier(PRColor.WHITE);
                } else if (j == PRGame.BLACK_STARTING_RANK) {
                    board[i][j].setOccupier(PRColor.BLACK);
                }
            }
        }
        this.board = new PRBoard(board);
    }


    @Test
    public void getSquare() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                assertNotNull(board.getSquare(i, j));
                assertEquals(i, board.getSquare(i, j).getX());
                assertEquals(j, board.getSquare(i, j).getY());
            }
        }
    }

    @Test
    public void testConstructorFull() {
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                PRSquare temp = new PRSquare(i, j);
                switch (j) {
                    case PRGame.WHITE_STARTING_RANK:
                        temp.setOccupier(PRColor.WHITE);
                        assertEquals(temp, board.getSquare(i, j));
                        break;
                    case PRGame.BLACK_STARTING_RANK:
                        temp.setOccupier(PRColor.BLACK);
                        assertEquals(temp, board.getSquare(i, j));
                        break;
                    default:
                        temp.setOccupier(PRColor.NONE);
                        assertEquals(temp, board.getSquare(i, j));
                        break;
                }
            }
        }
    }

    @Test
    public void applyMove() {
    }

    @Test
    public void unapplyMove() {
    }


}