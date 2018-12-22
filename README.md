The code used here ,save for the View and the AdapterUpdates classes and Interfaces, is not mine and is only used for educational purposes.
This new version has modified the previous version to fit better with the GUI.

This code is a representation of a BreakOut game, using Java FXGL to do so. The model used here is the Model-View-Controller, with the
model the defined by the classes in the logic carpet, the controller with the class Game, and the view with all the classes in the 
carpet View, using java FXGL.
The view is divided in diffent components, the GUI itself is represented by the class GameApp, the components in the game, Player, Brick
and the Ball each one represented with a Component Class, which defines the workings of each component. And the LevelView which represents
the view of the Levels in the Game.
The components in this Game are three, Player represented with a Bar, and with the class PlayerControl, this class allows the bar to move
to the right and left, and change colour with certain actions.  The Ball component is represented with a Circle and with the class 
BallComponent, which allows the ball to change it's speed when hitting something. And the BrickComponent class, which allows the brick to 
be hit, to be destroyed and so. Its representation in the View is a Rectangle.
The GameFactory allows the game to create Entities which are added to the GameWorld, by calling the factory and writing what Entity you want.
Each Entity has it's own different propiertes according to what is needed.

To set a Level and start playing, you need to press the KeyBoard letter "N", this letter calls a method in the GUI, which creates a
View to the level set. When you press "N" again, it creates another level and add its to the current game.
To start moving the ball, you need to press the KeyBoard Letter "Space", this throws the Ball by adding velocity. 
To add a Ball to the game, you press the letter "M", this creates a new ball and set it in top and center of the Player bar.
To move the bar to the left, you press "A", and to move to the right you press "D".


This game also has four features, two mayor ones and two minor.
The big ones are, First of all, a new type of Brick has been added, the Golden Brick, this bricks when destroyed doesn't give points,
rather it's effect only affects the view, this makes the Player Bar to grow longer, and it last only until the ball is lost.
The second feature is the testing feature, this allows to check that the bricks are working fine. This makes that when you click on 
a brick with the MouseKey, this bricks acts as being hit by a ball. This makes that by clicking on the brick works just as fine as
hitting the brick with a ball, which allows to test the bricks.

The minor features added, are mostly adding sound and effects when hitting a Brick, when a Ball or the Mouse hit the brick, a sound effect
can be heard, and also some particles seen. The sound effects depends on the type of Brick.

To allow the View receive updates from the Game, a new type of Update has been introduced, this works mainly as a adaptor to previous
UpdateClasses.

All the elements in the controller and the logic carpets has been tested. The ViewUpdates has not, as they are part of the GUI, and cannot
be tested without it.
The recourses are the carpet :/src/main/Recourses
The game affects has been obtained from: https://opengameart.org/
