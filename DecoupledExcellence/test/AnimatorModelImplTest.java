import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Move;
import cs3500.animator.model.shapes.CartPosition;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.RGBColor;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.ShapesModel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for Animator Model.
 */
public class AnimatorModelImplTest {

  AnimatorModel m = new AnimatorModelImpl();
  ShapesModel r = new Rectangle("R", new CartPosition(200, 200),
      new RGBColor(0, 0, 0), new Dimension(10, 20), 0.0);
  ShapesModel c = new Oval("C", new CartPosition(100, 100),
      new RGBColor(255, 255, 255), new Dimension(10, 10), 0.0);
  ShapesModel o = new Oval("O", new CartPosition(100, 100),
      new RGBColor(255, 255, 255), new Dimension(10, 10), 0.0);

  @Before
  public void setUp() {
    this.m = new AnimatorModelImpl();
    this.r = new Rectangle("R", new CartPosition(200, 200),
        new RGBColor(0, 0, 0), new Dimension(10, 20), 0.0);
    this.c = new Oval("C", new CartPosition(100, 100),
        new RGBColor(255, 255, 255), new Dimension(10, 10), 0.0);
    this.o = new Oval("O", new CartPosition(100, 100),
        new RGBColor(255, 255, 255), new Dimension(10, 10), 0.0);
    this.m.addShape(this.r);
    this.m.addShape(this.c);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSecondConstructor() {
    new AnimatorModelImpl(500, 500, 0, 0);
    new AnimatorModelImpl(500, -500, 0, 0);
    new AnimatorModelImpl(-500, 500, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThirdConstructor() {
    new AnimatorModelImpl(500, 500, 0, 0,
        new LinkedHashMap<>(), -2.0);
    new AnimatorModelImpl(500, 500, 0, 0,
        new LinkedHashMap<>(), 2.0);
    new AnimatorModelImpl(-500, 500, 0, 0,
        new LinkedHashMap<>(), -2.0);
    new AnimatorModelImpl(500, -500, 0, 0,
        new LinkedHashMap<>(), 2.0);
  }

  @Test
  public void testAddShape() {
    this.m.addShape(this.c);
    assertEquals(this.r, this.m.findShape(this.r.getName()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShape() {
    this.m.removeShape(this.c);
    assertEquals(this.c, this.m.findShape(this.c.getName()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveIllegalShape() {
    ArrayList<ShapesModel> n = new ArrayList<>(Arrays.asList(this.r));
    this.m.removeShape(this.o);
  }

  @Test
  public void testMoveShape() {
    this.m.moveShape(this.c, 100, 100, 500, 500, 0, 2);
    this.m.runAnimation();
    assertEquals("Motion C 0 100 100 10 10 255 255 255 "
        + " 2 500 500 10 10 255 255 255", this.m.getAnimationStateShape(this.c));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveIllegalShape() {
    this.m.moveShape(this.o, 100, 100, 500, 500, 0, 2);
  }


  @Test
  public void testChangeColor() {
    this.m.changeColor(this.c, 255, 255, 255, 100, 100, 100, 0, 2);
    this.m.moveShape(this.r, 200, 200, 500, 500, 0, 2);
    this.m.runAnimation();
    assertEquals("Motion C 0 100 100 10 10 255 255 255 "
        + " 2 100 100 10 10 100 100 100", this.m.getAnimationStateShape(this.c));
    assertEquals("Motion R 0 200 200 10 20 0 0 0 "
        + " 2 500 500 10 20 0 0 0", this.m.getAnimationStateShape(this.r));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorIllegalShape() {
    this.m.changeColor(this.o, 255, 255, 255, 255, 0, 0, 0, 2);
  }

  @Test
  public void testChangeSize() {
    setUp();
    this.m.changeSize(this.r, 10, 20, 30, 30, 0, 3);
    this.m.runAnimation();
    assertEquals("Motion R 0 200 200 10 20 0 0 0  3 200 200 30 30 0 0 0"
        , this.m.getAnimationStateShape(this.r));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeIllegalShape() {
    this.m.changeSize(this.o, 10, 10, 10, 10, 0, 2);
  }

  @Test
  public void testGetAnimationState() {
    assertEquals("Motion C 0 100 100 10 10 255 255 255 "
            + " 0 100 100 10 10 255 255 255"
        , this.m.getAnimationStateShape(this.c));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetAnimationStateIllegalShape() {
    this.m.getAnimationStateShape(this.o);
  }

  @Test
  public void testGetAnimationStateShape() {
    this.m.changeColor(this.c, 255, 255, 255, 100, 100, 100, 0, 2);
    this.m.moveShape(this.r, 200, 200, 500, 500, 0, 2);
    this.m.runAnimation();
    assertEquals("Motion R 0 200 200 10 20 0 0 0  2 500 500 10 20 0 0 0\n"
            + "\n"
            + "Motion C 0 100 100 10 10 255 255 255  2 100 100 10 10 100 100 100",
        this.m.getAnimationState());

  }

  @Test
  public void testUpdateClock() {
    assertEquals(0.0, this.m.getClock(), 0.01);
    this.m.updateClock();
    assertEquals(1.0, this.m.getClock(), 0.01);
  }

  @Test
  public void testStartEditKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    AnimatorCommand move1 = new Move(0, 0, 5, 5, 1, 5, 1);
    this.r.addMotion(move1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move1)), this.r.getCommands());
    this.m.editKeyFrame(this.r, 3, 3, null, null, null, null, null, 5);
    AnimatorCommand move2 = new Move(0, 0, 3, 3, 1, 5, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2)), this.r.getCommands());
  }

  @Test
  public void testEndEditKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    AnimatorCommand move1 = new Move(0, 0, 5, 5, 1, 5, 1);
    this.r.addMotion(move1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move1)), this.r.getCommands());
    this.m.editKeyFrame(this.r, 3, 3, null, null, null, null, null, 1);
    AnimatorCommand move2 = new Move(3, 3, 5, 5, 1, 5, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2)), this.r.getCommands());
  }

  @Test
  public void testBetweenEditKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    AnimatorCommand move1 = new Move(0, 0, 5, 5, 1, 5, 1);
    this.r.addMotion(move1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move1)), this.r.getCommands());
    this.m.editKeyFrame(this.r, 3, 3, null, null, null, null, null, 2);
    AnimatorCommand move2 = new Move(0, 0, 3, 3, 1, 2, 1);
    AnimatorCommand move3 = new Move(3, 3, 5, 5, 2, 5, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2, move3)), this.r.getCommands());
  }

  @Test
  public void testInsertInitialKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    this.m.insertKeyFrame(this.r, 3, 3, null, null, null, null, null, 3);
    AnimatorCommand move1 = new Move(3, 3, 3, 3, 3, 3, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move1)), this.r.getCommands());
  }

  @Test
  public void testInsertAfterKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    this.m.moveShape(this.r, 3, 3, 5, 5, 2, 3);
    this.m.insertKeyFrame(this.r, 1, 1, null, null, null, null, null, 6);
    AnimatorCommand move1 = new Move(5, 5, 1, 1, 3, 6, 1);
    AnimatorCommand move2 = new Move(3, 3, 5, 5, 2, 3, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2, move1)), this.r.getCommands());
  }


  @Test
  public void testInsertBeforeKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result,this.r.getCommands());
    this.m.moveShape(this.r,3,3,5,5,2,3);
    this.m.insertKeyFrame(this.r,1,1,null,null,null,null,null,1);
    AnimatorCommand move1 = new Move(1,1,3,3,1,2,1);
    AnimatorCommand move2 = new Move(3,3,5,5,2,3,1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2,move1)),this.r.getCommands());
  }


  @Test
  public void testRemoveKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    this.m.moveShape(this.r, 3, 3, 3, 3, 2, 2);
    this.m.moveShape(this.r, 3, 3, 5, 5, 2, 3);
    this.m.moveShape(this.r, 5, 5, 7, 7, 3, 4);
    this.m.removeKeyFrame(this.r, 3);
    AnimatorCommand move1 = new Move(3, 3, 7, 7, 2, 4, 1);
    AnimatorCommand move2 = new Move(3, 3, 3, 3, 2, 2, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2, move1)), this.r.getCommands());
  }

  @Test
  public void testRemoveFirstKeyFrameAfterInitialize() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    this.m.moveShape(this.r, 3, 3, 3, 3, 2, 2);
    this.m.moveShape(this.r, 3, 3, 5, 5, 2, 3);
    this.m.removeKeyFrame(this.r, 3);
    AnimatorCommand move2 = new Move(3, 3, 3, 3, 2, 2, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2)), this.r.getCommands());
  }

  @Test
  public void testRemoveFirstKeyFrameAfterInitialize2() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    this.m.changeSize(this.r, 3, 3, 3, 3, 2, 2);
    this.m.changeSize(this.r, 3, 3, 5, 5, 2, 3);
    this.m.removeKeyFrame(this.r, 3);
    AnimatorCommand move2 = new ChangeSize(3, 3, 3, 3, 2, 2, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move2)), this.r.getCommands());
  }

  @Test
  public void testRemoveInitializeKeyFrame() {
    ArrayList<AnimatorCommand> result = new ArrayList<AnimatorCommand>();
    assertEquals(result, this.r.getCommands());
    this.m.moveShape(this.r, 3, 3, 3, 3, 2, 2);
    this.m.changeSize(this.r, 3, 3, 3, 3, 2, 2);
    this.m.moveShape(this.r, 3, 3, 5, 5, 2, 3);
    this.m.removeKeyFrame(this.r, 2);
    AnimatorCommand move1 = new Move(3, 3, 5, 5, 2, 3, 1);
    assertEquals(new ArrayList<AnimatorCommand>(Arrays.asList(move1)), this.r.getCommands());
  }

}