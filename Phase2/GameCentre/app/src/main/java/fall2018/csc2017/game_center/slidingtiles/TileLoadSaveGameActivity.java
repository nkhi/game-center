package fall2018.csc2017.game_center.slidingtiles;

import android.os.Bundle;

import fall2018.csc2017.game_center.LoadSaveGameActivity;
import fall2018.csc2017.game_center.R;

/**
 * The Load Game and Save Game screen for the sliding tiles game.
 */
public class TileLoadSaveGameActivity extends LoadSaveGameActivity<TileBoardManager> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_load_save_game);
        initializeLoadSave(TileGameMenuActivity.TILE_SAVE_FILE, this, TileGameActivity.class);
    }

}
