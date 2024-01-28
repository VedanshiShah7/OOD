package cs3500.animator.model;

import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.commands.Rotate;
import cs3500.animator.model.shapes.CartPosition;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.RGBColor;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.ShapesModel;
import cs3500.animator.util.AnimationBuilder;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the animator model implementation class that extends the animator model and all its
 * methods. It is used in the constructor to animate objects of ShapeModel.
 */
public class AnimatorModelImpl implements AnimatorModel {

  protected final int height;
  protected final int width;
  protected final int leftMost;
  protected final int topMost;
  protected LinkedHashMap<String, ShapesModel> shapes;
  protected LinkedHashMap<Integer, ArrayList<String>> layers;
  protected double clock;

  /**
   * Constructor for animator model implementation class that does not take in any inputs.
   */
  public AnimatorModelImpl() {
    this.height = 500;
    this.width = 500;
    this.shapes = new LinkedHashMap<>();
    this.layers = new LinkedHashMap<Integer, ArrayList<String>>();
    this.leftMost = 0;
    this.topMost = 0;
    this.clock = 0;
  }

  /**
   * Constructor to construct the Animation World with Height and Width and desired tick speed.
   *
   * @param height   represent the height of the window
   * @param width    represent the width of the window
   * @param leftMost represent the leftMost value of the animation
   * @param topMost  represent the topMost value of the animation
   */
  public AnimatorModelImpl(int height, int width, int leftMost, int topMost) {
    if (height > 0 && width > 0) {
      this.height = height;
      this.width = width;
      this.shapes = new LinkedHashMap<>();
      this.layers = new LinkedHashMap<>();
      this.leftMost = leftMost;
      this.topMost = topMost;
      this.clock = 0;
    } else {
      throw new IllegalArgumentException("Height Width and Tickspeed must be positive");
    }
  }

  /**
   * Constructor to construct the Animation World with Height and Width and desired shapes.
   *
   * @param height   represent the height of the window
   * @param width    represent the width of the window
   * @param topMost  represent the topMost value of the animation
   * @param leftMost represent the leftMost value of the animation
   * @param shapes   represent the shapes inside this animator
   */
  public AnimatorModelImpl(int height, int width, int leftMost, int topMost,
                           LinkedHashMap<String, ShapesModel> shapes) {
    if (height > 0 && width > 0) {
      this.height = height;
      this.width = width;
      this.shapes = shapes;
      this.layers = new LinkedHashMap<>();
      this.leftMost = leftMost;
      this.topMost = topMost;
      this.clock = 0;
    } else {
      throw new IllegalArgumentException("Height Width and Tickspeed must be positive");
    }
  }

  /**
   * Constructor to construct the Animation World with custom parameters.
   *
   * @param height   represent the height of the window
   * @param width    represent the width of the window
   * @param topMost  represent the topMost value of the animation
   * @param leftMost represent the leftMost value of the animation
   * @param shapes   represent a list of shapes in this Animation World
   * @param clock    represent the clock for this animation world
   */
  public AnimatorModelImpl(int height, int width, int leftMost, int topMost,
                           LinkedHashMap<String, ShapesModel> shapes, double clock) {
    if (clock > 0 && height > 0 && width > 0) {
      this.height = height;
      this.width = width;
      this.leftMost = leftMost;
      this.topMost = topMost;
      this.shapes = shapes;
      this.layers = new LinkedHashMap<>();
      this.clock = clock;
    } else {
      throw new IllegalArgumentException("Height Width and Tickspeed must be positive");
    }
  }

  /**
   * Constructor to construct the Animation World with custom parameters.
   *
   * @param height   represent the height of the window
   * @param width    represent the width of the window
   * @param topMost  represent the topMost value of the animation
   * @param leftMost represent the leftMost value of the animation
   * @param shapes   represent a list of shapes in this Animation World
   * @param layers   represents the list of shapes associated with a layer.
   */
  public AnimatorModelImpl(int height, int width, int leftMost, int topMost,
                           LinkedHashMap<String, ShapesModel> shapes,
                           LinkedHashMap<Integer, ArrayList<String>> layers) {
    if (height > 0 && width > 0) {
      this.height = height;
      this.width = width;
      this.leftMost = leftMost;
      this.topMost = topMost;
      this.shapes = shapes;
      this.layers = layers;
      this.clock = 0;
    } else {
      throw new IllegalArgumentException("Height Width and Tickspeed must be positive");
    }
  }

