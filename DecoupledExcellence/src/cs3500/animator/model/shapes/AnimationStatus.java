package cs3500.animator.model.shapes;

/**
 * Representing the State of Animation as an enum.
 */
public enum AnimationStatus {
  MOVE, COLOR, SIZE, GAP, ROTATE;


  AnimationStatus() {
  }

  @Override
  public String toString() {
    switch (this) {
      case MOVE:
        return "move";
      case COLOR:
        return "color";
      case GAP:
        return "gap";
      case SIZE:
        return "size";
      case ROTATE:
        return "rotate";
      default:
        throw new IllegalArgumentException("No Match Animation types");
    }
  }
}
