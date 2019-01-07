package fall2018.csc2017.game_center;

import java.io.Serializable;

/**
 * Class that stores User information for a single user.
 */
public class User implements Serializable {

    /**
     * Serial Version UID used for serialization purposes
     */
    private static final long serialVersionUID = 1L;

    /**
     * Unique username of the user
     */
    private final String username;

    /**
     * Password for user. (Stored in plaintext right now but potentially will implement hashing)
     */
    private String password;

    /**
     * Creates a user
     *
     * @param username username of user to be created
     * @param password password of user to be created
     */
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Return the username of the user
     *
     * @return username of user
     */
    String getUsername() {
        return username;
    }

    /**
     * Return the password of the user
     *
     * @return password of user
     */
    String getPassword() {
        return password;
    }

}
