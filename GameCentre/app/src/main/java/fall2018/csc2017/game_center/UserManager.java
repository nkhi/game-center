package fall2018.csc2017.game_center;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that stores all the Users registered in Game Center.
 */
public class UserManager implements Serializable {

    /**
     * Serial Version UID used for serialization purposes
     */
    private static final long serialVersionUID = 2L;

    /**
     * Stores user data in a Map
     */
    private Map<String, User> userMap;

    /**
     * Creates an empty user manager
     */
    UserManager() {
        userMap = new HashMap<>();
    }

    /**
     * Creates a new user
     * Precondition: username has not been taken (method must be called after contains check)
     *
     * @param username username of user to be created
     * @param password password of user to be created
     */
    void createUser(String username, String password) {
        User user = new User(username, password);
        userMap.put(username, user);
    }

    /**
     * Checks if login is valid
     *
     * @param username username of attempted login
     * @param password password of attempted login
     * @return true if user exists and password matches, false otherwise
     */
    public boolean login(String username, String password) {
        return contains(username) && userMap.get(username).getPassword().equals(password);
    }

    /**
     * Return whether UserManager contains a User with username
     *
     * @param username username of User to be checked
     * @return true if UserManager contains a user with username, false otherwise
     */
    boolean contains(String username) {
        return userMap.containsKey(username);
    }

}
