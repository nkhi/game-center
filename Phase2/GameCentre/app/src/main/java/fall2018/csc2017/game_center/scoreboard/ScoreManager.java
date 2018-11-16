package fall2018.csc2017.game_center.scoreboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * The scoreboard for displaying top scores in sliding tiles.
 *
 * TODO: Implement way to sort game scores by tile complexity (its more fair).
 * TODO: Make Sharing feature when you tap a score, share to twitter link.
 */
public class ScoreManager extends Observable implements Serializable {

    /**
     * Serial Version UID used for serialization purposes
     */
    private static final long serialVersionUID = 4L;

    /**
     * Stores lists of scores with reference to a username.
     */
    private Map<String,List<Score>> scoreMap;

    /**
     * Creates an empty ScoreManager.
     */
    public ScoreManager() {
        scoreMap = new HashMap<>();
    }

    /**
     * Creates a new score
     * @param username the string username of the user who is playing.
     * @param numMoves the number of moves needed to win the game.
     * @param timeTaken the number of whole seconds needed to win the game.
     */
    void createScore(String username, int numMoves, int timeTaken) {
        Score finalScore = new Score(username, numMoves, timeTaken);
        // finalScore.getScore() returns calculated score value at this point.
        if (scoreMap.containsKey(username)) {
            scoreMap.get(username).add(finalScore);
        } else {
            List<Score> list = new ArrayList<>();
            list.add(finalScore);
            scoreMap.put(username, list);
        }
    }

    /**
     * Sorts scores in descending order of score values.
     * @return List<Score> a list of the top 5 score objects, ranked by points.
     */
    public List<Score> sortScores() {
        List<Score> allScores = getAllScores();
        Collections.sort(allScores);
        Collections.reverse(allScores); //descending order list of all scores
        return allScores.subList(0,5); //slice of the top 5 scores
    }

    /**
     * Retrieves all recorded scores as a list.
     * @return List score objects (all time history) saved on the device.
     */
    public List<Score> getAllScores() {
        List<Score> listAll = new ArrayList<>();
        for (List<Score> list : scoreMap.values()) {
            listAll.addAll(list);
        }
        Collections.sort(listAll);
        return listAll;
    }

    /**
     * Returns all scores as a list, attributed to a certain username.
     * Precondition: username must already be a registered user.
     *
     * @param username the username of the current player.
     * @return List<Score> the list of score objects (all time score history) of a user.
     */
    public List<Score> getAllScores(String username) { return scoreMap.get(username); }

    /**
     * Return whether ScoreManager contains a Score with username
     * @param username username of User to be checked
     * @return true if ScoreManager contains a user with username, false otherwise
     */
    boolean contains(String username) {
        return scoreMap.containsKey(username);
    }
}