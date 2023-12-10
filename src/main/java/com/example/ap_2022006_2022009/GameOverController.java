package com.example.ap_2022006_2022009;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends RunningGameController{

    // Method 1: To show the current score
    @FXML
    private Label currentScore;
    // Method 2: To show the best score, hence defining its label.
    @FXML
    private Label bestScore;

    // ****************************************************
    // Work Pending :
    // To connect the display inside the labels, with the respective scores.
    // ****************************************************


    // Defining the Button-Clicking methods.
    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        //Will switch the screen to Home page,
        switchToHomePage(event);
        System.out.println("Home Button clicked");
    }

    @FXML
    protected void onRetryButtonClick(ActionEvent event) throws InterruptedException {
        //Will switch the screen to running page;
        switchToRunningPage(event, primaryStage);
        // Saving the current game's score.
        System.out.println("Retry Button clicked");
    }

    @FXML
    protected void onLeaderboardButtonClick(ActionEvent event) {
        // Will show the leaderboard, which will show the top 3 scores.
        // The leaderboard must have a back button, which takes the user back to the pause page
        // This means that Leaderboard screen is also to be created.
        // This method will call a switchToLeaderboard() method.
        switchToLeaderBoardPage(event);
        System.out.println("Leaderboard Button clicked");
    }

    // I have removed the fav. button, as it is used for rating the app in the original game.
    // Didn't found its use.
}
