package cs3500.animator.view;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Represent the View for Excellence Animator using Swing library. This is an implementation of the
 * AnimatorView that uses Java Swing to draw the results of the animation. It shows any error
 * messages using a pop-up dialog box, offers scrolling ability, and shows the animation.
 */
public class AnimatorSwingView extends JFrame implements AnimatorView {

  private final AnimatorPanel panel;

  /**
   * Represent a default constructor for AnimationPanel.
   *
   * @param panel    represent the panel in which it would animate
   * @param leftMost represent the leftMost coordinate.
   * @param topMost  represent the topMost coordinate.
   * @param width    represent the width of the frame window.
   * @param height   represent the height of the frame window.
   */
  public AnimatorSwingView(AnimatorPanel panel, int leftMost, int topMost, int width, int height) {
    super();
    this.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(width, height));
    this.panel = panel;
    this.setSize(width, height);
    this.setBounds(leftMost, topMost, width, height);
    this.setTitle("Excellence Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane scrollPane = new JScrollPane((Component) panel); // added this for scrolling
    scrollPane.setPreferredSize(new Dimension(width + 20, height + 20));
    //resizing added
    this.add(scrollPane, BorderLayout.CENTER); // adds the scroll pane to the frame
    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "ERROR!",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setCommandListener(ActionListener actionEvent) {
    throw new UnsupportedOperationException("Invalid command for create animation");
  }

  @Override
  public void createAnimationOutput() {
    throw new UnsupportedOperationException("Invalid command for create animation");
  }

  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("Invalid command for create animation");
  }

  @Override
  public void changeTickSpeed(int framePerSecond) {
    this.panel.changeTickSpeed(framePerSecond);
  }

}
