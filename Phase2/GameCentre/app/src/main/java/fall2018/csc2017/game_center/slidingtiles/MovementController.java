package fall2018.csc2017.game_center.slidingtiles;

import android.content.Context;
import android.widget.Toast;

/**
 * Allows for the display of Toast text when interactions are processed in the game activity
 */
class MovementController {

    private BoardManager boardManager;

    /**
     * Sets the board manager
     * @param boardManager board manager to be set
     */
    void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * Displays text based on whether a move is allowed following a tap on a tile
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
     * @param context context to display text on
     */
    void processSwipe(Context context) {
        if (boardManager.hasUndo()) {
            Toast.makeText(context, "Undo'd Last Move", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Undo Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }
}
