package fall2018.csc2017.game_center.pawnrace;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PRGameTest {

    private PRGame game;
    private PRMove move1, move2, move3;

    @Before
    public void setup() {
        game = new PRGame(0, 0);
        move1 = new PRMove(game.getBoard().getSquare(1, 1),
                game.getBoard().getSquare(1, 3), false, false);
        move2 = new PRMove(game.getBoard().getSquare(2, 6),
                game.getBoard().getSquare(2, 4), false, false);
        move3 = new PRMove(game.getBoard().getSquare(1, 3),
                game.getBoard().getSquare(2, 4), true, false);
    }

    @Test
    public void getCurrentPlayer() {
        assertEquals(PRColor.WHITE, game.getCurrentPlayer());
        game.applyMove(move1);
        assertEquals(PRColor.BLACK, game.getCurrentPlayer());
        game.applyMove(move2);
        assertEquals(PRColor.WHITE, game.getCurrentPlayer());
    }

    @Test
    public void getBoardAndConstructor() {
        assertEquals(PRColor.NONE, game.getBoard().getSquare(0, 1).occupiedBy());
        assertEquals(PRColor.NONE, game.getBoard().getSquare(0, 6).occupiedBy());
        assertEquals(PRColor.WHITE, game.getBoard().getSquare(1, 1).occupiedBy());
        assertEquals(PRColor.BLACK, game.getBoard().getSquare(1, 6).occupiedBy());
    }

    @Test
    public void isFinished() {
        assertFalse(game.isFinished());
        game.applyMove(new PRMove(game.getBoard().getSquare(1, 1),
                game.getBoard().getSquare(1, 7), false, false));
        assertTrue(game.isFinished());
        game.unapplyMove();
        for (int i = 0; i < 8; i++) {
            game.getBoard().getSquare(i, 1).setOccupier(PRColor.NONE);
        }
        assertTrue(game.isFinished());
    }

    @Test
    public void getNumMovesMade() {
        assertEquals(0, game.getNumMovesMade());
        game.applyMove(move1);
        assertEquals(1, game.getNumMovesMade());
        game.applyMove(move2);
        assertEquals(2, game.getNumMovesMade());
        game.applyMove(move3);
        assertEquals(3, game.getNumMovesMade());
    }

    @Test
    public void getLastMove() {
        assertNull(game.getLastMove());
        game.applyMove(move1);
        assertEquals(move1, game.getLastMove());
    }

    @Test
    public void applyMove() {
        assertEquals(PRColor.WHITE, game.getBoard().getSquare(1, 1).occupiedBy());
        assertEquals(PRColor.NONE, game.getBoard().getSquare(1, 3).occupiedBy());
        game.applyMove(move1);
        assertEquals(PRColor.NONE, game.getBoard().getSquare(1, 1).occupiedBy());
        assertEquals(PRColor.WHITE, game.getBoard().getSquare(1, 3).occupiedBy());
    }

    @Test
    public void unapplyMove() {
        game.applyMove(move1);
        game.applyMove(move2);
        game.applyMove(move3);
        assertEquals(PRColor.WHITE, game.getBoard().getSquare(2, 4).occupiedBy());
        assertEquals(PRColor.NONE, game.getBoard().getSquare(1, 1).occupiedBy());
        game.unapplyMove();
        game.unapplyMove();
        game.unapplyMove();
        assertEquals(PRColor.WHITE, game.getBoard().getSquare(1, 1).occupiedBy());
        assertEquals(PRColor.NONE, game.getBoard().getSquare(2, 4).occupiedBy());
        assertEquals(PRColor.BLACK, game.getBoard().getSquare(2, 6).occupiedBy());
    }

    @Test
    public void getGameResult() {
        game.applyMove(new PRMove(game.getBoard().getSquare(1, 1),
                game.getBoard().getSquare(1, 7), false, false));
        assertEquals(PRColor.WHITE, game.getGameResult());
        game.unapplyMove();
        game.applyMove(new PRMove(game.getBoard().getSquare(1, 6),
                game.getBoard().getSquare(1, 0), false, false));
        assertEquals(PRColor.BLACK, game.getGameResult());
        game.unapplyMove();
        assertEquals(PRColor.NONE, game.getGameResult());
    }
}