package com.example.ap_2022006_2022009;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

class Hello {
    public void modify(){};
        }

public class HelloController extends Hello{
    public void modify(){
        System.out.println("Fuck ");
    }
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}

