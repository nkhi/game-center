package fall2018.csc2017.game_center.pawnrace;

import java.io.Serializable;

/**
 * Stores the information needed to make a move
 */
public class PRMove implements Serializable {

    /**
     * The square to move from
     */
    private PRSquare from;

    /**
     * The square to move to
     */
    private PRSquare to;

    /**
     * Whether the move is a capture
     */
    private boolean isCapture;

    /**
     * Whether the move is an en-passant capture (refer to standard chess rules)
     * if true, isCapture must also be true
     */
    private boolean isEnPassantCapture;

    /**
     * Initializes a move with the relevant information
     *
     * @param from               square to move from
     * @param to                 square to move to
     * @param isCapture          whether the move is a capture
     * @param isEnPassantCapture whether the move is an en-passant capture
     */
    PRMove(PRSquare from, PRSquare to, boolean isCapture, boolean isEnPassantCapture) {
        this.from = from;
        this.to = to;
        this.isCapture = isCapture;
        this.isEnPassantCapture = isEnPassantCapture;
    }

    /**
     * Return the from square
     *
     * @return the square to move from
     */
    public PRSquare getFrom() {
        return from;
    }

    /**
     * Return the to square
     *
     * @return the square to move to
     */
    public PRSquare getTo() {
        return to;
    }

    /**
     * Return whether the move is a capture
     *
     * @return whether the move is a capture
     */
    boolean isCapture() {
        return isCapture;
    }

    /**
     * Return whether the move is an en-passant capture
     *
     * @return whether the move is an en-passant capture
     */
    boolean isEnPassantCapture() {
        return isEnPassantCapture;
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PRMove)) {
            return false;
        }
        return (from.equals(((PRMove) obj).from)) && (to.equals(((PRMove) obj).to)) &&
                (isCapture == ((PRMove) obj).isCapture) &&
                (isEnPassantCapture == ((PRMove) obj).isEnPassantCapture);
    }
}