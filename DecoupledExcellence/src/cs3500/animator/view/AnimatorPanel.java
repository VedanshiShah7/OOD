package cs3500.animator.view;


import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.commands.AnimatorCommand;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.ShapesModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 * Represent the Panel to display the animation. Used to create the panel which is used by the
 * JFrame. It represents the region where the animation must be drawn. It is a container that
 * represents custom drawing.
 */
public class AnimatorPanel extends JPanel implements ActionListener {

  private AnimatorModel model;
  private double tickspeed; // made the tickspeed double
  private double currentTime;
  private boolean isLoop;
  private Timer clock;
  private JSlider slider;

  /**
   * Represent the default constructor for the animation Panel to take in Shapes and speed of
   * animation.
   *
   * @param model     represent the model to animate
   * @param tickspeed represent the speed of the animation. (need more information about how to
   *                  implement it)
   */
  public AnimatorPanel(AnimatorModel model, double tickspeed) { // made the tickspeed double
    super();
    this.model = model;
    this.tickspeed = tickspeed;
    this.currentTime = 0.0;
    this.isLoop = false;
    this.clock = new Timer((int) (1000 / this.tickspeed), this); // had to cast to int
    double maxTime;
    maxTime = this.maxTime();
    slider = new JSlider(0, (int) maxTime);
    slider.setValue(0);
    slider.setMajorTickSpacing(50);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setValue((int) this.currentTime);
    this.add(slider);
    slider.addChangeListener((ChangeEvent e) -> {
      JSlider eSource = (JSlider) e.getSource();
      int time = eSource.getValue();
      this.currentTime = time;
      for (ShapesModel s : this.model.getShapes()) {
        s.updateAnimatorClock(time);
        s.runAnimation();
        this.repaint();
      }
    });

    this.clock.setDelay(1);
    startTimer();
    this.setVisible(false);
  }

  /**
   * Returns the shapes with the given name.
   *
   * @param name shapes with given name.
   * @return ShapesModel of the shape.
   */
  public ShapesModel getShape(String name) {
    for (ShapesModel s : this.model.getShapes()) {
      if (s.getName().equals(name)) {
        return s;
      }
    }
    return null;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    AffineTransform old = g2d.getTransform();
    for (Integer layerId : this.model.getLayers().keySet()) {
      ArrayList<String> layer = this.model.getLayers().get(layerId);
      for (String sId : layer) {
        ShapesModel s = this.getShape(sId);
        Integer minTime = null;
        Integer maxTime = null;
        s.saveAnimation(currentTime);
        for (AnimatorCommand a : s.getCommands()) {
          if (minTime == null || minTime > a.getStart()) {
            minTime = (int) a.getStart();
          }
          if (maxTime == null || maxTime < a.getStop()) {
            maxTime = (int) a.getStop();
          }
          if (this.currentTime >= minTime && this.currentTime <= maxTime) {
            g2d.rotate(Math.toRadians(s.getRotation().getDegree()), s.getPosition().getX() +
                    (s.getDimension().getWidth() / 2), s.getPosition().getY() +
                    (s.getDimension().getHeight() / 2));
            if (s instanceof Rectangle) {
              g2d.fillRect((int) s.getPosition().getX(), (int) s.getPosition().getY()
                      , (int) s.getDimension().getWidth(), (int) s.getDimension().getHeight());
            } else {
              g2d.fillOval((int) s.getPosition().getX(), (int) s.getPosition().getY()
                      , (int) s.getDimension().getWidth(), (int) s.getDimension().getHeight());
            }
            g2d.setColor(new Color((int) s.getColor().getR(), (int) s.getColor().getG(),
                    (int) s.getColor().getB()));
            g2d.setTransform(old);
          }
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.repaint();
    for (ShapesModel s : this.model.getShapes()) {
      s.updateAnimatorClock(this.currentTime);
      s.runAnimation();
      this.repaint();
    }
    this.currentTime = this.currentTime + 1;
    this.slider.setValue((int) this.currentTime);
    if (this.willLoop() && clock.getDelay() == 1) {
      this.setVisible(true);
      this.clock.setDelay((int) (1000 / this.tickspeed));
      this.restartAnimation();
      this.currentTime = 0;
      this.clock.restart();
      this.slider.setValue((int) this.currentTime);
      this.clock.stop();
    }
    if (this.willLoop() && this.isLoop) {
      this.restartAnimation();
    }
  }

  /**
   * To start and stop the animation.
   *
   * @param command play or pause for the animation.
   */
  public void startStopAnimation(String command) {
    if (command.equalsIgnoreCase("Pause")) {
      this.clock.stop();
    } else if (command.equalsIgnoreCase("Play")) {
      this.clock.start();
    }
  }

  protected void restartAnimation() {
    this.clock.restart();
    this.currentTime = 1;
  }

  protected void setLoop(boolean status) {
    this.isLoop = status;
  }

  protected boolean willLoop() {
    double lastTime = 0.0;
    for (ShapesModel s : this.model.getShapes()) {
      for (AnimatorCommand a : s.getCommands()) {
        lastTime = Math.max(lastTime, a.getStop());
      }
    }

    boolean result = this.currentTime >= lastTime;
    return result;
  }

  protected void setSpeed(String command) {
    if (tickspeed > 0) {
      if (command.equalsIgnoreCase(Functionality.DECREASE_SPEED.toString()) && tickspeed > 1) {
        this.tickspeed--;
        try {
          this.clock.setDelay((int) (1000 / this.tickspeed));
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException(iae.getMessage());
        }
      } else if (command.equalsIgnoreCase(Functionality.INCREASE_SPEED.toString())) {
        this.tickspeed++;
        try {
          this.clock.setDelay((int) (1000 / this.tickspeed));
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException(iae.getMessage());
        }
      }
    }
  }


  /**
   * Allow the client to modify the change tick speed of the animation.
   */
  public void changeTickSpeed(double framePerSecond) {
    this.tickspeed = framePerSecond;
  }

  private double maxTime() {
    double lastTime = 0;
    for (ShapesModel s : this.model.getShapes()) {
      for (AnimatorCommand a : s.getCommands()) {
        lastTime = Math.max(lastTime, a.getStop());
      }
    }
    return lastTime;
  }

  public void startTimer() {
    this.clock.start();
  }
}