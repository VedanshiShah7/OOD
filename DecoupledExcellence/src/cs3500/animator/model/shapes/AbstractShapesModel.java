package cs3500.animator.model.shapes;

import cs3500.animator.model.commands.AnimatorCommand;

import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.commands.Rotate;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An Abstract representation of each Objects that implement the Animator Model, which would manage
 * all of the operation by KeyFrame. It implements all the operations offered by the Animator model.
 * One object of ShapesModel represents one of the shapes. This implementation currently represents
 * Ellipse shape and a Rectangle shape. It offers functionality on the object like moving the
 * object, change the object color, change the object size amongst others.
 */
public abstract class AbstractShapesModel implements ShapesModel {

  protected String name;
  protected CartPosition position;
  protected RGBColor color;
  protected Dimension dimension;
  protected Rotation rotation;
  protected final Map<Double, Double[]> frame;
  protected final Map<AnimationStatus, ArrayList<Interval>> interval;
  protected List<AnimatorCommand> animation;

  /**
   * Constructor for Initializing the Animation.
   *
   * @param position  represent the initial position of the Object
   * @param color     represent the initial color of the Object
   * @param dimension represent the initial Dimension of the Object
   * @param clock     represent the time in which this object existed
   */
  public AbstractShapesModel(String name, CartPosition position, RGBColor color,
                             Dimension dimension, double clock) {
    if (position != null && color != null && dimension != null) {
      this.name = name;
      this.position = position;
      this.color = color;
      this.dimension = dimension;
      this.rotation = new Rotation(0, position.getX(), position.getY());
      this.frame = new HashMap<Double, Double[]>();
      this.animation = new ArrayList<>();
      this.interval = new HashMap<AnimationStatus, ArrayList<Interval>>();
      this.interval.put(AnimationStatus.MOVE, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.SIZE, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.COLOR, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.ROTATE, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.GAP, new ArrayList<Interval>());
      this.saveAnimation(clock);
    } else {
      throw new IllegalArgumentException("Bad Input");
    }
  }

  /**
   * Constructor for Initializing the Animation.
   *
   * @param position  represent the initial position of the Object
   * @param color     represent the initial color of the Object
   * @param dimension represent the initial Dimension of the Object
   * @param clock     represent the time in which this object existed
   */
  public AbstractShapesModel(String name, CartPosition position, RGBColor color,
                             Dimension dimension, Rotation rotation, double clock) {
    if (position != null && color != null && dimension != null) {
      this.name = name;
      this.position = position;
      this.color = color;
      this.dimension = dimension;
      this.rotation = rotation;
      this.frame = new HashMap<Double, Double[]>();
      this.animation = new ArrayList<>();
      this.interval = new HashMap<AnimationStatus, ArrayList<Interval>>();
      this.interval.put(AnimationStatus.MOVE, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.SIZE, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.COLOR, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.GAP, new ArrayList<Interval>());
      this.interval.put(AnimationStatus.ROTATE, new ArrayList<Interval>());
      this.saveAnimation(clock);
    } else {
      throw new IllegalArgumentException("Bad Input");
    }
  }

  @Override
  public void move(int fromX, int fromY, int toX, int toY, double start, double stop,
                   double clock) {
    if (Math.abs(stop - start) < 0.01) {
      this.position = new CartPosition(fromX, fromY);
    } else if (Math.abs(clock - start) < 0.01) { // When the clock hit the first time of the motion

      ArrayList<Interval> m = new ArrayList<>();
      m.addAll(this.interval.get(AnimationStatus.MOVE));
      m.addAll(this.interval.get(AnimationStatus.GAP));

      // Check if there existed a gap in motion
      if (!this.checkGap(this.getAllInterval(), start)) {
        throw new IllegalArgumentException("There existed a gap in motion");
        // Check for given initial Position of the Move Motion
      } else if (Math.abs(fromX - this.position.getX()) > 0.01
              || Math.abs(fromY - this.position.getY()) > 0.01) {
        throw new IllegalArgumentException(
                "The start of this position is not equal to last position endpoint!");
      } else {
        this.saveAnimation(clock);
        ArrayList<Interval> addInterval = this.interval.get(AnimationStatus.MOVE);
        addInterval.add(new Interval((int) start, (int) stop));
        this.interval.put(AnimationStatus.MOVE, addInterval);
      }
    } else if ((this.position.getX() != toX || this.position.getY() != toY)
            && (clock > start && clock <= stop)) {
      // Move the objects
      this.movePerTick(toX, toY, start, stop, clock);
    }
  }

