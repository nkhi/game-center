package fall2018.csc2017.slidingtiles;

import java.io.Serializable;

/**
 * AI to calculate the best available move for the computer - implemented with a minimax algorithm
 * This is my own implementation that I (Robert Tan) created in winter 2017 for a pawn race AI
 * challenge at Imperial College London, which placed third out of ~30 entries
 */
class MinimaxAI implements Serializable {

    /**
     * Maximum search depth for a dynamically determined depth
     */
    private static final int MAX_DYNAMIC_DEPTH = 10;

    /**
     * Minimum index of moves to begin dynamic depth calculation
     */
    private static final int MIN_INDEX_DYNAMIC_DEPTH = 2;

    /**
     * Dynamic depth incrementer
     */
    private static final int DYNAMIC_DEPTH_INCREMENTER = 2;

    /**
     * Num pawn score
     */
    private static final int NUM_PAWN_SCORE = 10000;

    /**
     * Protected pawn score
     */
    private static final int PROTECTED_PAWN_SCORE = 8000;

    /**
     * Forwardness & semi-open file score
     */
    private static final int FORWARDNESS_SEMI_OPEN_SCORE = 10;

    /**
     * Passed pawn score
     */
    private static final int PASSED_PAWN_SCORE = 100000;

    /**
     * Player of the computer player to calculate moves
     */
    private Player player;

    /**
     * Starting calculation depth
     */
    private int depth;

    /**
     * Moves index to determine dynamic depth calculation
     */
    private int index;

    /**
     * Initializes a minimax AI with a player and depth
     *
     * @param player player to calculate for
     * @param depth initial depth of calculation
     */
    MinimaxAI(Player player, int depth) {
        this.player = player;
        this.depth = depth;
        index = 0;
    }

    /**
     * Return the best move as calculated by the dynamic depth minimax AI
     *
     * @return the best move as calculated by the dynamic depth minimax AI
     */
    Move minimaxBestMove() {
        Move move = minimaxBestMove(depth);
        if (depth < MAX_DYNAMIC_DEPTH && index > MIN_INDEX_DYNAMIC_DEPTH) {
            depth += DYNAMIC_DEPTH_INCREMENTER;
        }
        return move;
    }

    /**
     * Return the best move calculated by the depth given
     *
     * @param depth depth of calculation (in moves)
     * @return the best move calculated by the depth given
     */
    private Move minimaxBestMove(int depth) {
        return minimax(depth, player.getColor(), Integer.MIN_VALUE, Integer.MAX_VALUE).getValue();
    }

