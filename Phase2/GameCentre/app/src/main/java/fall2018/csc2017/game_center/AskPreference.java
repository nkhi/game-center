package fall2018.csc2017.game_center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.game_center.slidingtiles.GameActivity;
import fall2018.csc2017.game_center.slidingtiles.SettingsActivity;
import fall2018.csc2017.game_center.slidingtiles.TileSaveManager;

public class AskPreference extends TileSaveManager {

    TextView textview;

    private int autosave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_preference);
        textview = findViewById(R.id.pref_type);
        autosave = getIntent().getIntExtra(SettingsActivity.AUTOSAVE_CONSTANT, 3);

        addYesButtonListener();
        addNoButtonListener();
    }

    /**
     * Activate the Yes Button.
     */
    private void addYesButtonListener() {
        Button startButton = findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSelectImage();
            }
        });
    }

    /**
     * Activate the No Button.
     */
    private void addNoButtonListener() {
        Button loadButton = findViewById(R.id.button2);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame();
            }
        });
    }

    /**
     * Switch to the screen that allows the users to choose the way that image comes from.
     */
    private void switchToSelectImage() {
        Intent tmp = new Intent(this, SelectImageActivity.class);
        startActivity(tmp);
        finish();
    }

    /**
     * Switch to the screen that starts a new game without changing the background.
     */
    private void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        tmp.putExtra(LoginActivity.CURRENT_USER, username);
        tmp.putExtra(SettingsActivity.AUTOSAVE_CONSTANT, autosave);
        startActivity(tmp);
        finish();
    }

}
