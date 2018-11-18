package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.widget.Toast;


public class MovementController {

    private Player player;

    public void setGame(Player player) {
        this.player = player;
    }

    public void processTapMovement(Context context, int position, boolean display) {
        if (player.processTap(Board.NUM_COLS - (position % Board.NUM_COLS) - 1,
                Board.NUM_ROWS - (position / Board.NUM_ROWS) - 1)) {
            Toast.makeText(context, "TAPPED", Toast.LENGTH_SHORT).show();
            if (player.isFinished()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}
