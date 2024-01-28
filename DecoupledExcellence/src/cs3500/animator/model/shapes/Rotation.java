package cs3500.animator.model.shapes;

import java.util.Objects;

/**
 * Represents a rotation.
 */
public class Rotation {

  private final double degree;
  private final double xPivot;
  private final double yPivot;

  /**
   * Initialize this object to the specified position.
   */
  public Rotation(double degree, double xPivot, double yPivot) {
    this.degree = degree;
    this.xPivot = xPivot;
    this.yPivot = yPivot;
  }

  /**
   * Returns the degree of a rotation.
   *
   * @return degree of a rotation.
   */
  public double getDegree() {
    return this.degree;
  }

  /**
   * Returns the x pivot component of a rotation.
   *
   * @return the x pivot component of a rotation.
   */
  public double getXPivot() {
    return this.xPivot;
  }

  /**
   * Returns the y pivot component of a rotation.
   *
   * @return the y pivot component of a rotation.
   */
  public double getYPivot() {
    return this.yPivot;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.degree, this.xPivot, this.yPivot);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Rotation)) {
      return false;
    }

    Rotation that = (Rotation) obj;

    return ((Math.abs(this.degree - that.degree) < 0.01) &&
            (Math.abs(this.xPivot - that.xPivot) < 0.01) &&
            (Math.abs(this.yPivot - that.yPivot) < 0.01));
  }

  @Override
  public String toString() {
    return String.format("(%f, %f, %f)", this.degree, this.xPivot, this.yPivot);
  }
}
