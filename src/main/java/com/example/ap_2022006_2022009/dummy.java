package com.example.ap_2022006_2022009;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Random;


public class dummy extends GameApplication implements Initializable{
    //public class RunningGameController extends GameApplication{
    // Defining the labels
    @FXML
    private static Label currentScore;
    @FXML
    private static Label netCherryCount;
    @FXML
    private ImageView HeroImg;

    /////////////////////////////////////////
    public Stage primaryStage;
    public static Pane G;
    public static Pane pane_home, pane_running, pane_game_over, pane_app_exit, pane_leaderBoard, pane_pause;
    private Line rod;
    private double rodBaseX = 72; // X-coordinate of the base of the rod
    private double rodBaseY = 283; // Y-coordinate of the base of the rod
    private boolean isGrowing = false;
    private boolean isRotating = false;
    private boolean rotationComplete = false; // Flag to track rotation completion
    private static double rodLength = 0;
    private static double displacement = 0;
    private double growthSpeed = 3; // Velocity of rod growth
    private double rotationAngle = 0;
    private boolean firstCycleDone = false;
    private boolean rotationStart = false;

    @FXML
    protected void onPauseButtonClick(ActionEvent event) {
        switchToPausePage(event);
        System.out.println("Pause Button clicked");
    }

    //     Defining switch to methods :
    public void switchToRunningPage(ActionEvent event) throws InterruptedException {
        // Using a singleton design pattern, if the instance is not present, then create the instance at the very moment.
        SuperClass superObj = SuperClass.getInstance();
//        currentScore.setText(superobj.my_score);
        Pane root_running = loadFXML("RunningPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_running = new Scene(root_running);
        primaryStage.setScene(scene_running);




        // mouse click ka move
        Timeline delayTimer_stick = new Timeline(
                new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // Handling the movement of stick first.
                        rod = new Line();
                        rod.setStartX(rodBaseX);
                        rod.setStartY(rodBaseY);
                        rod.setEndX(rodBaseX);
                        rod.setEndY(rodBaseY);
                        rod.setStrokeWidth(3); // Set a wider stroke width
                        rod.setStroke(Color.BLACK); // Set a darker stroke color
                        root_running.getChildren().add(rod);
                        blockGenerator(root_running, 100);

                        scene_running.setOnMousePressed(this::handleMousePressed);
                        scene_running.setOnMouseReleased(this::handleMouseReleased);
                    }

                    private void handleMouseReleased(MouseEvent event) {
                        if (rotationStart){
                            return;
                        }
                        if (!rotationComplete) { // Check if the rotation is complete
                            isGrowing = false;
                            isRotating = true; // Set rotation flag
                        }
                    }

                    private void handleMousePressed(MouseEvent event) {
                        // Function to make rod
                        if (rotationStart){
                            return;
                        }
                        if (!rotationComplete) { // Check if the rotation is complete
                            if(!firstCycleDone) {
                                isGrowing = true;
                            }
                            isRotating = false; // Reset rotation flag
                        }
                    }
                })
        );
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isGrowing) {
                    rodLength += growthSpeed;
                    rod.setEndX(rodBaseX);
                    rod.setEndY(rodBaseY - rodLength);
                }
                if (isRotating) {
                    firstCycleDone = true;
                    rotationStart = true;
                    rotateRod();
                }
                displacement = rodLength;
                System.out.println(displacement);
            }
        };
        timer.start();
//        delayTimer_stick.play();
//        delayTimer_stick.play();


