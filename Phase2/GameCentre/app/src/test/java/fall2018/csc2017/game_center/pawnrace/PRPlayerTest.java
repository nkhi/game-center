package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PRPlayerTest {
    private PRPlayer player;

    @Before
    public void setup(){
        /*
        PRSquare[][] boards = new PRSquare[PRBoard.NUM_ROW_COL][PRBoard.NUM_ROW_COL];
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                boards[i][j] = new PRSquare(i, j);
                boards[i][j].setOccupier(PRColor.NONE);
                if (j == PRGame.WHITE_STARTING_RANK) {
                    boards[i][j].setOccupier(PRColor.WHITE);
                } else if (j == PRGame.BLACK_STARTING_RANK) {
                    boards[i][j].setOccupier(PRColor.BLACK);
                }
            }
        }
        */
        PRGame game = new PRGame(4, 5);
        player = new PRPlayer(game, game.getBoard(), PRColor.WHITE, false, 3, -1);
    }

    @Test
    public void testisFinished() {
        assertFalse(player.isFinished());
    }

    @Test
    public void testgetNumAllPawns() {
        assertEquals(7, player.getNumAllPawns());
    }

    @Test
    public void getAllValidMoves() {
    }

    @Test
    public void getNumValidMoves() {
    }

    @Test
    public void numProtectedPawns() {
    }

    @Test
    public void forwardness() {
    }

    @Test
    public void numSemiOpenFiles() {
    }

    @Test
    public void hasPassedPawn() {
    }

    @Test
    public void getPassedPawn() {
    }

    @Test
    public void computerMakeMove() {
    }

    @Test
    public void hasUndo() {
    }

    @Test
    public void undoMove() {
    }

    @Test
    public void processTap() {
    }

    @Test
    public void getScore() {
    }
}