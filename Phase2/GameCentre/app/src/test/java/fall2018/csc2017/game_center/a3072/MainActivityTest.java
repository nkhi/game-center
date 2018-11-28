package fall2018.csc2017.game_center.a3072;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    private MainActivity MA;

    //Not working
    @Before
    public void setup() {
        this.MA = new MainActivity();
    }

    @Test
    public void clearScore(){
        MA.clearScore();
        assertEquals(0, MA.getScore());
    }

    @Test
    public void getScore() {
        for (int i = 0; i < 100; i++) {
            MA.addScore(i);
            assertEquals(i, MA.getScore());
        }
    }
}