  @Override
  public void addShape(ShapesModel s) {
    int layer = 0;
    shapes.put(s.getName(), s);
    if (layers.get(layer) == null) {
      layers.put(layer, new ArrayList<>());
    }
    layers.get(layer).add(s.getName());
  }

  @Override
  public void removeShape(ShapesModel s) {
    if (shapes.containsKey(s.getName())) {
      shapes.remove(s.getName());
    } else {
      throw new IllegalArgumentException("This shape does not exist.");
    }
  }

  @Override
  public void moveShape(ShapesModel s, int fromX, int fromY, int toX, int toY, double start,
                        double end) {
    if (shapes.containsKey(s.getName())) {
      AnimatorCommand m = new Move(fromX, fromY, toX, toY, start, end, this.clock);
      s.addMotion(m);
    } else {
      throw new IllegalArgumentException("The shape does not exist.");
    }
  }

  @Override
  public void changeColor(ShapesModel s, int fromR, int fromG, int fromB, int r, int g, int b,
                          double start, double stop) {
    if (shapes.containsKey(s.getName())) {
      AnimatorCommand m = new ChangeColor(fromR, fromG, fromB, r, g, b, start, stop, this.clock);
      s.addMotion(m);
    } else {
      throw new IllegalArgumentException("The Shape does not exist.");
    }
  }

  @Override
  public void changeSize(ShapesModel s, int fromw, int fromh, int tow, int toh, double start,
                         double stop) {
    if (shapes.containsKey(s.getName())) {
      AnimatorCommand m = new ChangeSize(fromw, fromh, tow, toh, start, stop, this.clock);
      s.addMotion(m);
    } else {
      throw new IllegalArgumentException("The shape does not exist.");
    }
  }

  @Override
  public void rotate(ShapesModel s, double fromAngle, double fromXPivot, double fromYPivot,
                     double toAngle, double toXPivot, double toYPivot, double start, double stop) {
    if (shapes.containsKey(s)) {
      AnimatorCommand m = new Rotate(fromAngle, fromXPivot, fromYPivot, toAngle, toXPivot, toYPivot,
              start, stop, this.clock);
    } else {
      throw new IllegalArgumentException("The shape does not exist.");
    }
  }

  @Override
  public String getAnimationState() {
    ArrayList<String> keyset = new ArrayList<>(this.shapes.keySet());
    String result = "";
    for (int i = 0; i < keyset.size(); i++) {
      if (i == this.shapes.keySet().size() - 1) {
        result += this.shapes.get(keyset.get(i)).getAnimationState(clock);
      } else {
        result += this.shapes.get(keyset.get(i)).getAnimationState(clock) + "\n\n";
      }
    }
    return result;
  }

  @Override
  public String getAnimationStateShape(ShapesModel s) {
    if (shapes.containsKey(s.getName())) {
      return s.getAnimationState(this.clock);
    } else {
      throw new IllegalArgumentException("Shape does not exist.");
    }
  }

  @Override
  public boolean isOver() {
    boolean result = true;

    for (String s : this.shapes.keySet()) {
      result = result && this.shapes.get(s).motionOver(clock);
    }
    return result;
  }

  @Override
  public void runAnimation() {
    while (!this.isOver()) {
      for (String s : this.shapes.keySet()) {
        this.shapes.get(s).updateAnimatorClock(this.clock);
        this.shapes.get(s).runAnimation();
      }
      this.updateClock();
    }

    // Latest run to make sure that end animation work
    for (String s : this.shapes.keySet()) {
      this.shapes.get(s).updateAnimatorClock(this.clock);
      this.shapes.get(s).runAnimation();
    }
  }

  @Override
  public ArrayList<ShapesModel> getShapes() {
    ArrayList<ShapesModel> result = new ArrayList<>();

    for (String s : this.shapes.keySet()) {
      result.add(this.shapes.get(s));
    }
    return result;
  }

  @Override
  public ShapesModel findShape(String name) {
    ShapesModel result = null;

    for (String s : this.shapes.keySet()) {
      if (this.shapes.get(s).getName().equals(name)) {
        result = this.shapes.get(s);
      }
    }

    if (result == null) {
      throw new IllegalArgumentException("Shapes cannot be found");
    } else {
      return result;
    }
  }

