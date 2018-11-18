package fall2018.csc2017.game_center.pawnrace;

import android.content.Context;
import android.widget.Toast;


class PRMovementController {

    private PRPlayer player;

    void setGame(PRPlayer player) {
        this.player = player;
    }

    void processTapMovement(Context context, int position, boolean display) {
        int row = PRBoard.NUM_ROW_COL - (position % PRBoard.NUM_ROW_COL) - 1;
        int col = PRBoard.NUM_ROW_COL - (position / PRBoard.NUM_ROW_COL) - 1;
        if (row < PRBoard.NUM_ROW_COL && col < PRBoard.NUM_ROW_COL && player.processTap(row, col)) {
            Toast.makeText(context, "TAPPED", Toast.LENGTH_SHORT).show();
            if (player.isFinished()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
