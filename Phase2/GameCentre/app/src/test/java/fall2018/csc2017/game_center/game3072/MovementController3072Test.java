package fall2018.csc2017.game_center.game3072;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class MovementController3072Test {

    //TODO
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Mock
    private MovementController3072 mc3072;

    @Mock
    private Board3072 board;

    //TODO
    @Test
    public void setBoard() {
    }

    @Test
    public void swipeRight() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.getBoard()[i][j].setNum(0);
            }
        }
        board.getBoard()[0][3].setNum(6);
        mc3072.swipeRight();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(1, cnt);
        board.getBoard()[0][2].setNum(6);
        mc3072.swipeRight();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board1 = new Board3072();
        board1.getBoard()[0][3].setNum(6);
        board1.getBoard()[0][2].setNum(3);
        mc3072.swipeRight();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board2 = new Board3072();
        board2.getBoard()[0][3].setNum(12);
        board2.getBoard()[0][2].setNum(12);
        board2.getBoard()[0][0].setNum(3);
        board2.getBoard()[0][1].setNum(3);
        board2.getBoard()[1][3].setNum(3);
        mc3072.swipeRight();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(4, cnt);
    }

    @Test
    public void swipeLeft() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.getBoard()[i][j].setNum(0);
            }
        }
        board.getBoard()[0][3].setNum(6);
        mc3072.swipeLeft();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(1, cnt);
        board.getBoard()[0][2].setNum(6);
        mc3072.swipeLeft();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board1 = new Board3072();
        board1.getBoard()[0][3].setNum(6);
        board1.getBoard()[0][2].setNum(3);
        mc3072.swipeLeft();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board2 = new Board3072();
        board2.getBoard()[0][3].setNum(12);
        board2.getBoard()[0][2].setNum(12);
        board2.getBoard()[0][0].setNum(3);
        board2.getBoard()[0][1].setNum(3);
        board2.getBoard()[1][3].setNum(3);
        mc3072.swipeLeft();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(4, cnt);
    }

    @Test
    public void swipeUp() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.getBoard()[i][j].setNum(0);
            }
        }
        board.getBoard()[0][3].setNum(6);
        mc3072.swipeUp();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(1, cnt);
        board.getBoard()[0][2].setNum(6);
        mc3072.swipeUp();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board1 = new Board3072();
        board1.getBoard()[0][3].setNum(6);
        board1.getBoard()[0][2].setNum(3);
        mc3072.swipeUp();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board2 = new Board3072();
        board2.getBoard()[3][3].setNum(12);
        board2.getBoard()[3][2].setNum(12);
        board2.getBoard()[1][0].setNum(3);
        board2.getBoard()[1][1].setNum(3);
        board2.getBoard()[1][3].setNum(3);
        mc3072.swipeUp();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(6, cnt);

    }

    @Test
    public void swipeDown() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board.getBoard()[i][j].setNum(0);
            }
        }
        board.getBoard()[0][3].setNum(6);
        mc3072.swipeDown();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(1, cnt);
        board.getBoard()[0][2].setNum(6);
        mc3072.swipeDown();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board1 = new Board3072();
        board1.getBoard()[0][3].setNum(6);
        board1.getBoard()[0][2].setNum(3);
        mc3072.swipeDown();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        Board3072 board2 = new Board3072();
        board2.getBoard()[0][3].setNum(12);
        board2.getBoard()[1][2].setNum(12);
        board2.getBoard()[0][1].setNum(3);
        board2.getBoard()[1][1].setNum(3);
        board2.getBoard()[1][3].setNum(12);
        mc3072.swipeDown();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(4, cnt);
    }
}