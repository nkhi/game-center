package fall2018.csc2017.game_center;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Abstract class that processes loading and saving a SavedGameState for a game to a file
 *
 * @param <T> Savable game file to be saved
 */
public abstract class SaveManager<T extends Serializable>
        extends FileProcessor<SavedGameState<T>> {

    /**
     * Unique alphanumeric + underscores username
     */
    protected String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = getIntent().getStringExtra(LoginActivity.CURRENT_USER);
    }

    /**
     * Initializes parameters on creation of the activity and creates a save file if not already
     * present
     *
     * @param suffix suffix of the file path - usually denoting the game for which the saves
     *               belong
     */
    protected void initialize(String suffix) {
        setFilePath(username + suffix);

        readFile();
        if (getSaveFile() == null) {
            setSaveFile(new SavedGameState<T>());
            writeFile();
        }
    }

    /**
     * Load a saved state into the temporary save slot
     *
     * @param state saved state to load into the temporary save slot
     */
    protected void loadIntoTemp(T state) {
        readFile();
        getSaveFile().setTempSave(state);
        writeFile();
    }

    /**
     * Return the temporarily saved BoardManager
     *
     * @return the saved temporary BoardManager
     */
    protected T getTemp() {
        readFile();
        return getSaveFile().getTempSave();
    }

    /**
     * Writes the temporary BoardManager into the auto save slot
     */
    protected void autoSave() {
        readFile();
        getSaveFile().setAutoSave(getSaveFile().getTempSave());
        writeFile();
    }

}
