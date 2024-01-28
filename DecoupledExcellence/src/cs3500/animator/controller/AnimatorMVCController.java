package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.shapes.CartPosition;
import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.RGBColor;
import cs3500.animator.model.shapes.Rectangle;

import cs3500.animator.view.Functionality;
import cs3500.animator.view.IEditorView;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JOptionPane;


/**
 * Represent the Controller that receive input from view and manage animator operation. This class
 * represents the implementation of the AnimationController interface.
 */
public class AnimatorMVCController implements AnimatorController, ActionListener {

  private AnimatorModel model;
  private IEditorView view;

  /**
   * Represent the Default constructor for the Animator MVC model.
   *
   * @param model represent the model of the Animator Excellence
   * @param view  represent the view of the animator Excellence
   */
  public AnimatorMVCController(AnimatorModel model, IEditorView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void executeCommand() {
    this.view.makeVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "start":
        this.view.startStopTimer();
        break;
      case "restart":
        this.view.restartTimer();
        break;
      case "enable_looping":
        this.view.setLoopStatus();
        break;
      case "increase_speed":
        this.view.setSpeed(Functionality.INCREASE_SPEED.toString());
        break;
      case "decrease_speed":
        this.view.setSpeed(Functionality.DECREASE_SPEED.toString());
        break;
      case "addShape":
        this.view.createShape();
        break;
      case "createRectangle":
        for (String s : this.view.getShapeData().keySet()) {
          try {
            int x = this.view.getShapeData().get(s)[0];
            int y = this.view.getShapeData().get(s)[1];
            int width = this.view.getShapeData().get(s)[2];
            int height = this.view.getShapeData().get(s)[3];
            int r = this.view.getShapeData().get(s)[4];
            int g = this.view.getShapeData().get(s)[5];
            int b = this.view.getShapeData().get(s)[6];
            int time = this.view.getShapeData().get(s)[7];
            CartPosition initialPosition = new CartPosition(0, 0);
            Dimension initialDimension = new Dimension(0, 0);
            RGBColor initialRgb = new RGBColor(0, 0, 0);
            Rectangle rect = new Rectangle(s, initialPosition, initialRgb, initialDimension
                , (double) time);
            model.addShape(rect);
            model.insertKeyFrame(rect,x,y,width,height,r,g,b,time);
          } catch (Exception er) {
            JOptionPane.showMessageDialog(new JPanel(), er, "ERROR!", JOptionPane.ERROR_MESSAGE);
          }
        }
        break;
      case "createEllipse":
        for (String s : this.view.getShapeData().keySet()) {
          try {
            int x = this.view.getShapeData().get(s)[0];
            int y = this.view.getShapeData().get(s)[1];
            int width = this.view.getShapeData().get(s)[2];
            int height = this.view.getShapeData().get(s)[3];
            int r = this.view.getShapeData().get(s)[4];
            int g = this.view.getShapeData().get(s)[5];
            int b = this.view.getShapeData().get(s)[6];
            int time = this.view.getShapeData().get(s)[7];
            CartPosition initialPosition = new CartPosition(0, 0);
            Dimension initialDimension = new Dimension(0, 0);
            RGBColor initialRgb = new RGBColor(0, 0, 0);
            Oval ell = new Oval(s, initialPosition, initialRgb, initialDimension, (double) time);
            model.addShape(ell);
            model.insertKeyFrame(ell,x,y,width,height,r,g,b,time);
          } catch (Exception er) {
            JOptionPane.showMessageDialog(new JPanel(), er, "ERROR!", JOptionPane.ERROR_MESSAGE);
          }
        }
        break;
      case "deleteShape":
        this.view.removeShape();
        break;
      case "removeShape":
        for (String s : this.view.getShapeData().keySet()) {
          model.removeShape(model.findShape(s));
        }
        break;
      case "addKeyFrame":
        this.view.addKeyFrame();
        break;
      case "createKeyFrame":
        for (String s :this.view.getKeyFrameData().keySet()) {
          Integer x = this.view.getKeyFrameData().get(s)[0];
          Integer y = this.view.getKeyFrameData().get(s)[1];
          Integer width = this.view.getKeyFrameData().get(s)[2];
          Integer height = this.view.getKeyFrameData().get(s)[3];
          Integer r = this.view.getKeyFrameData().get(s)[4];
          Integer g = this.view.getKeyFrameData().get(s)[5];
          Integer b = this.view.getKeyFrameData().get(s)[6];
          Integer time = this.view.getKeyFrameData().get(s)[7];
          model.insertKeyFrame(model.findShape(s),x,y,width,height,r,g,b,time);
        }
        break;
      case "modifyKeyFrame":
        this.view.editKeyFrame();
        break;
      case "editKeyFrame":
        for (String s :this.view.getKeyFrameData().keySet()) {
          Integer x = this.view.getKeyFrameData().get(s)[0];
          Integer y = this.view.getKeyFrameData().get(s)[1];
          Integer width = this.view.getKeyFrameData().get(s)[2];
          Integer height = this.view.getKeyFrameData().get(s)[3];
          Integer r = this.view.getKeyFrameData().get(s)[4];
          Integer g = this.view.getKeyFrameData().get(s)[5];
          Integer b = this.view.getKeyFrameData().get(s)[6];
          Integer time = this.view.getKeyFrameData().get(s)[7];
          model.editKeyFrame(model.findShape(s),x,y,width,height,r,g,b,time);
        }
        break;
      case "removeKeyFrame":
        this.view.removeKeyFrame();
        break;
      case "deleteKeyFrame":
        for (String s :this.view.getKeyFrameData().keySet()) {
          Integer time = this.view.getKeyFrameData().get(s)[7];
          model.removeKeyFrame(model.findShape(s),time);
        }
        break;
      case "addLayer":
        this.view.addLayer();
        break;
      case "addLayer2":
        for (String s :this.view.getLayerData().keySet()) {
          Integer layer = this.view.getLayerData().get(s)[0];
          model.addShapeToLayer(model.findShape(s), layer);
        }
        break;
      case "swapLayer":
        this.view.swapLayer();
        break;
      case "swapLayer2":
        for (String s :this.view.getLayerData().keySet()) {
          Integer layerFrom = this.view.getLayerData().get(s)[1];
          Integer layerTo = this.view.getLayerData().get(s)[2];
          model.swapLayer(layerFrom, layerTo);
        }
        break;
      case "deleteLayer":
        this.view.deleteLayer();
        break;
      case "deleteLayer2":
        for (String s :this.view.getLayerData().keySet()) {
          Integer layerDel = this.view.getLayerData().get(s)[3];
          model.deleteLayer(layerDel);
        }
        break;
      default:
        this.view.showErrorMessage("Invalid Command is called");
    }
  }

}
