package cs3500.animator.util;

/**
 * Represent the Builder to construct animator Model.
 *
 * @param <Doc> represent the placeholder of a model
 */
public interface AnimationBuilder<Doc> {

  /**
   * Constructs a final document.
   *
   * @return the newly constructed document
   */
  Doc build();


  /**
   * Specify the bounding box to be used for the animation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> setBounds(int x, int y, int width, int height);

  /**
   * Adds a new shape to the growing document.
   *
   * @param name The unique name of the shape to be added. No shape with this name should already
   *             exist.
   * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
   *             shapes is unspecified, but should include "ellipse" and "rectangle" as a minimum.
   * @param layer The position of the layer in the animation.
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> declareShape(String name, String type, int layer);

  /**
   * Adds a transformation to the growing document.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param ints The list of document details.
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> addMotion(String name, Integer[] ints);

  /**
   * Adds an individual keyframe to the growing document.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   * @return This {@link AnimationBuilder}
   */
  AnimationBuilder<Doc> addKeyframe(String name,
                                    int t, int x, int y, int w, int h, int r, int g, int b);
}
