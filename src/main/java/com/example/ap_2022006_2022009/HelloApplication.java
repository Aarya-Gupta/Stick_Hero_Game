package com.example.ap_2022006_2022009;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private Scene scene_home, scene_running, scene_exit; // Example scenes

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Load the HomePage FXML file
        Parent root_home = loadFXML("HomePage.fxml");
        scene_home = new Scene(root_home);

        // Load the RunningPage FXML file
        Parent root_running = loadFXML("RunningPage.fxml");
        scene_running = new Scene(root_running);

        // Load the ExitPage FXML file
        Parent root_exit = loadFXML("ExitPage.fxml");
        scene_exit = new Scene(root_exit);


        // Set the initial scene
        primaryStage.setScene(scene_home);
        primaryStage.setTitle("First Scene");
        primaryStage.show();
    }

    private Parent loadFXML(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to switch to Scene 2
    public void switchToRunningPage() {
        primaryStage.setScene(scene_running);
        primaryStage.setTitle("Running Screen");
    }

    // Method to switch back to Scene 1
    public void switchToExitPage() {
        primaryStage.setScene(scene_exit);
        primaryStage.setTitle("Exit Scene");
    }

    // Make other screens like going from exit to home.
    public static void main(String[] args) {
        launch(args);
    }
}
