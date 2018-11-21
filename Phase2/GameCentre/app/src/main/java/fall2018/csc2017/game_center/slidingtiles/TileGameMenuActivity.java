package fall2018.csc2017.game_center.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.SaveManager;
import fall2018.csc2017.game_center.SavedGameState;

/**
 * The starting menu for the sliding tiles game.
 */
public class TileGameMenuActivity extends SaveManager<TileBoardManager> {

    /**
     * Constant suffix for the sliding tile save file (unique per user)
     */
    public static final String TILE_SAVE_FILE = "_tile_saves.ser";

    private int size;
    private int autosave;
    private int undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(TILE_SAVE_FILE);

        size = getIntent().getIntExtra(TileSettingsActivity.SIZE_CONSTANT,
                TileBoard.DEFAULT_ROW_COL);
        autosave = getIntent().getIntExtra(TileSettingsActivity.AUTOSAVE_CONSTANT, 0);
        undo = getIntent().getIntExtra(TileSettingsActivity.UNDO_CONSTANT,
                TileBoardManager.INFINITY_UNDO);

        loadIntoTemp(new TileBoardManager(size));
        setContentView(R.layout.activity_game_menu);
        ((TextView) findViewById(R.id.LoggedInAs)).append(username);
        ((TextView) findViewById(R.id.GameMenuTitle)).setText(R.string.silding_tiles_welcome);

        addNewGameButtonListener();
        addSaveButtonListener();
        addLoadButtonListener();
        addSettingsButtonListener();
        addScoreboardButtonListener();
    }

    /**
     * Activate the start button
     */
    private void addNewGameButtonListener() {
        Button startButton = findViewById(R.id.NewGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIntoTemp(new TileBoardManager(size, undo));
                writeFile();
                switchToGame();
            }
        });
    }

    /**
     * Activate the scoreboard button
     */
    private void addScoreboardButtonListener() {
        Button scoreboardButton = findViewById(R.id.ScoreboardButton);
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Activate the load button
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadGameButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLoadSaveMenu(true);
            }
        });
    }

    /**
     * Activate the save button
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveGameButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoSave();
                switchToLoadSaveMenu(false);
            }
        });
    }

    /**
     * Activate the settings button
     */
    private void addSettingsButtonListener() {
        Button settingButton = findViewById(R.id.SettingsButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToSettingMenu();
            }
        });
    }

    /**
     * Read the temporary board from disk
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Switch to the TileGameActivity to play the game
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, TileGameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        tmp.putExtra(TileSettingsActivity.AUTOSAVE_CONSTANT, autosave);
        startActivity(tmp);
    }

    /**
     * Switch to the TileLoadSaveGameActivity view to load or save games
     *
     * @param isLoadActivity Whether to switch to the load menu or save menu.
     */
    private void switchToLoadSaveMenu(boolean isLoadActivity) {
        Intent tmp = new Intent(this, TileLoadSaveGameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        tmp.putExtra(TileLoadSaveGameActivity.IS_LOAD_ACTIVITY, isLoadActivity);
        startActivity(tmp);
    }

    /**
     * Switch to the TileSettingsActivity view to set game
     */
    private void switchToSettingMenu() {
        Intent tmp = new Intent(this, TileSettingsActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        startActivity(tmp);
    }

    private void switchToScoreboard() {
        Intent tmp = new Intent(this, TileScoreboard.class);
        startActivity(tmp);
    }

}
