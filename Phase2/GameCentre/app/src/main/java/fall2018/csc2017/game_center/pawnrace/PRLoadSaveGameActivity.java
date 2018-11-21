package fall2018.csc2017.game_center.pawnrace;

import android.os.Bundle;

import fall2018.csc2017.game_center.LoadSaveGameActivity;
import fall2018.csc2017.game_center.R;

public class PRLoadSaveGameActivity extends LoadSaveGameActivity<PRPlayer> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pawn_race_load_save_game);
        initializeLoadSave(PRGameMenuActivity.TILE_SAVE_FILE, this, PRGameActivity.class);
    }

}
