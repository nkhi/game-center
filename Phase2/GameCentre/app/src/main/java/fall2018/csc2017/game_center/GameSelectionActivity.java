package fall2018.csc2017.game_center;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import fall2018.csc2017.game_center.game3072.GameActivity3072;
import fall2018.csc2017.game_center.pawnrace.PRGameMenuActivity;
import fall2018.csc2017.game_center.slidingtiles.TileGameMenuActivity;

/**
 * ListView activity used for game selection
 */
public class GameSelectionActivity extends AppCompatActivity {

    /**
     * List of game names
     */
    private static final String[] GAME_NAMES = {
            "Sliding Tiles", "Pawn Race", "3072"
    };

    /**
     * List of game descriptions corresponding to the prev. list of game names
     */
    private static final String[] GAME_DESCRIPTIONS = {
            "Arrange the tiles in order!", "First to the end wins!", "2048 but 1 better!"
    };

    /**
     * List of starting activities for each game to jump to, corresponding to prev. lists
     */
    private static final Class[] GAME_MENU_ACTIVITIES = {
            TileGameMenuActivity.class, PRGameMenuActivity.class, GameActivity3072.class
    };

    /**
     * Username of the current logged in user
     */
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        ListView savedGamesView = findViewById(R.id.GameSelectionList);
        savedGamesView.setAdapter(new GameSelectionAdapter());

        username = getIntent().getStringExtra(LoginActivity.CURRENT_USER);
    }

    /**
     * ListView adapter for selecting a game from a list of games
     */
    class GameSelectionAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GAME_NAMES.length;
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

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tmp = new Intent(GameSelectionActivity.this,
                            GAME_MENU_ACTIVITIES[position]);
                    tmp.putExtra(LoginActivity.CURRENT_USER, username);
                    startActivity(tmp);
                }
            };
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listview_entry, parent, false);
            }
            convertView.setClickable(true);
            convertView.setOnClickListener(listener);
            TextView name = convertView.findViewById(R.id.ListViewPrimary);
            TextView description = convertView.findViewById(R.id.ListViewSecondary);

            description.setText(GAME_DESCRIPTIONS[position]);
            name.setText(GAME_NAMES[position]);
            return convertView;
        }

    }
}
