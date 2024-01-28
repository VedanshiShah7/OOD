package cs3500.animator.model.commands;

import cs3500.animator.model.shapes.ShapesModel;

/**
 * Abstract similarity from all of the Command Objects.
 */
public abstract class AbstractCommand implements AnimatorCommand {
  double start;
  double stop;
  double clock;

  @Override
  public abstract void run(ShapesModel m);

  @Override
  public void updateClock(double clock) {
    this.clock = clock;
  }

  @Override
  public boolean motionOver(double clock) {
    return this.stop <= clock;
  }

  @Override
  public double getStart() {
    return this.start;
  }

  @Override
  public double getStop() {
    return this.stop;
  }

  @Override
  public double getDuration() {
    return this.stop - this.start;
  }

  @Override
  public int[] getColor() {
    throw new IllegalArgumentException("No color is found");
  }

  @Override
  public int[] getDimension() {
    throw new IllegalArgumentException("No dimension is found");
  }

  @Override
  public double[] getRotation() {
    throw new IllegalArgumentException("No rotation is found");
  }

  @Override
  public int[] getPosition() {
    throw new IllegalArgumentException("No position is found");
  }

  @Override
  public abstract boolean sameCommand(AnimatorCommand that);

  @Override
  public boolean sameMove(Move that) {
    return false;
  }

  @Override
  public boolean sameSize(ChangeSize that) {
    return false;
  }

  @Override
  public boolean sameColor(ChangeColor that) {
    return false;
  }

  @Override
  public boolean sameRotate(Rotate that) {
    return false;
  }

}