  // Move the AnimatorModel's object per tick
  private void movePerTick(int x, int y, double start, double stop, double clock) {
    double startX = this.frame.get(start)[0];
    double startY = this.frame.get(start)[1];

    double xAtT = calculateTick(startX, x, start, stop, clock);
    double yAtT = calculateTick(startY, y, start, stop, clock);

    this.position = new CartPosition(xAtT, yAtT);
  }

  @Override
  public void changeSize(int fromW, int fromH, int toW, int toH, double start, double stop,
                         double clock) {
    if (Math.abs(stop - start) < 0.01) {
      this.dimension = new Dimension(fromW, fromH);
    } else if (Math.abs(clock - start) < 0.01) {

      ArrayList<Interval> m = new ArrayList<>();
      m.addAll(this.interval.get(AnimationStatus.SIZE));
      m.addAll(this.interval.get(AnimationStatus.GAP));

      // Check if there existed a gap in motion
      if (!this.checkGap(this.getAllInterval(), start)) {
        throw new IllegalArgumentException("There existed a gap in motion");
        // Check if the initial dimension that the user enter is valid
      } else if (Math.abs(fromW - this.dimension.getWidth()) > 0.01
              || Math.abs(fromH - this.dimension.getHeight()) > 0.01) {
        throw new IllegalArgumentException(
                "The start of this position is not equal to last position endpoint!");
      } else {
        this.saveAnimation(clock);
        ArrayList<Interval> addInterval = this.interval.get(AnimationStatus.SIZE);
        addInterval.add(new Interval((int) start, (int) stop));
        this.interval.put(AnimationStatus.SIZE, addInterval);
      }
    } else if ((Math.abs(this.dimension.width - toW) > 0.01
            || Math.abs(this.dimension.height - toH) > 0.01)
            && (clock > start && clock <= stop)) {
      this.changeSizePerTick(toW, toH, start, stop, clock);
    }
  }

  // Change the width and height of the AnimatorModel's object per tick
  private void changeSizePerTick(int w, int h, double start, double stop, double clock) {
    double startWidth = this.frame.get(start)[2];
    double startHeight = this.frame.get(start)[3];

    double widthAtT = calculateTick(startWidth, w, start, stop, clock);
    double heightAtT = calculateTick(startHeight, h, start, stop, clock);

    // Change the dimension
    Dimension d1 = (Dimension) this.dimension.clone();
    d1.setSize(widthAtT, heightAtT);
    this.dimension = d1;
  }


  @Override
  public void changeColor(int fromR, int fromG, int fromB, int toR, int toG, int toB,
                          double start, double stop, double clock) {
    if (Math.abs(stop - start) < 0.01) {
      this.color = new RGBColor(toR, toG, toB);
    } else if (Math.abs(clock - start) < 0.01) {

      ArrayList<Interval> m = new ArrayList<>();
      m.addAll(this.interval.get(AnimationStatus.COLOR));
      m.addAll(this.interval.get(AnimationStatus.GAP));

      // Check if there existed a gap in motion
      if (!this.checkGap(this.getAllInterval(), start)) {
        throw new IllegalArgumentException("There existed a gap in motion");
      } else if (this.color.getR() != fromR || this.color.getG() != fromG
              || this.color.getB() != fromB) {
        throw new IllegalArgumentException("The initial color of this motion "
                + "is not equal to the color of the last motion");
      } else {
        this.saveAnimation(clock);
        ArrayList<Interval> addInterval = this.interval.get(AnimationStatus.COLOR);
        addInterval.add(new Interval((int) start, (int) stop));
        this.interval.put(AnimationStatus.COLOR, addInterval);
      }
    } else if (((Math.abs(this.color.getR() - toR) > 0.01)
            || (Math.abs(this.color.getG() - toG) > 0.01)
            || (Math.abs(this.color.getB() - toB) > 0.01))
            && (clock > start && clock <= stop)) {
      this.changeColorPerTick(toR, toG, toB, start, stop, clock);
    }
  }

  // Change the Color of the AnimatorModel's object per tick
  private void changeColorPerTick(int r, int g, int b, double start, double stop,
                                  double clock) {
    double startR = this.frame.get(start)[4];
    double startG = this.frame.get(start)[5];
    double startB = this.frame.get(start)[6];

    double rAtT = calculateTick(startR, r, start, stop, clock);
    double gAtT = calculateTick(startG, g, start, stop, clock);
    double bAtT = calculateTick(startB, b, start, stop, clock);

    // Change color of the objects
    this.color = new RGBColor(rAtT, gAtT, bAtT);
  }

  private double calculateTick(double startParam, double param, double start, double stop,
                               double clock) {
    return startParam * ((stop - clock) / (stop - start)) +
            param * ((clock - start) / (stop - start));
  }

