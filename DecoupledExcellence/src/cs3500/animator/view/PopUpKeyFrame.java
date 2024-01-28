package cs3500.animator.view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;


/**
 * Represent the Panel in which the client use to manage all keyFrame operation. It extends the
 * abstract pop up and creates the frame and buttons of the user interface for the key frame.
 */
public class PopUpKeyFrame extends PopUp {

  private JButton addKeyFrame;
  private JButton editKeyFrame;
  private JButton removeKeyFrame;

  private JTextField timeRemoved;

  /**
   * Represent the default Constructor for the PopUp keyFrame Panel.
   */
  public PopUpKeyFrame() {
    super();
    this.addKeyFrame = new JButton("Add KeyFrame");
    this.editKeyFrame = new JButton("Modify KeyFrame");
    this.removeKeyFrame = new JButton("Remove KeyFrame");
    this.addKeyFrame.setActionCommand("createKeyFrame");
    this.editKeyFrame.setActionCommand("editKeyFrame");
    this.removeKeyFrame.setActionCommand("deleteKeyFrame");
    buttonPanel.add(this.addKeyFrame);
    buttonPanel.add(this.editKeyFrame);
    buttonPanel.add(this.removeKeyFrame);

    this.removeJPanel.add(new JLabel("Name: "));
    this.removeJPanel.add(new JTextField("name", 20));
    this.timeRemoved = new JTextField("time", 20);
    this.removeJPanel.add(new JLabel("Time: "));
    this.removeJPanel.add(this.timeRemoved);
  }


  @Override
  public void makeVisible(String command) {
    setAllVisible(false);
    this.addKeyFrame.setVisible(false);
    this.removeKeyFrame.setVisible(false);
    this.editKeyFrame.setVisible(false);
    if (command.equalsIgnoreCase("addKeyFrame")) {
      setAllVisible(true);
      this.addKeyFrame.setVisible(true);
    } else if (command.equalsIgnoreCase("removeKeyFrame")) {
      this.removeKeyFrame.setVisible(true);
      this.removeJPanel.setVisible(true);
    } else if (command.equalsIgnoreCase("modifyKeyFrame")) {
      setAllVisible(true);
      this.editKeyFrame.setVisible(true);
    }
  }

  @Override
  public void setActionListener(ActionListener a) {
    this.addKeyFrame.addActionListener(a);
    this.editKeyFrame.addActionListener(a);
    this.removeKeyFrame.addActionListener(a);
  }

  @Override
  public Integer[] getShapeState() {
    Integer x = null;
    Integer y = null;
    Integer width = null;
    Integer height = null;
    Integer r = null;
    Integer g = null;
    Integer b = null;
    Integer time = null;

    if (this.name.isVisible()) {
      try {
        x = Integer.parseInt(this.x.getText());
        y = Integer.parseInt(this.y.getText());
        width = Integer.parseInt(this.width.getText());
        height = Integer.parseInt(this.height.getText());
        r = Integer.parseInt(this.r.getText());
        g = Integer.parseInt(this.g.getText());
        b = Integer.parseInt(this.b.getText());
        time = Integer.parseInt(this.time.getText());


      } catch (Exception e) {
        showErrorMessage(e.toString());
      }
    } else {
      try {
        time = Integer.parseInt(this.timeRemoved.getText());
      } catch (Exception e2) {
        showErrorMessage(e2.toString());
      }
    }
    Integer[] result = new Integer[]{x, y, width, height, r, g, b, time};
    return result;

  }
}
