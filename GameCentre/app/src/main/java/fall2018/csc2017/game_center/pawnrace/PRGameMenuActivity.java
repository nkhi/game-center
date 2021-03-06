package fall2018.csc2017.game_center.pawnrace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import fall2018.csc2017.game_center.LoadSaveGameActivity;
import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.SaveManager;

public class PRGameMenuActivity extends SaveManager<PRPlayer> {

    /**
     * Constant suffix for the pawn race save file (unique per user)
     */
    public static final String TILE_SAVE_FILE = "_pawn_race_saves.ser";

    /**
     * Default color of the player
     */
    private static final PRColor DEFAULT_COLOR = PRColor.WHITE;

    /**
     * The white player's 'gap' rank
     */
    private int whiteGap;

    /**
     * The black player's 'gap' rank
     */
    private int blackGap;

    /**
     * Number of total undo moves allowed
     */
    private int undo;

    /**
     * Difficulty depth of the game
     */
    private int difficulty;

    /**
     * Autosave interval
     */
    private int autosave;

    /**
     * The player's color
     */
    private PRColor playerColor;

    /**
     * The random number generator used to determine a black and white gap
     */
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(TILE_SAVE_FILE);

        random = new Random();
        newGameInitializer();

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

    /**
     * Initializes a new game from default values or values given through the settings menu
     */
    private void newGameInitializer() {
        whiteGap = random.nextInt(PRBoard.NUM_ROW_COL);
        blackGap = random.nextInt(PRBoard.NUM_ROW_COL);
        playerColor = (PRColor) getIntent().getSerializableExtra(PRSettingsActivity.COLOR_CONSTANT);
        if (playerColor == null) {
            playerColor = DEFAULT_COLOR;
        }
        autosave = getIntent().getIntExtra(PRSettingsActivity.AUTOSAVE_CONSTANT, 1);
        difficulty = getIntent().getIntExtra(PRSettingsActivity.DIFFICULTY_CONSTANT,
                PRPlayer.DYNAMIC_DEPTH);
        undo = getIntent().getIntExtra(PRSettingsActivity.UNDO_CONSTANT, PRPlayer.INFINITE_UNDO);
    }

    /**
     * Initializes a player given parameters
     *
     * @param blackGap black player's gap rank
     * @param whiteGap white player's gap rank
     * @param playerColor player's color
     * @param difficulty difficulty of the AI
     * @return the PRPlayer class of the player with above attributes
     */
    private PRPlayer initializeGame(int blackGap, int whiteGap, PRColor playerColor,
                                    int difficulty) {
        System.out.println(playerColor);
        PRColor computerColor = playerColor == PRColor.WHITE ? PRColor.BLACK : PRColor.WHITE;
        PRGame game = new PRGame(blackGap, whiteGap);
        PRPlayer computerPlayer = new PRPlayer(game, game.getBoard(), computerColor,
                true, difficulty, 0);
        PRPlayer player = new PRPlayer(game, game.getBoard(), playerColor,
                false, 0, undo);
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
                newGameInitializer();
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
     * Switch to the PRGameActivity to play the game
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, PRGameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        tmp.putExtra(PRSettingsActivity.AUTOSAVE_CONSTANT, autosave);
        startActivity(tmp);
    }

    /**
     * Switch to the PRLoadSaveGameActivity view to load or save games
     *
     * @param isLoadActivity Whether to switch to the load menu or save menu.
     */
    private void switchToLoadSaveMenu(boolean isLoadActivity) {
        Intent tmp = new Intent(this, PRLoadSaveGameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        tmp.putExtra(LoadSaveGameActivity.IS_LOAD_ACTIVITY, isLoadActivity);
        startActivity(tmp);
    }

    /**
     * Switch to the PRSettingsActivity view to set game
     */
    private void switchToSettingMenu() {
        Intent tmp = new Intent(this, PRSettingsActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        startActivity(tmp);
    }

    /**
     * Switch to the PRScoreboardActivity activity to view the scoreboard
     */
    private void switchToScoreboard() {
        Intent tmp = new Intent(this, PRScoreboardActivity.class);
        startActivity(tmp);
    }

}
