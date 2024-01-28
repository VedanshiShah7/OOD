package cs3500.animator.model.shapes;

import java.util.Objects;

/**
 * Represent Cartesian Coordinate on a Cartesian plane.
 */
public class CartPosition {

  private final double x;
  private final double y;

  /**
   * Initialize this object to the specified position.
   */
  public CartPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CartPosition)) {
      return false;
    }

    CartPosition that = (CartPosition) obj;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }
}