  @Override
  public String getAnimationState(double clock) {
    String result = "";

    ArrayList<Double> gameKeys = getGameKeys();

    for (int i = 0; i < gameKeys.size(); i++) {
      Double[] frameInfo = this.frame.get(gameKeys.get(i));
      if (Math.floorMod(i, 2) != 0) {
        result += String
                .format(" %s %s %s %s %s %s %s %s\n",
                        Math.round(gameKeys.get(i)),
                        Math.round(frameInfo[0]), Math.round(frameInfo[1]),
                        Math.round(frameInfo[2]), Math.round(frameInfo[3]),
                        Math.round(frameInfo[4]), Math.round(frameInfo[5]),
                        Math.round(frameInfo[6]));
      } else {
        result += String
                .format("Motion %s %s %s %s %s %s %s %s %s ", this.name,
                        Math.round(gameKeys.get(i)),
                        Math.round(frameInfo[0]), Math.round(frameInfo[1]),
                        Math.round(frameInfo[2]), Math.round(frameInfo[3]),
                        Math.round(frameInfo[4]), Math.round(frameInfo[5]),
                        Math.round(frameInfo[6]));
      }
    }

    return result + String.format(" %s %s %s %s %s %s %s %s", (int) clock,
            (int) this.position.getX(),
            (int) this.position.getY(),
            this.dimension.width,
            this.dimension.height,
            (int) this.color.getR(),
            (int) this.color.getG(),
            (int) this.color.getB());
  }

  @Override
  public void saveAnimation(double clock) {
    if (this.frame.containsKey(clock)) {
      this.frame.replace(clock, new Double[]{this.position.getX(), this.position.getY(),
          (double) this.dimension.width, (double) this.dimension.height,
            this.color.getR(), this.color.getG(), this.color.getB(), this.rotation.getDegree(),
              this.rotation.getXPivot(), this.rotation.getYPivot()});
    } else {
      this.frame.put(clock, new Double[]{this.position.getX(), this.position.getY(),
          (double) this.dimension.width, (double) this.dimension.height, this.color.getR(),
            this.color.getG(), this.color.getB(), this.rotation.getDegree(),
              this.rotation.getXPivot(), this.rotation.getYPivot()});
    }
  }

  @Override
  public boolean motionOver(double clock) {
    boolean result = true;

    for (AnimatorCommand a : this.animation) {
      result = result && a.motionOver(clock);
    }
    return result;
  }

  @Override
  public void addMotion(AnimatorCommand a) {
    this.animation.add(a);
  }

  @Override
  public void removeMotion(AnimatorCommand a) {
    this.animation.remove(a);
  }

  @Override
  public void updateAnimatorClock(double clock) {
    for (AnimatorCommand a : this.animation) {
      a.updateClock(clock);
    }
  }

  @Override
  public void runAnimation() {
    for (AnimatorCommand a : this.animation) {
      a.run(this);
    }
  }

  // Get the game Key
  private ArrayList<Double> getGameKeys() {
    ArrayList<Double> gameKeys = new ArrayList<>();
    for (Double key : this.frame.keySet()) {
      gameKeys.add(key);
      gameKeys.add(key);
    }
    Collections.sort(gameKeys);
    gameKeys.remove(0);
    return gameKeys;
  }


  // Return true if there is no overlap
  private boolean checkOverlapInterval(ArrayList<Interval> arr, double start, double stop) {
    boolean result = true;

    for (int i = 0; i < arr.size(); i++) {
      // Find the total range
      double min = Math.min(arr.get(i).getStart(), start);
      double max = Math.max(arr.get(i).getStop(), stop);
      double totalRange = max - min;

      // Find the range of the Arr interval
      double arrRange = Math.abs(arr.get(i).getStop() - arr.get(i).getStart());

      // Find the range of the given interval
      double givenRange = Math.abs(stop - start);

      result = result && (givenRange + arrRange <= totalRange);

    }

    if (this.interval.containsKey(new Interval(start, stop))) {
      return true;
    } else {
      return result;
    }
  }


  // Return true if there is no gaps
  private boolean checkGap(ArrayList<Interval> arr, double start) {
    boolean result = false;

    if (arr.size() == 0) {
      return true;
    } else {
      for (int i = 0; i < arr.size(); i++) {
        result = result || (start <= arr.get(i).getStop() && start >= arr.get(i)
                .getStart());
      }
      return result;
    }
  }

  // Return all of the interval of the animation.
  private ArrayList<Interval> getAllInterval() {
    ArrayList<Interval> allInterval = new ArrayList<>();
    allInterval.addAll(this.interval.get(AnimationStatus.MOVE));
    allInterval.addAll(this.interval.get(AnimationStatus.COLOR));
    allInterval.addAll(this.interval.get(AnimationStatus.SIZE));
    allInterval.addAll(this.interval.get(AnimationStatus.GAP));
    return allInterval;
  }

