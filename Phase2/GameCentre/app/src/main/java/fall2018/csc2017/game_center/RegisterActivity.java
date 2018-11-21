package fall2018.csc2017.game_center;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * The view for the register screen.
 */
public class RegisterActivity extends LoginRegisterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addLoginRegisterButtonListener();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addLoginRegisterButtonListener() {
        Button registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
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
    protected void loginRegister() {
        if (loginRegisterCheck()) {
            writeFile();
            finish();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean loginRegisterCheck() {
        String username = getUsername();
        String password = getPassword();
        String confirmPassword = getConfirmPassword();
        if (inputCheck()) {
            if (!password.equals(confirmPassword)) {
                makeToastShortText("Password and confirm password must match");
            } else if (getSaveFile().contains(username)) {
                makeToastShortText("A user with this username has already been created");
            } else {
                getSaveFile().createUser(username, password);
                makeToastShortText("User successfully created");
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getUsername() {
        return ((EditText) findViewById(R.id.UsernameRegisterField)).getText().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPassword() {
        return ((EditText) findViewById(R.id.PasswordRegisterField)).getText().toString();
    }

    /**
     * Return the text in the confirm password field
     *
     * @return The text in the confirm password field
     */
    private String getConfirmPassword() {
        return ((EditText) findViewById(R.id.ConfirmPasswordField)).getText().toString();
    }

}
