package cs3500.animator.view;

/**
 * Represent available command for user to modify the Excellence Animator Model.
 */
public enum KeyframeEdit {
  ADD_SHAPE,
  DELETE_SHAPE,
  ADD_KEYFRAME,
  REMOVE_KEYFRAME,
  MODIFY_KEYFRAME,
  ADD_LAYER,
  SWAP_LAYER,
  DELETE_LAYER;


  @Override
  public String toString() {
    switch (this) {
      case ADD_SHAPE:
        return "addShape";
      case DELETE_SHAPE:
        return "deleteShape";
      case ADD_KEYFRAME:
        return "addKeyFrame";
      case MODIFY_KEYFRAME:
        return "modifyKeyFrame";
      case REMOVE_KEYFRAME:
        return "removeKeyFrame";
      case ADD_LAYER:
        return "addLayer";
      case SWAP_LAYER:
        return "swapLayer";
      case DELETE_LAYER:
        return "deleteLayer";
      default:
        throw new IllegalArgumentException("not a valid command");
    }
  }
}
