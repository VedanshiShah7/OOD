package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Represents an abstract class that extends JFrame and implements the IPopUp interface that manages
 * UI in which what component of the PopUp panel is visible, and setting the ActionListener for each
 * component, and also parse in user input to the controller.
 */
public abstract class PopUp extends JFrame implements IPopUp {
  protected JPanel buttonPanel;
  protected JPanel textPanel;
  protected JPanel removeJPanel;

  protected JTextField name;
  protected JTextField time;
  protected JTextField x;
  protected JTextField y;
  protected JTextField width;
  protected JTextField height;
  protected JTextField r;
  protected JTextField g;
  protected JTextField b;

  protected JLabel prompt;

  /**
   * Represent the default Constructor for the abstract PopUp Panel.
   */
  public PopUp() {
    this.buttonPanel = new JPanel();
    this.textPanel = new JPanel();
    this.removeJPanel = new JPanel();

    // UI for create a pop up
    this.name = new JTextField("name", 20);
    enterValue("Name: ", this.name);

    this.time = new JTextField("time", 20);
    enterValue("Time: ", this.time);

    this.x = new JTextField("x pos", 20);
    enterValue("X position: ", this.x);

    this.y = new JTextField("y pos", 20);
    enterValue("Y position: ", this.y);

    this.width = new JTextField("width", 20);
    enterValue("Width: ", this.width);

    this.height = new JTextField("height", 20);
    enterValue("Height: ", this.height);

    this.r = new JTextField("red", 20);
    enterValue("Red Color: ", this.r);

    this.g = new JTextField("green", 20);
    enterValue("Green Color: ", this.g);

    this.b = new JTextField("b", 20);
    enterValue("Blue Color: ", this.b);

    this.setLayout(new FlowLayout());
    this.textPanel.setLayout(new GridLayout(20, 1));
    this.removeJPanel.setLayout(new GridLayout(10, 1));
    this.add(this.removeJPanel, BorderLayout.EAST);
    this.add(this.textPanel, BorderLayout.NORTH);
    this.add(this.buttonPanel, BorderLayout.SOUTH);

    this.setSize(500, 720);
    this.setResizable(true);
  }

  private void enterValue(String title, JTextField field) {
    this.prompt = new JLabel(title);
    this.textPanel.add(prompt);
    this.textPanel.add(field);
  }

  @Override
  public abstract void makeVisible(String command);

  protected void setAllVisible(Boolean bool) {
    this.name.setVisible(bool);
    this.time.setVisible(bool);
    this.x.setVisible(bool);
    this.y.setVisible(bool);
    this.width.setVisible(bool);
    this.height.setVisible(bool);
    this.r.setVisible(bool);
    this.g.setVisible(bool);
    this.b.setVisible(bool);
    this.textPanel.setVisible(bool);
    this.removeJPanel.setVisible(!bool);
  }

  @Override
  public abstract void setActionListener(ActionListener a);

  @Override
  public String getName() {
    return this.name.getText();
  }

  @Override
  public abstract Integer[] getShapeState();

  /**
   * To show error messages as a popUp on the frame.
   * @param error the error.
   */
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "ERROR!", JOptionPane.ERROR_MESSAGE);
  }
}
