package cs3500.animator.model.commands;

import cs3500.animator.model.shapes.ShapesModel;

/**
 * Represent the Command Object to process Rotate for Shapes Model. It takes the current angle,
 * pivot points and the angle, pivot points expected and the start, end and current time.
 */
public class Rotate extends AbstractCommand {

  private double fromAngle;
  private double fromPivotX;
  private double fromPivotY;
  private double toAngle;
  private double toPivotX;
  private double toPivotY;

  /**
   * Constructor for Rotate object.
   *
   * @param fromAngle represents the initial angle.
   * @param fromPivotX represents the initial x pivot point.
   * @param fromPivotY represents the initial y pivot point.
   * @param toAngle represents the final angle.
   * @param toPivotX represents the final x pivot point.
   * @param toPivotY represents the final y pivot point.
   * @param start represents the time in which animation start.
   * @param stop represents the time in which animation stops.
   * @param clock represents the current time.
   */
  public Rotate(double fromAngle, double fromPivotX, double fromPivotY, double toAngle,
                double toPivotX, double toPivotY, double start, double stop, double clock) {
    this.fromAngle = fromAngle;
    this.fromPivotX = fromPivotX;
    this.fromPivotY = fromPivotY;
    this.toAngle = toAngle;
    this.toPivotX = toPivotX;
    this.toPivotY = toPivotY;
    this.start = start;
    this.stop = stop;
    this.clock = clock;
  }

  @Override
  public void run(ShapesModel m) {
    m.rotate(this.start, this.stop, this.clock, this.fromAngle, this.fromPivotX, this.fromPivotY,
            this.toAngle, this.toPivotX, this.toPivotY);
  }

  @Override
  public double[] getRotation() {
    return new double[]{this.fromAngle, this.fromPivotX, this.fromPivotY,
      this.toAngle, this.toPivotX, this.toPivotY};
  }

  @Override
  public boolean sameCommand(AnimatorCommand that) {
    return that.sameRotate(this);
  }

  @Override
  public boolean sameRotate(Rotate that) {
    return (this.fromAngle - that.fromAngle) < 0.01 &&
            (this.fromPivotX - that.fromPivotX) < 0.01 &&
            (this.fromPivotY - that.fromPivotY) < 0.01 &&
            (this.toAngle - that.toAngle) < 0.01 &&
            (this.toPivotX - that.toPivotX) < 0.01 &&
            (this.toPivotY - that.toPivotY) < 0.01;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.fromAngle + this.fromPivotX + this.fromPivotY
            + this.toAngle + this.toPivotX + this.toPivotY
            + this.start + this.stop + this.clock);
  }

  @Override
  public boolean equals(Object obj) {
    return this.sameCommand((AnimatorCommand) obj);
  }
}
