package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.ShapesModel;

import java.awt.event.ActionListener;

/**
 * Representing Textual summary view of the Animation. Represents the animation as a txt. Implements
 * the animatorView interface and implements all its methods.
 */
public class AnimationTextView implements AnimatorView {

  private AnimatorModel model;
  private StringBuilder output;
  private int leftMost;
  private int topMost;
  private int width;
  private int height;

  /**
   * Represent the default constructor for Animation Text View.
   *
   * @param model represent the model to be animate.
   */
  public AnimationTextView(AnimatorModel model) {
    this.model = model;
    this.output = new StringBuilder();
    this.leftMost = this.model.getLeftMost();
    this.topMost = this.model.getTopMost();
    this.width = this.model.getWidth();
    this.height = this.model.getHeight();
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("This is a textual representation");
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("This is a textual representation");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("This is a textual representation");
  }

  @Override
  public void setCommandListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This is a textual representation");
  }

  @Override
  public void createAnimationOutput() {
    String shapeType = "";
    output.append(String.format("canvas: %s %s %s %s\n",
            this.leftMost, this.topMost, this.width, this.height));

    for (ShapesModel s : this.model.getShapes()) {
      if (s instanceof Oval) {
        shapeType = "Ellipse";
      } else {
        shapeType = "Rectangle";
      }

      output.append(String.format("Shape %s %s \n", s.getName(),
              shapeType));
      this.model.runAnimation();
      output.append(String.format("%s\n", this.model.getAnimationStateShape(s)));
    }
  }

  @Override
  public Appendable getOutput() {
    return this.output;
  }

  @Override
  public void changeTickSpeed(int framePerSecond) {
    throw new UnsupportedOperationException("This is a textual representation");
  }
}
