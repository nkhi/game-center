package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;


class MovementController {

    private Player player;

    void setGame(Player player) {
        this.player = player;
    }

    void processTapMovement(Context context, int position, boolean display) {
        int row = Board.NUM_ROW_COL - (position % Board.NUM_ROW_COL) - 1;
        int col = Board.NUM_ROW_COL - (position / Board.NUM_ROW_COL) - 1;
        if (row < Board.NUM_ROW_COL && col < Board.NUM_ROW_COL && player.processTap(row, col)) {
            Toast.makeText(context, "TAPPED", Toast.LENGTH_SHORT).show();
            if (player.isFinished()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
