package cs3500.animator.model.commands;

import cs3500.animator.model.shapes.ShapesModel;

/**
 * Represent the Command Object to process Move for Shapes Model. It takes the current coordinates
 * and the coordinates expected and the start, end and current time.
 */
public class Move extends AbstractCommand {

  int fromX;
  int fromY;
  int toX;
  int toY;

  /**
   * Constructor for Move object.
   *
   * @param fromX represent the initial x coordinate
   * @param fromY represent the initial y coordinate
   * @param toX   represent the desire x coordinate
   * @param toY   represent the desire y coordinate
   * @param start represent the time in which animation start
   * @param stop  represent the time in which animation stop
   * @param clock represents the current time
   */
  public Move(int fromX, int fromY, int toX, int toY, double start, double stop, double clock) {
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
    this.start = start;
    this.stop = stop;
    this.clock = clock;
  }

  @Override
  public void run(ShapesModel m) {
    m.move(fromX, fromY, toX, toY, start, stop, clock);
  }

  @Override
  public int[] getPosition() {
    return new int[]{this.fromX, this.fromY, this.toX, this.toY};
  }

  @Override
  public boolean sameCommand(AnimatorCommand that) {
    return that.sameMove(this);
  }

  @Override
  public boolean sameMove(Move that) {
    return this.fromX == that.fromX && this.fromY == that.fromY
        && this.toX == that.toX && this.toY == that.toY
        && Math.abs(this.start - that.start) < 0.01
        && Math.abs(this.stop - that.stop) < 0.01;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.fromX + this.fromY + this.toX + this.toY
        + this.start + this.stop + this.clock);
  }

  @Override
  public boolean equals(Object obj) {
    return this.sameCommand((AnimatorCommand) obj);
  }
}
