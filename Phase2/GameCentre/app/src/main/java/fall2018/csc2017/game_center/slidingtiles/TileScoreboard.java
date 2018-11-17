package fall2018.csc2017.game_center.slidingtiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.game_center.FileProcessor;
import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.Score;

public class TileScoreboard extends FileProcessor<List<Score>> {

    public static final String TILE_SCORE_FILE = "tile_scores.ser";
    public static final String SCORE_EXTRA = "SCORE_EXTRA";

    protected Score score;

    private Integer[] scores;
    private String[] usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFilePath(TILE_SCORE_FILE);
        score = (Score) getIntent().getSerializableExtra(SCORE_EXTRA);

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

        scores = new Integer[getSaveFile().size()];
        usernames = new String[getSaveFile().size()];

        for (int i = 0; i < getSaveFile().size(); i++) {
            scores[i] = getSaveFile().get(i).getScore();
            usernames[i] = getSaveFile().get(i).getUsername();
        }

        setContentView(R.layout.activity_tile_scoreboard);

        ListView scoreboardView = findViewById(R.id.ScoreboardList);
        scoreboardView.setAdapter(new ScoreboardAdapter());
    }

    /**
     * Custom adapter for the Load/Save game ListView.
     */
    class ScoreboardAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return getSaveFile().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listview_entry, parent, false);
            }
            TextView score = convertView.findViewById(R.id.ListViewPrimary);
            TextView username = convertView.findViewById(R.id.ListViewSecondary);

            String scoreText = String.valueOf(position + 1) + getString(R.string.scoreboard_score) +
                    String.valueOf(scores[position]);
            score.setText(scoreText);
            String usernameText = getString(R.string.scoreboard_user) + usernames[position];
            username.setText(usernameText);

            return convertView;

        }

    }

}
