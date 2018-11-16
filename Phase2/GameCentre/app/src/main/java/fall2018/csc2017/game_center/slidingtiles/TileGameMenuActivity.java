package fall2018.csc2017.game_center.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.AskPreference;
import fall2018.csc2017.game_center.R;

/**
 * The starting menu for the sliding tiles game.
 */
public class TileGameMenuActivity extends TileSaveManager {

    private int size;
    private int autosave;
    private int undo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        size = getIntent().getIntExtra(SettingsActivity.SIZE_CONSTANT, Board.DEFAULT_ROW_COL);
        autosave = getIntent().getIntExtra(SettingsActivity.AUTOSAVE_CONSTANT, 0);
        undo = getIntent().getIntExtra(SettingsActivity.UNDO_CONSTANT, BoardManager.INFINITY_UNDO);

        loadIntoTemp(new BoardManager(size));
        setContentView(R.layout.activity_tile_game_menu);
        ((TextView) findViewById(R.id.LoggedInAs)).append(username);
        addNewGameButtonListener();
        addSaveButtonListener();
        addLoadButtonListener();
        addSettingsButtonListener();
    }

    /**
     * Activate the start button.
     */
    private void addNewGameButtonListener() {
        Button startButton = findViewById(R.id.NewGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIntoTemp(new BoardManager(size, undo));
                writeFile();
                switchToGame();
            }
        });
    }


    /**
     * Activate the load button.
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
     * Activate the save button.
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
     * Activate the Settings button
     */
    private void addSettingsButtonListener(){
        Button settingButton = findViewById(R.id.SettingsButton);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToSettingMenu();
            }
        });
    }
    /**
     * Read the temporary board from disk.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Switch to the AskPreference to ask the user if they want to change the background.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, AskPreference.class);
        tmp.putExtra(SettingsActivity.AUTOSAVE_CONSTANT, autosave);
        tmp.putExtra(SettingsActivity.SIZE_CONSTANT, size);
        tmp.putExtra(SettingsActivity.UNDO_CONSTANT, undo);
        startActivity(tmp);
    }

    /**
     * Switch to the LoadSaveGameActivity view to load or save games
     * @param isLoadActivity Whether to switch to the load menu or save menu.
     */
    private void switchToLoadSaveMenu(boolean isLoadActivity) {
        Intent tmp = new Intent(this, LoadSaveGameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        tmp.putExtra(LoadSaveGameActivity.IS_LOAD_ACTIVITY, isLoadActivity);
        startActivity(tmp);
    }

    /**
     * Switch to the SettingsActivity view to set game
     */
    private void switchToSettingMenu() {
        Intent tmp = new Intent(this, SettingsActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        startActivity(tmp);
    }

}
