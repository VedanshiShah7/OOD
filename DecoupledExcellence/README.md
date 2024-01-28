# ExCELlence
This README file is documenting changes that has made to the excellence project since assignment 7

## Changes made in the excellence project to accommodate the superior excellence functionality.
**Level 1: Scrubbing**
  Created a slider in the animation panel to represent the scrubbing and added a change listener to it. In the action performed method, set the value of the slider to the current time so that it would move with the animation.

**Level 2: Add a new animation: rotation**
  Created a command to get the changed rotation animation and to check if the given command is the same as the object in the AnimatorCommand interface and added the implementation in the abstract class.
  Created a rotate class that extends the abstract command class. The class represents the command object that processes Rotate for the ShapesModel interface.
  In the ShapesModel interface, rotate method was added to change the angle of the shape by specifying a new angle and pivot in order to rotate the shape. The getRotation method obtains the rotation of the Shape. The get rotate commands method obtains all the command motions associated with that shape. These methods are implemented in the abstract shapes model.
  The Rotation class that represents a rotation extends the abstract shapes model. It has different methods to return the values of different components of the rotate method.
  The animator model interface offers a rotate method that gives the client the ability to rotate a shape provided. The animatorModelImpl implements this method.
  The add motion method in the builder class now adds the rotation values to the builder if they exist. In order to accommodate this the add motion method in the animation builder interface contains a list of integers to document the details of the inputted parameters. This change is implemented in the animation reader class where there are extra fields for the initial degree and the final degree. If the values do not exist then they are set to a default in order to maintain compatibility with the existing input files.
  The SVG view implementation now contains a field to reflect the rotation in the form of an SVG if represented with a rotation.
  The paint component method in the animator panel contains the rotation drawn.

**Level 3: Shapes in Layers**
  In the Animator model, there is a shape to layer method that adds the given shape to the layer number. There is a swap layer method that takes two layers and swaps their position. There is a delete layer method that deletes the given layer along with all the contents in it. There is a get layers method that returns all the layers.  
  The animator model implementation represents layers as a linked hashmat containing an integer as the key value for the shape and an array list of the names of all the shapes.
  The add shape method in the animator model implementation is modified to add the shape to the given layer. If no layer is specified then it adds it to the default layer '0'.
  The add shape to layer method to put the given shape in the given layer, swap layer method that swaps the given layers, delete layer method that deletes the given layer and the get layers method from the animator model interface are implemented.
  The builder class is modified to contain a linked hashmap of integers to shapes to represents layers. The build method now contains the layers. The declare shape method adds the shape to the layer.
  In the animation builder interface, declare shape method now takes in the layer that the shape should be added to. The animation reader class implements this and in the read shape method it assigns the shape to the specified layer, if no layer is mentioned then it sets it to the default layer '0'.
  In the paint component method of the animator panel. The layers are drawn with the first  inputted layer as the bottommost layer and draws the shapes accordingly.
  The composite swing view contains a layer panel that contains the buttons to add layer, swap layers and delte layer. In the functionality enum add, delete and swap layer functionality is added. A PopUpLayer class is created which extends the IPopUp interface represents the Panel in which the client use to manage all layer related operations. It implements the pop up interface and creates the frame and buttons of the user interface for the layers.
  The MVC controller is set as the action listener for the layer related buttons. Here, the controller calls the model to reflect the changes to the layer.

## Changes in the model and Main according to assignment and the self-eval
1. We used a linked hashmap instead of a map as it has an order and this ensures that the objects are drawn correctly.
2. We also added an interface IEditorView and IPopUp to work with composite function view in order to ensure the SOLID rule of open for extension and closed for modification.
3. Our IPopUp interface is created to ensure that the windows open up when the add shape/ delete shape/ insert key frame/ edit key frame/ delete key frame functions are added. Making it an interface that is used in the composite view also helps to ensure that it can be further extended easily to add functionality.
4. Decouple timer out of the AnimatorPanel constructor and invoke the timer outside the class.
5. Excellence main has been re-configured to deal with different input requirement.
6. Change in SVG and Text test.
7. We added the controller functionality such that it can easily control the view and the model when given the specific commands to do so.

## Design Choice
1. Implementing view Interface where all operation is managed and can be accessed through the interface.
2. There is no method in the view implementation that would modify any change to the model
3. setCommandListner is current blank awaiting for instruction on user input
4. Controller will implement ActionListner where all the compositeView component would set this controller 
as an actionListener.
5. View would be separate into different view manager such as ShapeManager, KeyFrame manager and Composite as main JFrame.
6. The compositeView does not mutate any model data.
7. IEditorView interface is an extension of the AnimatorView to handle the operation within the composite view.
8. Reuse AnimatorPanel in composite View.
9. KeyFrame is implement in the AnimatorModelImpl, and method related to keyframe operation is added to the model interface.
## We use a model-view-controller (MVC) design.
  **Controller:**  
   We have a controller interface that represents the operations offered by the controller. It is used to give directions to control to the modela and the view. It is used to run the animation.  
  **Model:**  
   We have a model that is used to manage the interaction between all the shapes. The Model will only proccess the given information from the controller. The goal of the model is to process animate-like method which would change the position, color and dimension per animation tick.  
   The ShapesModel interface specifies the operation for ShapesAnimation for each frame. In our model a shape is charecterized by the size, 2D position and its color, and the animation aspect of the shape will be constructed via updating ticks. It can be asked to animate the shapes through various commands specified to produce a change by each keyframe via updating the clock and tickspeed in animator model.  
  **View:**  
   We have a view that is used to display the animation and it manages to the listner operation and interactive user interface. These are the methods that the controller would need to invoke on the view.   
   We have three different views: SVG, Text and Visual (Swing). The SVG view represents the animated view as an .svg. The text view represents the animation as a text file. The visual view pops up a frame with the animation.  
