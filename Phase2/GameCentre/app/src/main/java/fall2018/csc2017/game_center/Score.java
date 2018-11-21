package fall2018.csc2017.game_center;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {

    private String username;
    private int score;

    public Score(String username, Scoreable s) {
        score = s.getScore();
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int compareTo(@NonNull Score o) {
        return (score - o.score);
    }
}
