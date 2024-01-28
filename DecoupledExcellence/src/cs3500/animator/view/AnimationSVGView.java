package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.ShapesModel;

import java.awt.event.ActionListener;


/**
 * SVG representation of the file. Represents the animated view as an SVG. Implements the
 * AnimatorView and all its methods.
 */
public class AnimationSVGView implements AnimatorView {

  private AnimatorModel model;
  private StringBuilder output;
  private int leftMost;
  private int topMost;
  private int width;
  private int height;
  private int framePerSecond;

  /**
   * Represent the default constructor for the SVG representation.
   *
   * @param model          represent the model to animate
   * @param framePerSecond represents the frames per second
   */
  public AnimationSVGView(AnimatorModel model, int framePerSecond) {
    this.model = model;
    this.output = new StringBuilder();
    this.leftMost = this.model.getLeftMost();
    this.topMost = this.model.getTopMost();
    this.width = this.model.getWidth();
    this.height = this.model.getHeight();
    this.framePerSecond = framePerSecond;
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("This is an SVG representation");
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("This is an SVG representation");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("This is an SVG representation");
  }

  @Override
  public void setCommandListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("This is an SVG representation");
  }

  @Override
  public void createAnimationOutput() {
    output.append(String.format("<svg viewBox=\"%s %s %s %s\" "
                    + "xmlns=\"http://www.w3.org/2000/svg\">", this.leftMost, this.topMost,
            this.width, this.height));

    String shapeType = "";
    for (ShapesModel s : this.model.getShapes()) {
      if (s instanceof Rectangle) {
        shapeType = "rect";
        output.append(
                String.format("<%s id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\""
                                + " fill=\"rgb(%s,%s,%s)\" visibility=\"visible\">\n", shapeType,
                        s.getName(), s.getPosition().getX(), s.getPosition().getY(),
                        s.getDimension().width, s.getDimension().height, s.getColor().getR(),
                        s.getColor().getG(), s.getColor().getB()));
      } else {
        shapeType = "ellipse";
        output.append(String.format("<%s id=\"%s\" cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\""
                        + " fill=\"rgb(%s,%s,%s)\" visibility=\"visible\">\n", shapeType,
                s.getName(), s.getPosition().getX(), s.getPosition().getY(),
                s.getDimension().width, s.getDimension().height, s.getColor().getR(),
                s.getColor().getG(), s.getColor().getB()));
      }
      double x = s.getPosition().getX();
      double y = s.getPosition().getY();
      double w = s.getDimension().width;
      double h = s.getDimension().height;
      for (AnimatorCommand a : s.getCommands()) {
        if (a instanceof Move) {
          x = a.getPosition()[2];
          y = a.getPosition()[3];
        } else if (a instanceof ChangeSize) {
          w = a.getDimension()[2];
          h = a.getDimension()[3];
        }
        this.createSVGMotion(a, s, (int) (x + w / 2), (int) (y + h / 2));
      }
      output.append(String.format("</%s>\n", shapeType));
    }
    output.append("</svg>");
  }


  private void createSVGMotion(AnimatorCommand a, ShapesModel s, int x, int y) {
    if (s instanceof Rectangle) {
      if (a instanceof Move) {
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"x\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000 /
                        this.framePerSecond,
                a.getPosition()[0], a.getPosition()[2]));
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"y\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000 /
                        this.framePerSecond,
                a.getPosition()[1], a.getPosition()[3]));
      } else if (a instanceof ChangeSize) {
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"width\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000 /
                        this.framePerSecond,
                a.getDimension()[0], a.getDimension()[2]));
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"height\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000 /
                        this.framePerSecond,
                a.getDimension()[1], a.getDimension()[3]));
      } else if (a instanceof ChangeColor) {
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"fill\" from=\"rgb(%s,%s,%s)\" to=\"rgb(%s,%s,%s)\" "
                        + "fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000
                        / this.framePerSecond,
                a.getColor()[0], a.getColor()[1],
                a.getColor()[2],
                a.getColor()[3], a.getColor()[4], a.getColor()[5]));
      } else {
        output.append(String.format("<animateTransform attributeType=\"xml\" attributeName=" +
                        "\"transform\" type=\"rotate\" from=\"%s %s %s\" to=\"%s %s %s\" begin=" +
                        "\"%sms\" dur=\"%sms\"/>\n",
                a.getRotation()[0],
                x,
                y,
                a.getRotation()[3],
                x,
                y,
                a.getStart() * 1000 / this.framePerSecond,
                a.getDuration() * 1000 / this.framePerSecond
        ));
      }
    } else {
      if (a instanceof Move) {
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"cx\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000
                        / this.framePerSecond,
                a.getPosition()[0], a.getPosition()[2]));
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"cy\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000
                        / this.framePerSecond,
                a.getPosition()[1], a.getPosition()[3]));
      } else if (a instanceof ChangeSize) {
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"rx\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000
                        / this.framePerSecond,
                a.getDimension()[0], a.getDimension()[2]));
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"ry\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000
                        / this.framePerSecond,
                a.getDimension()[1], a.getDimension()[3]));
      } else {
        output.append(String.format("<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
                        + "attributeName=\"fill\" from=\"rgb(%s,%s,%s)\" to=\"rgb(%s,%s,%s)\" "
                        + "fill=\"freeze\" />\n",
                a.getStart() * 1000 / this.framePerSecond, a.getDuration() * 1000
                        / this.framePerSecond,
                a.getColor()[0], a.getColor()[1],
                a.getColor()[2],
                a.getColor()[3], a.getColor()[4], a.getColor()[5]));
      }
    }
  }

  @Override
  public Appendable getOutput() {
    return this.output;
  }

  @Override
  public void changeTickSpeed(int framePerSecond) {
    this.framePerSecond = framePerSecond;
  }

}
