Stick Hero Game - JavaFXML & OOPS
Welcome to Stick Hero, a captivating game crafted with JavaFXML and Object-Oriented Programming Principles. Embark on an exciting journey where core game mechanics, design patterns, and interactive elements converge to create an immersive gaming experience.

Game Overview
  Platform Adventure: Navigate a character through rectangle-shaped platforms, stretching sticks, and collecting rewards.
  Dynamic Challenges: Encounter randomly-sized pillars, providing an ever-changing challenge to test your skills.
  Reviving Feature: Use collected cherries for a one-time revival, adding a strategic element to the gameplay.
  Precise Timing: Extend sticks accurately to land on the next platform; mistiming leads to a game-ending fall.
  Reward System: Flip upside down to collect rewards (cherries) that contribute to your score.
  Progress Tracking: Save your progress, including last score, highest score, total cherries, and more.
  
Game Elements
  Visual Enhancements: Immerse yourself in the gaming experience with visual effects, graphics, and animations.
  Screens: Explore five distinct screens - Homepage, RunningPage, PausePage, LeaderboardPage, and ExitPage.
  Revive Feature: A unique functionality for users to continue the game after a fall.
  
Design Patterns
  Singleton: Implemented in the SuperClass for creating a single instance of cherryCount and maximumScore, ensuring easy updates when needed.
  Factory Design Pattern: Used in creating rectangle blocks/platforms with shared characteristics but different parameters.
  
Functionality Highlights
  Homepage: Play, Settings, Mute/Unmute, and character change buttons for an engaging start.
  RunningPage: Main gameplay screen where users interact, jump, collect cherries, extend sticks, and navigate the challenges.
  PausePage: Appears when the user pauses the game, offering options to retry, return to the Homepage, or view the global leaderboard.
  ExitPage: Displays final scores and highest score; provides options to return to the Homepage, retry the game, or explore the leaderboard.
  Progress Saving: Save progress only if the score exceeds the highest score, maintaining game continuity.
  
Assumptions
  Visual Consideration: A large checkBox for hero_img may create a visual illusion of cherries being consumed before visible access.
  
Dive into Stick Hero and challenge yourself in this dynamic and visually stimulating gaming experience! 🚀🎮
