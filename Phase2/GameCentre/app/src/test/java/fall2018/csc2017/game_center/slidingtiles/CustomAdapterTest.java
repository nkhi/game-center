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

public class CustomAdapterTest {
    private TileCustomAdapter adapter;
    private ArrayList<Button> buttons;
    private View view;
    private View view2;
    private ViewGroup parent;


    @Before
    public void setup() {
        Context context = mock(Context.class);
        final ArrayList<Button> button = new ArrayList<>();
        TileCustomAdapter Testadpter = new TileCustomAdapter(button, 2, 3);
        Button e, x, z;
        e = new Button(context);
        x = new Button(context);
        z = new Button(context);
        button.add(e);
        button.add(x);
        button.add(z);
        View v = null;
        ViewGroup prt = null;

        this.parent = prt;
        this.view = v;
        this.view2 = e;
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

    @Test
    public void getView() {
        assertNull(view);
        for(int i = 0; i < adapter.getCount();i++) {assertEquals(buttons.get(i), adapter.getView(i, view, parent));}
        assertNotNull(view2);
        for(int i = 0; i < adapter.getCount();i++) {assertEquals(view2, adapter.getView(i, view2, parent));}



    }
}