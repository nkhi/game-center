package fall2018.csc2017.game_center.pawnrace;

import android.content.Context;
import android.widget.Toast;


class PRMovementController {

    enum TapResult {
        MOVED, INIT, INVALID;
    }

    private PRPlayer player;

    void setGame(PRPlayer player) {
        this.player = player;
    }

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

    void processSwipe(Context context) {
        if (player.hasUndo()) {
            player.undoMove();
            Toast.makeText(context, "Undo'd Last Move", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Undo Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

}
