package fall2018.csc2017.game_center;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Custom adapter for the Load/Save game ListView.
 */
public class ScoreboardAdapter extends BaseAdapter {

    private Context context;
    private Integer[] scores;
    private String[] usernames;

    public ScoreboardAdapter(Context context, Integer[] scores, String[] usernames) {
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
