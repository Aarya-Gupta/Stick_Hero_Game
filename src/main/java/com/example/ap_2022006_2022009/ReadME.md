Stick Hero is a game we have created using JavaFXML and Object Oriented Programming Principles.
The project involves involving core game mechanics, design patterns, etc. The game involves a character that can
move around rectangle shaped platforms, stretching sticks, collecting rewards and do multiple interactive
activities with the game. There are multiple pillars in the game of random sizes which serve as a challenge in
the game. We have also implemented a reviving feature that allows users to use collected cherries for one time
revival. Players must time stick extension correctly to land on the next platform. Failure results in the
character falling, ending the game. The character can collect rewards (cherries) by flipping upside
down while traversing platforms. Rewards contribute to the player's score. We have also included an option to
save the player's progress like last score, highest score, total cherries, etc. The gaming experience of the
player is enhanced by visual effects, graphics and animations.
To implement this game successfully, we have made use of Junit tests(testing returnSuperObj and blocksGenerator functions), Singleton and factory. Maximum scores and netCherryCount
is stored in Parameters.txt.
We have in total 5 screens in our gaming application : Homepage, RunningPage, PausePage, LeaderboardPage, and
ExitPage.
Cherry will be prompted only after the current score crosses the highest score. This is my functionality.
Their is also a revive feature in it.

// Design Patterns : 
Singleton used in the implication of SuperClass; Taking into consideration that only one instance of cherryCount and maximumScore can be created, which can further be updated whenever required.
Factory Design Pattern is used in the making of rectangle blocks/platforms, as they belong to a same type, but are of different parameters.
We will save the progress, only if the score is greater than the highest score.
SuperClass contains the methods inside it, through which the the attributes "highest score" and "net cherry count" can be updated in the Parameters.txt file; so that they can be called whenever the game is restarted, in order to attain the previous state of the game.
The HomePage includes the play button, Settings button, Mute or Unmute button, and character change button,
all these buttons add additional functionality to the game and makes it more interesting to play.
The RunningPage is the main screen where the player plays the game, it is where the user gets to interact the most
with the game and the program. Player can jump to collect cherries, extend sticks, walk, etc.
The PausePage appears when the user presses the pause button while playing the game, the player can retry the game,
go directly to HomePage or also look at the leaderboard that shows the Top rankers of the game globally.
The ExitPage shows the final scores of the player after he/she dies and also shows their highest score which is
stored in the program. Again the user is given the option to go to homePage directly, retry the game, look at
the leaderboard, etc.

// Assumptions : 
The checkBox of hero_img is big, hence it looks like the cherry is consumed before it is accessed visually.


