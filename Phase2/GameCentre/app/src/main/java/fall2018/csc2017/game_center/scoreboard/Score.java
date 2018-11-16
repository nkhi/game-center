package fall2018.csc2017.game_center.scoreboard;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.lang.Math;

/**
 * The scores of a finished game of SlidingTiles.
 * Precondition: current user == GameScore.getUsername()
 * Postcondition: Username must be a non-empty string with
 *      a valid registered username. Score must be >= 0.
 */
public class Score implements Comparable<Score>, Serializable {

    /**
     * Serial Version UID used for serialization purposes
     */
    private static final long serialVersionUID = 3L;

    /**
     * The string of the user playing the game.
     * Game score will be attributed to this username only.
     */
    private static String username;

    /**
     * A total number of moves made by the player before winning.
     * Used in calculating the final score metric.
     */
    private static int numMoves;

    /**
     * The amount of time in whole seconds taken by a user to solve the game.
     * Used in calculating the final score metric.
     */
    private static int timeTaken;

    /**
     * The final score value of a completed game.
     * Based on time taken to complete and number of moves made.
     * Score's have no maximum value, but an ideal score is closest to 0.
     */
    private static int score;

    /**
     * A new GameScore object to monitor scoring attributes.
     * Precondition: username == getUsername() of the current player. Users may not
     * log scores under alternative usernames.
     *
     * @param username the name of the current player.
     * @param numMoves the number of moves it took the player to complete the game.
     * @param time the number of seconds it took the player to solve the puzzle,
     *                  rounded to the nearest whole second.
     */
    public Score(String username, int numMoves, int time) {
        this.username = username;
        this.numMoves = numMoves;
        this.timeTaken = time;
        this.score = calculateScore(numMoves, time);
    }

    /**
     * Calculate the score of a game using the number of moves made
     * and number of whole seconds.
     * @param numMoves number of moves made in completing the game.
     * @param seconds number of whole seconds used in completing the game.
     * @return int representing a score for the game.
     */
    public int calculateScore(int numMoves, int seconds) { return numMoves + Math.round(seconds); }

    /**
     * Retrieve score attribute for this game.
     * @return int score representing the number of points
     * gained from a completed game.
     */
    public int getScore() { return this.score; }

    /**
     * Set score attribute for this game.
     * @param val the integer representing the number of points
     *              for a completed game.
     */
    public void setScore(int val) { this.score = val; }

    /**
     * Retrieve the username attributed with a score.
     * @return String of the score holder's username.
     */
    public String getPlayer() { return this.username; }

    @Override
    public int compareTo(@NonNull Score o) { return o.score - this.score; }
}