package fall2018.csc2017.game_center;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * A singular numerical score associated with a username
 */
public class Score implements Serializable, Comparable<Score> {

    /**
     * User who achieved this score
     */
    private String username;

    /**
     * Final score at the end of the game
     */
    private int score;

    public Score(String username, Scoreable s) {
        score = s.getScore();
        this.username = username;
    }

    /**
     * Return the score
     *
     * @return the saved score
     */
    public int getScore() {
        return score;
    }

    /**
     * Return the username
     *
     * @return the username associated with the score
     */
    public String getUsername() {
        return username;
    }

    @Override
    public int compareTo(@NonNull Score o) {
        return (score - o.score);
    }
}
