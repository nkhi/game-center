package fall2018.csc2017.game_center.game3072;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import android.content.Context;
import android.util.DisplayMetrics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Objects;

import static org.junit.Assert.*;

public class Card3072Test {

    @Mock
    private Context mMockContext;

    @Mock
    DisplayMetrics dm;

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
        int i = 1000;
        card1.setNum(3072);
        assertEquals(0, card1.getCardWidth());
    }

    @Test
    public void equals() {
        card1.setNum(3072);
        assertEquals(card1, card1);
        card2.setNum(1536);
        assertFalse(card1.equals(card2));
        assertFalse(card1.equals(null));

    }

    //TODO or not?
    //@Test
    //public void hashCode() {
    //}
}