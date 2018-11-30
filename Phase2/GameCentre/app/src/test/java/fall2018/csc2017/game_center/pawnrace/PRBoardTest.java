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
    public void applyMoveForward() {
        assertEquals(board.getSquare(1, 1).occupiedBy(), PRColor.WHITE);
        assertEquals(board.getSquare(1, 3).occupiedBy(), PRColor.NONE);
        board.applyMove(new PRMove(board.getSquare(1, 1),
                board.getSquare(1, 3), false, false));
        assertEquals(board.getSquare(1, 3).occupiedBy(), PRColor.WHITE);
        assertEquals(board.getSquare(1, 1).occupiedBy(), PRColor.NONE);
    }

    @Test
    public void applyMoveCapture() {
        board.applyMove(new PRMove(board.getSquare(1, 1),
                board.getSquare(1, 3), false, false));
        board.applyMove(new PRMove(board.getSquare(2, 6),
                board.getSquare(2, 4), false, false));
        board.applyMove(new PRMove(board.getSquare(1, 3),
                board.getSquare(2, 4), true, false));
        assertEquals(board.getSquare(2, 4).occupiedBy(), PRColor.WHITE);
        assertEquals(board.getSquare(1, 3).occupiedBy(), PRColor.NONE);
        assertEquals(board.getSquare(2, 6).occupiedBy(), PRColor.NONE);
    }

    @Test
    public void applyMoveEnPassantCaptureWhite() {
        board.applyMove(new PRMove(board.getSquare(1, 1),
                board.getSquare(1, 4), false, false));
        board.applyMove(new PRMove(board.getSquare(2, 6),
                board.getSquare(2, 4), false, false));
        assertEquals(board.getSquare(2, 4).occupiedBy(), PRColor.BLACK);
        board.applyMove(new PRMove(board.getSquare(1, 4),
                board.getSquare(2, 5), true, true));
        assertEquals(board.getSquare(2, 5).occupiedBy(), PRColor.WHITE);
        assertEquals(board.getSquare(2, 4).occupiedBy(), PRColor.NONE);
        assertEquals(board.getSquare(1, 4).occupiedBy(), PRColor.NONE);
    }

    @Test
    public void applyMoveEnPassantCaptureBlack() {
        board.applyMove(new PRMove(board.getSquare(1, 6),
                board.getSquare(1, 3), false, false));
        board.applyMove(new PRMove(board.getSquare(2, 1),
                board.getSquare(2, 3), false, false));
        board.applyMove(new PRMove(board.getSquare(1, 3),
                board.getSquare(2, 2), true, true));
        assertEquals(board.getSquare(2, 2).occupiedBy(), PRColor.BLACK);
        assertEquals(board.getSquare(1, 3).occupiedBy(), PRColor.NONE);
        assertEquals(board.getSquare(2, 3).occupiedBy(), PRColor.NONE);
    }

    @Test
    public void unapplyMove() {
        PRMove move = new PRMove(board.getSquare(1, 1),
                board.getSquare(1, 3), false, false);
        board.applyMove(move);
        board.unapplyMove(move);
        assertEquals(board.getSquare(1, 1).occupiedBy(), PRColor.WHITE);
        assertEquals(board.getSquare(1, 3).occupiedBy(), PRColor.NONE);
        board.applyMove(move);
        PRMove move1 = new PRMove(board.getSquare(2, 6),
                board.getSquare(2, 4), false, false);
        PRMove move2 = new PRMove(board.getSquare(2, 4),
                board.getSquare(1, 3), true, false);
        board.applyMove(move1);
        board.applyMove(move2);
        assertEquals(PRColor.BLACK, board.getSquare(1,3).occupiedBy());
        board.unapplyMove(move2);
        assertEquals(PRColor.WHITE, board.getSquare(1,3).occupiedBy());
    }

    @Test
    public void unapplyMoveEnPassantCapture() {
        applyMoveEnPassantCaptureWhite();
        board.unapplyMove(new PRMove(board.getSquare(1, 4),
                board.getSquare(2, 5), true, true));
        assertEquals(PRColor.BLACK, board.getSquare(2, 4).occupiedBy());
        applyMoveEnPassantCaptureBlack();
        board.unapplyMove(new PRMove(board.getSquare(1, 3),
                board.getSquare(2, 2), true, true));
        assertEquals(PRColor.BLACK, board.getSquare(1, 3).occupiedBy());
        assertEquals(PRColor.WHITE, board.getSquare(2, 3).occupiedBy());
    }


}