import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.shapes.ShapesModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationSVGView;
import cs3500.animator.view.AnimatorView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/**
 * Represent the test for SVG representation.
 */
public class AnimationSVGViewTest {

  AnimatorModel model;
  AnimatorView view;

  @Before
  public void setup() throws FileNotFoundException {
    this.model = AnimationReader.parseFile(new FileReader("test.txt"),
        new AnimatorModelImpl.Builder(new HashMap<String, ShapesModel>()));

    this.view = new AnimationSVGView(this.model, 1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void makeVisible() {
    this.view.makeVisible();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void refresh() {
    this.view.refresh();
  }

  @Test
  public void testSVGView() {
    this.view.createAnimationOutput();
    assertEquals(
        "<svg viewBox=\"145 50 410 220\" xmlns=\"http://www.w3.org/2000/svg\">"
            + "<ellipse id=\"P1\" cx=\"0.0\" cy=\"0.0\" rx=\"0\" ry=\"0\" "
            + "fill=\"rgb(0.0,0.0,0.0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"396\" to=\"396\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"396\" to=\"396\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(240,122,106)\" to=\"rgb(240,122,106)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"rx\" from=\"8\" to=\"8\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"ry\" from=\"8\" to=\"8\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"cx\" from=\"396\" to=\"396\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"cy\" from=\"396\" to=\"396\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(240,122,106)\" to=\"rgb(240,122,106)\""
            + " fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"rx\" from=\"8\" to=\"8\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"ry\" from=\"8\" to=\"8\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<rect id=\"disk1\" x=\"0.0\" y=\"0.0\" width=\"0\" height=\"0\" "
            + "fill=\"rgb(0.0,0.0,0.0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"190\" to=\"190\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"180\" to=\"180\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,49,90)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"width\" from=\"20\" to=\"20\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"height\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"24000.0ms\" "
            + "attributeName=\"x\" from=\"190\" to=\"190\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"24000.0ms\" "
            + "attributeName=\"y\" from=\"180\" to=\"180\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"24000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,49,90)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"24000.0ms\" "
            + "attributeName=\"width\" from=\"20\" to=\"20\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"24000.0ms\" "
            + "attributeName=\"height\" from=\"30\" to=\"30\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>", this.view.getOutput().toString());
  }
}