  @Override
  public void insertKeyFrame(ShapesModel model, Integer x, Integer y, Integer width, Integer height,
                             Integer r, Integer g, Integer b, Integer time) {
    if (time == null) {
      throw new IllegalArgumentException("time of this keyframe is not specify");
    } else {
      if (x != null && y != null) {
        this.insertMoveCommand(model, x, y, time);
      }

      if (width != null && height != null) {
        this.insertSizeCommand(model, width, height, time);
      }

      if (r != null && g != null && b != null) {
        this.insertColorCommand(model, r, g, b, time);
      }
    }
  }

  private void insertMoveCommand(ShapesModel model, int x, int y, double time) {
    if (model.getMoveCommands().isEmpty()) {
      Move move = new Move(x, y, x, y, time, time, this.clock);
      model.addMotion(move);
    } else {
      int fromX = x;
      int fromY = y;
      int toX = x;
      int toY = y;
      double startTime = model.getMoveCommands().get(0).getStop();
      double endTime = model.getMoveCommands().get(0).getStart();
      for (Move m : model.getMoveCommands()) {
        // Find the last keyframe in the shape
        startTime = Math.max(startTime, m.getStop());
        // Find the first keyframe in the shape
        endTime = Math.min(endTime, m.getStart());
      }

      for (Move m : model.getMoveCommands()) {
        // Find the stop position of the last keyFrame
        if (Math.abs(m.getStop() - startTime) < 0.01) {
          fromX = m.getPosition()[2];
          fromY = m.getPosition()[3];
        }

        // Find the start position of the first keyFrame
        if (Math.abs(m.getStart() - endTime) < 0.01) {
          toX = m.getPosition()[0];
          toY = m.getPosition()[1];
        }
      }

      if (time > startTime) {
        model.addMotion(new Move(fromX, fromY, x, y, startTime, time, this.clock));
      } else {
        model.addMotion(new Move(x, y, toX, toY, time, endTime, this.clock));
      }
    }
  }


  private void insertSizeCommand(ShapesModel model, int width, int height, double time) {
    if (model.getSizeCommands().isEmpty()) {
      ChangeSize size = new ChangeSize(width, height, width, height, time, time, this.clock);
      model.addMotion(size);
    } else {
      int fromWidth = width;
      int fromHeight = height;
      int toWidth = width;
      int toHeight = height;
      double startTime = model.getSizeCommands().get(0).getStop();
      double endTime = model.getSizeCommands().get(0).getStart();
      for (ChangeSize s : model.getSizeCommands()) {
        // Find the last keyframe in the shape
        startTime = Math.max(startTime, s.getStop());
        // Find the first keyframe in the shape
        endTime = Math.min(endTime, s.getStart());
      }

      for (ChangeSize s : model.getSizeCommands()) {
        // Find the stop position of the last keyFrame
        if (Math.abs(s.getStop() - startTime) < 0.01) {
          fromWidth = s.getDimension()[2];
          fromHeight = s.getDimension()[3];
        }

        // Find the start position of the first keyFrame
        if (Math.abs(s.getStart() - endTime) < 0.01) {
          toWidth = s.getDimension()[0];
          toHeight = s.getDimension()[1];
        }
      }

      if (time > startTime) {
        model.addMotion(
                new ChangeSize(fromWidth, fromHeight, width, height, startTime, time, this.clock));
      } else {
        model.addMotion(new ChangeSize(fromWidth, fromHeight, width, height, startTime, time,
                this.clock));
      }
    }
  }

  private void insertColorCommand(ShapesModel model, int r, int g, int b, double time) {
    if (model.getColorCommands().isEmpty()) {
      ChangeColor color = new ChangeColor(r, g, b, r, g, b, time, time, this.clock);
      model.addMotion(color);
    } else {

      int fromR = r;
      int fromG = g;
      int fromB = b;
      int toR = r;
      int toG = g;
      int toB = b;
      double startTime = model.getColorCommands().get(0).getStop();
      double endTime = model.getColorCommands().get(0).getStart();
      for (ChangeColor c : model.getColorCommands()) {
        // Find the last keyframe in the shape
        startTime = Math.max(startTime, c.getStop());
        // Find the first keyframe in the shape
        endTime = Math.min(endTime, c.getStart());
      }

      for (ChangeColor c : model.getColorCommands()) {
        // Find the stop position of the last keyFrame
        if (Math.abs(c.getStop() - startTime) < 0.01) {
          fromR = c.getColor()[3];
          fromG = c.getColor()[4];
          fromB = c.getColor()[5];
        }

        // Find the start position of the first keyFrame
        if (Math.abs(c.getStart() - endTime) < 0.01) {
          toR = c.getColor()[0];
          toG = c.getColor()[1];
          toB = c.getColor()[2];
        }
      }

      if (time > startTime) {
        model.addMotion(
                new ChangeColor(fromR, fromG, fromB, r, g, b, startTime, time, this.clock));
      } else {
        model.addMotion(new ChangeColor(r, g, b, toR, toG, toB, time, endTime, this.clock));
      }
    }
  }


