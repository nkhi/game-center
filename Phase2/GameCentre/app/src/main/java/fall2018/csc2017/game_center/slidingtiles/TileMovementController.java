package fall2018.csc2017.game_center.slidingtiles;

import android.content.Context;
import android.widget.Toast;

/**
 * Processes tile swaps and toast text when interactions are detected in the game activity
 */
class TileMovementController {

    private TileBoardManager boardManager;

    /**
     * Sets the board manager
     *
     * @param boardManager board manager to be set
     */
    void setBoardManager(TileBoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Processes tap on the board manager and displays relevant text
     *
     * @param context context to display text on
     */
    void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            boardManager.touchMove(position);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Displays text based on whether an undo is allowed following an undo swipe
     *
     * @param context context to display text on
     */
    void processSwipe(Context context) {
        if (boardManager.hasUndo()) {
            boardManager.undo();
            Toast.makeText(context, "Undo'd Last PRMove", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Undo Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }
}
