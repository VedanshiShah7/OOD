package cs3500.animator.model.shapes;

import java.awt.Dimension;

/**
 * Implementation of an Ellipse Shape and manages all animation operation related to this Ellipse
 * shape.
 */
public class Oval extends AbstractShapesModel {

  public Oval(String name, CartPosition position,
              RGBColor color, Dimension dimension, Double clock) {
    super(name, position, color, dimension, clock);
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Oval)) {
      return false;
    }

    Oval that = (Oval) obj;
    return this.name.equals(that.name)
            && this.dimension.equals(that.dimension)
            && this.position.equals(that.position)
            && this.color.equals(that.color)
            && this.frame.equals(that.frame);
  }

  @Override
  public int hashCode() {
    return this.name.hashCode() + this.dimension.hashCode()
            + this.color.hashCode() + this.position.hashCode()
            + this.frame.hashCode();
  }

}