  @Override
  public void editKeyFrame(ShapesModel model, Integer x, Integer y, Integer width, Integer height,
                           Integer r, Integer g, Integer b, Integer time) {
    if (time == null) {
      throw new IllegalArgumentException("time of this keyframe is not specify");
    } else {
      if (x != null && y != null) {
        this.editMoveKeyFrame(model, x, y, time);
      }

      if (width != null && height != null) {
        this.editSizeKeyFrame(model, width, height, time);
      }

      if (r != null && g != null && b != null) {
        this.editColorKeyFrame(model, r, g, b, time);
      }
    }
  }


  private void editMoveKeyFrame(ShapesModel model, int x, int y, double time) {
    for (Move a : model.getMoveCommands()) {
      if (Math.abs(time - a.getStart()) < 0.01) {
        int toX = a.getPosition()[2];
        int toY = a.getPosition()[3];
        double endTime = a.getStop();
        model.removeMotion(a);
        model.addMotion(new Move(x, y, toX, toY, time, endTime,
                this.clock)); // Need to test how the clock work
      } else if (Math.abs(time - a.getStop()) < 0.01) {
        int fromX = a.getPosition()[0];
        int fromY = a.getPosition()[1];
        double startTime = a.getStart();
        model.removeMotion(a);
        model.addMotion(new Move(fromX, fromY, x, y, startTime, time,
                this.clock)); // Need to test how the clock work
      } else if (time > a.getStart() && time < a.getStop()) {
        int fromX = a.getPosition()[0];
        int fromY = a.getPosition()[1];
        int toX = a.getPosition()[2];
        int toY = a.getPosition()[3];
        double endTime = a.getStop();
        double startTime = a.getStart();
        model.removeMotion(a);
        model.addMotion(new Move(fromX, fromY, x, y, startTime, time, this.clock));
        model.addMotion(new Move(x, y, toX, toY, time, endTime, this.clock));
      }
    }
  }

  private void editSizeKeyFrame(ShapesModel model, int width, int height, double time) {
    for (ChangeSize a : model.getSizeCommands()) {
      if (Math.abs(time - a.getStart()) < 0.01) {
        int toWidth = a.getDimension()[2];
        int toHeight = a.getDimension()[3];
        double endTime = a.getStop();
        model.removeMotion(a);
        model.addMotion(
                new ChangeSize(width, height, toWidth, toHeight, time, endTime, this.clock));
      } else if (Math.abs(time - a.getStop()) < 0.01) {
        int fromWidth = a.getDimension()[0];
        int fromHeight = a.getDimension()[1];
        double startTime = a.getStart();
        model.removeMotion(a);
        model.addMotion(
                new ChangeSize(fromWidth, fromHeight, width, height, startTime, time, this.clock));
      } else if (time > a.getStart() && time < a.getStop()) {
        int fromWidth = a.getDimension()[0];
        int fromHeight = a.getDimension()[1];
        int toWidth = a.getDimension()[2];
        int toHeight = a.getDimension()[3];
        double startTime = a.getStart();
        double endTime = a.getStop();
        model.removeMotion(a);
        model.addMotion(
                new ChangeSize(fromHeight, fromWidth, width, height, startTime, time, this.clock));
        model.addMotion(
                new ChangeSize(width, height, toWidth, toHeight, time, endTime, this.clock));
      }
    }
  }

