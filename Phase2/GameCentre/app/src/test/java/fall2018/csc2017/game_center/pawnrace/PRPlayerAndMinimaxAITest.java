package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class PRPlayerAndMinimaxAITest {
    private PRPlayer player;

    private PRPlayer opponent;

    private PRGame game;

    private PRSquare getSquare(int x, int y){
        return game.getBoard().getSquare(x, y);
    }

    private void moveToWin() {
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
        getSquare(2, 5).setOccupier(PRColor.BLACK);
        getSquare(7, 6).setOccupier(PRColor.WHITE);
        getSquare(1, 4).setOccupier(PRColor.WHITE);
        getSquare(1, 1).setOccupier(PRColor.WHITE);
        player = new PRPlayer(game, game.getBoard(), PRColor.WHITE, false,
                3, -1);
        opponent = new PRPlayer(game, game.getBoard(), PRColor.BLACK, true,
                PRPlayer.DYNAMIC_DEPTH, -1);
        player.setOpponent(opponent);
        opponent.setOpponent(player);
    }

    @After
    public void teardown() {
        game = null;
        opponent = null;
        player = null;
    }

    /**
     * Tests to see if isFinished works when the game ends.
     */
    @Test
    public void testIsFinished() {
        assertFalse(player.isFinished());
        moveToWin();
        assertTrue(player.isFinished());
    }

    /**
     * Tests to see if getNumAllPawns return the total number of pawns.
     */
    @Test
    public void testGetNumAllPawns() {
        assertEquals(3, player.getNumAllPawns());
        getSquare(7, 6).setOccupier(PRColor.NONE);
        getSquare(1, 4).setOccupier(PRColor.NONE);
        assertEquals(1, player.getNumAllPawns());
    }

    /**
     * Tests to see if all Valid Moves are returned.
     */
    @Test
    public void testGetAllValidMoves() {
        PRMove[] actual = player.getAllValidMoves();
        player.getGame().applyMove(new PRMove(getSquare(1,1), getSquare(1,2),false,false));
        int size = actual.length;
        for (PRMove move : actual) {
            if (move == null)
                size--;
        }
        assertEquals(5, size);
    }

    @Test
    public void testGetNumValidMoves() {
        assertEquals(5, player.getNumValidMoves());
    }

    @Test
    public void testNumProtectedPawns() {
        assertEquals(0, player.numProtectedPawns());
        getSquare(6, 7).setOccupier(PRColor.WHITE);
        assertEquals(1, player.numProtectedPawns());
    }

    @Test
    public void testForwardness() {
        assertEquals(59, player.forwardness());
        player.getBoard().applyMove(new PRMove(getSquare(1,4), getSquare(1,5), false, false));
        assertEquals(54, player.forwardness());
    }

    @Test
    public void testNumSemiOpenFiles() {
        assertEquals(2, player.numSemiOpenFiles());
        assertEquals(1,opponent.numSemiOpenFiles());
        getSquare(7,7).setOccupier(PRColor.BLACK);
        assertEquals(1, player.numSemiOpenFiles());
        assertEquals(1,opponent.numSemiOpenFiles());
    }

    @Test
    public void testHasPassedPawn() {
        assertTrue(player.hasPassedPawn());
        getSquare(7, 6).setOccupier(PRColor.NONE);
        assertFalse(player.hasPassedPawn());
    }

    @Test
    public void testGetPassedPawn() {
        assertEquals(7, player.getPassedPawn().getX());
        assertEquals(6, player.getPassedPawn().getY());
    }

    @Test
    public void testComputerMakeMove() {
        assertEquals(PRColor.BLACK, getSquare(2,5).occupiedBy());
        opponent.computerMakeMove();
        assertEquals(PRColor.NONE, getSquare(2,5).occupiedBy());
    }

    @Test
    public void testUndoMove() {
        player.getGame().applyMove(new PRMove(getSquare(1,4),getSquare(1,5),false,false));
        opponent.getGame().applyMove(new PRMove(getSquare(2,5),getSquare(2,4),false, false));
        assertEquals(PRColor.WHITE, getSquare(1,5).occupiedBy());
        player.undoMove();
        assertEquals(PRColor.BLACK, getSquare(2,5).occupiedBy());
    }

    @Test
    public void testHasUndo(){
        assertFalse(player.hasUndo());
        player.getGame().applyMove(new PRMove(getSquare(1,5),getSquare(1,6),false,false));
        assertTrue(player.hasUndo());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, player.getScore());
        moveToWin();
        assertEquals(100, player.getScore());
    }

    /**
     * Tests to see if PRMinimaxAI method works.
     */
    @Test
    public void testMakeBestMove(){
        boolean checker = false;
        player.getOpponent().computerMakeMove();
        assertEquals(PRColor.NONE, getSquare(2,5).occupiedBy());
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++){
            for (int j = 0; j < PRBoard.NUM_ROW_COL; j++){
                if (PRColor.BLACK == getSquare(i,j).occupiedBy())
                    System.out.println(j);
            }
        }
        for (int i = 0; i < PRBoard.NUM_ROW_COL; i++){
            if (PRColor.BLACK == getSquare(i, 4).occupiedBy())
                checker = true;
        }
        assertTrue(checker);
    }

    @Test
    public void testMakeMove() {
        player.processTap(1, 1);
        player.processTap(1, 3);
        assertEquals(PRColor.WHITE, player.getBoard().getSquare(1, 3).occupiedBy());
    }

}