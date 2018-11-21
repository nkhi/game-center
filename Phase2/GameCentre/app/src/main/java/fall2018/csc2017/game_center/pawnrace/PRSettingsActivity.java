package fall2018.csc2017.game_center.pawnrace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.R;

public class PRSettingsActivity extends AppCompatActivity {

    /**
     * Constants for referencing extras when settings are passed
     */
    static final String COLOR_CONSTANT = "COLOR_CONSTANT";
    static final String UNDO_CONSTANT = "UNDO_CONSTANT";
    static final String AUTOSAVE_CONSTANT = "AUTOSAVE_CONSTANT";
    static final String DIFFICULTY_CONSTANT = "DIFFICULTY_CONSTANT";

    private static final int EASY_DIFFICULTY = 3;
    private static final int HARD_DIFFICULTY = 6;

    /**
     * Radiobutton and radiogroup in choosing game type
     */
    private RadioButton gameColorRadioButton;
    private RadioGroup gameColorRadioGroup;

    private RadioButton gameDifficultyRadioButton;
    private RadioGroup gameDifficultyRadioGroup;

    /**
     * Undo limit field
     */
    private TextView undo;

    /**
     * Autosave interval field
     */
    private TextView autosave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pawn_race_settings);
        initialize();
        addApplyListener();
    }

    /**
     * Activate the Type Button
     */
    private void initialize() {
        gameColorRadioGroup = findViewById(R.id.PRButtonColor);
        gameDifficultyRadioGroup = findViewById(R.id.PRButtonDiff);
        undo = findViewById(R.id.PRUndoLimitEdit);
        autosave = findViewById(R.id.PRSaveIntervalLimitEdit);
    }

    /**
     * Saves the settings when apply is pressed
     */
    private void addApplyListener() {
        Button buttonSize = findViewById(R.id.PRButtonApply);
        buttonSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioIDColor = gameColorRadioGroup.getCheckedRadioButtonId();
                gameColorRadioButton = findViewById(radioIDColor);
                PRColor color = PRColor.WHITE;
                switch (gameColorRadioButton.getId()) {
                    case R.id.PRButtonBlack:
                        color = PRColor.BLACK;
                        break;
                    case R.id.PRButtonWhite:
                        color = PRColor.WHITE;
                        break;
                }

                int radioIDDifficulty = gameDifficultyRadioGroup.getCheckedRadioButtonId();
                gameDifficultyRadioButton = findViewById(radioIDDifficulty);
                int difficulty = PRPlayer.DYNAMIC_DEPTH;
                switch (gameDifficultyRadioButton.getId()) {
                    case R.id.PRButtonEasy:
                        difficulty = EASY_DIFFICULTY;
                        break;
                    case R.id.PRButtonHard:
                        difficulty = HARD_DIFFICULTY;
                        break;
                }

                Intent tmp = new Intent(PRSettingsActivity.this,
                        PRGameMenuActivity.class);
                tmp.putExtra(COLOR_CONSTANT, color);
                tmp.putExtra(DIFFICULTY_CONSTANT, difficulty);
                tmp.putExtra(UNDO_CONSTANT, Integer.parseInt(undo.getText().toString()));
                tmp.putExtra(AUTOSAVE_CONSTANT,
                        Integer.parseInt(autosave.getText().toString()));
                tmp.putExtra(LoginActivity.CURRENT_USER,
                        getIntent().getStringExtra(LoginActivity.CURRENT_USER));
                startActivity(tmp);
                finish();
            }
        });
    }
}
