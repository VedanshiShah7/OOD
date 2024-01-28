package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * Manages UI in which what component of the PopUp panel is visible, and setting the ActionListener
 * for each component, and also parse in user input to the controller.
 */
public interface IPopUp {

  /**
   * represent a method in which it would summaries all the key frame data to be pass to controller.
   *
   * @param command for which one to make visible.
   */
  void makeVisible(String command);

  /**
   * Set the action listener for all of the component inside the pop up window.
   *
   * @param a represent a action listener.
   */
  void setActionListener(ActionListener a);

  /**
   * Get the name from the user text field.
   *
   * @return name of the shape
   */
  String getName();

  /**
   * Get the shape data that is enter by the client.
   *
   * @return position dimension and color of the shape
   */
  Integer[] getShapeState();
}
