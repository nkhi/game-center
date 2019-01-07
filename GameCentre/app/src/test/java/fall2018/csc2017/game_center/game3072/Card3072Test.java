package fall2018.csc2017.game_center.game3072;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import static org.junit.Assert.*;

public class Card3072Test {

    @Mock
    private Context mMockContext;

    @Mock
    private Resources rs;

    private Card3072 card1;

    private Card3072 card2;

    private static final int LABEL_MARGIN = 10;

    @Before
    public void setUp() throws Exception {
        this.card1 = new Card3072(mMockContext);
        this.card2 = new Card3072(mMockContext);
    }

    @Test
    public void getNum() {
        for (int i = 1; i < 13; i++) {
            card1.setNum(i);
            assertEquals(i, card1.getNum());
        }
    }

    @Test
    public void setNum() {
        for (int i = 1; i < 13; i++) {
            card1.setNum(3 * (1 << i));
            assertEquals(3 * (1 << i), card1.getNum());
        }
    }

    //TODO: change this number
    @Test
    public void getCardWidth() {

//        int abc = rs.getDisplayMetrics().widthPixels;
//        System.out.println(abc);
//        assertEquals(1000 / 4, card1.getCardWidth());
    }

    @Test
    public void hashCodeTest() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(new Card3072(mMockContext).hashCode(), card1.hashCode());
            }
        }

    }

    @Test
    public void equals() {
        card1.setNum(3072);
        assertEquals(card1, card1);
        card2.setNum(1536);
        assertNotEquals(card1, card2);
        assertNotEquals(card1,null);

    }

}