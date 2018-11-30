package fall2018.csc2017.game_center.game3072;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class MovementController3072Test {

    private MovementController3072 mc3072;

    private Board3072 board;

    @Mock
    private Context mMockContext;

    private Board3072 board1;

    private Board3072 board2;

    @Before
    public void setUp() throws Exception {
        this.mc3072 = new MovementController3072();
        this.board = new Board3072();
        board1 = new Board3072();
        board2 = new Board3072();

        this.mc3072.setBoard(this.board);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.board.getBoard()[i][j] = new Card3072(mMockContext);
                board1.getBoard()[i][j] = new Card3072(mMockContext);
                board2.getBoard()[i][j] = new Card3072(mMockContext);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
    }

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
        board.getBoard()[0][3].setNum(3);
        mc3072.swipeRight();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(2, cnt);
        board.getBoard()[3][0].setNum(6);
        board.getBoard()[2][0].setNum(6);
        mc3072.swipeRight();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(4, cnt);
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
        assertEquals(5, cnt);
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
        board1.getBoard()[3][0].setNum(12);
        board1.getBoard()[1][1].setNum(12);
        board1.getBoard()[0][0].setNum(3);
        board1.getBoard()[2][1].setNum(3);
        board1.getBoard()[3][1].setNum(3);
        mc3072.swipeLeft();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(7, cnt);
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
        board.getBoard()[0][3].setNum(6);
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
//        board2.getBoard()[3][3].setNum(12);
//        board2.getBoard()[3][2].setNum(12);
//        board2.getBoard()[1][0].setNum(3);
//        board2.getBoard()[1][1].setNum(3);
//        board2.getBoard()[1][3].setNum(3);
//        mc3072.swipeUp();
//        cnt = 0;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (board2.getBoard()[i][j].getNum() != 0){
//                    cnt++;
//                }
//            }
//        }
//        assertEquals(6, cnt);

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
        board2.getBoard()[3][0].setNum(12);
        board2.getBoard()[2][1].setNum(12);
        board2.getBoard()[1][0].setNum(3);
        board2.getBoard()[1][1].setNum(3);
        board2.getBoard()[3][1].setNum(12);
        mc3072.swipeDown();
        cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board2.getBoard()[i][j].getNum() != 0){
                    cnt++;
                }
            }
        }
        assertEquals(5, cnt);
    }
}