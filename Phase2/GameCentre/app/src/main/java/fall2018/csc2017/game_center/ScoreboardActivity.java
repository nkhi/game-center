package fall2018.csc2017.game_center;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract scoreboard class containing the scoreboard list view functionality
 */
public abstract class ScoreboardActivity extends FileProcessor<List<Score>> {

    /**
     * Constant for storing the score extra passed when a game ends
     */
    public static final String SCORE_EXTRA = "SCORE_EXTRA";

    protected void initializeScoreboard(String filePath, Context context) {
        setFilePath(filePath);
        Score score = (Score) getIntent().getSerializableExtra(SCORE_EXTRA);

        readFile();
        if (getSaveFile() == null) {
            setSaveFile(new ArrayList<Score>());
            writeFile();
        }

        if (score != null && !getSaveFile().contains(score)) {
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
        ListView scoreboardView = findViewById(R.id.ScoreboardList);
        scoreboardView.setAdapter(new ScoreboardAdapter(context, scores, usernames));
    }

    /**
     * Custom adapter for the Load/Save game ListView.
     */
    private class ScoreboardAdapter extends BaseAdapter {

        /**
         * The activity in which this adapter is defined
         */
        private Context context;

        /**
         * An array of scores for this particular game
         */
        private Integer[] scores;

        /**
         * An array of usernames corresponding to the scores array
         */
        private String[] usernames;

        private ScoreboardAdapter(Context context, Integer[] scores, String[] usernames) {
            this.context = context;
            this.scores = scores;
            this.usernames = usernames;
        }

        @Override
        public int getCount() {
            return scores.length;
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

            LayoutInflater inflater = LayoutInflater.from(context);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listview_entry, parent, false);
            }
            TextView score = convertView.findViewById(R.id.ListViewPrimary);
            TextView username = convertView.findViewById(R.id.ListViewSecondary);

            String scoreText = String.valueOf(position + 1) +
                    context.getString(R.string.scoreboard_score) + String.valueOf(scores[position]);
            score.setText(scoreText);
            String usernameText = context.getString(R.string.scoreboard_user) + usernames[position];
            username.setText(usernameText);

            return convertView;

        }

    }

}
