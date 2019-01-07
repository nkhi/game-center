package fall2018.csc2017.game_center;

/**
 * Game object able to return an integer score of the game at the end state of the game
 */
public interface Scoreable {

    /**
     * Return the score calculated by the game - greater = better score
     *
     * @return score calculated by the game
     */
    int getScore();

}
