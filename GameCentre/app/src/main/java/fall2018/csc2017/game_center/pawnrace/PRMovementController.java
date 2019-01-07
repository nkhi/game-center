package fall2018.csc2017.game_center.pawnrace;

import android.content.Context;
import android.widget.Toast;

/**
 * Movement controller for pawn race swipes and taps
 */
class PRMovementController {

    /**
     * The player to control
     */
    private PRPlayer player;

    /**
     * Sets the player parameter
     *
     * @param player player to be set
     */
    void setGame(PRPlayer player) {
        this.player = player;
    }

    /**
     * Processes a tap on the board (to move a piece)
     *
     * @param context the parent activity
     * @param position position of the board tapped
     */
    void processTapMovement(Context context, int position) {
        int row = PRBoard.NUM_ROW_COL - (position % PRBoard.NUM_ROW_COL) - 1;
        int col = PRBoard.NUM_ROW_COL - (position / PRBoard.NUM_ROW_COL) - 1;
        if (row < PRBoard.NUM_ROW_COL && col < PRBoard.NUM_ROW_COL) {
            TapResult result = player.processTap(row, col);
            switch (result) {
                case INIT:
                    Toast.makeText(context, "First Tap Registered", Toast.LENGTH_SHORT).show();
                    break;
                case INVALID:
                    Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Processes an undo swipe
     *
     * @param context the parent activity
     */
    void processSwipe(Context context) {
        if (player.hasUndo()) {
            player.undoMove();
            Toast.makeText(context, "Undo'd Last Move", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Undo Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Enum of a tap result that allows three outcomes
     */
    enum TapResult {
        MOVED, INIT, INVALID
    }

}
