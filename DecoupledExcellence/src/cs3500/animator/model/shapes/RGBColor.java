package cs3500.animator.model.shapes;

import java.util.Objects;

/**
 * Represent the RGB color code.
 */
public class RGBColor {

  private final double r;
  private final double g;
  private final double b;

  /**
   * Initialize Constructor for RGB value given 3 RGB value. Where the range must be [0,255]
   *
   * @param r represent r component of RGB [0,255]
   * @param g represent g component of RGB [0,255]
   * @param b represent b component of RGB [0,255]
   */
  public RGBColor(double r, double g, double b) {
    this.r = r;
    this.b = b;
    this.g = g;
  }

  /**
   * Return R value of the RGB Component.
   *
   * @return r value
   */
  public double getR() {
    return this.r;
  }

  /**
   * Return G value of the RGB Component.
   *
   * @return g value
   */
  public double getG() {
    return this.g;
  }

  /**
   * Return B value of the RGB Component.
   *
   * @return b value
   */
  public double getB() {
    return this.b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.b, this.g);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof RGBColor)) {
      return false;
    }

    RGBColor that = (RGBColor) obj;

    return ((Math.abs(this.r - that.r) < 0.01) && (Math.abs(this.g - that.g) < 0.01)
        && (Math.abs(this.b - that.b) < 0.01));
  }

  @Override
  public String toString() {
    return String.format("r:%f g:%f b:%f", this.r, this.b, this.g);
  }
}
