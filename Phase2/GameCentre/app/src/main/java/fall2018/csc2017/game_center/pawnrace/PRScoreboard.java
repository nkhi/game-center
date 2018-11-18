package fall2018.csc2017.game_center.pawnrace;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.game_center.FileProcessor;
import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.Score;
import fall2018.csc2017.game_center.ScoreboardAdapter;

public class PRScoreboard extends FileProcessor<List<Score>> {

    public static final String PR_SCORE_FILE = "pawn_race_scores.ser";
    public static final String SCORE_EXTRA = "SCORE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFilePath(PR_SCORE_FILE);
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

        setContentView(R.layout.activity_pawn_race_scoreboard);

        ListView scoreboardView = findViewById(R.id.ScoreboardList);
        scoreboardView.setAdapter(new ScoreboardAdapter(this, scores, usernames));
    }

}
