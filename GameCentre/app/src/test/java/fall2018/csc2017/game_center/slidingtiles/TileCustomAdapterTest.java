package fall2018.csc2017.game_center.slidingtiles;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import fall2018.csc2017.game_center.R;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TileCustomAdapterTest {
    private TileCustomAdapter adapter;
    private ArrayList<Button> buttons;


    @Before
    public void setup() {
        Context context = mock(TileGameMenuActivity.class);
        final ArrayList<Button> button = new ArrayList<>();
        TileCustomAdapter Testadpter = new TileCustomAdapter(button, 2, 3);
        Button e, x, z;
        e = new Button(context);
        x = new Button(context);
        z = new Button(context);
        button.add(e);
        button.add(x);
        button.add(z);

        this.adapter = Testadpter;
        this.buttons = button;

    }

    @Test
    public void getCount() {
        assertEquals(3, adapter.getCount());
    }

    @Test
    public void getItem() {
        for (int i = 0; i < adapter.getCount();i++) {
            assertEquals(buttons.get(i), adapter.getItem(i));
        }
    }

    @Test
    public void getItemId() {
        for(int i = 0; i < adapter.getCount(); i++) {
            assertEquals(i, adapter.getItemId(i));
        }
    }

}