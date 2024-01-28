package cs3500.animator.view;

import java.awt.event.ActionListener;

/**
 * The view interface. Display the Animation and manages to listener operation, and interactive UI.
 * These are the methods that the controller would need to invoke on the view.
 */
public interface AnimatorView {

  /**
   * Make the view visible to the client.
   */
  void makeVisible();


  /**
   * Transmit an error message to the client, in case animation command is not properly set.
   *
   * @param error String that represent the error
   */
  void showErrorMessage(String error);

  /**
   * Refresh the view of the animation.
   */
  void refresh();


  /**
   * Provide the view with an action listener.
   *
   * @param actionEvent represent the actionListener
   */
  void setCommandListener(ActionListener actionEvent);


  /**
   * Writes a console output of the Animator Model.
   */
  void createAnimationOutput();

  /**
   * Gets the output of the Animator Output for text and SVG view.
   *
   * @return String the output to be written in the file output
   */
  Appendable getOutput();

  /**
   * Allow client to modify the tick speed for the animation.
   *
   * @param framePerSecond represent the number of tick per second
   */
  void changeTickSpeed(int framePerSecond);


}
