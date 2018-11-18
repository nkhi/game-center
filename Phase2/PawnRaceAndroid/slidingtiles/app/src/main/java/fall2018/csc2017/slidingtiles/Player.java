package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Stores a player and its relevant functions to make board evaluations...etc.
 */
public class Player implements Serializable {

    /**
     * Max possible moves
     */
    private static final int MAX_POSSIBLE_MOVES = 28;

    /**
     * Direction in which white moves
     */
    private static final int WHITE_DIRECTION = 1;

    /**
     * Direction in which black moves
     */
    private static final int BLACK_DIRECTION = -1;

    /**
     * Forwardness modifier constant
     */
    private static final int FORWARDNESS_MODIFIER = 10;

    /**
     * No adjacent pawn and protected modifier constant
     */
    private static final int NO_ADJACENT_PAWN_MODIFIER_PROTECTED = 5;

    /**
     * No adjacent pawn modifier constant
     */
    private static final int NO_ADJACENT_PAWN_MODIFIER = 3;

    /**
     * Guarding pawn modifier constant
     */
    private static final int GUARDING_MODIFIER = 3;

    /**
     * Protected modifier constant
     */
    private static final int PROTECTED_MODIFIER = 2;
    /**
     * Game currently being played
     */
    private Game game;
    /**
     * Board currently being played
     */
    private Board board;
    /**
     * Player's color
     */
    private Color color;
    /**
     * Player's opponent
     */
    private Player opponent;
    /**
     * Starting rank of player
     */
    private int startingRank;
    /**
     * Direction of player to advance in
     */
    private int direction;
    /**
     * Android two-step tap processing
     */
    private Square initialTap;
    /**
     * AI (if player is a computer player)
     */
    private MinimaxAI ai;

    /**
     * Initializes the player with relevant parameters
     *
     * @param game game to be played
     * @param board board of the game to be played
     * @param color color of the player
     * @param isComputerPlayer whether the player is played by the computer
     * @param difficulty difficulty of computer AI
     */
    Player(Game game, Board board, Color color, boolean isComputerPlayer, int difficulty) {
        this.game = game;
        this.board = board;
        this.color = color;
        if (color == Color.WHITE) {
            startingRank = Game.WHITE_STARTING_RANK;
            direction = WHITE_DIRECTION;
        } else {
            startingRank = Game.BLACK_STARTING_RANK;
            direction = BLACK_DIRECTION;
        }
        if (isComputerPlayer) {
            ai = new MinimaxAI(this, difficulty);
        }
    }

    /**
     * Return the player's opponent
     *
     * @return player's opponent
     */
    Player getOpponent() {
        return opponent;
    }

    /**
     * Sets the opponent
     *
     * @param opponent opponent of player
     */
    void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Return the game
     *
     * @return the game
     */
    Game getGame() {
        return game;
    }

    /**
     * Return the board
     *
     * @return the board
     */
    Board getBoard() {
        return board;
    }

    /**
     * Return the color of the player
     *
     * @return the player's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return whether the game is finished when it is the player's move
     * (takes into account stalemates)
     *
     * @return whether the game is finished when it is the player's turn
     */
    boolean isFinished() {
        return game.isFinished() || isStuck();
    }

    /**
     * Return whether the player has no moves
     *
     * @return whether the player has no moves
     */
    private boolean isStuck() {
        return getAllValidMoves()[0] == null;
    }

    /**
     * Get all the pawns that the player has
     *
     * @return all the pawns that the player has
     */
    private Square[] getAllPawns() {
        Square[] allPawns = new Square[Board.NUM_ROW_COL - 1];
        int num = 0;
        for (int i = 0; i < Board.NUM_ROW_COL; i++) {
            for (int j = 0; j < Board.NUM_ROW_COL; j++) {
                if (board.getSquare(i, j).occupiedBy() == color) {
                    allPawns[num] = board.getSquare(i, j);
                    num++;
                }
            }
        }
        return allPawns;
    }

