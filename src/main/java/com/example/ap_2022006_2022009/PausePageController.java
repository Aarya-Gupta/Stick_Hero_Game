package com.example.ap_2022006_2022009;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PausePageController extends RunningGameControllerFinal{
    // Made this as the interface, so that the functionality of the methods can by defined inside the RunningGameController Class.
//    public void saveProgress();
//    // Will save the score of the current gameplay, and update the best score (if needed).
//    // Also, after the progress has been stored, the player will be having 2 options:
//    // 1. To resume the game;
//    public void resumeGame();
//    // 2. To return to the homepage.
//    public void switchToHomePage();


    // No Labels to be created in this page.
    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        //Will switch the screen to Home page.
        switchToHomePage(event);
        System.out.println("Home Button clicked");
    }

    @FXML
    protected void onResumeButtonClick(ActionEvent event) throws Exception {
        // Will resume the game again, w/o saving the progress.
        // But don't know how to resume to the original state of the running screen/restore to the state achieved in game.
        stop();
        switchToRunningPage(event);
        System.out.println("Resume Button clicked");
    }
    @FXML
    protected void onSaveProgressButtonClick(ActionEvent event) throws Exception {
        // This will save the current progress in the leaderboard at the respective position.
        // Will switch the screen to HomePage.
        stop();
        switchToHomePage(event);
        System.out.println("SaveProgress Button clicked");
    }
}
