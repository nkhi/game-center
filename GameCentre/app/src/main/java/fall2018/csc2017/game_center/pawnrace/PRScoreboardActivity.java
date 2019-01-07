package fall2018.csc2017.game_center.pawnrace;

import android.os.Bundle;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.ScoreboardActivity;

/**
 * Scoreboard activity to display all scores associated with Pawn Race in a list
 */
public class PRScoreboardActivity extends ScoreboardActivity {

    /**
     * Path of the scoreboard file for the Pawn Race game
     */
    public static final String PR_SCORE_FILE = "pawn_race_scores.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pawn_race_scoreboard);
        initializeScoreboard(PR_SCORE_FILE, this);
    }

}
