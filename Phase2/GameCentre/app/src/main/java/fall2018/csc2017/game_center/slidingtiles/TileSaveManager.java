package fall2018.csc2017.game_center.slidingtiles;

import android.os.Bundle;
import android.support.annotation.Nullable;

import fall2018.csc2017.game_center.FileProcessor;
import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.SavedGameState;

/**
 * Abstract class used to provide save and load functionality activities within the scope of
 * sliding tiles.
 */
public abstract class TileSaveManager extends FileProcessor<SavedGameState<BoardManager>> {

    /**
     * Constant suffix for the sliding tile save file (unique per user)
     */
    public static final String TILE_SAVE_FILE = "_tile_saves.ser";

    /**
     * Unique alphanumeric + underscores username
     */
    protected String username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFilePath(username + TILE_SAVE_FILE);
        username = getIntent().getStringExtra(LoginActivity.CURRENT_USER);

        readFile();
        if (getSaveFile() == null) {
            setSaveFile(new SavedGameState<BoardManager>());
            writeFile();
        }
    }

    /**
     * Load a BoardManager into the temporary save slot
     * @param boardManager BoardManager to load into the temporary save slot
     */
    protected void loadIntoTemp(BoardManager boardManager) {
        readFile();
        getSaveFile().setTempSave(boardManager);
        writeFile();
    }

    /**
     * Return the temporarily saved BoardManager
     * @return the saved temporary BoardManager
     */
    protected BoardManager getTemp() {
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
