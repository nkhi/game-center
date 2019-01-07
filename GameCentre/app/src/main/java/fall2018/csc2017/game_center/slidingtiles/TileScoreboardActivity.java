package fall2018.csc2017.game_center.slidingtiles;

import android.os.Bundle;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.ScoreboardActivity;

/**
 * Scoreboard activity to display all scores associated with the Tile Game in a list
 */
public class TileScoreboardActivity extends ScoreboardActivity {

    /**
     * Path of the scoreboard file for the tile game
     */
    public static final String TILE_SCORE_FILE = "tile_scores.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_scoreboard);
        initializeScoreboard(TILE_SCORE_FILE, this);
    }

}
