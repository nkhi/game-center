package fall2018.csc2017.game_center;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserManagerTest {
    private User user;
    private UserManager userManager;

    @Before
    public void setUp() {
        String username = "testuser";
        String password = "testpword";

        user = new User (username, password);
        userManager = new UserManager();
        userManager.createUser("testuser","testpword");
    }

    @After
    public void tearDown() throws Exception {
        user = null;
        userManager = null;
    }

    @Test
    public void testCreateUser() {
        assertEquals(user.getUsername(), userManager.getUser("testuser").getUsername());
        assertEquals(user.getPassword(), userManager.getUser("testuser").getPassword());
    }

    @Test
    public void testLogin() {
        assertFalse(userManager.login("testnotuser", "testnotpword"));
        assertFalse(userManager.login("testnotuser","testpword"));
        assertFalse(userManager.login("testuser", "testnotpword"));
        assertTrue(userManager.login("testuser","testpword"));
    }

    @Test
    public void testContains() {
        assertFalse(userManager.contains("testnotuser"));
        assertTrue(userManager.contains("testuser"));
    }
}