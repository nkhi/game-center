package fall2018.csc2017.game_center.slidingtiles;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.StartingActivity;

/**
 * The game activity.
 */
public class GameActivity extends TileSaveManager implements Observer {

    /**
     * The autosave amount
     */
    private int autosave = -1;

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Constants for swiping directions. Should be an enum, probably.
     */
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;

    /**
     * The time counter
     */
    static int time;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        time = 1;

        boardManager = getTemp();

        if (autosave == -1) {
            autosave = getIntent().getIntExtra(SettingsActivity.AUTOSAVE_CONSTANT, 0);
            if (autosave == 0) {
                Toast.makeText(this, "Autosave has been reverted to 3 moves", Toast.LENGTH_SHORT).show();
                autosave = 3;
            }
            boardManager.setAutosave(autosave);
        }
        else
            autosave = boardManager.getAutosave();

        createTileButtons(this);
        setContentView(R.layout.activity_main);

        // Add View to activity
        gridView = findViewById(R.id.grid);
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
        Board board = boardManager.getBoard();
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
        Board board = boardManager.getBoard();
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
       // loadIntoTemp(boardManager);
        //writeFile();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        if (time == autosave) {
            System.out.println(autosave);
            time = 0;
            loadIntoTemp(boardManager);
            autoSave();
            System.out.println("File saved");
        }
        time++;
    }

}
