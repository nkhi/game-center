package fall2018.csc2017.game_center.pawnrace;

import android.os.Bundle;
import android.support.annotation.Nullable;

import fall2018.csc2017.game_center.SaveManager;
import fall2018.csc2017.game_center.SavedGameState;

public abstract class PRSaveManager extends SaveManager<PRPlayer> {

    /**
     * Constant suffix for the pawn race save file (unique per user)
     */
    public static final String TILE_SAVE_FILE = "_pawn_race_saves.ser";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFilePath(username + TILE_SAVE_FILE);

        readFile();
        if (getSaveFile() == null) {
            setSaveFile(new SavedGameState<PRPlayer>());
            writeFile();
        }
    }

}
