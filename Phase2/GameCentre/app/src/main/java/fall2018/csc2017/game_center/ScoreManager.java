package fall2018.csc2017.game_center;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager implements Serializable {

    private List<Score> scores;

    public ScoreManager() {
        scores = new ArrayList<>();
    }

    public List<String> getUsernames() {
        List<String> result = new ArrayList<>();
        for (Score s : scores) {
            result.add(s.getUsername());
        }
        return result;
    }

    public List<Integer> getScores() {
        List<Integer> result = new ArrayList<>();
        for (Score s : scores) {
            result.add(s.getScore());
        }
        return result;
    }

    public void addScore(Score score) {
        scores.add(score);
        Collections.sort(scores);
    }

}
