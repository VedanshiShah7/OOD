package cs3500.animator.view;

import java.util.Map;

/**
 * Manage Operation for the Editor View while maintain backward compatibility with the AnimatorView.
 * It extends the Animator view interface through which it displays the Animation and manages to
 * listener operation, and interactive UI. These are the methods that the controller would need to
 * invoke on the view. It also include operation in which to controller is used to manipulate the
 * view such as start, resume, restart, loop, add shape, remove shape, and manages key frame
 * operation UI.
 */
public interface IEditorView extends AnimatorView {

  /**
   * represent a method in which to control the panel timer.
   */
  void startStopTimer();

  /**
   * represent a method in which is used to restart the time of the panel.
   */
  void restartTimer();

  /**
   * represent a method that would toggle on-off the loop status of the animation.
   */
  void setLoopStatus();

  /**
   * represent a method that would allow user to increase or decrease the speed of the animation.
   *
   * @param command represent a command that is increase or decrease the speed
   */
  void setSpeed(String command);

  /**
   * represent a method in which would set the create shape manager panel visible.
   */
  void createShape();

  /**
   * represent a method in which would set the remove shape manager panel visible.
   */
  void removeShape();

  /**
   * represent a method in which would set the add key frame panel visible.
   */
  void addKeyFrame();

  /**
   * represent a method in which would set the edit key frame panel visible.
   */
  void editKeyFrame();

  /**
   * represent a method in which would set the remove key frame panel visible.
   */
  void removeKeyFrame();

  /**
   * represents a method that sets the add layer panel visible.
   */
  void addLayer();

  /**
   * represents a method that sets the swap layer panel visible.
   */
  void swapLayer();

  /**
   * represents a method that sets the delete layer panel visible.
   */
  void deleteLayer();

  /**
   * represent a method in which it would summarizes all the shape data to be pass to controller.
   * @return shape data with associated name
   */
  Map<String, Integer[]> getShapeData();

  /**
   * represent a method in which it would summaries all the key frame data to be pass to controller.
   * @return key frame data with associated name.
   */
  Map<String, Integer[]> getKeyFrameData();

  /**
   * Represents a method in which it would summarize all the layer data to pass to the controller.
   *
   * @return layer data with the associated name.
   */
  Map<String, Integer[]> getLayerData();
}
