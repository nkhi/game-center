package fall2018.csc2017.game_center.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovementControllerTest {
    private TileBoardManager manager;
    private TileBoardManager manage2;

    @Before
    public void setup() {
        TileBoardManager managerTest = new TileBoardManager(4,3);
        TileBoardManager managerTest2 = new TileBoardManager(5,  -1);
        this.manager = managerTest;
        this.manage2 = managerTest2;


    }

    @Test
    public void setBoardManager() {
        assertTrue(manager.hasUndo() == true);
        int y = 0;
        for( int i = 0; i < manager.getBoard().getNumRowCol();i++) {
            if(manager.isValidTap(i)){
                manager.touchMove(i);
                y++;
                if(y > 4) {break;}
            }
        }
        manager.undo();
        manager.undo();
        manager.undo();
        assertFalse(manager.hasUndo() != true);
        assertTrue(manager.getBoard().getNumRowCol() == 16);




    }

    @Test
    public void processTapMovement() {


    }

    @Test
    public void processSwipe() {
    }
}