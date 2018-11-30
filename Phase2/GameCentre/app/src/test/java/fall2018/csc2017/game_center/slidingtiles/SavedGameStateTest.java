package fall2018.csc2017.game_center.slidingtiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.game_center.SaveManager;
import fall2018.csc2017.game_center.SavedGameState;

import static org.junit.Assert.*;

public class SavedGameStateTest{
    private SavedGameState<TileBoardManager> savedGameState;

    private TileBoardManager boardManager;

    @Before
    public void setUp(){
        boardManager = new TileBoardManager(4);
        savedGameState = new SavedGameState<>();
    }

    @After
    public void tearDown(){
        savedGameState = null;
        boardManager = null;
    }

    @Test
    public void testSize() {
        assertEquals(0, savedGameState.size());
        savedGameState.saveGame(boardManager);
        assertEquals(1,savedGameState.size());
    }

    /**
     * Tests to see if all saves will be returned,
     */
    @Test
    public void testGetSaves() {
        List<TileBoardManager> list = savedGameState.getSaves();
        assertEquals(0,list.size());
        savedGameState.saveGame(boardManager);
        savedGameState.saveGame(new TileBoardManager());
        list = savedGameState.getSaves();
        assertNotNull(list);
        assertEquals(2, list.size());
        savedGameState.setAutoSave(boardManager);
        list = savedGameState.getSaves();
        assertEquals(3, list.size());
    }

    @Test
    public void testGetTimes() {
        savedGameState.saveGame(boardManager);
        List<String> list = savedGameState.getTimes();
        String time = list.get(0);
        //Assuming test is not done when day is about to change
        for (int i = 0; i < 8; i++)
            assertEquals(time.charAt(i + 1), savedGameState.getTimes().get(0).charAt(i + 1));

        savedGameState.setAutoSave(boardManager);
        list = new ArrayList<>(savedGameState.getTimes());
        time = list.get(0);
        //Assuming test is not done when day is about to change
        for (int i = 0; i < 8; i++)
            assertEquals(time.charAt(i + 1), savedGameState.getTimes().get(0).charAt(i + 1));
        assertEquals(2,savedGameState.getSaves().size());
    }

    /**
     * Tests saveGame() to the next index.
     */
    @Test
    public void testSaveGame() {
        assertEquals(0, savedGameState.size());
        savedGameState.saveGame(boardManager);
        assertEquals(boardManager, savedGameState.getSaves().get(0));
    }

    /**
     * Tests saveGame() to a certain index.
     */
    @Test
    public void testSaveGame1() {
        assertEquals(0, savedGameState.size());
        savedGameState.saveGame(new TileBoardManager());
        savedGameState.saveGame(new TileBoardManager());
        savedGameState.saveGame(boardManager, 0);
        assertEquals(3,savedGameState.size());
        assertNotEquals(boardManager,savedGameState.getSaves().get(2));
        assertEquals(boardManager,savedGameState.getSaves().get(0));
    }

    @Test
    public void testDeleteSave() {
        savedGameState.saveGame(boardManager);
        assertEquals(1,savedGameState.size());
        savedGameState.deleteSave(0);
        assertEquals(0,savedGameState.size());
    }

    @Test
    public void testGetAndSetTempSave(){
        savedGameState.setTempSave(boardManager);
        assertNotNull(savedGameState.getTempSave());
    }
}