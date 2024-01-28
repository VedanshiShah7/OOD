package cs3500.animator.model.commands;

import cs3500.animator.model.shapes.ShapesModel;

/**
 * Represent the Command Object to process change size for Shapes Model. It takes the current width
 * and height and the width and height expected and the start, end and current time.
 */
public class ChangeSize extends AbstractCommand {

  int fromW;
  int fromH;
  int toW;
  int toH;

  /**
   * Represent the default constructor for the Change Size command.
   *
   * @param fromW represent the initial width
   * @param fromH represent the initial height
   * @param toW   represent the desire width
   * @param toH   represent the desire height
   * @param start represent the start time of animation
   * @param stop  represent the stop time of animation
   * @param clock represent the clock
   */
  public ChangeSize(int fromW, int fromH, int toW, int toH, double start, double stop,
                    double clock) {
    this.fromW = fromW;
    this.fromH = fromH;
    this.toW = toW;
    this.toH = toH;
    this.start = start;
    this.stop = stop;
    this.clock = clock;
  }

  @Override
  public void run(ShapesModel m) {
    m.changeSize(fromW, fromH, toW, toH, start, stop, this.clock);
  }

  @Override
  public int[] getDimension() {
    return new int[]{this.fromW, this.fromH, this.toW, this.toH};
  }

  @Override
  public boolean sameCommand(AnimatorCommand that) {
    return that.sameSize(this);
  }

  @Override
  public boolean sameSize(ChangeSize that) {
    return this.fromW == that.fromW && this.fromH == that.fromH
        && this.toW == that.toW && this.toH == that.toH
        && Math.abs(this.start - that.start) < 0.01
        && Math.abs(this.stop - that.stop) < 0.01;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.fromW + this.fromH + this.toW + this.toH
        + this.start + this.stop + this.clock);
  }

  @Override
  public boolean equals(Object obj) {
    return this.sameCommand((AnimatorCommand) obj);
  }
}
