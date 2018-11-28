package fall2018.csc2017.game_center.slidingtiles;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomAdapterTest {
    private TileCustomAdapter

    @Before
    public void setup() {
        ArrayList<Button> button = new ArrayList<>();
        button.add(e);
        button.add(x);
        button.add(z);
        TileCustomAdapter Testadpter = new TileCustomAdapter(button, 2, 3);


    }

    @Test
    public void getCount() {
        ArrayList<Button> button = new ArrayList<>();
        button.add(e);
        button.add(x);
        button.add(z);
        TileCustomAdapter Testadpter = new TileCustomAdapter(button,2,3);
        assertEquals(3, Testadpter.getCount());
    }

    @Test
    public void getItem() {
        ArrayList<Button> button = new ArrayList<>();
        button.add(e);
        button.add(x);
        button.add(z);
        TileCustomAdapter Testadpter = new TileCustomAdapter(button, 2, 3);
        for (int i = 0; i < Testadpter.getCount();i++) {
            assertEquals(button.get(i), Testadpter.getItem(i));
        }
    }

    @Test
    public void getItemId() {
    }

    @Test
    public void getView() {
    }
}