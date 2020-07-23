# MyTetris
MainPage

Tetris game instruction (user stories):

* user can use the left,right,down to make the tetromino move to different directions; 
* user can use the quick down button to let the tetromino directly move to the button of the game screen;
* user can use the rotate button to make the tetromino rotate clockwise 90 degree each time;
* user can also start and pause the game by pressing the corresponding buttons;
* When the game is pause or over, the corrsponding message will show on the screen;
* User will complete lines by moving different shapes of tetrominoes and when the lines are completed, they will disappear and the user can keep filling the vacant spaces;
* Once the playing field is filled, the game is over. 

Download instruction:

Please download the code and make sure you can compile and run the java language.

I developed at Android Studio, but as the computer issue I cannot use the AVD of the android studio.
I download the other AVD and when the code runs at different size of mobile phones or AVDs, the screen may shows variously.
My test AVD is the size of width:1080, height: 1920, DPI:420. 

Architecture:

The important method is the startGame(), which randomly initialize the tetromino and make it go down for each 500ms and also check the pause and game over states.
Another critical parts are the three method to eliminate the completed lines. Once all small boxes of any lines filled, clear it. 
Also recheck the line after the previous line has been deleted. (check the lines from button to top)

Test diagrams:

first version:
> ![](https://github.com/Caraluvian/MyTetris/raw/master/test/maps&boxesDrawingTest.png)   </br>

final version:
> ![](https://github.com/Caraluvian/MyTetris/raw/master/test/finaltest.png)

Hints to add more feature:

* You can add the feature to score the game;
* You can make the game online and the users can have a opponent to play together;
* You can show the next tetrominos at the space above the start button.