  @Override
  public CartPosition getPosition() {
    return new CartPosition(this.position.getX(), this.position.getY());
  }

  @Override
  public RGBColor getColor() {
    return new RGBColor(this.color.getR(), this.color.getG(), this.color.getB());
  }

  @Override
  public Dimension getDimension() {
    return new Dimension(this.dimension.width, this.dimension.height);
  }

  @Override
  public Rotation getRotation() {
    return new Rotation(this.rotation.getDegree(), this.rotation.getXPivot(),
            this.rotation.getYPivot());
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ArrayList<AnimatorCommand> getCommands() {
    ArrayList<AnimatorCommand> result = (ArrayList<AnimatorCommand>) this.animation;
    return (ArrayList<AnimatorCommand>) result.clone();
  }

  @Override
  public ArrayList<Move> getMoveCommands() {
    ArrayList<Move> result = new ArrayList<Move>();
    for (AnimatorCommand a : this.animation) {
      if (a instanceof Move) {
        result.add((Move) a);
      }
    }
    return result;
  }

  @Override
  public ArrayList<ChangeSize> getSizeCommands() {
    ArrayList<ChangeSize> result = new ArrayList<ChangeSize>();
    for (AnimatorCommand a : this.animation) {
      if (a instanceof ChangeSize) {
        result.add((ChangeSize) a);
      }
    }
    return result;
  }

  @Override
  public ArrayList<ChangeColor> getColorCommands() {
    ArrayList<ChangeColor> result = new ArrayList<ChangeColor>();
    for (AnimatorCommand a : this.animation) {
      if (a instanceof ChangeColor) {
        result.add((ChangeColor) a);
      }
    }
    return result;
  }

  @Override
  public ArrayList<Rotate> getRotateCommands() {
    ArrayList<Rotate> result = new ArrayList<Rotate>();
    for (AnimatorCommand a : this.animation) {
      if (a instanceof Rotate) {
        result.add((Rotate) a);
      }
    }
    return result;
  }

  @Override
  public HashMap<Double, Double[]> getFrame() {
    HashMap<Double, Double[]> result = (HashMap<Double, Double[]>) frame;
    return (HashMap<Double, Double[]>) result.clone();
  }

  @Override
  public void rotate(double start, double stop, double clock,
                     double fromAngle, double fromPivotX, double fromPivotY,
                     double toAngle, double toPivotX, double toPivotY) {
    if (Math.abs(stop - start) < 0.01) {
      this.rotation = new Rotation(fromAngle, fromPivotX, fromPivotY);
    } else if (Math.abs(clock - start) < 0.01) {

      ArrayList<Interval> m = new ArrayList<Interval>();
      ArrayList<Interval> a = this.interval.get(AnimationStatus.ROTATE);
      m.addAll(a);
      m.addAll(this.interval.get(AnimationStatus.GAP));

      // Check if there existed a gap in motion
      if (!this.checkGap(this.getAllInterval(), start)) {
        throw new IllegalArgumentException("There existed a gap in motion");
        // Check if the initial dimension that the user enter is valid
      } else if (Math.abs(fromAngle - this.rotation.getDegree()) > 0.01
              || Math.abs(fromPivotX - this.rotation.getXPivot()) > 0.01
              || Math.abs(fromPivotY - this.rotation.getYPivot()) > 0.01) {
        throw new IllegalArgumentException(
                "The start of this position is not equal to last position endpoint!");
      } else {
        this.saveAnimation(clock);
        ArrayList<Interval> addInterval = this.interval.get(AnimationStatus.ROTATE);
        addInterval.add(new Interval((int) start, (int) stop));
        this.interval.put(AnimationStatus.ROTATE, addInterval);
      }
    } else if ((Math.abs(this.rotation.getDegree() - toAngle) > 0.01
            || Math.abs(this.rotation.getXPivot() - toPivotX) > 0.01
            || Math.abs(this.rotation.getYPivot() - toPivotY) > 0.01)
            && (clock > start && clock <= stop)) {
      this.changeRotationPerTick(toAngle, toPivotX, toPivotY, start, stop, clock);
    }
  }

  private void changeRotationPerTick(double angle, double pivotX, double pivotY, double start,
                                     double stop, double clock) {
    double startAngle = this.frame.get(start)[7];
    double startPivotX = this.frame.get(start)[8];
    double startPivotY = this.frame.get(start)[9];

    double angleAT = calculateTick(startAngle, angle, start, stop, clock);
    double pivotXAT = calculateTick(startPivotX, pivotX, start, stop, clock);
    double pivotYAT = calculateTick(startPivotY, pivotY, start, stop, clock);

    // Change color of the objects
    this.rotation = new Rotation(angleAT, pivotXAT, pivotYAT);
  }
}
