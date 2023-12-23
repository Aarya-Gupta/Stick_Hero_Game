package com.example.ap_2022006_2022009;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AppExitPageController extends RunningGameControllerFinal{
    //    This class will be used when the user wants to exit the game.
    @FXML
    protected void exit(ActionEvent event){
        // Will close the game
        System.exit(0);
    };
    protected void cancel(ActionEvent event){
        // Will switch the screen to HomePage.
        switchToHomePage(event);
        System.out.println("Cancel Button Clicked.");
    };
}
