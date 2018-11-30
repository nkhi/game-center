package fall2018.csc2017.game_center.slidingtiles;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import fall2018.csc2017.game_center.FileProcessor;
import fall2018.csc2017.game_center.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;
import fall2018.csc2017.game_center.SavedGameState;
import fall2018.csc2017.game_center.SaveManager;

import static org.junit.Assert.*;

public class TileGameActivityTest extends FileProcessor {

    @Rule
    public ActivityTestRule<TileGameActivity> mActivityTestRule = new ActivityTestRule<>(TileGameActivity.class);

    private TileGameActivity mActivity = null;

    private SavedGameState savedGameState;

    @Before
    public void setup(){
        savedGameState = new SavedGameState();

        TileBoardManager boardManager= new TileBoardManager();
        savedGameState.setTempSave(boardManager);

        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void teardown(){
        mActivity = null;
    }

    @Test
    public void testLaunch(){
        System.out.println("hi");
        View view = mActivity.findViewById(R.id.GameMenuTitle);
        assertNotNull(view);
    }

    @Test
    public void display() {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onPause() {
    }

    @Test
    public void update() {
    }
}