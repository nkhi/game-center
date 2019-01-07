package fall2018.csc2017.game_center.game3072;

import android.os.Bundle;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.ScoreboardActivity;

/**
 * Scoreboard activity for the 3072 game
 */
public class ScoreboardActivity3072 extends ScoreboardActivity {

    /**
     * Scoreboard file for the 3072 game
     */
    public static final String A3072_SCORE_FILE = "3072_scores.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        initializeScoreboard(A3072_SCORE_FILE, this);
    }

}
