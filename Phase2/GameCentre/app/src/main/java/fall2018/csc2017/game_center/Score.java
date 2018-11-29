package fall2018.csc2017.game_center;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * A singular numerical score associated with a username
 */
public class Score implements Serializable, Comparable<Score> {

    /**
     * Interval of tolerance between two scores to be regarded as the same one
     */
    private static final long DATE_DIFF_ALLOWANCE = 1000;

    /**
     * User who achieved this score
     */
    private String username;

    /**
     * Final score at the end of the game
     */
    private int score;

    /**
     * Timestamp of the score creation
     */
    private long date;

    /**
     * Initializes a score with the username and scoreable game file
     *
     * @param username username of the user
     * @param s scoreable game file
     */
    public Score(String username, Scoreable s) {
        score = s.getScore();
        this.username = username;
        date = Calendar.getInstance().getTime().getTime();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                (Math.abs(date - score1.date) < DATE_DIFF_ALLOWANCE) &&
                Objects.equals(username, score1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, score);
    }

}
