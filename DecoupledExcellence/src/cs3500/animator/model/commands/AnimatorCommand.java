package cs3500.animator.model.commands;

import cs3500.animator.model.shapes.ShapesModel;

/**
 * Represent Command Object in which it will be used to execute commands in the ExCELIence
 * Controller. This will allow to us to execute command as a list and also validate user input to
 * check if there is conflicted/overlap command.
 */
public interface AnimatorCommand {

  /**
   * Use to execute the command object which are support in Shapes Model.
   *
   * @param m represent specific shapes object.
   */
  void run(ShapesModel m);

  /**
   * Use to update the clock of this animator object.
   *
   * @param clock represent specific shapes object.
   */
  void updateClock(double clock);

  /**
   * Determine if this animatorCommand motion is over.
   *
   * @param clock represent specific shapes object.
   */
  boolean motionOver(double clock);

  /**
   * Get the start time of the animation.
   *
   * @return the start time of the animation.
   */
  double getStart();

  /**
   * Get the stop time of the animation.
   *
   * @return the stop time of the animation.
   */
  double getStop();

  /**
   * Get the duration of the animation.
   *
   * @return the duration of the animation.
   */
  double getDuration();

  /**
   * Get the color code for the change color animation.
   *
   * @return the color of the change color animation
   */
  int[] getColor();

  /**
   * Get the color code for the change dimension animation.
   *
   * @return the color of the change dimension animation
   */
  int[] getDimension();

  /**
   * Get the rotation for the changed rotation animation.
   *
   * @return the rotation of the changed rotation animation.
   */
  double[] getRotation();

  /**
   * Get the color code for the change Position animation.
   *
   * @return the color of the change position animation
   */
  int[] getPosition();

  /**
   * Determine if the given command is the game as this command.
   *
   * @param that represent client given command
   * @return true if the given command is the same as the object
   */
  boolean sameCommand(AnimatorCommand that);


  /**
   * Determine if the given move is the game as this move.
   *
   * @param that represent client given command
   * @return true if the given command is the same as the object
   */
  boolean sameMove(Move that);

  /**
   * Determine if the given size is the game as this size.
   *
   * @param that represent client given command
   * @return true if the given command is the same as the object
   */
  boolean sameSize(ChangeSize that);

  /**
   * Determine if the given color is the same as this color.
   *
   * @param that represent client given command
   * @return true if the given command is the same as the object
   */
  boolean sameColor(ChangeColor that);

  /**
   * Determine if the given rotation is the same as this rotation.
   *
   * @param that represents the command passed by the client.
   * @return true if the given command is the same as the object.
   */
  boolean sameRotate(Rotate that);
}
