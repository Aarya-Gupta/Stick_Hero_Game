package com.example.ap_2022006_2022009;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomePageController extends RunningGameController{
    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws InterruptedException {
        //Will switch the screen to running page.
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        switchToRunningPage(event, primaryStage);
        System.out.println("Play Button clicked");
    }

    @FXML
    protected void onCherryButtonClick(ActionEvent event) {
        // will display an image, which contains the cherry pricing
        // it will purely be a non-clickable image
        System.out.println("Cherry Button clicked");
    }
    @FXML
    protected void onSettingsButtonClick(ActionEvent event) {
        // Settings button, which can be used to change the background and the music
        System.out.println("Settings button clicked");
    }
    @FXML
    protected void onPlayerButtonClick(ActionEvent event) {
        // Don't know the use yet.
        System.out.println("Player button clicked");
    }
    @FXML
    protected void onSoundButtonClick(ActionEvent event) {
        // Will turn off/on the sound.
        // Add On : Change the button type, i.e. when sound is on, then show mute button so that it can be muted;
        // and the reverse when the sound is off.
        System.out.println("Sound Button Clicked");
    }
}
