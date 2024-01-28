package cs3500.animator.model.shapes;

import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.commands.Rotate;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface specifies the operation for Shapes Animation for each KeyFrame
 * <p> A Shapes is characterized by the size, 2D position and its color, and the animation aspect
 * of this shapes will be constructed via updating ticks</p> It can be asked to animate the shapes
 * through various command specify below to produce a change by each keyframe via updating
 * AnimatorModel clock and tick speed.
 */
public interface ShapesModel {

  /**
   * Move the Shapes by specifying new coordinates, using cartesian coordinate system.
   *
   * @param fromX represent the old horizontal parameter of the coordinate system
   * @param fromY represent the old vertical parameter of the coordinate system
   * @param toX   represent the new horizontal parameter of the coordinate system
   * @param toY   represent the new vertical parameter of the coordinate system
   * @param start represent in which the time for such animation start
   * @param stop  represent in which the time for such animation stop
   * @param clock represent the current time in animation
   * @throws IllegalArgumentException if the there is an overlap motion of duration is zero
   */
  void move(int fromX, int fromY, int toX, int toY, double start, double stop, double clock);


  /**
   * Change the dimension of the Shapes by specifying a new dimension.
   *
   * @param fromW represent the initial width component of the shapes
   * @param fromH represent the initial height component of the shapes
   * @param toW   represent the width component of the shapes
   * @param toH   represent the height component of the shapes
   * @param start represent in which the time for such animation start
   * @param stop  represent in which the time for such animation stop
   * @param clock represent the current time in animation
   * @throws IllegalArgumentException if the there is an overlap motion of duration is zero
   */
  void changeSize(int fromW, int fromH, int toW, int toH, double start, double stop, double clock);

  /**
   * Change the color of the Shapes by specifying a new color through RGB code.
   *
   * @param fromR represent the initial r component of the RGB code
   * @param fromG represent the initial g component of the RGB code
   * @param fromB represent the initial b component of the RGB code
   * @param toR   represent the desire r component of the RGB code
   * @param toG   represent the desire g component of the RGB code
   * @param toB   represent the desire b component of the RGB code
   * @param start represent in which the time for such animation start
   * @param stop  represent in which the time for such animation stop
   * @param clock represent the current time in animation
   * @throws IllegalArgumentException if the there is an overlap motion of duration is zero
   */
  void changeColor(int fromR, int fromG, int fromB, int toR, int toG, int toB, double start,
      double stop, double clock);

  /**
   * Change the angle of the shape by specifying a new angle and pivot (Rotation).
   *
   * @param start represents the start time of the animation.
   * @param stop represents the stop time for the animation.
   * @param clock represents the current time in the animation
   * @param fromAngle represents the initial angle component of the Rotation.
   * @param fromPivotX represents the initial x pivot component of the Rotation.
   * @param fromPivotY represents the initial y pivot component of the Rotation.
   * @param toAngle represents the desired angle component of the Rotation.
   * @param toPivotX represents the desired x pivot component of the Rotation.
   * @param toPivotY represents the desired y pivot component of the Rotation.
   */
  void rotate(double start, double stop, double clock, double fromAngle, double fromPivotX,
              double fromPivotY, double toAngle, double toPivotX, double toPivotY);

  /**
   * Obtain summary of animation through String output.
   *
   * @param clock represent the current time in animation
   * @return return String output that represent summary of an animation
   */
  String getAnimationState(double clock);

  /**
   * Save the animation state such as its position color and dimension whenever there is a change.
   *
   * @param clock represent the current time in animation
   */
  void saveAnimation(double clock);

  /**
   * Add motion to this shape.
   *
   * @param a represent the Animator Command that client wish to add to the shapes
   */
  void addMotion(AnimatorCommand a);

  /**
   * Remove the motion from the shapes.
   *
   * @param a represent the Animator Command that client wish to remove from the shapes
   */
  void removeMotion(AnimatorCommand a);

  /**
   * Update the clock for motion command.
   */
  void updateAnimatorClock(double clock);

  /**
   * Determine if the motion on this shapes is over.
   */
  boolean motionOver(double clock);

  /**
   * Run the animation for that shape.
   */
  void runAnimation();

  /**
   * Obtain the position of the Shape.
   *
   * @return the position of the shape
   */
  CartPosition getPosition();

  /**
   * Obtain the color of the Shape.
   *
   * @return the color of the shape
   */
  RGBColor getColor();

  /**
   * Obtain the Dimension of the Shape.
   *
   * @return the dimension of the shape
   */
  Dimension getDimension();

  /**
   * Obtain the Rotation of the Shape.
   *
   * @return the Rotation of the Shape.
   */
  Rotation getRotation();

  /**
   * Obtain the Name of the Shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Obtain all of the command associate with a shape.
   *
   * @return all of the command motion with the shape
   */
  ArrayList<AnimatorCommand> getCommands();

  /**
   * Obtain all of the move command associate with a shape.
   *
   * @return all of the move command motion with the shape
   */
  ArrayList<Move> getMoveCommands();

  /**
   * Obtain all of the size command associate with a shape.
   *
   * @return all of the size command motion with the shape
   */
  ArrayList<ChangeSize> getSizeCommands();


  /**
   * Obtain all of the color command associate with a shape.
   *
   * @return all of the color command motion with the shape
   */
  ArrayList<ChangeColor> getColorCommands();

  /**
   * Obtain all of the rotate commands associated with a shape.
   *
   * @return all of the rotate commands associated with a shape.
   */
  ArrayList<Rotate> getRotateCommands();

  /**
   * Returns the frame.
   *
   * @return the frame.
   */
  HashMap<Double, Double[]> getFrame();
}
