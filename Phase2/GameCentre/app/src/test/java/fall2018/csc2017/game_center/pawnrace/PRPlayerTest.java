package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PRPlayerTest {
    private PRPlayer player;

    private PRPlayer opponent;

    private PRGame game;

    private PRSquare getSquare(int x, int y){
        return game.getBoard().getSquare(x, y);
    }

    private void MoveToWin() {
        player.getBoard().applyMove(new PRMove(getSquare(7,6),getSquare(7,7),false,false));
    }

    /**
     * Sets a board with 2 white pieces and 1 black piece where the white piece is 1 move from winning
     */
    @Before
    public void setup(){
        game = new PRGame(4, 3);
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++) {
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++) {
                getSquare(i, j).setOccupier(PRColor.NONE);
            }
        }
        getSquare(2, 6).setOccupier(PRColor.BLACK);
        getSquare(7, 6).setOccupier(PRColor.WHITE);
        getSquare(1, 5).setOccupier(PRColor.WHITE);
        player = new PRPlayer(game, game.getBoard(), PRColor.WHITE, false, 3, -1);
        opponent = new PRPlayer(game, game.getBoard(), PRColor.BLACK, true, 3, -1);
        player.setOpponent(opponent);
        opponent.setOpponent(player);
    }

    @Test
    public void testisFinished() {
        assertFalse(player.isFinished());
        MoveToWin();
        assertTrue(player.isFinished());
    }

    @Test
    public void testgetNumAllPawns() {
        assertEquals(2, player.getNumAllPawns());
        getSquare(7, 6).setOccupier(PRColor.NONE);
        getSquare(1, 5).setOccupier(PRColor.NONE);
        assertEquals(0, player.getNumAllPawns());
    }

    @Test
    public void testgetAllValidMoves() {
        PRMove[] actual = player.getAllValidMoves();
        int size = actual.length;
        for (int i =0; i < actual.length; i++){
            if (actual[i] == null)
                size --;
        }
        assertEquals(3, size);
    }

    @Test
    public void testgetNumValidMoves() {
        assertEquals(3, player.getNumValidMoves());
    }

    @Test
    public void testnumProtectedPawns() {
        assertEquals(0, player.numProtectedPawns());
      //need TODO
    }

    @Test
    public void testforwardness() {
        assertEquals(62, player.forwardness());
        player.getBoard().applyMove(new PRMove(getSquare(1,5), getSquare(1,6), false, false));
        assertEquals(55, player.forwardness());
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
    public void testcomputerMakeMove() {
        assertEquals(PRColor.BLACK, getSquare(2,6).occupiedBy());
        opponent.computerMakeMove();
        assertEquals(PRColor.NONE, getSquare(2,6).occupiedBy());
    }

    /**
     * Tests undoMove() and hasUndo()
     */
    @Test
    public void testundoMove() {
        assertFalse(player.hasUndo());
        player.getGame().applyMove(new PRMove(getSquare(1,5),getSquare(2,6),true,false));
        assertEquals(0, opponent.getNumAllPawns());
        assertEquals(PRColor.WHITE, getSquare(2,6).occupiedBy());
        assertTrue(player.hasUndo());
        player.undoMove();
        assertEquals(1, opponent.getNumAllPawns());
        assertEquals(PRColor.BLACK, getSquare(2,6).occupiedBy());
    }

    @Test
    public void testgetScore() {
        assertEquals(0, player.getScore());
        MoveToWin();
        assertEquals(60, player.getScore());
    }
}