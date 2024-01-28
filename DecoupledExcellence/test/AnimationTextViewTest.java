import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl.Builder;
import cs3500.animator.model.shapes.ShapesModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationTextView;
import cs3500.animator.view.AnimatorView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/**
 * Represent the Text representation of the animator.
 */
public class AnimationTextViewTest {

  AnimatorModel model;
  AnimatorView view;

  @Before
  public void setup() throws FileNotFoundException {
    this.model = AnimationReader.parseFile(new FileReader("test.txt"),
        new Builder(new HashMap<String, ShapesModel>()));

    this.view = new AnimationTextView(this.model);
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
  public void testTextView() {
    this.view.createAnimationOutput();
    assertEquals("canvas: 145 50 410 220\n"
            + "Shape P1 Ellipse \n"
            + "Motion P1 0 0 0 0 0 0 0 0  1 396 396 8 8 240 122 106\n"
            + "Motion P1 1 396 396 8 8 240 122 106  25 396 396 8 8 240 122 106\n"
            + "Shape disk1 Rectangle \n"
            + "Motion disk1 0 0 0 0 0 0 0 0  1 190 180 20 30 0 49 90\n"
            + "Motion disk1 1 190 180 20 30 0 49 90  25 190 180 20 30 0 49 90\n"
        , this.view.getOutput().toString());
  }


}