//        // All the functionalities in the running page will be declared here only.
//        // Generating 100 rectangles; not making any function.
//        Random random = new Random();
//        int maxWidth = 100;
//        int maxGap = 100;
//        int minWidth = 10;
//        int minGap = 5;
//        int xPosition = 0;
//        int yPosition_Block = 283;
//        for (int i=0; i<1; i++) {
//            double width = minWidth + random.nextDouble() * maxWidth;
//            Rectangle rectangle = new Rectangle(width, 50);
//            rectangle.setHeight(118); // Set height to 150 pixels
//            rectangle.setX(xPosition);
//            rectangle.setY(yPosition_Block);
//            xPosition += (int) (minGap + width + random.nextDouble() * maxGap);
//            // Not able to resolve how to add the rectangle into the scene; the following line is throwing error.
//            (root_running).getChildren().add(rectangle);
//            Thread.sleep(100);
//        }
        primaryStage.setTitle("RunningPage Screen");
        primaryStage.show();
    }



    public void switchToHomePage(ActionEvent event) {
        Parent root_home = loadFXML("HomePage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Initialised scenes, but they are still empty. Their respective values are put when switched to particular scene.
        Scene scene_home = new Scene(root_home);
        primaryStage.setScene(scene_home);
        primaryStage.setTitle("HomePage Screen");
        primaryStage.show();
    }

    public void switchToPausePage(ActionEvent event) {
        Parent root_pause = loadFXML("PausePage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // All scenes
        Scene scene_pause = new Scene(root_pause);
        primaryStage.setScene(scene_pause);
        primaryStage.setTitle("PausePage Screen");
        primaryStage.show();
    }

    public void switchToGameOverPage(ActionEvent event) {
        Parent root_game_over = loadFXML("GameOverPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene_game_over = new Scene(root_game_over);
        primaryStage.setScene(scene_game_over);
        primaryStage.setTitle("GameOverPage Screen");
        primaryStage.show();
    }

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

    // Methods related to movement of stick
    private void handleMousePressed(MouseEvent event) {
//        timer.start();
        // Function to make rod
        if (rotationStart){
            return;
        }
        if (!rotationComplete) { // Check if the rotation is complete
            if(!firstCycleDone) {
                isGrowing = true;
            }
            isRotating = false; // Reset rotation flag
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        if (rotationStart){
            return;
        }
        if (!rotationComplete) { // Check if the rotation is complete
            isGrowing = false;
            isRotating = true; // Set rotation flag
        }
    }


    public void rotateRod() {
        double endX = rodBaseX;
        double endY = rodBaseY - rodLength; // Store the current end point

        rod.setEndX(rodBaseX);
        rod.setEndY(rodBaseY); // Set the end point to the base to rotate around the base

        rod.getTransforms().clear(); // Clear previous transformations

        rod.getTransforms().add(new javafx.scene.transform.Rotate(rotationAngle, rodBaseX, rodBaseY));
        rotationAngle += 1; // Increment angle (adjust rotation speed as needed)

        rod.setEndX(endX);
        rod.setEndY(endY); // Set the end point back to its original position

        if (rotationAngle > 90) {
            isRotating = false; // Stop rotating after 90 degrees
            rotationComplete = true; // Set rotation completion flag
            if(!firstCycleDone) {
                firstCycleDone = true;
            }
        }
    }



    // Making a function to move hero
    public void moveHero(double displacement){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(HeroImg);
        translate.setDuration(Duration.millis(10000));
        translate.setByX(displacement);
        translate.setOnFinished(e -> {
            // call blocksGenerator() here, and generate them;
//            blockGenerator();
        });
        translate.play();
    }

    protected void blockGenerator(Pane root, int lastBlockPosition){
        Random random = new Random();
        int maxWidth = 100;
        int maxGap = 50;
        int minWidth = 5;
        int minGap = 5;
        int xPosition = lastBlockPosition + 1; // Giving x-coordinate greater than current block's coordinate.
        int yPosition_Block = 283;
        double width = minWidth + random.nextDouble() * maxWidth;
        Rectangle rectangle = new Rectangle(width, 50);
        rectangle.setHeight(118); // Set height to 150 pixels
        rectangle.setX(xPosition);
        rectangle.setY(yPosition_Block);
        xPosition += (int) (minGap + width + random.nextDouble() * maxGap);
        // Not able to resolve how to add the rectangle into the scene; the following line is throwing error.
        (root).getChildren().add(rectangle);
    }

    protected void blocksGenerator(ArrayList<Rectangle> List_Of_Blocks, Parent root){
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // translate
//        // Moving hero
//        int xPosition_Block = 0;
//        int yPosition_Block = 283;
//        int maxWidth = 100;
//        int maxGap = 50;
//        int minWidth = 5;
//        int minGap = 5;
//
//        for (int i=0; i<5; i++) {
//            // Creating an object of hero.
//            Hero hero = new Hero(10, 193);
//
//            // Creating a block
//            Random random = new Random();
//            double width = minWidth + random.nextDouble() * maxWidth;
//            Rectangle rectangle = new Rectangle(width, 50);
//            rectangle.setHeight(118); // Set height to 150 pixels
//            rectangle.setX(xPosition_Block);
//            rectangle.setY(yPosition_Block);
//            xPosition_Block += (int) (minGap + width + random.nextDouble() * maxGap);
//            (root_running).getChildren().add(rectangle);
//
//            // Handling the movement of stick first.
//            rod = new Line();
////        rodBaseX = hero.heroBaseX;
////        rodBaseY = hero.heroBaseY;
//            rod.setStartX(rodBaseX);
//            rod.setStartY(rodBaseY);
//            rod.setEndX(rodBaseX);
//            rod.setEndY(rodBaseY);
//            rod.setStrokeWidth(3); // Set a wider stroke width
//            rod.setStroke(Color.BLACK); // Set a darker stroke color
//            root_running.getChildren().add(rod);
//
//            scene_running.setOnMousePressed(this::handleMousePressed);
//            scene_running.setOnMouseReleased(this::handleMouseReleased);
//
//            AnimationTimer timer = new AnimationTimer() {
//                //        timer = new AnimationTimer() {
//                @Override
//                public void handle(long now) {
//                    if (isGrowing) {
//                        rodLength += growthSpeed;
//                        rod.setEndX(rodBaseX);
//                        rod.setEndY(rodBaseY - rodLength);
//                    }
//                    if (isRotating) {
//                        rotateRod();
//                    }
//                }
//            };
//            timer.start();
//        }
        // Moving the object across 1 st block
//        Timeline delayTimer = new Timeline(
//                new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(HeroImg);
        translate.setDuration(Duration.millis(1000));
        translate.setByX(rodLength);
        translate.setOnFinished(e->{
            // call blocksGenerator() here, and generate them;
            //            blockGenerator(root_running);
        });
        translate.play();                    }
//                })
//        );
//        delayTimer.play();
//        delayTimer.play();

//    }
}
