package cs3500.animator.model;

import cs3500.animator.model.shapes.ShapesModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Manage the interaction between all the shapes...
 */
public interface AnimatorModel {

  /**
   * Adding the corresponding shapes to this Animation World.
   *
   * @param s represent the given Shapes model which will be given through controller when client
   *          instantiate a shapes
   */
  void addShape(ShapesModel s);

  /**
   * Remove the corresponding shapes to this Animation World.
   *
   * @param s represent the given Shapes model
   * @throws IllegalArgumentException if the shapes is existed in Animation
   */
  void removeShape(ShapesModel s);

  /**
   * Give the client an ability to move corresponding shapes inside this animation world.
   *
   * @param s     represent the desire shapes/object to move
   * @param fromX represent the old horizontal parameter of the coordinate system
   * @param fromY represent the old vertical parameter of the coordinate system
   * @param toX   represent the desire x coordinate
   * @param toY   represent the desire y coordinate
   * @param start represent the desire start time of the animation
   * @param end   represent the desire stop time of the animation
   * @throws IllegalArgumentException if the shapes is existed in Animation
   */
  void moveShape(ShapesModel s, int fromX, int fromY, int toX, int toY, double start, double end);

  /**
   * Gives the client the ability to change the color of the shape provided.
   *
   * @param s     represents one of the shapes.
   * @param fromR represents the initial red color.
   * @param fromG represents the initial green color.
   * @param fromB represents the initial blue color.
   * @param toR   represents the desire red color.
   * @param toG   represents the desire green color.
   * @param toB   represents the desire blue color.
   * @param start represents the start time.
   * @param stop  represents the end time.
   * @throws IllegalArgumentException if the shapes is existed in Animation
   */
  void changeColor(ShapesModel s, int fromR, int fromG, int fromB, int toR, int toG, int toB,
                   double start, double stop);

  /**
   * Gives the client the ability to change the size of the shape provided.
   *
   * @param s     represents one of the shapes.
   * @param fromW represents the initial width of the shape.
   * @param fromH represents the initial height of the shape.
   * @param toW   represents the width of the shape.
   * @param toH   represents the height of the shape.
   * @param start represents the start time.
   * @param stop  represents the end time.
   * @throws IllegalArgumentException if the shapes is existed in Animation
   */
  void changeSize(ShapesModel s, int fromW, int fromH, int toW, int toH, double start, double stop);

  /**
   * Gives the client the ability to rotate the shape provided.
   *
   * @param s          represents one of the shapes.
   * @param fromAngle  represents the initial angle of the shape.
   * @param fromXPivot represents the initial x pivot of the shape.
   * @param fromYPivot represents the initial y pivot of the shape.
   * @param toAngle    represents the desired angle of the shape.
   * @param toxPivot   represents the desired x pivot of the shape.
   * @param toyPivot     represents the desired y pivot of the shape.
   * @param start      represents the start time.
   * @param end        represents the end time.
   * @throws IllegalArgumentException if the shapes is existed in Animation
   */
  void rotate(ShapesModel s, double fromAngle, double fromXPivot, double fromYPivot,
              double toAngle, double toxPivot, double toyPivot, double start, double end);

  /**
   * Gets the animation state of the shape given.
   *
   * @param s represents one the shapes.
   * @return the animated state.
   * @throws IllegalArgumentException if the shapes is existed in Animation
   */
  String getAnimationStateShape(ShapesModel s);

  /**
   * Gets the animation state of all the objects.
   *
   * @return the animation state with all the objects.
   */
  String getAnimationState();

  /**
   * Return all the shapes in this animation.
   *
   * @return the all of the shapes in the representation
   */
  ArrayList<ShapesModel> getShapes();

  /**
   * Return a shapes with a given corresponding name.
   *
   * @return the shapes that match the given name
   */
  ShapesModel findShape(String name);


  /**
   * Given the client ability to insert a desire keyframe which composed of a state of the object.
   *
   * @param model  represent the shapes in which the client desire to modify
   * @param x      represent the x coordinate at given time
   * @param y      represent the y coordinate at given time
   * @param width  represent the width at given time
   * @param height represent the height at given time
   * @param r      represent the r color at given time
   * @param g      represent the g color at given time
   * @param b      represent the b color at given time
   * @param time   represent the time in which client desire to modified
   */
  void insertKeyFrame(ShapesModel model, Integer x, Integer y, Integer width, Integer height,
                      Integer r, Integer g, Integer b, Integer time);

  /**
   * Given the client ability to edit a desire keyframe which composed of a state of the object.
   *
   * @param model  represent the shapes in which the client desire to modify
   * @param x      represent the x coordinate at given time
   * @param y      represent the y coordinate at given time
   * @param width  represent the width at given time
   * @param height represent the height at given time
   * @param r      represent the r color at given time
   * @param g      represent the g color at given time
   * @param b      represent the b color at given time
   * @param time   represent the time in which client desire to modified
   */
  void editKeyFrame(ShapesModel model, Integer x, Integer y, Integer width, Integer height,
                    Integer r, Integer g, Integer b, Integer time);

  /**
   * Given the client ability to remove a desire keyframe which composed of a state of the object.
   *
   * @param model represent the shapes in which the client desire to modify
   * @param time  represent the time in which client desire to modified
   */
  void removeKeyFrame(ShapesModel model, Integer time);

  /**
   * Determine if the animation is over.
   */
  boolean isOver();

  /**
   * Run the animation.
   */
  void runAnimation();

  /**
   * Update the Clock such that every object has a same clock time.
   */
  void updateClock();

  /**
   * Get the current clock time.
   */
  double getClock();

  /**
   * Get the current width.
   */
  int getWidth();

  /**
   * Get the current height.
   */
  int getHeight();

  /**
   * Get the current LeftMost.
   */
  int getLeftMost();

  /**
   * Get the current LeftMost.
   */
  int getTopMost();

  /**
   * Adds the given shape to the provided layer.
   *
   * @param s represents the shape provided.
   * @param layer represents the layer number on which the shape must exist.
   */
  void addShapeToLayer(ShapesModel s, int layer);

  /**
   * Swaps the two layers provided.
   *
   * @param fromLayer layer to swap.
   * @param toLayer   layer to be swapped with.
   */
  void swapLayer(int fromLayer, int toLayer);

  /**
   * Deletes the given layer.
   *
   * @param layer the layer number to be deleted.
   */
  void deleteLayer(int layer);

  /**
   * Gets all the layers.
   *
   * @return all the layers.
   */
  LinkedHashMap<Integer, ArrayList<String>> getLayers();
}