  private void editColorKeyFrame(ShapesModel model, int r, int g, int b, double time) {
    for (ChangeColor a : model.getColorCommands()) {
      if (Math.abs(time - a.getStart()) < 0.01) {
        int toR = a.getColor()[3];
        int toG = a.getColor()[4];
        int toB = a.getColor()[5];
        double endTime = a.getStop();
        model.removeMotion(a);
        model.addMotion(new ChangeColor(r, g, b, toR, toG, toB, time, endTime, this.clock));
      } else if (Math.abs(time - a.getStop()) < 0.01) {
        int fromR = a.getColor()[0];
        int fromG = a.getColor()[1];
        int fromB = a.getColor()[2];
        double startTime = a.getStart();
        model.removeMotion(a);
        model.addMotion(
                new ChangeColor(fromR, fromG, fromB, r, g, b, startTime, time, this.clock));
      } else if (time > a.getStart() && time < a.getStop()) {
        int fromR = a.getColor()[0];
        int fromG = a.getColor()[1];
        int fromB = a.getColor()[2];
        int toR = a.getColor()[3];
        int toG = a.getColor()[4];
        int toB = a.getColor()[5];
        double startTime = a.getStart();
        double endTime = a.getStop();
        model.removeMotion(a);
        model.addMotion(
                new ChangeColor(fromR, fromG, fromB, r, g, b, startTime, time, this.clock));
        model.addMotion(
                new ChangeColor(r, g, b, toR, toG, toB, time, endTime, this.clock));
      }
    }
  }


  @Override
  public void removeKeyFrame(ShapesModel model, Integer time) {
    if (time == null) {
      throw new IllegalArgumentException("time of this keyframe is not specify");
    } else {
      this.removeMoveKeyFrame(model, time);
      this.removeSizeKeyFrame(model, time);
      this.removeColorKeyFrame(model, time);
    }
  }

  private void removeMoveKeyFrame(ShapesModel model, Integer time) {
    ArrayList<Move> moves = model.getMoveCommands();
    for (int i = 0; i < moves.size(); i++) {
      Move m = moves.get(i);
      if (Math.abs(time - m.getStop()) < 0.01) {
        model.removeMotion(m);
        if (i != moves.size() - 1) {
          Move next = moves.get(i + 1);
          model.removeMotion(next);
          model.addMotion(new Move(m.getPosition()[0], m.getPosition()[1], next.getPosition()[2],
                  next.getPosition()[3], m.getStart(), next.getStop(), this.clock));
        }
      }
    }
  }

  private void removeSizeKeyFrame(ShapesModel model, Integer time) {
    ArrayList<ChangeSize> changeSizes = model.getSizeCommands();
    for (int i = 0; i < changeSizes.size(); i++) {
      ChangeSize c = changeSizes.get(i);
      if (Math.abs(time - c.getStop()) < 0.01) {
        model.removeMotion(c);
        if (i != changeSizes.size() - 1) {
          ChangeSize next = changeSizes.get(i + 1);
          model.removeMotion(next);
          model.addMotion(new ChangeSize(c.getDimension()[0], c.getDimension()[1],
                  next.getDimension()[2], next.getDimension()[3], c.getStart(), next.getStop(),
                  this.clock));
        }
      }
    }
  }

  private void removeColorKeyFrame(ShapesModel model, Integer time) {
    ArrayList<ChangeColor> changeColors = model.getColorCommands();
    for (int i = 0; i < changeColors.size(); i++) {
      ChangeColor c = changeColors.get(i);
      if (Math.abs(time - c.getStop()) < 0.01) {
        model.removeMotion(c);
        if (i != changeColors.size() - 1) {
          ChangeColor next = changeColors.get(i + 1);
          model.removeMotion(next);
          model.addMotion(new ChangeColor(c.getColor()[0], c.getColor()[1],
                  c.getColor()[2], next.getColor()[3], next.getColor()[4],
                  next.getColor()[5], c.getStart(), next.getStop(),
                  this.clock));
        }
      }
    }
  }

  @Override
  public void addShapeToLayer(ShapesModel s, int layer) {
    for (Integer id : layers.keySet()) {
      if (layers.get(id).contains(s.getName())) {
        layers.get(id).remove(s.getName());
      }
    }
    if (layers.get(layer) == null) {
      layers.put(layer, new ArrayList<>());
    }
    try {
      layers.get(layer).add(s.getName());
    } catch (Exception e) {
      throw new IllegalArgumentException("Layer doesn't exist");
    }
  }

  @Override
  public void swapLayer(int fromLayer, int toLayer) {
    ArrayList<String> tempLayer = new ArrayList<>();
    tempLayer = layers.get(toLayer);
    layers.replace(toLayer, layers.get(fromLayer));
    layers.replace(fromLayer, tempLayer);
  }

