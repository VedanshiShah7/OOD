package cs3500.animator.view;

/**
 * Represent the Functionality that is allowed for the user to enter.
 */
public enum Functionality {
  START,
  PAUSE,
  RESUME,
  RESTART,
  ENABLE_LOOPING,
  DISABLE_LOOPING,
  INCREASE_SPEED,
  DECREASE_SPEED;

  Functionality() {
  }

  @Override
  public String toString() {
    switch (this) {
      case START:
        return "start";
      case PAUSE:
        return "pause";
      case RESUME:
        return "resume";
      case RESTART:
        return "restart";
      case ENABLE_LOOPING:
        return "enable_looping";
      case DISABLE_LOOPING:
        return "disable_looping";
      case INCREASE_SPEED:
        return "increase_speed";
      case DECREASE_SPEED:
        return "decrease_speed";
      default:
        throw new IllegalArgumentException("No Match Animation types");
    }
  }
}
