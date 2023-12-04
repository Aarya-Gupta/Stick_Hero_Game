package com.example.ap_2022006_2022009;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.application.Application.launch;

public class GameApplication extends Application{
    public Stage primaryStage;
    public static Pane G;
    public static Pane pane_home, pane_running, pane_game_over, pane_app_exit, pane_leaderBoard, pane_pause;

    //    private Parent root_running;
    private AnchorPane loadFXML(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Defining blocksGenerator() method to generate a list of blocks.
    protected void blockGenerator(ArrayList<Rectangle> List_Of_Blocks, Pane root){
        Random random = new Random();
        int maxWidth = 100;
        int maxGap = 50;
        int minWidth = 5;
        int minGap = 5;
        int x_initial_pos = 0;  // Initial position for the first rectangle.

        // Generating 100 blocks.
        for (int i=0; i<100; i++){
            double width = minWidth + random.nextDouble() * maxWidth;
            // Create the rectangle with the random width, but constant height.
            Rectangle rectangle = new Rectangle(width, 118);
            List_Of_Blocks.add(rectangle);
//            G.getChildren().add(rectangle);
            // Update xPosition for the next rectangle (with a random gap)
            x_initial_pos += (int) (minGap + width + random.nextDouble() * maxGap);
        }
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        G = new AnchorPane();
        this.primaryStage = primaryStage;

        // Setting the icon of the game :
        Image app_icon = new Image("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\app_icon.png");
        primaryStage.getIcons().add(app_icon);


        /* Loading all the files initially, so that they can remain in their previous state.
        * In cases where I want to load the file w/o any cache, I will do it by loading the file again.*/

        // Load the HomePage FXML file
        AnchorPane root_home = loadFXML("HomePage.fxml");
//        scene_home = new Scene(root_home);
        (G).getChildren().add(root_home);

        // Load the RunningPage FXML file
        AnchorPane root_running = loadFXML("RunningPage.fxml");
//        scene_running = new Scene(root_running);
        assert root_running != null;
        root_running.setVisible(false);
        (G).getChildren().add(root_running);

        // Load the PausePage FXML file
        AnchorPane root_pause = loadFXML("PausePage.fxml");
//        scene_pause = new Scene(root_pause);
        assert root_pause != null;
        root_pause.setVisible(false);
        (G).getChildren().add(root_pause);

        // Load the GameOver FXML file
        AnchorPane root_game_over = loadFXML("GameOverPage.fxml");
//        scene_game_over = new Scene(root_game_over);
        assert root_game_over != null;
        root_game_over.setVisible(false);
        (G).getChildren().add(root_game_over);


        // Load the ExitPage FXML file
        AnchorPane root_exit = loadFXML("AppExitPage.fxml");
//        scene_app_exit = new Scene(root_exit);
        assert root_exit != null;
        root_exit.setVisible(false);
        (G).getChildren().add(root_exit);

        // Load the LeaderBoard FXML file
        AnchorPane root_leaderBoard = loadFXML("LeaderBoard.fxml");
//        scene_leaderBoard = new Scene(root_leaderBoard);
        assert root_leaderBoard != null;
        root_leaderBoard.setVisible(false);
        (G).getChildren().add(root_leaderBoard);


        // Adding the group G to all the roots :
//        assert root_leaderBoard != null; assert root_exit != null;assert root_home != null;assert root_running != null;assert root_pause != null;assert root_game_over != null;
//        (root_home).getChildren().add(G);
//        (root_running).getChildren().add(G);
//        (root_pause).getChildren().add(G);
//        (root_game_over).getChildren().add(G);
//        (root_leaderBoard).getChildren().add(G);

        // Setting the initial scene
        Scene scene = new Scene(G);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Home Page");
        primaryStage.show();



//        // Generating 100 blocks;
//        ArrayList<Rectangle> ListOfBlocks = new ArrayList<>();
//        blocksGenerator(ListOfBlocks, (Pane) root_running);
//        for (int i = 0; i<100; i++){
//            Rectangle child = ListOfBlocks.get(i);
//            G.getChildren().add(child);
//        }
    }

    // Now, defining the methods to switch the screens;
    // Method to switch to Home Page
    public void switchToHomePage(ActionEvent event) {
        Parent root_home = loadFXML("HomePage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Initialised scenes, but they are still empty. Their respective values are put when switched to particular scene.
        Scene scene_home = new Scene(root_home);
        primaryStage.setScene(scene_home);
        primaryStage.setTitle("HomePage Screen");
        primaryStage.show();
    }

    // Method to switch to Running Page
    public void switchToRunningPage(ActionEvent event) {
        Pane root_running = loadFXML("RunningPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_running = new Scene(root_running);
        primaryStage.setScene(scene_running);
        // Call all the functions related to Running Page
        // All the functionalities in the running page will be declared here only.
        // Generating 100 rectangles; not making any function.
        Random random = new Random();
        int maxWidth = 100;
        int maxGap = 50;
        int minWidth = 5;
        int minGap = 5;
        int xPosition = 0;
        int yPosition = 283;
        for (int i=0; i<100; i++) {
            double width = minWidth + random.nextDouble() * maxWidth;
            Rectangle rectangle = new Rectangle(width, 50);
            rectangle.setHeight(118); // Set height to 150 pixels
            rectangle.setX(xPosition);
            rectangle.setY(yPosition);
            xPosition += (int) (minGap + width + random.nextDouble() * maxGap);
            // Not able to resolve how to add the rectangle into the scene; the following line is throwing error.
            (root_running).getChildren().add(rectangle);
        }
        primaryStage.setTitle("RunningPage Screen");
        primaryStage.show();
    }

    // Method to switch to Pause Page
    public void switchToPausePage(ActionEvent event) {
        Parent root_pause = loadFXML("PausePage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // All scenes
        Scene scene_pause = new Scene(root_pause);
        primaryStage.setScene(scene_pause);
        primaryStage.setTitle("PausePage Screen");
        primaryStage.show();
    }

    // Method to switch to Game Over Page
    public void switchToGameOverPage(ActionEvent event) {
        Parent root_game_over = loadFXML("GameOverPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_game_over = new Scene(root_game_over);
        primaryStage.setScene(scene_game_over);
        primaryStage.setTitle("GameOverPage Screen");
        primaryStage.show();
    }

    // Method to switch to AppExitPage
    public void switchToAppExitPage(ActionEvent event) {
        Parent root_app_exit = loadFXML("AppExitPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_app_exit = new Scene(root_app_exit);
        primaryStage.setScene(scene_app_exit);
        primaryStage.setTitle("AppExitPage Screen");
        primaryStage.show();
    }

    public void switchToLeaderBoardPage(ActionEvent event) {
        Parent root_leaderboard = loadFXML("LeaderBoard.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_leaderBoard = new Scene(root_leaderboard);
        primaryStage.setScene(scene_leaderBoard);
        primaryStage.setTitle("LeaderBoard Screen");
        primaryStage.show();
    }

//    protected ArrayList<Rectangle> blocksGenerator(ArrayList<Rectangle> List_Of_Blocks){
//        Random random = new Random();
//        int maxWidth = 100;
//        int maxGap = 50;
//        int minWidth = 5;
//        int minGap = 5;
//        int x_initial_pos = 0;  // Initial position for the first rectangle.
//
//        // Generating 100 blocks.
//        for (int i=0; i<100; i++){
//            double width = minWidth + random.nextDouble() * maxWidth;
//            // Create the rectangle with the random width, but constant height.
//            Rectangle rectangle = new Rectangle(width, 118);
//            List_Of_Blocks.add(rectangle);
//            root_running.getChildren().add(rectangle);
//            // Update xPosition for the next rectangle (with a random gap)
//            x_initial_pos += (int) (minGap + width + random.nextDouble() * maxGap);
//        }
//        return List_Of_Blocks;
//    }




    // Make other screens like going from exit to home.
    public static void main(String[] args) {
        launch(args);
    }
}
