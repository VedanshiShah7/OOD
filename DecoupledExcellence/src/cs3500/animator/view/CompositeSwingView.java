package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

import cs3500.animator.model.AnimatorModel;

/**
 * Represents a  composite view in which will compose of UI to add remove shapes and key frame or
 * edit keyframe. This is an implementation of the IEditorView interface and extends JFrame. It uses
 * Java Swing to draw the results of the animation. It shows any error messages using a pop-up
 * dialog box, offers scrolling ability, and shows the animation.
 */
public class CompositeSwingView extends JFrame implements IEditorView {

  //Keep track of the button on the Bottom of the Window
  private JButton playButton;
  private JButton restartButton;
  private JButton enableLoopingButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;

  // Left Sidebar button
  private JButton createShapeButton;
  private JButton deleteShapeButton;

  private JButton addKeyFrameButton;
  private JButton removeKeyFrameButton;
  private JButton modifyKeyFrameButton;

  private JButton addLayer;
  private JButton swapLayer;
  private JButton deleteLayer;

  private PopUpShape shapeManagerPanel;
  private PopUpKeyFrame keyFrameManagerPanel;
  private PopUpLayer layerMangerPanel;

  private AnimatorPanel mainPanel;
  private boolean looping;

  /**
   * Set up the UI for the user to use.
   *
   * @param panel represent the panel in which the animation play
   * @param model represent the model in which the information will be pass in
   */
  public CompositeSwingView(AnimatorPanel panel, AnimatorModel model) {
    super();
    JPanel buttonPanel = new JPanel();
    JPanel keyFramePanel = new JPanel();
    JPanel shapePanel = new JPanel();
    JPanel layerPanel = new JPanel();
    this.shapeManagerPanel = new PopUpShape();
    this.keyFrameManagerPanel = new PopUpKeyFrame();
    this.layerMangerPanel = new PopUpLayer();
    this.mainPanel = panel;

    this.looping = false;
    this.setLayout(new BorderLayout());

    // Frame
    this.setTitle("Excellence Animator");
    this.setMinimumSize(panel.getMinimumSize());
    this.setMaximumSize(panel.getMaximumSize());
    this.setSize(getMaximumSize());
    this.setResizable(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));

    JScrollPane scrollPane = new JScrollPane(buttonPanel); // added this for scrolling
    scrollPane.setPreferredSize(new Dimension(panel.getWidth() + 20,
            panel.getHeight() + 20)); //resizing added
    this.add(scrollPane, BorderLayout.CENTER); // adds the scroll pane to the frame

    // COMPONENTS
    // For Button Panel
    this.playButton = new JButton("Play");
    this.playButton.setActionCommand(Functionality.START.toString());
    buttonPanel.add(this.playButton);
    this.restartButton = new JButton("Restart");
    this.restartButton.setActionCommand(Functionality.RESTART.toString());
    buttonPanel.add(this.restartButton);
    this.enableLoopingButton = new JButton("Enable Looping");
    this.enableLoopingButton.setActionCommand(Functionality.ENABLE_LOOPING.toString());
    buttonPanel.add(this.enableLoopingButton);
    this.increaseSpeedButton = new JButton("Increase Speed");
    this.increaseSpeedButton.setActionCommand(Functionality.INCREASE_SPEED.toString());
    buttonPanel.add(this.increaseSpeedButton);
    this.decreaseSpeedButton = new JButton("Decrease Speed");
    this.decreaseSpeedButton.setActionCommand(Functionality.DECREASE_SPEED.toString());
    buttonPanel.add(this.decreaseSpeedButton);


    // KEY FRAMES
    keyFramePanel.setLayout(new GridLayout(20, 1));
    this.addKeyFrameButton = new JButton("Add Key Frame");
    this.addKeyFrameButton.setActionCommand(KeyframeEdit.ADD_KEYFRAME.toString());
    keyFramePanel.add(this.addKeyFrameButton);
    this.removeKeyFrameButton = new JButton("Remove Key Frame");
    this.removeKeyFrameButton.setActionCommand(KeyframeEdit.REMOVE_KEYFRAME.toString());
    keyFramePanel.add(this.removeKeyFrameButton);
    this.modifyKeyFrameButton = new JButton("Modify Key Frame");
    this.modifyKeyFrameButton.setActionCommand(KeyframeEdit.MODIFY_KEYFRAME.toString());
    keyFramePanel.add(this.modifyKeyFrameButton);
    keyFramePanel.setLayout(new BoxLayout(keyFramePanel, BoxLayout.Y_AXIS));

