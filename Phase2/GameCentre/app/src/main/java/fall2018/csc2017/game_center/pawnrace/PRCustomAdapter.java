package fall2018.csc2017.game_center.pawnrace;

/*
Taken from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java

This Class is an overwrite of the Base Adapter class
It is designed to aid setting the button sizes and positions in the GridView
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 * View adapter for the Pawn Race game
 */
public class PRCustomAdapter extends BaseAdapter {

    /**
     * List of all the squares as buttons
     */
    private List<Button> mButtons;

    /**
     * Width and height of the buttons (square button so equal width and height)
     */
    private int mColumnWidth;

    /**
     * Initializes the view adapter with parameters
     *
     * @param buttons list of all the squares as buttons
     * @param columnWidth width and height of the buttons
     */
    PRCustomAdapter(List<Button> buttons, int columnWidth) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnWidth);
        button.setLayoutParams(params);

        return button;
    }
}
