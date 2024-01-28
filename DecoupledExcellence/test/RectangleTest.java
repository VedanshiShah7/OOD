import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.shapes.CartPosition;
import cs3500.animator.model.shapes.RGBColor;
import cs3500.animator.model.shapes.Rectangle;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Represent the test for Rectangle representation.
 */
public class RectangleTest {

  CartPosition position = new CartPosition(200, 200);
  RGBColor color = new RGBColor(255, 0, 0);
  Dimension dimension = new Dimension(10, 5);
  Rectangle r1 = new Rectangle("C", position, color, dimension, 0.0);
  AnimatorModel m = new AnimatorModelImpl();

  @Before
  public void setUp() {
    this.m = new AnimatorModelImpl();
    this.position = new CartPosition(200, 200);
    this.color = new RGBColor(255, 0, 0);
    this.dimension = new Dimension(10, 5);
    this.r1 = new Rectangle("C", position, color, dimension, 0.0);
    this.m.updateClock();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInputFirstConstructor() {
    new Rectangle("C", null, this.color, this.dimension, 1.0);
    new Rectangle("C", this.position, null, this.dimension, 1.0);
    new Rectangle("C", this.position, this.color, null, 1.0);
  }


  @Test
  public void testMove() {
    setUp();
    // T = 1
    this.r1.move(200, 200, 250, 250, 1, 2, 1); // Save the current state of Oval
    this.r1.move(200, 200, 250, 250, 1, 2, 2); // Move the Oval to 250, 250
    this.r1.move(250, 250, 350, 350, 2, 3, 2); // Save the current state of Oval
    CartPosition p2 = new CartPosition(250, 250);
    assertEquals(this.r1.getPosition(), p2); // Check current position at t = 2
    this.r1.move(250, 250, 350, 350, 2, 3, 2.25);
    CartPosition p3 = new CartPosition(275, 275);
    assertEquals(p3, this.r1.getPosition());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testDifferentStartFromLastMoveAnimation() {
    this.r1.move(200, 200, 250, 250, 0, 2, 0);
    this.r1.move(200, 200, 250, 250, 0, 2, 1);
    this.r1.move(200, 200, 250, 250, 0, 2, 2);
    this.r1.move(200, 200, 100, 100, 2, 5, 2);
  }

  @Test
  public void testChangeColor() {
    setUp();
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 1, 11, 1);
    assertEquals(new RGBColor(255.0, 0.0, 0.0), this.r1.getColor());
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 1, 11, 2);
    assertEquals(new RGBColor(229.5, 0.0, 0.0), this.r1.getColor());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testOverlapChangeColor() {
    this.r1.changeColor(255, 0, 0, 255, 250, 240, 4, 6, 4);
    this.r1.changeColor(255, 0, 0, 255, 250, 240, 1, 5, 1);
  }

  @Test
  public void testChangeDimension() {
    setUp();
    this.r1.changeSize(10, 5, 50, 50, 1, 2, 1);
    this.r1.changeSize(10, 5, 50, 50, 1, 2, 2);
    assertEquals(new Dimension(50, 50), this.r1.getDimension());
    this.r1.changeSize(50, 50, 20, 20, 2, 3, 2);
    this.r1.changeSize(50, 50, 20, 20, 2, 3, 3);
    assertEquals(new Dimension(20, 20), this.r1.getDimension());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDifferentStartFromLastDimensionAnimation() {
    this.r1.changeSize(10, 5, 250, 250, 0, 2, 0);
    this.r1.changeSize(10, 5, 250, 250, 0, 2, 1);
    this.r1.changeSize(200, 200, 250, 250, 0, 2, 2);
    this.r1.changeSize(200, 200, 100, 100, 2, 5, 2);
  }

  @Test
  public void testGetGameStateNoOverlap() {
    setUp();
    // T = 1
    this.r1.move(200, 200, 250, 250, 1, 2, 1);
    this.r1.move(200, 200, 250, 250, 1, 2, 2);
    this.r1.changeSize(10, 5, 20, 15, 2, 3, 2);
    this.r1.changeSize(10, 5, 20, 15, 2, 3, 3);
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 3, 4, 3);
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 3, 4, 4);
    assertEquals("Motion C 0 200 200 10 5 255 0 0  1 200 200 10 5 255 0 0\n"
            + "Motion C 1 200 200 10 5 255 0 0  2 250 250 10 5 255 0 0\n"
            + "Motion C 2 250 250 10 5 255 0 0  3 250 250 20 15 255 0 0\n"
            + "Motion C 3 250 250 20 15 255 0 0  4 250 250 20 15 0 0 0",
        this.r1.getAnimationState(4));
  }

