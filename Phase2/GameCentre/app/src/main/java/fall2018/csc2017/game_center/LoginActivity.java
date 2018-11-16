package fall2018.csc2017.game_center;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fall2018.csc2017.game_center.slidingtiles.TileGameMenuActivity;
import fall2018.csc2017.game_center.R;

/**
 * The view for the login screen
 */
public class LoginActivity extends LoginRegisterActivity {

    /**
     * Constant for storing the current user when passed as an extra
     */
    public static final String CURRENT_USER = "CURRENT_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addLoginRegisterButtonListener();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addLoginRegisterButtonListener() {
        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegister();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean loginRegisterCheck() {
        String username = getUsername();
        String password = getPassword();
        if (inputCheck()) {
            if (!getSaveFile().contains(username) || !getSaveFile().login(username, password)) {
                makeToastShortText("Invalid username or password");
            } else {
                makeToastShortText("Login Successful");
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loginRegister() {
        readFile();
        if (loginRegisterCheck()) {
            writeFile();
            Intent tmp = new Intent(this, TileGameMenuActivity.class);
            tmp.putExtra(CURRENT_USER, getUsername());
            startActivity(tmp);
            finish();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getUsername() {
        return ((EditText) findViewById(R.id.UsernameField)).getText().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPassword() {
        return ((EditText) findViewById(R.id.PasswordField)).getText().toString();
    }

}