    /**
     * Recursive minimax algorithm with alpha-beta pruning that loops through all possible moves
     *
     * @param depth depth of moves still to be calculated
     * @param c color of currently moving player
     * @param alpha alpha-beta pruning alpha score
     * @param beta alpha-beta pruning beta score
     * @return the best move and its value for this particular calculation step
     */
    private MinimaxKeyVal minimax(int depth, Color c, int alpha, int beta) {
        int score;
        int bestScore;
        Color opColor = (c == Color.WHITE) ? Color.BLACK : Color.WHITE;
        Player currentPlayer;
        if (player.getColor() == c) {
            currentPlayer = player;
        } else {
            currentPlayer = player.getOpponent();
        }
        Move[] validMoves = currentPlayer.getAllValidMoves();
        Move bestMove = validMoves[0];
        if (depth == 0 || player.getGame().isFinished()) {
            bestScore = evaluateBoard();
        } else {
            for (Move move : validMoves) {
                if (move != null) {
                    player.getGame().applyMove(move);
                    score = minimax(depth - 1, opColor, alpha, beta).getKey();
                    if (player.getColor() == c) {
                        if (score > alpha) {
                            alpha = score;
                            bestMove = move;
                        }
                    } else {
                        if (score < beta) {
                            beta = score;
                            bestMove = move;
                        }
                    }
                    player.getGame().unapplyMove();
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
            bestScore = (c == player.getColor()) ? alpha : beta;
        }
        return new MinimaxKeyVal(bestScore, bestMove);
    }

    /**
     * Evaluation function for the minimax algorithm to determine the best possible move.
     * Takes into account whether:
     *  - Game is finished
     *  - Number of pawns each player possesses
     *  - Number of protected pawns each player possesses
     *  - Number of semi-open files each player possesses
     *  - Forwardness of player's pawns
     *  - Whether a player has a passed pawn and how likely that pawn is to get to the end of the
     *  board
     *
     * @return an integer score of the board's current state
     */
    private int evaluateBoard() {
        // TODO: Add pawn chain, !!controls opponents spaces!!, zugzwang, blocking move formulas
        int score = 0;

        if (player.isFinished() && player.getGame().getGameResult() == player.getColor()) {
            return Integer.MAX_VALUE;
        }
        if (player.isFinished() && player.getGame().getGameResult() == player.getOpponent()
                .getColor()) {
            return Integer.MIN_VALUE;
        }
        if (player.isFinished() && player.getGame().getGameResult() == Color.NONE) {
            return 0;
        }

        score += NUM_PAWN_SCORE * player.getNumAllPawns() -
                NUM_PAWN_SCORE * player.getOpponent().getNumAllPawns();
        score += PROTECTED_PAWN_SCORE * player.numProtectedPawns() -
                PROTECTED_PAWN_SCORE * player.getOpponent().numProtectedPawns();
        score += FORWARDNESS_SEMI_OPEN_SCORE * player.forwardness() -
                FORWARDNESS_SEMI_OPEN_SCORE * player.getOpponent().forwardness();
        score += FORWARDNESS_SEMI_OPEN_SCORE * player.numSemiOpenFiles() -
                FORWARDNESS_SEMI_OPEN_SCORE * player.getOpponent().numSemiOpenFiles();

        if (player.hasPassedPawn()) {
            score += PASSED_PAWN_SCORE;
        }
        if (player.getOpponent().hasPassedPawn()) {
            score -= PASSED_PAWN_SCORE;
        }
        if (player.hasPassedPawn() && player.getOpponent().hasPassedPawn()) {
            Square passedPawn = player.getPassedPawn();
            Square opPassedPawn = player.getOpponent().getPassedPawn();
            if (player.getColor() == Color.BLACK) {
                if (passedPawn.getY() < (Board.NUM_ROW_COL - 1) - opPassedPawn.getY()) {
                    score += PASSED_PAWN_SCORE;
                } else if (passedPawn.getY() > (Board.NUM_ROW_COL - 1) - opPassedPawn.getY()) {
                    score -= PASSED_PAWN_SCORE;
                } else {
                    if (player.getGame().getCurrentPlayer() == Color.BLACK) {
                        score += PASSED_PAWN_SCORE;
                    } else {
                        score -= PASSED_PAWN_SCORE;
                    }
                }
            } else if (player.getColor() == Color.WHITE) {
                if ((Board.NUM_ROW_COL - 1) - passedPawn.getY() < opPassedPawn.getY()) {
                    score += PASSED_PAWN_SCORE;
                } else if ((Board.NUM_ROW_COL - 1) - passedPawn.getY() > opPassedPawn.getY()) {
                    score -= PASSED_PAWN_SCORE;
                } else {
                    if (player.getGame().getCurrentPlayer() == Color.WHITE) {
                        score += PASSED_PAWN_SCORE;
                    } else {
                        score -= PASSED_PAWN_SCORE;
                    }
                }
            }
        }
        return score;
    }

    /**
     * Private class for storing the move and its minimax score
     */
    private class MinimaxKeyVal implements Serializable {

        /**
         * Score of move
         */
        private int key;

        /**
         * Move calculated
         */
        private Move val;

        /**
         * Initializes a move with a minimax value
         * @param key score of move
         * @param val move calculated
         */
        MinimaxKeyVal(int key, Move val) {
            this.key = key;
            this.val = val;
        }

        /**
         * Return the score of the move
         *
         * @return the score of the move
         */
        int getKey() {
            return key;
        }

        /**
         * Return the move stored
         *
         * @return the move stored
         */
        Move getValue() {
            return val;
        }
    }

}