    /**
     * Counts all of the player's pawns
     *
     * @return the number of pawns the player has
     */
    int getNumAllPawns() {
        int num = 0;
        for (int i = 0; i < Board.NUM_ROW_COL; i++) {
            for (int j = 0; j < Board.NUM_ROW_COL; j++) {
                if (board.getSquare(i, j).occupiedBy() == color) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * Return an array of all valid moves that the player can make
     *
     * @return an array of all valid moves that the player can make
     */
    Move[] getAllValidMoves() {
        Move[] moves = new Move[MAX_POSSIBLE_MOVES];
        Square[] allPawns = getAllPawns();
        int index = 0;
        Color c = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        int moveDirection = color == Color.WHITE ? WHITE_DIRECTION : BLACK_DIRECTION;
        int startRow = color == Color.WHITE ? Game.WHITE_STARTING_RANK : Game.BLACK_STARTING_RANK;

        for (int i = 0; i < (Board.NUM_ROW_COL - 1) && allPawns[i] != null; i++) {
            Square pawn = allPawns[i];
            int x = pawn.getX();
            int y = pawn.getY();
            if (y == startRow && board.getSquare(x, y + 2 * moveDirection).occupiedBy()
                    == Color.NONE && board.getSquare(x, y + moveDirection).occupiedBy()
                    == Color.NONE) {
                moves[index] = new Move(pawn, board.getSquare(x, y + 2 * moveDirection),
                        false, false);
                index++;
            }
            if (y != (Board.NUM_ROW_COL - 1) && y != 0 &&
                    board.getSquare(x, y + moveDirection).occupiedBy() == Color.NONE) {
                moves[index] = new Move(pawn, board.getSquare(x, y + moveDirection),
                        false, false);
                index++;
            }
            if (x != (Board.NUM_ROW_COL - 1) && y != (Board.NUM_ROW_COL - 1) && y != 0 &&
                    board.getSquare(x + 1, y + moveDirection).occupiedBy() == c) {
                moves[index] = new Move(pawn, board.getSquare(x + 1, y + moveDirection),
                        true, false);
                index++;
            }
            if (x != 0 && y != (Board.NUM_ROW_COL - 1) && y != 0 &&
                    board.getSquare(x - 1, y + moveDirection).occupiedBy() == c) {
                moves[index] = new Move(pawn, board.getSquare(x - 1, y + moveDirection),
                        true, false);
                index++;
            }
            if (game.getLastMove() != null) {
                Square opFrom = game.getLastMove().getFrom();
                Square opTo = game.getLastMove().getTo();
                int opStartingRank = color == Color.WHITE ?
                        Game.BLACK_STARTING_RANK : Game.WHITE_STARTING_RANK;
                if (opFrom.getY() == opStartingRank && y == opStartingRank - 2 * moveDirection
                        && y == opTo.getY()) {
                    if (opFrom.getX() == x - 1) {
                        moves[index] = new Move(pawn, board.getSquare(x - 1, y + moveDirection),
                                true, true);
                        index++;
                    } else if (opFrom.getX() == x + 1) {
                        moves[index] = new Move(pawn, board.getSquare(x + 1, y + moveDirection),
                                true, true);
                        index++;
                    }
                }
            }
        }

        return moves;
    }

    /**
     * Return whether the pawn on the square is a passed pawn
     * Precondition: the square must be populated by a player's pawn
     *
     * @param square square of the pawn to check
     * @return whether the pawn on the square is a passed pawn
     */
    private boolean isPassed(Square square) {
        int x = square.getX();
        int y = square.getY();
        if (square.occupiedBy() == Color.WHITE) {
            for (int j = y; j < Board.NUM_ROW_COL; j++) {
                if (board.getSquare(x, j).occupiedBy() == Color.BLACK) {
                    return false;
                }
                if (x != 0 && board.getSquare(x - 1, j).occupiedBy() == Color.BLACK) {
                    return false;
                }
                if (x != (Board.NUM_ROW_COL - 1) && board.getSquare(x + 1, j).occupiedBy()
                        == Color.BLACK) {
                    return false;
                }
            }
        } else {
            for (int j = y; j >= 0; j--) {
                if (board.getSquare(x, j).occupiedBy() == Color.WHITE) {
                    return false;
                }
            }
            if (x != 0) {
                for (int j = y; j >= 0; j--) {
                    if (board.getSquare(x - 1, j).occupiedBy() == Color.WHITE) {
                        return false;
                    }
                }
            }
            if (x != (Board.NUM_ROW_COL - 1)) {
                for (int j = y; j >= 0; j--) {
                    if (board.getSquare(x + 1, j).occupiedBy() == Color.WHITE) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return the number of protected pawns that the player has
     *
     * @return the number of protected pawns that the player has
     */
    int numProtectedPawns() {
        int count = 0;
        for (Square pawn : getAllPawns()) {
            if (pawn != null && isProtected(pawn)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Return whether the pawn on the square is protected
     * Precondition: the square must be populated by a player's pawn
     *
     * @param pawn square of the pawn to check
     * @return whether the pawn on the square is protected
     */
    private boolean isProtected(Square pawn) {
        int a = (pawn.occupiedBy() == Color.WHITE) ? 1 : -1;
        if (pawn.getX() != (Board.NUM_ROW_COL - 1)) {
            if (board.getSquare(pawn.getY() - a, pawn.getX() + 1).occupiedBy() == color)
                return true;
        }
        if (pawn.getX() != 0) {
            return board.getSquare(pawn.getY() - a, pawn.getX() - 1).occupiedBy() == color;
        }
        return false;
    }

    /**
     * Return whether the pawn on the square is guarded
     * Precondition: the square must be populated by a player's pawn
     *
     * @param pawn square of the pawn to check
     * @return whether the pawn on the square is guarded
     */
    private boolean guarding(Square pawn) {
        if (pawn.getX() != (Board.NUM_ROW_COL - 1)) {
            if (board.getSquare(pawn.getX() + 1, pawn.getY() + direction).occupiedBy()
                    == opponent.getColor()) {
                return true;
            }
        }
        if (pawn.getX() != 0) {
            return board.getSquare(pawn.getX() - 1, pawn.getY() + direction).occupiedBy()
                    == opponent.getColor();
        }
        return false;
    }

    /**
     * Return whether the pawn on the square has no adjacent pawns
     * Precondition: the square must be populated by a player's pawn
     *
     * @param pawn square of the pawn to check
     * @return whether the pawn on the square has no adjacent pawns
     */
    private boolean noAdjacentPawn(Square pawn) {
        int count = 0;
        for (int i = 0; i < Board.NUM_ROW_COL; i++) {
            if (pawn.getX() != Board.NUM_ROW_COL) {
                if (board.getSquare(pawn.getX() + 1, i).occupiedBy() == color) count++;
            }
            if (pawn.getX() != 0) {
                if (board.getSquare(pawn.getX() - 1, i).occupiedBy() == color) count++;
            }
        }
        return count >= 2;
    }

    /**
     * Calculates the "forwardness" of all the player's pawns by the "forwardness" algorithm"
     *
     * @return the "forwardness" score of all the player's pwans
     */
    int forwardness() {
        int forwardness = 0;
        for (Square pawn : getAllPawns()) {
            if (pawn != null) {
                forwardness += forwardnessOfPawn(pawn);
            }
        }
        return forwardness;
    }

    /**
     * Return the forwardness of a single pawn
     * Precondition: the square must be populated by a player's pawn
     *
     * @param pawn square of the pawn to check
     * @return the forwardness score of a single pawn
     */
    private int forwardnessOfPawn(Square pawn) {
        int forwardness = 0;
        forwardness += FORWARDNESS_MODIFIER * (direction * (pawn.getY() - startingRank));
        if (isPassed(pawn)) {
            forwardness *= FORWARDNESS_MODIFIER;
        }
        if (noAdjacentPawn(pawn) && isProtected(pawn)) {
            forwardness *= NO_ADJACENT_PAWN_MODIFIER_PROTECTED;
        } else if (noAdjacentPawn(pawn)) {
            forwardness *= NO_ADJACENT_PAWN_MODIFIER;
        }
        if (guarding(pawn)) {
            forwardness *= GUARDING_MODIFIER;
        }
        if (isProtected(pawn)) {
            forwardness *= PROTECTED_MODIFIER;
        }
        if (canBeCaptured(pawn) && !isProtected(pawn)) {
            forwardness = 0;
        } else if (canBeCaptured(pawn) && isProtected(pawn)) {
            forwardness /= PROTECTED_MODIFIER;
        }
        return forwardness;
    }

    /**
     * Return the number of semi-open files on the player's side
     *
     * @return the number of semi-open files on the player's side
     */
    int numSemiOpenFiles() {
        int count = 0;
        for (Square pawn : getAllPawns()) {
            if (pawn != null && semiOpenFile(pawn)) count++;
        }
        return count;
    }

    /**
     * Return whether the pawn on the square is on a semi-open file
     * Precondition: the square must be populated by a player's pawn
     *
     * @param pawn square of the pawn to check
     * @return whether the pawn on the square is on a semi-open file
     */
    private boolean semiOpenFile(Square pawn) {
        if (color == Color.WHITE) {
            for (int i = pawn.getY(); i < Board.NUM_ROW_COL; i++) {
                if (board.getSquare(pawn.getX(), i).occupiedBy() != Color.NONE) return false;
            }
            return true;
        } else {
            for (int i = pawn.getY(); i >= 0; i--) {
                if (board.getSquare(pawn.getX(), i).occupiedBy() != Color.NONE) return false;
            }
            return true;
        }
    }

    /**
     * Return whether the pawn on the square can be captured
     * Precondition: the square must be populated by a player's pawn
     *
     * @param pawn square of the pawn to check
     * @return whether the pawn on the square can be captured
     */
    private boolean canBeCaptured(Square pawn) {
        int a = (pawn.occupiedBy() == Color.WHITE) ? 1 : -1;
        if (pawn.getX() != 7) {
            if (board.getSquare(pawn.getY() + a, pawn.getX() + 1).occupiedBy()
                    == opponent.getColor())
                return true;
        }
        if (pawn.getX() != 0) {
            return board.getSquare(pawn.getY() + a, pawn.getX() - 1).occupiedBy()
                    == opponent.getColor();
        }
        return false;
    }

    /**
     * Return whether the player has a passed pawn
     *
     * @return whether the player has a passed pawn
     */
    boolean hasPassedPawn() {
        Square[] pawns = getAllPawns();
        for (int i = 0; i < getNumAllPawns(); i++) {
            if (isPassed(pawns[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the passed pawn if present
     *
     * @return the passed pawn if present (null otherwise)
     */
    Square getPassedPawn() {
        Square[] pawns = getAllPawns();
        for (int i = 0; i < getNumAllPawns(); i++) {
            if (isPassed(pawns[i])) {
                return pawns[i];
            }
        }
        return null;
    }

    /**
     * Allows the computer to make a move
     * Precondition: the game must not be finished and the player must be a computer player
     */
    private void computerMakeMove() {
        makeMove(ai.minimaxBestMove());
    }

    /**
     * Make a move and update the board on the interface
     *
     * @param move move to be made
     */
    private void makeMove(Move move) {
        game.applyMove(move);
        board.notifyObservers();
    }

    /**
     * Processes a tap on the board in a two-step process. First tap stores the potential move,
     * second tap applies the move to the target
     *
     * @param row the row of the square tapped
     * @param col the column of the square tapped
     * @return whether the tap was valid
     */
    boolean processTap(int row, int col) {
        if (game.getCurrentPlayer() == color) {
            if (initialTap != null) {
                Square secondTap = board.getSquare(row, col);
                Move move = getMove(initialTap, secondTap);
                initialTap = null;
                if (Arrays.asList(getAllValidMoves()).contains(move)) {
                    makeMove(move);
                    if (!isFinished()) {
                        getOpponent().computerMakeMove();
                    }
                    return true;
                }
            } else if (board.getSquare(row, col).occupiedBy() == color) {
                initialTap = board.getSquare(row, col);
                return true;
            }
        }
        return false;
    }

    /**
     * Parses a move from two squares
     *
     * @param from square to move from
     * @param to square to move to
     * @return the Move from the from square to the to square
     */
    private Move getMove(Square from, Square to) {
        boolean isCapture = false;
        boolean isEnPassantCapture = false;
        if (to.getX() != from.getX()) {
            isCapture = true;
            if (to.occupiedBy() == Color.NONE) {
                isEnPassantCapture = true;
            }
        }
        return new Move(from, to, isCapture, isEnPassantCapture);
    }

}

