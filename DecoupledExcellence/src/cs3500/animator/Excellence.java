package cs3500.animator;


import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorMVCController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.shapes.ShapesModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationSVGView;
import cs3500.animator.view.AnimationTextView;
import cs3500.animator.view.AnimatorPanel;
import cs3500.animator.view.AnimatorSwingView;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.CompositeSwingView;

import cs3500.animator.view.IEditorView;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;


/**
 * Represent the Main Class of Excellence Animator.
 */
public final class Excellence {

  /**
   * Represent the Main class to run Excellence Animator.
   *
   * @param args represent the command line enter from user.
   * @throws IOException when there is an error in parsing user input
   */
  public static void main(String[] args) throws IOException {
    AnimatorModel model = new AnimatorModelImpl();
    AnimatorView view = null;
    Readable file;
    Appendable output;
    String viewType = null;
    String inputFileName = null;
    String outputFileName = null;
    int tickSpeed = 1;

    //Turn args to String
    String commandLineInput = "";
    for (String s : args) {
      commandLineInput += s + " ";
    }
    Scanner scan = new Scanner(commandLineInput);
    while (scan.hasNext()) {
      String in = scan.next();
      switch (in) {
        case "-in":
          try {
            inputFileName = scan.next();
          } catch (Exception ex) {
            System.out.println("Invalid file input");
          }
          break;
        case "-out":
          try {
            outputFileName = scan.next();
          } catch (Exception ex) {
            System.out.println("Invalid file output Name");
          }
          break;
        case "-view":
          try {
            viewType = scan.next();
          } catch (Exception ex) {
            System.out.println("Invalid view");
          }
          break;
        case "-speed":
          try {
            tickSpeed = scan.nextInt();
          } catch (Exception ex) {
            System.out.println("Invalid tickSpeed");
          }
          break;
        default:
          System.out.println("Invalid command line argument");
      }
    }

    // Instantiate Model
    if (viewType == null) {
      throw new IllegalArgumentException("Missing inputFile or view type");
    } else {
      if (inputFileName != null) {
        model = AnimationReader.parseFile(new FileReader(inputFileName),
                new AnimatorModelImpl.Builder(new LinkedHashMap<String, ShapesModel>()));
      }
      if (viewType.equalsIgnoreCase("Text") && inputFileName != null && outputFileName != null) {
        view = new AnimationTextView(model);
        view.createAnimationOutput();
        if (outputFileName == null) {
          System.out.println(view.getOutput().toString());
        } else {
          File outputFile = new File(outputFileName);
          FileWriter writeOutput = new FileWriter(outputFile);
          BufferedWriter write = new BufferedWriter(writeOutput);
          write.append(view.getOutput().toString());
          write.close();
        }
      } else if (viewType.equalsIgnoreCase("SVG") && inputFileName
              != null && outputFileName != null) {
        view = new AnimationSVGView(model, tickSpeed);
        view.createAnimationOutput();
        if (outputFileName == null) {
          System.out.println(view.getOutput().toString());
        } else {
          File outputFile = new File(outputFileName);
          FileWriter writeOutput = new FileWriter(outputFile);
          BufferedWriter write = new BufferedWriter(writeOutput);
          write.append(view.getOutput().toString());
          write.close();
        }
      } else if (viewType.equalsIgnoreCase("visual") && inputFileName != null) {
        AnimatorPanel animatorPanel = new AnimatorPanel(model,
                tickSpeed);
        view = new AnimatorSwingView(animatorPanel, model.getLeftMost(), model.getTopMost(),
                model.getWidth(), model.getHeight());
        view.makeVisible();
        model.runAnimation();
        animatorPanel.startStopAnimation("Play");
      } else if (viewType.equalsIgnoreCase("edit")) {
        AnimatorPanel animatorPanel = new AnimatorPanel(model,
                tickSpeed);
        view = new CompositeSwingView(animatorPanel, model);
        AnimatorController controller = new AnimatorMVCController(model, (IEditorView) view);
        view.setCommandListener((ActionListener) controller);
        controller.executeCommand();
      }
    }
  }
}

