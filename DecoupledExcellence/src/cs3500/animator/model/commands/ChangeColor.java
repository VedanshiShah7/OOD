package cs3500.animator.model.commands;

import cs3500.animator.model.shapes.ShapesModel;

/**
 * Represent the Command Object to process ChangeColor for Shapes Model.
 * It takes the current color and the color expected and the start, end and current time.
 */
public class ChangeColor extends AbstractCommand {

  int fromR;
  int fromG;
  int fromB;
  int r;
  int g;
  int b;

  /**
   * Represent the default constructor for the change color command.
   *
   * @param fromR represent the initial r code
   * @param fromG represent the initial g code
   * @param fromB represent the initial b code
   * @param r     represent the desire r code
   * @param g     represent the desire g code
   * @param b     represent the desire b code
   * @param start represent the start time of animation
   * @param stop  represent the stop time of animation
   * @param clock represent the current clock
   */
  public ChangeColor(int fromR, int fromG, int fromB, int r, int g, int b, double start,
      double stop, double clock) {
    this.fromR = fromR;
    this.fromG = fromG;
    this.fromB = fromB;
    this.r = r;
    this.g = g;
    this.b = b;
    this.start = start;
    this.stop = stop;
    this.clock = clock;
  }

  @Override
  public void run(ShapesModel m) {
    m.changeColor(fromR, fromG, fromB, r, g, b, start, stop, clock);
  }

  @Override
  public int[] getColor() {
    return new int[]{this.fromR, this.fromG, this.fromB, this.r, this.g, this.b};
  }

  @Override
  public boolean sameCommand(AnimatorCommand that) {
    return that.sameColor(this);
  }

  @Override
  public boolean sameColor(ChangeColor that) {
    return this.r == that.r && this.g == that.g && this.b == that.b && this.fromR == that.fromR
        && this.fromB == that.fromB
        && this.fromG == that.fromG
        && Math.abs(this.start - that.start) < 0.01
        && Math.abs(this.stop - that.stop) < 0.01;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.fromR + this.fromG + this.fromB + this.r + this.g + this.b
        + this.start + this.stop + this.clock);
  }

  @Override
  public boolean equals(Object obj) {
    return this.sameCommand((AnimatorCommand) obj);
  }
}
