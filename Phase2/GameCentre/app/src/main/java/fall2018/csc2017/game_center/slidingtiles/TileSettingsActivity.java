package fall2018.csc2017.game_center.slidingtiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import fall2018.csc2017.game_center.LoginActivity;
import fall2018.csc2017.game_center.R;

public class TileSettingsActivity extends AppCompatActivity {

    /**
     * Constants for referencing extras when settings are passed
     */
    public static final String SIZE_CONSTANT = "SIZE_CONSTANT";
    public static final String UNDO_CONSTANT = "UNDO_CONSTANT";
    public static final String AUTOSAVE_CONSTANT = "AUTOSAVE_CONSTANT";

    /**
     * Radiobutton and radiogroup in choosing game type
     */
    public RadioButton radioButton;
    RadioGroup radioGroup;

    /**
     * Undo limit field
     */
    TextView undo;

    /**
     * Autosave interval field
     */
    TextView autosave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tile_settings);
        initialize();
        addApplyListener();
    }

    /**
     * Activate the Type Button
     */
    private void initialize() {
        radioGroup = findViewById(R.id.ButtonSize);
        undo = findViewById(R.id.UndoLimitEdit);
        autosave = findViewById(R.id.SaveIntervalLimitEdit);
    }

    /**
     * Saves the settings when apply is pressed
     */
    private void addApplyListener() {
        Button buttonSize = findViewById(R.id.button_apply);
        buttonSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                int size = TileBoard.DEFAULT_ROW_COL;
                switch (radioButton.getId()) {
                    case R.id.Button3:
                        size = 3;
                        break;
                    case R.id.Button4:
                        size = 4;
                        break;
                    case R.id.Button5:
                        size = 5;
                        break;
                }

                Intent tmp = new Intent(TileSettingsActivity.this,
                        TileGameMenuActivity.class);
                tmp.putExtra(SIZE_CONSTANT, size);
                tmp.putExtra(UNDO_CONSTANT, Integer.parseInt(undo.getText().toString()));
                tmp.putExtra(AUTOSAVE_CONSTANT,
                        Integer.parseInt(autosave.getText().toString()));
                tmp.putExtra(LoginActivity.CURRENT_USER,
                        getIntent().getStringExtra(LoginActivity.CURRENT_USER));
                startActivity(tmp);
                finish();
            }
        });
    }


}
