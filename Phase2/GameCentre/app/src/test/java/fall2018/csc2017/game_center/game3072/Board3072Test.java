package fall2018.csc2017.game_center.game3072;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class Board3072Test {

    @Mock
    private Board3072 board1;

    @Mock
    private Board3072 board2;

    @Mock
    private Context mMockContext;

    @Before
    public void setUp() throws Exception {
        board1 = new Board3072();
        board2 = new Board3072();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board1.getBoard()[i][j] = new Card3072(mMockContext);
                board2.getBoard()[i][j] = new Card3072(mMockContext);
            }
        }
    }

    @Test
    public void getBoard() {
        assertEquals(4, board1.getBoard().length);
        assertNotNull(board1.getBoard());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j ++) {
                assertEquals(0, board1.getBoard()[i][j].getNum());
            }
        }
    }

    @Test
    public void addRandomNum() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0) {
                    cnt ++;
                }
            }
        }
        board1.addRandomNum();
        int cnt2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0) {
                    cnt2 ++;
                }
            }
        }
        assertEquals(cnt + 1, cnt2);
    }

    @Test
    public void startGame() {
        Board3072 board = board1;
        board1.startGame();

        int cnt = 0;
        for (int i = 0; i < 4; i ++){
            for (int j = 0; j < 4; j++){
                if (board1.getBoard()[i][j].getNum() != 0) {
                    cnt++;
                }
            }
        }
        assertEquals(1, cnt);
    }

    @Test
    public void gestureUp() {
        board2.gestureUp();
        board1.getBoard()[0][3].setNum(3);
        board1.getBoard()[1][3].setNum(3);
        assertTrue(board1.gestureUp());
        assertNotEquals(board1.getBoard(), board2.getBoard());
        board2.getBoard()[0][3].setNum(6);
    }

    @Test
    public void gestureDown() {
        board1.getBoard()[3][0].setNum(3);
        board1.getBoard()[3][1].setNum(3);
        assertTrue(board1.gestureDown());
        board1.gestureDown();
        assertNotEquals(board1.getBoard(), board2.getBoard());
        board2.getBoard()[3][3].setNum(6);
    }

    @Test
    public void gestureLeft() {
        board2.gestureLeft();
        board1.getBoard()[0][0].setNum(3);
        board1.getBoard()[1][0].setNum(3);
        assertTrue(board1.gestureLeft());
        assertNotEquals(board1.getBoard(), board2.getBoard());
        board2.getBoard()[0][0].setNum(6);
    }

    @Test
    public void gestureRight() {
        board2.gestureRight();
        board1.getBoard()[0][0].setNum(3);
        board1.getBoard()[0][1].setNum(3);
        assertTrue(board1.gestureRight());
        assertNotEquals(board1.getBoard(), board2.getBoard());
        board2.getBoard()[0][3].setNum(6);
    }

    @Test
    public void isFinished() {
        int num = 1;
        for (int i = 0; i < 4; i ++){
            for (int j = 0; j < 4; j++){
                board1.getBoard()[i][j].setNum(num * 3);
                num += 1;
            }
        }
        assertTrue(board1.isFinished());
        board1.getBoard()[0][1].setNum(3);
        assertFalse(board1.isFinished());
    }

    @Test
    public void getScore() {
        assertEquals(0, board2.getScore());
        board2.startGame();
        board2.getBoard()[0][0].setNum(24);
        board2.getBoard()[3][3].setNum(48);
        assertEquals(24+48, board2.getScore());
    }
}