  @Override
  public void deleteLayer(int layer) {
    if (layers.get(layer) == null) {
      return;
    }
    for (String shapeId : layers.get(layer)) {
      for (ShapesModel s : this.getShapes()) {
        if (s.getName() == shapeId) {
          this.getShapes().remove(s);
        }
      }
    }
    layers.remove(layer);
  }

  @Override
  public LinkedHashMap<Integer, ArrayList<String>> getLayers() {
    return this.layers;
  }

  @Override
  public void updateClock() {
    this.clock++;
  }

  @Override
  public double getClock() {
    return this.clock;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getLeftMost() {
    return this.leftMost;
  }

  @Override
  public int getTopMost() {
    return this.topMost;
  }

  /**
   * Builder Pattern for construct the animation.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    private int height;
    private int width;
    private int leftMost;
    private int topMost;
    private Map<String, ShapesModel> shapes;
    private LinkedHashMap<Integer, ArrayList<String>> layers;

    /**
     * Represent the Builder that parse in the given txt file.
     *
     * @param stringShapesModelHashMap represent the list of Shapes with its name as a key
     */
    public Builder(HashMap<String, ShapesModel> stringShapesModelHashMap) {
      this.height = 500;
      this.width = 500;
      this.leftMost = 0;
      this.topMost = 0;
      this.shapes = stringShapesModelHashMap;
      this.layers = new LinkedHashMap<>();
    }


    @Override
    public AnimatorModel build() {
      return new AnimatorModelImpl(height, width, leftMost, topMost,
              (LinkedHashMap<String, ShapesModel>) shapes, layers);
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.height = height;
      this.width = width;
      this.leftMost = x;
      this.topMost = y;
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type, int layer) {
      switch (type.toLowerCase()) {
        case "ellipse":
          this.shapes.put(name, new Oval(name,
                  new CartPosition(0, 0), new RGBColor(0, 0, 0),
                  new Dimension(0, 0), this.build().getClock()));
          break;
        case "rectangle":
          this.shapes.put(name, new Rectangle(name,
                  new CartPosition(0, 0), new RGBColor(0, 0, 0),
                  new Dimension(0, 0), this.build().getClock()));
          break;
        default:
          throw new IllegalArgumentException("Shapes cannot be created with the given parameter");
      }
      if (this.layers.get(layer) == null) {
        this.layers.put(layer, new ArrayList<String>());
      }
      this.layers.get(layer).add(name);
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(
            String name, Integer[] x) {
      int t1 = x[0];
      int x1 = x[1];
      int y1 = x[2];
      int w1 = x[3];
      int h1 = x[4];
      int r1 = x[5];
      int g1 = x[6];
      int b1 = x[7];
      int t2 = x[8];
      int x2 = x[9];
      int y2 = x[10];
      int w2 = x[11];
      int h2 = x[12];
      int r2 = x[13];
      int g2 = x[14];
      int b2 = x[15];

      AnimatorCommand move = new Move(x1, y1, x2, y2, t1, t2, this.build().getClock());
      AnimatorCommand changeColor = new ChangeColor(r1, g1, b1, r2, g2, b2, t1, t2,
              this.build().getClock());
      AnimatorCommand changeSize = new ChangeSize(w1, h1, w2, h2, t1, t2, this.build().getClock());
      this.shapes.get(name).addMotion(changeSize);
      this.shapes.get(name).addMotion(move);
      this.shapes.get(name).addMotion(changeColor);
      if (x[16] != null) {
        //add rotation
        double d1 = x[16];
        double xp1 = (this.shapes.get(name).getPosition().getX() +
                (this.shapes.get(name).getDimension().getWidth() / 2));
        double yp1 = (this.shapes.get(name).getPosition().getY() +
                (this.shapes.get(name).getDimension().getHeight() / 2));
        double d2 = x[17];
        double xp2 = (this.shapes.get(name).getPosition().getX() +
                (this.shapes.get(name).getDimension().getWidth() / 2));
        double yp2 = (this.shapes.get(name).getPosition().getY() +
                (this.shapes.get(name).getDimension().getHeight() / 2));

        AnimatorCommand rotate = new Rotate(d1, xp1, yp1, d2, xp2, yp2, t1, t2,
                this.build().getClock());
        this.shapes.get(name).addMotion(rotate);
      }
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
                                                       int h, int r, int g, int b) {
      return null;
    }
  }
}
