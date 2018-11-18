package fall2018.csc2017.game_center.pawnrace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.R;

public class PRGameMenuActivity extends PRSaveManager {

    private static final int DEFAULT_DIFFICULTY = 5;
    private static final PRColor DEFAULT_COLOR = PRColor.WHITE;

    private int whiteGap;
    private int blackGap;
    private int undo;
    private int difficulty;
    private PRColor playerColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Random random = new Random();
        whiteGap = random.nextInt(PRBoard.NUM_ROW_COL);
        blackGap = random.nextInt(PRBoard.NUM_ROW_COL);
        playerColor = DEFAULT_COLOR;
        difficulty = DEFAULT_DIFFICULTY;

        PRPlayer player = initializeGame(blackGap, whiteGap, playerColor, difficulty);
        loadIntoTemp(player);

        setContentView(R.layout.activity_game_menu);
        ((TextView) findViewById(R.id.LoggedInAs)).append(username);
        ((TextView) findViewById(R.id.GameMenuTitle)).setText(R.string.pawn_race_welcome);

        addNewGameButtonListener();
        addSaveButtonListener();
        addLoadButtonListener();
        addSettingsButtonListener();
        addScoreboardButtonListener();
    }

    private PRPlayer initializeGame(int blackGap, int whiteGap, PRColor playerColor, int difficulty) {
        PRColor computerColor = playerColor == PRColor.WHITE ? PRColor.BLACK : PRColor.WHITE;
        PRGame game = new PRGame(blackGap, whiteGap);
        PRPlayer computerPlayer = new PRPlayer(game, game.getBoard(), computerColor, true, difficulty);
        PRPlayer player = new PRPlayer(game, game.getBoard(), playerColor, false, 0);
        player.setOpponent(computerPlayer);
        computerPlayer.setOpponent(player);
        return player;
    }

    /**
     * Activate the start button
     */
    private void addNewGameButtonListener() {
        Button startButton = findViewById(R.id.NewGameButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PRPlayer player = initializeGame(blackGap, whiteGap, playerColor, difficulty);
                loadIntoTemp(player);
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
        Intent tmp = new Intent(this, PRGameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
//        tmp.putExtra(TileSettingsActivity.AUTOSAVE_CONSTANT, autosave);
        startActivity(tmp);
    }

    /**
     * Switch to the TileLoadSaveGameActivity view to load or save games
     *
     * @param isLoadActivity Whether to switch to the load menu or save menu.
     */
    private void switchToLoadSaveMenu(boolean isLoadActivity) {
//        Intent tmp = new Intent(this, TileLoadSaveGameActivity.class);
//        tmp.putExtra(LoginActivity.CURRENT_USER, username);
//        tmp.putExtra(TileLoadSaveGameActivity.IS_LOAD_ACTIVITY, isLoadActivity);
//        startActivity(tmp);
    }

    /**
     * Switch to the TileSettingsActivity view to set game
     */
    private void switchToSettingMenu() {
//        Intent tmp = new Intent(this, TileSettingsActivity.class);
//        tmp.putExtra(LoginActivity.CURRENT_USER, username);
//        startActivity(tmp);
    }

    private void switchToScoreboard() {
        Intent tmp = new Intent(this, PRScoreboard.class);
        startActivity(tmp);
    }

}