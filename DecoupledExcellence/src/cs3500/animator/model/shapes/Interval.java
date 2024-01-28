package cs3500.animator.model.shapes;

import java.util.Objects;

/**
 * Represent the Interval for each animation.
 */
public class Interval {

  private double start;
  private double end;

  /**
   * Constructor to initialize the Interval.
   *
   * @param start represent the starting time of the motion
   * @param end   represent the end time of the motion
   */
  public Interval(double start, double end) {
    if (end < start || start < 0 || end < 0) {
      throw new IllegalArgumentException("Invalid Interval");
    } else {
      this.start = start;
      this.end = end;
    }
  }

  /**
   * Return the start interval.
   *
   * @return return the start interval
   */
  public double getStart() {
    return start;
  }

  /**
   * Return the stop interval.
   *
   * @return return the stop interval
   */
  public double getStop() {
    return end;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Interval)) {
      return false;
    }

    Interval that = (Interval) obj;

    return ((Math.abs(this.start - that.start) < 0.01) && (Math.abs(this.end - that.end) < 0.01));
  }
}

