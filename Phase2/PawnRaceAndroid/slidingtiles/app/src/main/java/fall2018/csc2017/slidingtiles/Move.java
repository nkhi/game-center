package fall2018.csc2017.slidingtiles;

public class Move {

  private Square from;
  private Square to;
  private boolean isCapture;
  private boolean isEnPassantCapture;

  public Move(Square from, Square to, boolean isCapture, boolean isEnPassantCapture) {
    this.from = from;
    this.to = to;
    this.isCapture = isCapture;
    this.isEnPassantCapture = isEnPassantCapture;
  }

  public Square getFrom() {
    return from;
  }

  public Square getTo() {
    return to;
  }

  public boolean isCapture() {
    return isCapture;
  }

  public boolean isEnPassantCapture() {
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