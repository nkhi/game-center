package fall2018.csc2017.slidingtiles;

/**
 * Stores the information needed to make a move
 */
public class Move {

    /**
     * The square to move from
     */
    private Square from;

    /**
     * The square to move to
     */
    private Square to;

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
     * @param from square to move from
     * @param to square to move to
     * @param isCapture whether the move is a capture
     * @param isEnPassantCapture whether the move is an en-passant capture
     */
    Move(Square from, Square to, boolean isCapture, boolean isEnPassantCapture) {
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
    public Square getFrom() {
        return from;
    }

    /**
     * Return the to square
     *
     * @return the square to move to
     */
    public Square getTo() {
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
        if (!(obj instanceof Move)) {
            return false;
        }
        return (from.equals(((Move) obj).from)) && (to.equals(((Move) obj).to)) &&
                (isCapture == ((Move) obj).isCapture) &&
                (isEnPassantCapture == ((Move) obj).isEnPassantCapture);
    }
}