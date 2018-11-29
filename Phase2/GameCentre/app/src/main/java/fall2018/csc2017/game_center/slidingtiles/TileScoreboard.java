package fall2018.csc2017.game_center.slidingtiles;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.game_center.FileProcessor;
import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.Score;
import fall2018.csc2017.game_center.ScoreboardAdapter;

/**
 * Scoreboard activity to display all scores associated with the Tile Game in a list
 */
public class TileScoreboard extends FileProcessor<List<Score>> {

    /**
     * Path of the scoreboard file for the tile game
     */
    public static final String TILE_SCORE_FILE = "tile_scores.ser";

    /**
     * Constant for storing the score extra passed when a game ends
     */
    public static final String SCORE_EXTRA = "SCORE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFilePath(TILE_SCORE_FILE);
        Score score = (Score) getIntent().getSerializableExtra(SCORE_EXTRA);

        readFile();
        if (getSaveFile() == null) {
            setSaveFile(new ArrayList<Score>());
            writeFile();
        }

        if (score != null) {
            getSaveFile().add(score);
            writeFile();
        }

        Collections.sort(getSaveFile(), Collections.<Score>reverseOrder());

        Integer[] scores = new Integer[getSaveFile().size()];
        String[] usernames = new String[getSaveFile().size()];

        for (int i = 0; i < getSaveFile().size(); i++) {
            scores[i] = getSaveFile().get(i).getScore();
            usernames[i] = getSaveFile().get(i).getUsername();
        }

        setContentView(R.layout.activity_tile_scoreboard);

        ListView scoreboardView = findViewById(R.id.ScoreboardList);
        scoreboardView.setAdapter(new ScoreboardAdapter(this, scores, usernames));
    }

}
