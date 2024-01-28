import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.shapes.CartPosition;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.RGBColor;
import java.awt.Dimension;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Oval Implementation.
 */
public class OvalTest {

  CartPosition position = new CartPosition(200, 200);
  RGBColor color = new RGBColor(255, 0, 0);
  Dimension dimension = new Dimension(10, 5);
  Oval o1 = new Oval("C", position, color, dimension, 0.0);
  AnimatorModel m = new AnimatorModelImpl();

  @Before
  public void setUp() {
    this.m = new AnimatorModelImpl();
    this.position = new CartPosition(200, 200);
    this.color = new RGBColor(255, 0, 0);
    this.dimension = new Dimension(10, 5);
    this.o1 = new Oval("C", position, color, dimension, 0.0);
    this.m.updateClock();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInputFirstConstructor() {
    new Oval("C", null, this.color, this.dimension, 1.0);
    new Oval("C", this.position, null, this.dimension, 1.0);
    new Oval("C", this.position, this.color, null, 1.0);
  }


  @Test
  public void testMove() {
    setUp();
    // T = 1
    this.o1.move(200, 200, 250, 250, 1, 2, 1); // Save the current state of Oval
    this.o1.move(200, 200, 250, 250, 1, 2, 2); // Move the Oval to 250, 250
    this.o1.move(250, 250, 350, 350, 2, 3, 2); // Save the current state of Oval
    CartPosition p2 = new CartPosition(250, 250);
    assertEquals(this.o1.getPosition(), p2); // Check current position at t = 2
    this.o1.move(250, 250, 350, 350, 2, 3, 2.25);
    CartPosition p3 = new CartPosition(275, 275);
    assertEquals(this.o1.getPosition(), p3);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testDifferentStartFromLastMoveAnimation() {
    this.o1.move(200, 200, 250, 250, 0, 2, 0);
    this.o1.move(200, 200, 250, 250, 0, 2, 1);
    this.o1.move(200, 200, 250, 250, 0, 2, 2);
    this.o1.move(200, 200, 100, 100, 2, 5, 2);
  }

  @Test
  public void testChangeColor() {
    setUp();
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 1, 11, 1);
    assertEquals(new RGBColor(255.0, 0.0, 0.0), this.o1.getColor());
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 1, 11, 2);
    assertEquals(new RGBColor(229.5, 0.0, 0.0), this.o1.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlapChangeColor() {
    this.o1.changeColor(255, 0, 0, 255, 250, 240, 4, 6, 4);
    this.o1.changeColor(255, 0, 0, 255, 250, 240, 1, 5, 1);
  }

  @Test
  public void testChangeDimension() {
    setUp();
    this.o1.changeSize(10, 5, 50, 50, 1, 2, 1);
    this.o1.changeSize(10, 5, 50, 50, 1, 2, 2);
    assertEquals(new Dimension(50, 50), this.o1.getDimension());
    this.o1.changeSize(50, 50, 20, 20, 2, 3, 2);
    this.o1.changeSize(50, 50, 20, 20, 2, 3, 3);
    assertEquals(new Dimension(20, 20), this.o1.getDimension());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testDifferentStartFromLastDimensionAnimation() {
    this.o1.changeSize(10, 5, 250, 250, 0, 2, 0);
    this.o1.changeSize(10, 5, 250, 250, 0, 2, 1);
    this.o1.changeSize(200, 200, 250, 250, 0, 2, 2);
    this.o1.changeSize(200, 200, 100, 100, 2, 5, 2);
  }


  @Test
  public void testGetGameStateNoOverlap() {
    setUp();
    // T = 1
    this.o1.move(200, 200, 250, 250, 1, 2, 1);
    this.o1.move(200, 200, 250, 250, 1, 2, 2);
    this.o1.changeSize(10, 5, 20, 15, 2, 3, 2);
    this.o1.changeSize(10, 5, 20, 15, 2, 3, 3);
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 3, 4, 3);
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 3, 4, 4);
    assertEquals("Motion C 0 200 200 10 5 255 0 0  1 200 200 10 5 255 0 0\n"
            + "Motion C 1 200 200 10 5 255 0 0  2 250 250 10 5 255 0 0\n"
            + "Motion C 2 250 250 10 5 255 0 0  3 250 250 20 15 255 0 0\n"
            + "Motion C 3 250 250 20 15 255 0 0  4 250 250 20 15 0 0 0",
        this.o1.getAnimationState(4));
  }

  @Test
  public void testGetGameStateOverlap() {
    setUp();
    // t = 1;
    this.o1.move(200, 200, 600, 600, 1, 5, 1);
    this.o1.move(200, 200, 600, 600, 1, 5, 2);
    this.o1.changeSize(10, 5, 20, 20, 2, 5, 2);
    this.o1.move(200, 200, 600, 600, 1, 5, 3);
    this.o1.changeSize(10, 5, 20, 20, 2, 5, 3);
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 3, 5, 3);
    this.o1.move(200, 200, 600, 600, 1, 5, 4);
    this.o1.changeSize(10, 5, 20, 20, 2, 5, 4);
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 3, 5, 4);
    this.o1.move(200, 200, 600, 600, 1, 5, 5);
    this.o1.changeSize(10, 5, 20, 20, 2, 5, 5);
    this.o1.changeColor(255, 0, 0, 0, 0, 0, 3, 5, 5);
    assertEquals("Motion C 0 200 200 10 5 255 0 0  1 200 200 10 5 255 0 0\n" +
            "Motion C 1 200 200 10 5 255 0 0  2 300 300 10 5 255 0 0\n" +
            "Motion C 2 300 300 10 5 255 0 0  3 400 400 14 10 255 0 0\n" +
            "Motion C 3 400 400 14 10 255 0 0  5 600 600 20 20 0 0 0",
        this.o1.getAnimationState(5));

  }

  @Test
  public void testGetAnimationStateLoop() {

    while (this.m.getClock() < 8) {
      this.o1.move(200, 200, 500, 500, 1, 3, this.m.getClock());
      this.o1.changeColor(255, 0, 0, 0, 0, 0, 2, 4, this.m.getClock());
      this.o1.changeSize(10, 5, 50, 50, 3, 5, this.m.getClock());
      this.m.updateClock();
    }
    assertEquals("Motion C 0 200 200 10 5 255 0 0  1 200 200 10 5 255 0 0\n"
            + "Motion C 1 200 200 10 5 255 0 0  2 350 350 10 5 255 0 0\n"
            + "Motion C 2 350 350 10 5 255 0 0  3 500 500 10 5 128 0 0\n"
            + "Motion C 3 500 500 10 5 128 0 0  8 500 500 50 50 0 0 0",
        this.o1.getAnimationState(8));
  }

  @Test
  public void testAddMotion() {
    AnimatorCommand move = new Move(200, 200, 500, 500, 0, 3, 0);
    this.o1.addMotion(move);
    this.o1.runAnimation();
    this.o1.updateAnimatorClock(1);
    this.o1.runAnimation();
    this.o1.updateAnimatorClock(2);
    this.o1.runAnimation();
    assertEquals("Motion C 0 200 200 10 5 255 0 0  2 400 400 10 5 255 0 0",
        this.o1.getAnimationState(2));

  }

  @Test
  public void isMotionOver() {
    AnimatorCommand move = new Move(200, 200, 500, 500, 0, 3, this.m.getClock());
    this.o1.addMotion(move);
    assertEquals(false, this.o1.motionOver(0));
    assertEquals(true, this.o1.motionOver(4));
  }


}