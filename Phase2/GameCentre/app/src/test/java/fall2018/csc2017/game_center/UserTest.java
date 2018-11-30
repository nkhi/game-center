package fall2018.csc2017.game_center;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp(){
        String username = "testuname";
        String password = "testpword";
        user = new User(username, password);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void getUsername() {
        assertEquals("testuname", user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("testpword", user.getPassword());
    }
}