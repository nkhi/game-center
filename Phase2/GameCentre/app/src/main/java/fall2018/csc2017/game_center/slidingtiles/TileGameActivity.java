package fall2018.csc2017.game_center.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.SaveManager;
import fall2018.csc2017.game_center.Score;

/**
 * The game activity.
 */
public class TileGameActivity extends SaveManager<TileBoardManager> implements Observer {

    /**
     * Column width and heights for each gridView entry
     */
    private static int columnWidth, columnHeight;

    /**
     * The board manager.
     */
    private TileBoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Grid View and calculated column height and width based on device size
     */
    private TileGestureDetectGridView gridView;

    /**
     * The counter for autosaving purposes
     */
    private int autosaveIndex;

    /**
     * Interval to autosave
     */
    private int autosaveInterval;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new TileCustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(TileGameMenuActivity.TILE_SAVE_FILE);

        boardManager = getTemp();
        autosaveIndex = 1;
        autosaveInterval = getIntent().getIntExtra(TileSettingsActivity.AUTOSAVE_CONSTANT, 0);
        if (autosaveInterval == 0) {
            Toast.makeText(this, "Autosave has been reverted to 3 moves", Toast.LENGTH_SHORT).show();
            autosaveInterval = 3;
        }

        createTileButtons(this);
        setContentView(R.layout.activity_tile_main);

        // Add View to activity
        gridView = findViewById(R.id.tile_grid);
        gridView.setNumColumns(boardManager.getBoard().getNumRowCol());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getBoard().getNumRowCol();
                        columnHeight = displayHeight / boardManager.getBoard().getNumRowCol();

                        display();
                    }
                });

    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        TileBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoard().getNumRowCol(); row++) {
            for (int col = 0; col != boardManager.getBoard().getNumRowCol(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() {
        TileBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoard().getNumRowCol();
            int col = nextPos % boardManager.getBoard().getNumRowCol();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        loadIntoTemp(boardManager);
        writeFile();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        if (autosaveIndex == autosaveInterval) {
            autosaveIndex = 1;
            loadIntoTemp(boardManager);
            autoSave();
            writeFile();
            System.out.println("File saved");
        } else {
            autosaveIndex++;
        }
        if (boardManager.puzzleSolved()) {
            Intent tmp = new Intent(this, TileScoreboard.class);
            tmp.putExtra(TileScoreboard.SCORE_EXTRA, new Score(username, boardManager));
            startActivity(tmp);
            finish();
        }
    }

}
