package fall2018.csc2017.game_center;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Generic class to stare saved game info for various games
 *
 * @param <T> The type that contains game info
 */
public class SavedGameState<T extends Serializable> implements Serializable {

    /**
     * DateFormat to convert the Dates given to a String
     */
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
            Locale.CANADA);
    /**
     * A list of manually saved games
     */
    private List<SavedState> savedGames;
    /**
     * The automatically saved game
     */
    private SavedState autoSave;
    /**
     * Temporarily stored game for load/store access
     */
    private SavedState tempSave;

    /**
     * Creates a new SavedGameState with empty fields
     */
    public SavedGameState() {
        savedGames = new LinkedList<>();
    }

    /**
     * Return the number of manually saved games
     *
     * @return the number of manually saved games
     */
    public int size() {
        return savedGames.size();
    }

    /**
     * Return the List of saves with the auto save at index 0 if present and manually saved games
     * following.
     *
     * @return The List of saves with the auto save at index 0 if present and manually saved games
     * following.
     */
    public List<T> getSaves() {
        List<T> list = new ArrayList<>();
        for (SavedState savedState : savedGames) {
            list.add(savedState.save);
        }
        if (autoSave != null) {
            list.add(0, autoSave.save);
        }
        return list;
    }

    /**
     * Return the List of associated times with the previous method
     *
     * @return the List of associated times with each save provided by the getSaves() method.
     */
    public List<String> getTimes() {
        List<String> list = new ArrayList<>();
        for (SavedState savedState : savedGames) {
            list.add(df.format(savedState.time));
        }
        if (autoSave != null) {
            list.add(0, df.format(autoSave.time));
        }
        return list;
    }

    /**
     * Manually save a game
     *
     * @param game Game to be saved
     */
    public void saveGame(T game) {
        savedGames.add(new SavedState(game));
    }

    /**
     * Manually save a game to a certain index
     *
     * @param game  Game to be saved
     * @param index Index to insert the save data in
     */
    public void saveGame(T game, int index) {
        savedGames.add(index, new SavedState(game));
    }

    /**
     * Deletes a manually saved game
     *
     * @param index Index of game to be deleted
     */
    public void deleteSave(int index) {
        savedGames.remove(index);
    }

    /**
     * Stores a save into the auto save slot
     *
     * @param autoSave Game to be auto saved
     */
    public void setAutoSave(T autoSave) {
        this.autoSave = new SavedState(autoSave);
    }

    /**
     * Return the game stored in the temporary slot
     *
     * @return The game stored in the temporary slot
     */
    public T getTempSave() {
        return tempSave.save;
    }

    /**
     * Set the temporary slot
     *
     * @param tempSave Game to be stored in the temporary save slot
     */
    public void setTempSave(T tempSave) {
        this.tempSave = new SavedState(tempSave);
    }

    /**
     * Encapsulated class storing the save file and its relevant date
     */
    private class SavedState implements Serializable {
        T save;
        Date time;

        SavedState(T save) {
            this.save = save;
            time = Calendar.getInstance().getTime();
        }
    }
}
