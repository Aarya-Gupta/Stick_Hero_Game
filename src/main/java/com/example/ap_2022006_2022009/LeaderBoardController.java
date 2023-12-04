package com.example.ap_2022006_2022009;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LeaderBoardController extends GameApplication {
    // Will have only one button, to return to the home screen.
    @FXML
    protected void onCloseButtonClick(ActionEvent event) {
        //Will switch the screen to running page.
        switchToPausePage(event);
        System.out.println("Close Button clicked");
    }

}
