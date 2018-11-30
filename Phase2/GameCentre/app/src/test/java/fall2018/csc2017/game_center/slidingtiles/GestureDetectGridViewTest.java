package fall2018.csc2017.game_center.slidingtiles;

import android.os.SystemClock;
import android.renderscript.Matrix2f;
import android.view.MotionEvent;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;

public class GestureDetectGridViewTest {
    private MotionEvent m1;
    private MotionEvent m2;
    private int Threshhold_velocity = 100;
    private int min_dist = 100;
    private View view;
    private View view2;
    private float stx;
    private float sty;



    @Before
    public void setup() {
        int[] cod = new int[2];
        int[] cod2 = new int[2];
        view.getLocationOnScreen(cod);
        view2.getLocationOnScreen(cod2);
        long downtime = SystemClock.uptimeMillis();
        long eventtime = SystemClock.uptimeMillis();
        int x = cod[0];
        int y = cod[1];
        int a = cod2[0];
        int b = cod2[1];
        int Meta = 0;
        if(y-b>min_dist){int action = MotionEvent.ACTION_DOWN;}
        else if (b-a>min_dist) {int action = MotionEvent.ACTION_UP;}


        MotionEvent event1 = MotionEvent.obtain(downtime, eventtime, action, x, y, Meta);
        //MotionEvent event2 = MotionEvent.obtain(downtime,eventtime,MotionEvent.ACTION_UP,x,y,1);
        this.m1 = event1;
        //this.m2 = event2;



    }

    @Test
    public void onInterceptTouchEvent() {

    }

    @Test
    public void onTouchEvent() {
    }

    @Test
    public void setBoardManager() {
    }
}