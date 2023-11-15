package com.example.ap_2022006_2022009;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HelloController{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}

