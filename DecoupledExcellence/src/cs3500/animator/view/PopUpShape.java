package cs3500.animator.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * Represent the Panel in which the client use to manage all keyFrame operation. It extends the
 * abstract pop up and creates the frame and buttons of the user interface for the shapes. To create
 * or delete shapes.
 */
public class PopUpShape extends PopUp {

  private JButton rect;
  private JButton ell;
  private JButton remove;
  private JTextField nameRemove;

  /**
   * Default Constructor for the PopUp Shape Panel.
   */
  public PopUpShape() {
    super();

    this.rect = new JButton("Create Rectangle");
    this.ell = new JButton("Create Ellipse");
    this.remove = new JButton("Remove");
    this.rect.setActionCommand("createRectangle");
    this.ell.setActionCommand("createEllipse");
    this.remove.setActionCommand("removeShape");
    this.buttonPanel.add(this.rect);
    this.buttonPanel.add(this.ell);
    this.buttonPanel.add(this.remove);

    this.nameRemove = new JTextField("name", 20);
    this.removeJPanel.add(new JLabel("Name: "));
    this.removeJPanel.add(this.nameRemove);
  }

  @Override
  public void makeVisible(String command) {
    setAllVisible(false);
    this.rect.setVisible(false);
    this.ell.setVisible(false);
    this.remove.setVisible(false);
    if (command.equalsIgnoreCase("addShape")) {
      setAllVisible(true);
      this.rect.setVisible(true);
      this.ell.setVisible(true);
    } else if (command.equalsIgnoreCase("remove")) {
      setAllVisible(false);
      this.remove.setVisible(true);
      this.nameRemove.setVisible(true);
    }
  }

  @Override
  public void setActionListener(ActionListener a) {
    this.rect.addActionListener(a);
    this.ell.addActionListener(a);
    this.remove.addActionListener(a);
  }

  @Override
  public Integer[] getShapeState() {
    try {
      Integer x = Integer.parseInt(this.x.getText());
      Integer y = Integer.parseInt(this.y.getText());
      Integer width = Integer.parseInt(this.width.getText());
      Integer height = Integer.parseInt(this.height.getText());
      Integer r = Integer.parseInt(this.r.getText());
      Integer g = Integer.parseInt(this.g.getText());
      Integer b = Integer.parseInt(this.b.getText());
      Integer time = Integer.parseInt(this.time.getText());
      Integer[] result = new Integer[]{x, y, width, height, r, g, b, time};
      return result;
    } catch (Exception e) {
      showErrorMessage(e.toString());
      return new Integer[]{};
    }
  }

}