    // LAYERS
    layerPanel.setLayout(new GridLayout(20, 1));
    this.addLayer = new JButton("Add Layer");
    this.addLayer.setActionCommand(KeyframeEdit.ADD_LAYER.toString());
    layerPanel.add(addLayer);
    this.swapLayer = new JButton("Swap Layer");
    this.swapLayer.setActionCommand(KeyframeEdit.SWAP_LAYER.toString());
    layerPanel.add(swapLayer);
    this.deleteLayer = new JButton("Delete Layer");
    this.deleteLayer.setActionCommand(KeyframeEdit.DELETE_LAYER.toString());
    layerPanel.add(deleteLayer);
    layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.Y_AXIS));

    // SHAPES
    shapePanel.setLayout(new GridLayout(20, 1));
    this.createShapeButton = new JButton("Create Shape");
    this.createShapeButton.setActionCommand(KeyframeEdit.ADD_SHAPE.toString());
    shapePanel.add(this.createShapeButton);
    this.deleteShapeButton = new JButton("Delete Shape");
    this.deleteShapeButton.setActionCommand(KeyframeEdit.DELETE_SHAPE.toString());
    shapePanel.add(this.deleteShapeButton);
    shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));

    JPanel leftPanel = new JPanel();
    leftPanel.add(shapePanel);
    leftPanel.add(layerPanel);
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

    JPanel bottomPanel = new JPanel();
    bottomPanel.add(buttonPanel);
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
    this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    this.add(keyFramePanel, BorderLayout.EAST);
    this.add(bottomPanel, BorderLayout.SOUTH);
    this.add(leftPanel, BorderLayout.WEST);
    this.add(this.mainPanel, BorderLayout.CENTER);

  }

  /**
   * Make the view visible to the client.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Transmit an error message to the client, in case animation command is not properly set.
   *
   * @param error String that represent the error
   */
  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "ERROR!", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Refresh the view of the animation.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Provide the view with an action listener.
   *
   * @param actionEvent represent the actionListener
   */
  @Override
  public void setCommandListener(ActionListener actionEvent) {
    this.playButton.addActionListener(actionEvent);
    this.restartButton.addActionListener(actionEvent);
    this.enableLoopingButton.addActionListener(actionEvent);
    this.increaseSpeedButton.addActionListener(actionEvent);
    this.decreaseSpeedButton.addActionListener(actionEvent);
    this.addKeyFrameButton.addActionListener(actionEvent);
    this.removeKeyFrameButton.addActionListener(actionEvent);
    this.modifyKeyFrameButton.addActionListener(actionEvent);
    this.createShapeButton.addActionListener(actionEvent);
    this.deleteShapeButton.addActionListener(actionEvent);
    this.shapeManagerPanel.setActionListener(actionEvent);
    this.keyFrameManagerPanel.setActionListener(actionEvent);
    this.addLayer.addActionListener(actionEvent);
    this.swapLayer.addActionListener(actionEvent);
    this.deleteLayer.addActionListener(actionEvent);
    this.layerMangerPanel.setActionListener(actionEvent);
  }

  /**
   * Writes a console output of the Animator Model.
   */
  @Override
  public void createAnimationOutput() {
    throw new UnsupportedOperationException("Invalid command for create animation");
  }

  /**
   * Gets the output of the Animator Output for text and SVG view.
   *
   * @return String the output to be written in the file output
   */
  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("Invalid command for create animation");
  }

  /**
   * Allow client to modify the tick speed for the animation.
   *
   * @param framePerSecond represent the number of tick per second
   */
  @Override
  public void changeTickSpeed(int framePerSecond) {
    throw new UnsupportedOperationException("Invalid command for create animation");
  }

  @Override
  public void startStopTimer() {
    if (this.playButton.getText().equals("Play")) {
      this.mainPanel.startStopAnimation("Play");
      this.playButton.setText("Pause");
    } else {
      this.mainPanel.startStopAnimation("Pause");
      this.playButton.setText("Play");
    }
  }

  @Override
  public void restartTimer() {
    if (this.playButton.getText().equalsIgnoreCase("Play")) {
      this.playButton.setText("Pause");
    }
    this.mainPanel.restartAnimation();
  }

  @Override
  public void setLoopStatus() {
    if (this.looping) {
      this.looping = false;
      this.enableLoopingButton.setText("Enable Looping");
      this.mainPanel.setLoop(false);
    } else {
      this.looping = true;
      this.enableLoopingButton.setText("Disable Looping");
      this.mainPanel.setLoop(true);
    }
  }

  @Override
  public void createShape() {
    this.shapeManagerPanel.makeVisible("addShape");
    this.shapeManagerPanel.setVisible(true);
  }

  @Override
  public void removeShape() {
    this.shapeManagerPanel.makeVisible("remove");
    this.shapeManagerPanel.setVisible(true);
  }

  @Override
  public void addKeyFrame() {
    this.keyFrameManagerPanel.makeVisible("addKeyFrame");
    this.keyFrameManagerPanel.setVisible(true);

  }

  @Override
  public void editKeyFrame() {
    this.keyFrameManagerPanel.makeVisible("modifyKeyFrame");
    this.keyFrameManagerPanel.setVisible(true);

  }

  @Override
  public void removeKeyFrame() {
    this.keyFrameManagerPanel.makeVisible("removeKeyFrame");
    this.keyFrameManagerPanel.setVisible(true);

  }

  @Override
  public void addLayer() {
    this.layerMangerPanel.makeVisible("addLayer");
    this.layerMangerPanel.setVisible(true);
  }

  @Override
  public void swapLayer() {
    this.layerMangerPanel.makeVisible("swapLayer");
    this.layerMangerPanel.setVisible(true);
  }

  @Override
  public void deleteLayer() {
    this.layerMangerPanel.makeVisible("deleteLayer");
    this.layerMangerPanel.setVisible(true);
  }

  @Override
  public Map<String, Integer[]> getShapeData() {
    Map<String, Integer[]> result = new HashMap<>();
    result.put(this.shapeManagerPanel.getName(), this.shapeManagerPanel.getShapeState());
    return result;
  }

  @Override
  public Map<String, Integer[]> getKeyFrameData() {
    Map<String, Integer[]> result = new HashMap<>();
    result.put(this.keyFrameManagerPanel.getName(), this.keyFrameManagerPanel.getShapeState());
    return result;
  }

  @Override
  public Map<String, Integer[]> getLayerData() {
    Map<String, Integer[]> result = new HashMap<>();
    result.put(this.layerMangerPanel.getName(), this.layerMangerPanel.getShapeState());
    return result;
  }

  @Override
  public void setSpeed(String command) {
    this.mainPanel.setSpeed(command);
  }

}