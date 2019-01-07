package fall2018.csc2017.game_center;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Abstract class storing the common functionality of login and register activities.
 */
public abstract class LoginRegisterActivity extends FileProcessor<UserManager> {

    /**
     * File path for storing the UserManager containing all user info.
     */
    public static final String USERS_SAVE_FILENAME = "users_save_file.ser";

    /**
     * Regex expression for accepted username characters (must be alphanumeric + underscores
     * because it will be used as part of a filepath when saving files)
     */
    public static final String ACCEPTED_CHARACTERS = "^[a-zA-Z0-9_]*$";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFilePath(USERS_SAVE_FILENAME);
        readFile();
        if (getSaveFile() == null) {
            setSaveFile(new UserManager());
        }
    }

    /**
     * Creates a popup display of text
     *
     * @param s String of text to display
     */
    protected void makeToastShortText(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Checks username and password fields for acceptable inputs and displays warning messages if
     * not.
     *
     * @return true if input ok, false otherwise
     */
    protected boolean inputCheck() {
        String username = getUsername();
        String password = getPassword();
        if (username == null || username.isEmpty()) {
            makeToastShortText("Username cannot be empty");
        } else if (password == null || password.isEmpty()) {
            makeToastShortText("Password cannot be empty");
        } else if (!username.matches(ACCEPTED_CHARACTERS)) {
            makeToastShortText("Username can only contain alphanumeric characters and " +
                    "underscores");
        } else {
            return true;
        }
        return false;
    }

    /**
     * Activates the login or register button
     */
    protected abstract void addLoginRegisterButtonListener();

    /**
     * Attempts to login or register the user when the relevant button is pressed
     */
    protected abstract void loginRegister();

    /**
     * Checks for valid login/register
     *
     * @return true if login/register ok, false otherwise
     */
    protected abstract boolean loginRegisterCheck();

    /**
     * Return text in username field.
     *
     * @return Text in username field.
     */
    protected abstract String getUsername();

    /**
     * Return text in password field
     *
     * @return Text in password field
     */
    protected abstract String getPassword();

}