  @Test
  public void testGetGameStateOverlap() {
    setUp();
    // t = 1;
    this.r1.move(200, 200, 600, 600, 1, 5, 1);
    this.r1.move(200, 200, 600, 600, 1, 5, 2);
    this.r1.changeSize(10, 5, 20, 20, 2, 5, 2);
    this.r1.move(200, 200, 600, 600, 1, 5, 3);
    this.r1.changeSize(10, 5, 20, 20, 2, 5, 3);
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 3, 5, 3);
    this.r1.move(200, 200, 600, 600, 1, 5, 4);
    this.r1.changeSize(10, 5, 20, 20, 2, 5, 4);
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 3, 5, 4);
    this.r1.move(200, 200, 600, 600, 1, 5, 5);
    this.r1.changeSize(10, 5, 20, 20, 2, 5, 5);
    this.r1.changeColor(255, 0, 0, 0, 0, 0, 3, 5, 5);
    assertEquals("Motion C 0 200 200 10 5 255 0 0  1 200 200 10 5 255 0 0\n" +
            "Motion C 1 200 200 10 5 255 0 0  2 300 300 10 5 255 0 0\n" +
            "Motion C 2 300 300 10 5 255 0 0  3 400 400 14 10 255 0 0\n" +
            "Motion C 3 400 400 14 10 255 0 0  5 600 600 20 20 0 0 0",
        this.r1.getAnimationState(5));

  }

  @Test
  public void testGetAnimationStateLoop() {

    while (this.m.getClock() < 8) {
      this.r1.move(200, 200, 500, 500, 1, 3, this.m.getClock());
      this.r1.changeColor(255, 0, 0, 0, 0, 0, 2, 4, this.m.getClock());
      this.r1.changeSize(10, 5, 50, 50, 3, 5, this.m.getClock());
      this.m.updateClock();
    }
    assertEquals("Motion C 0 200 200 10 5 255 0 0  1 200 200 10 5 255 0 0\n"
            + "Motion C 1 200 200 10 5 255 0 0  2 350 350 10 5 255 0 0\n"
            + "Motion C 2 350 350 10 5 255 0 0  3 500 500 10 5 128 0 0\n"
            + "Motion C 3 500 500 10 5 128 0 0  8 500 500 50 50 0 0 0",
        this.r1.getAnimationState(8));
  }

  @Test
  public void testGetDistinctCommand() {
    AnimatorCommand c1 = new Move(0, 0, 5, 5, 1, 1, 1);
    AnimatorCommand c2 = new ChangeColor(0, 0, 0, 255, 255, 255, 1, 1, 1);
    AnimatorCommand c3 = new ChangeSize(0, 0, 10, 10, 1, 1, 1);
    this.r1.addMotion(c1);
    this.r1.addMotion(c2);
    this.r1.addMotion(c3);

    assertEquals(new ArrayList<Move>(Arrays.asList((Move) c1)), this.r1.getMoveCommands());
    assertEquals(new ArrayList<ChangeColor>(Arrays.asList((ChangeColor) c2)),
        this.r1.getColorCommands());
    assertEquals(new ArrayList<ChangeSize>(Arrays.asList((ChangeSize) c3)),
        this.r1.getSizeCommands());

  }


}