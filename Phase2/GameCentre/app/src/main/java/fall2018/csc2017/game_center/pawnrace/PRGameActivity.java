package fall2018.csc2017.game_center.pawnrace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.game_center.R;
import fall2018.csc2017.game_center.SaveManager;
import fall2018.csc2017.game_center.Score;

/**
 * The game activity.
 */
public class PRGameActivity extends SaveManager<PRPlayer> implements Observer {

    /**
     * Column width for each gridView entry
     */
    private static int columnWidth;

    /**
     * The game player
     */
    private PRPlayer player;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

    /**
     * Grid View and calculated column height and width based on device size
     */
    private PRGestureDetectGridView gridView;

    /**
     * The counter for autosaving purposes
     */
    private int autosaveIndex;

    /**
     * Interval to autosave
     */
    private int autosaveInterval;

    /**
     * Computer move handler - adds delay otherwise update() gets lagged after player plays move
     */
    private Handler handler;

    private Runnable computerMakeMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(PRGameMenuActivity.TILE_SAVE_FILE);

        player = getTemp();
        autosaveIndex = 0;
        autosaveInterval = getIntent().getIntExtra(PRSettingsActivity.AUTOSAVE_CONSTANT, 0);

        createTileButtons(this);
        setContentView(R.layout.activity_pawn_race_main);

        // Add View to activity
        gridView = findViewById(R.id.pawn_race_grid);
        gridView.setNumColumns(PRBoard.NUM_ROW_COL);
        gridView.setPlayer(player);
        player.getBoard().addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();

                        columnWidth = displayWidth / PRBoard.NUM_ROW_COL;
                        display();
                    }
                });

        handler = new Handler();
        computerMakeMove = new Runnable() {
            @Override
            public void run() {
                if (player.getGame().getCurrentPlayer() != player.getColor()) {
                    player.getOpponent().computerMakeMove();
                }
            }
        };

        handler.postDelayed(computerMakeMove, 1);
    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        PRBoard board = player.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != PRBoard.NUM_ROW_COL; row++) {
            for (int col = 0; col != PRBoard.NUM_ROW_COL; col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getSquare(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the board
     */
    private void updateTileButtons() {
        PRBoard board = player.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int col = PRBoard.NUM_ROW_COL - (nextPos / PRBoard.NUM_ROW_COL) - 1;
            int row = PRBoard.NUM_ROW_COL - (nextPos % PRBoard.NUM_ROW_COL) - 1;

            b.setBackgroundResource(board.getSquare(row, col).getBackground());
            nextPos++;
        }
    }

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    private void display() {
        updateTileButtons();
        gridView.setAdapter(new PRCustomAdapter(tileButtons, columnWidth));
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        loadIntoTemp(player);
        writeFile();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
        if (autosaveIndex == autosaveInterval) {
            autosaveIndex = 0;
            loadIntoTemp(player);
            autoSave();
            writeFile();
            System.out.println("File saved");
        } else {
            autosaveIndex++;
        }
        if (player.isFinished()) {
            Intent tmp = new Intent(this, PRScoreboard.class);
            tmp.putExtra(PRScoreboard.SCORE_EXTRA, new Score(username, player));
            startActivity(tmp);
            finish();
        } else {
            handler.postDelayed(computerMakeMove, 1);
        }
    }
}
