package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Represent the Panel in which the client use to manage all layer related operations. It implements
 * the pop up interface and creates the frame and buttons of the user interface for the layers.
 */
public class PopUpLayer extends JFrame implements IPopUp {

  protected JPanel swapPanel;
  protected JButton swapLayer;
  protected JTextField fromLayer;
  protected JTextField toLayer;
  protected JLabel takeFromLayer;
  protected JLabel takeToLayer;

  protected JPanel addPanel;
  protected JButton addLayer;
  protected JTextField layerName;
  protected JTextField shapeName;
  protected JLabel takeLayerName;
  protected JLabel takeShapeName;

  protected JPanel deletePanel;
  protected JButton deleteLayer;
  protected JTextField deleteLayerName;
  protected JLabel takeDeleteLayerName;

  /**
   * Represent the default Constructor for the PopUp layer Panel.
   */
  public PopUpLayer() {
    this.swapPanel = new JPanel();
    this.swapLayer = new JButton("Swap Layer");
    this.swapLayer.setActionCommand("swapLayer2");
    this.takeFromLayer = new JLabel("Layer from: ");
    this.takeToLayer = new JLabel("Layer to: ");
    this.fromLayer = new JTextField("fromLayer", 20);
    this.toLayer = new JTextField("toLayer", 20);
    this.swapPanel.add(takeFromLayer);
    this.swapPanel.add(this.fromLayer);
    this.swapPanel.add(takeToLayer);
    this.swapPanel.add(this.toLayer);
    this.swapPanel.add(this.swapLayer);


    this.addPanel = new JPanel();
    this.addLayer = new JButton("Add Layer");
    this.addLayer.setActionCommand("addLayer2");
    this.takeLayerName = new JLabel("Layer id: ");
    this.takeShapeName = new JLabel("Shape name: ");
    this.layerName = new JTextField("Layer to be added in", 20);
    this.shapeName = new JTextField("Shape to add in layer", 20);
    this.addPanel.add(this.takeLayerName);
    this.addPanel.add(this.layerName);
    this.addPanel.add(this.takeShapeName);
    this.addPanel.add(this.shapeName);
    this.addPanel.add(this.addLayer);

    this.deletePanel = new JPanel();
    this.deleteLayer = new JButton("Delete Layer");
    this.deleteLayer.setActionCommand("deleteLayer2");
    this.takeDeleteLayerName = new JLabel("Layer id: ");
    this.deleteLayerName = new JTextField("Layer to be deleted", 20);
    this.deletePanel.add(this.takeDeleteLayerName);
    this.deletePanel.add(this.deleteLayerName);
    this.deletePanel.add(this.deleteLayer);

    this.setSize(new Dimension(800, 100));
    this.setResizable(true);
    this.add(addPanel, BorderLayout.EAST);
    this.add(swapPanel, BorderLayout.WEST);
    this.add(deletePanel, BorderLayout.NORTH);
  }

  private void makeAddVisible(boolean bool) {
    this.addPanel.setVisible(bool);
    this.addLayer.setVisible(bool);
    this.takeShapeName.setVisible(bool);
    this.takeLayerName.setVisible(bool);
    this.shapeName.setVisible(bool);
    this.layerName.setVisible(bool);
  }

  private void makeSwapVisible(boolean bool) {
    this.swapPanel.setVisible(bool);
    this.swapLayer.setVisible(bool);
    this.fromLayer.setVisible(bool);
    this.toLayer.setVisible(bool);
    this.takeFromLayer.setVisible(bool);
    this.takeToLayer.setVisible(bool);
  }

  private void makeDeleteVisible(boolean bool) {
    this.deletePanel.setVisible(bool);
    this.deleteLayer.setVisible(bool);
    this.takeDeleteLayerName.setVisible(bool);
    this.deleteLayerName.setVisible(bool);
  }

  /**
   * represent a method in which it would summaries all the key frame data to be pass to
   * controller.
   *
   * @param command for which one to make visible.
   */
  @Override
  public void makeVisible(String command) {
    makeAddVisible(false);
    makeSwapVisible(false);
    makeDeleteVisible(false);

    if (command.equalsIgnoreCase("addLayer")) {
      makeAddVisible(true);
    } else if (command.equalsIgnoreCase("swapLayer")) {
      makeSwapVisible(true);
    } else if (command.equalsIgnoreCase("deleteLayer")) {
      makeDeleteVisible(true);
    }
  }

  /**
   * Set the action listener for all of the component inside the pop up window.
   *
   * @param a represent a action listener.
   */
  @Override
  public void setActionListener(ActionListener a) {
    this.addLayer.addActionListener(a);
    this.swapLayer.addActionListener(a);
    this.deleteLayer.addActionListener(a);
  }

  /**
   * Get the name from the user text field.
   *
   * @return name of the shape
   */
  @Override
  public String getName() {
    return this.shapeName.getText();
  }

  /**
   * Get the shape data that is enter by the client.
   *
   * @return position dimension and color of the shape
   */
  @Override
  public Integer[] getShapeState() {
    Integer layerNumber = null;
    Integer layerFrom = null;
    Integer layerTo = null;
    Integer layerNumberDel = null;
    try {
      if (this.addPanel.isVisible()) {
        layerNumber = Integer.parseInt(this.layerName.getText());
      } else if (this.swapPanel.isVisible()) {
        layerFrom = Integer.parseInt(this.fromLayer.getText());
        layerTo = Integer.parseInt(this.toLayer.getText());
      } else {
        layerNumberDel = Integer.parseInt(this.deleteLayerName.getText());
      }
    } catch (NumberFormatException e) {
      showErrorMessage(e.toString());
    }
    Integer[] result = new Integer[]{layerNumber, layerFrom, layerTo, layerNumberDel};
    return result;
  }

  /**
   * To show error messages as a popUp on the frame.
   *
   * @param error the error.
   */
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "ERROR!", JOptionPane.ERROR_MESSAGE);
  }
}