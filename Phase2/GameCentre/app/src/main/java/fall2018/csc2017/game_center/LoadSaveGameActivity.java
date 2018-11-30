package fall2018.csc2017.game_center;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * The Load Game and Save Game screen for the sliding tiles game.
 */
public abstract class LoadSaveGameActivity<T extends Serializable> extends SaveManager<T> {

    /**
     * Constant for storing the boolean extra determining whether to load or save.
     */
    public static final String IS_LOAD_ACTIVITY = "IS_LOAD_ACTIVITY";

    /**
     * Array of saved board managers with the first item being the auto save and subsequent ones
     * being manually saved boards.
     */
    private T[] saves;

    /**
     * Array of times as Strings to match the array above.
     */
    private String[] times;

    /**
     * Stores the boolean value of whether this should be a load or save activity passed as an
     * extra.
     */
    private boolean isLoadActivity;

    /**
     * Context of the actual non-abstract activity
     */
    private Context context;

    /**
     * The activity to start when a save file is loaded
     */
    private Class nextActivity;

    /**
     * Initializes the activity parameters
     *
     * @param path file path for save files
     * @param context context of the actual non-abstract activity
     * @param nextActivity the activity to start when a save file is loaded
     */
    @SuppressWarnings("unchecked")
    protected void initializeLoadSave(String path, Context context, Class nextActivity) {
        this.context = context;
        this.nextActivity = nextActivity;
        initialize(path);
        saves = getSaveFile().getSaves().toArray(((T[])
                new Serializable[getSaveFile().size() + 1]));
        times = getSaveFile().getTimes().toArray(new String[saves.length]);

        ListView savedGamesView = findViewById(R.id.SavedGamesList);
        savedGamesView.setAdapter(new LoadSaveGameAdapter());

        isLoadActivity = getIntent().getBooleanExtra(IS_LOAD_ACTIVITY, true);

        TextView saveLoadText = findViewById(R.id.SaveLoadText);
        if (isLoadActivity) {
            saveLoadText.setText(R.string.load_games);
        } else {
            saveLoadText.setText(R.string.save_game);
        }
    }

    /**
     * Custom adapter for the Load/Save game ListView.
     */
    class LoadSaveGameAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return saves.length;
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

            View.OnClickListener listener = getListener(position);
            LayoutInflater inflater = getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listview_entry, parent, false);
            }
            convertView.setClickable(true);
            convertView.setOnClickListener(listener);
            TextView name = convertView.findViewById(R.id.ListViewPrimary);
            TextView time = convertView.findViewById(R.id.ListViewSecondary);

            time.setText(times[position]);
            if (position == 0) {
                if (!getIntent().getBooleanExtra(IS_LOAD_ACTIVITY, true)) {
                    name.setText(R.string.new_save);
                    time.setText("");
                } else {
                    name.setText(R.string.autosave);
                }
            } else {
                name.setText(getString(R.string.save_file, position));
            }
            return convertView;

        }

        /**
         * @param position The index of the item in the ListView
         * @return The listener for the item depending on whether the activity was called to load
         * or save.
         */
        private View.OnClickListener getListener(final int position) {
            View.OnClickListener listener;
            if (isLoadActivity) {
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadIntoTemp(saves[position]);
                        writeFile();
                        Intent tmp = new Intent(context, nextActivity);
                        tmp.putExtra(LoginActivity.CURRENT_USER, username);
                        startActivity(tmp);
                        finish();
                    }
                };
            } else {
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T temp = getTemp();
                        if (position == 0) {
                            getSaveFile().saveGame(temp);

                        } else {
                            getSaveFile().saveGame(temp, position - 1);
                            getSaveFile().deleteSave(position);
                        }
                        writeFile();
                        finish();
                    }
                };
            }
            return listener;
        }

    }

}
