package com.example.ap_2022006_2022009;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

//public class Method2Class {
//}
        import javafx.animation.AnimationTimer;
        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.animation.TranslateTransition;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Line;
        import javafx.scene.shape.Rectangle;
        import javafx.stage.Stage;
        import javafx.scene.media.Media;
        import javafx.scene.media.MediaPlayer;
        import javafx.scene.media.MediaView;
        import javafx.util.Duration;

        import java.io.File;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Objects;
        import java.util.ResourceBundle;


public class Method2Class extends GameApplication implements Initializable {
    @FXML
    private static Label currentScore;
    @FXML
    private static Label netCherryCount;
    @FXML
    protected void onPauseButtonClick(ActionEvent event) {
        // Stop the media player
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        switchToPausePage(event);
        System.out.println("Pause Button clicked");
    }
    @FXML
    private static MediaView mediaView;
    private static MediaPlayer mediaPlayer;

    // *** Method to update the labels
    public void updateCurrentScore(String score){
        currentScore.setText(score);
    }
    public void updateNetCherryCount(String CherryCount){
        netCherryCount.setText(CherryCount);
    }
    public int getCurrentScore(){
        return Integer.parseInt(currentScore.getText());
    }
    public int getNetCherryCount(){
        return Integer.parseInt((netCherryCount.getText()));
    }
    public static Label dummy;
    // *** Methods related to labels ended.


    public static final ImageView heroImg = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");
    private static ArrayList<Rectangle> List_Of_Blocks ;

    // Variables related to run music
    public static String mediaPath;
    public static Media media;
    // Music ends.

    // *** Variables related to rod.
    public static Line rod;
    private static double rodBaseX = 72; // X-coordinate of the base of the rod
    private static double rodBaseY = 283; // Y-coordinate of the base of the rod
    private static boolean rotationStart = false;
    private static boolean isGrowing = false;
    private static boolean rotationComplete = false; // Flag to track rotation completion
    private static boolean firstCycleDone = false;
    private static boolean isRotating = false;
    private boolean isRodGrown = false;
    Timeline timeline_rodGrowth;
    Timeline timeline_rotation;
    private static double rotationAngle = 0;
    private static double rodLength = 0;
    private static boolean RotationDoneSunny = false;
    // *** Variables related to rod ends.

    public static Pane root_home, root_pause, root_running, root_gameOver, root_leaderBoard;;
    public static Scene scene_home, scene_pause, scene_running, scene_gameOver, scene_leaderBoard;

    protected ArrayList<Rectangle> blocksGenerator(Parent root) {
        double initialX = 0;
        double initialY = 283;
        return RectangleFactory.generateRectangles(100, initialX, initialY);
    }

    public void initializeNewGame(ActionEvent event) throws InterruptedException {
        switchToRunningPage(event);
    }

    public void addMusic(){
        //*** Code to add music
        mediaPath = Objects.requireNonNull(getClass().getResource("Music.mp3")).toString();;
        media = new Media(mediaPath);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        mediaView.setMediaPlayer(mediaPlayer);
        // Add mediaView to the scene graph
        // You might need to adjust the layout parameters based on your scene structure
        root_running.getChildren().add(mediaView);
        // Set the MediaPlayer to play
        mediaPlayer.play();
        //***Code to add music ends.
    }

    public void switchToRunningPage(ActionEvent event) throws InterruptedException {
        root_running = loadFXML("RunningPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_running = new Scene(root_running);
        primaryStage.setScene(scene_running);
        primaryStage.setTitle("PausePage Screen");
        primaryStage.show();
        addMusic(); //Adding Music


        //*** Main Functionality starts :
        ImageView new_hero = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");
//        new_hero.setX(setX);
//        new_hero.setY(setY);
        new_hero.setLayoutX(9);
        new_hero.setLayoutY(193);
        new_hero.setFitWidth(90);
        new_hero.setFitHeight(107);
        new_hero.setPreserveRatio(true);
        root_running.getChildren().add(new_hero);
        List_Of_Blocks = blocksGenerator(root_running);
        root_running.getChildren().add(List_Of_Blocks.get(0));
        root_running.getChildren().add(List_Of_Blocks.get(1));
        // Till the above code, 2 blocks and hero are placed.

        rod = new Line(rodBaseX, rodBaseY, rodBaseX, rodBaseY);
        rod.setStrokeWidth(2.5);
        root_running.getChildren().add(rod); // Added the instance of rod

        scene_running.setOnMousePressed(this::handleMousePressed);
        scene_running.setOnMouseReleased(this::handleMouseReleased);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isGrowing) {
                    rodLength += 3;
                    rod.setEndX(rodBaseX);
                    rod.setEndY(rodBaseY - rodLength);
                }
                if (isRotating) {
                    firstCycleDone = true;
                    rotationStart = true;
                    rotateRod();
                }
//                displacement = rodLength;
            }
        };
        timer.start();

        AnimationTimer timer2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (RotationDoneSunny){
                    TranslateTransition translate = new TranslateTransition();
                    translate.setNode(new_hero);
                    translate.setDuration(Duration.millis(1000));
                    translate.setByX(rodLength+20);
                    translate.setOnFinished(e-> {
//                         call blocksGenerator() here, and generate them;
//                                    blockGenerator(root_running);
                        System.out.println("Man has finished running.");
                        System.out.println(rod.getEndX());
                        System.out.println(List_Of_Blocks.get(1).getLayoutX());
                        System.out.println(List_Of_Blocks.get(1).getLayoutX());
                        System.out.println(List_Of_Blocks.get(1).getWidth());
                        System.out.println("This is the value: "+ rod.getEndX() );

                        if (rod.getEndX() > List_Of_Blocks.get(1).getX() && rod.getEndX() < List_Of_Blocks.get(1).getX() + List_Of_Blocks.get(1).getWidth()) {
                            System.out.println("Entered try block");
//                            initializeNewGame();

                            // Updating the current score by one.
                            String currentScoreText = currentScore.getText();
                            int currentScoreValue = 0;
                            try {
                                currentScoreValue = Integer.parseInt(currentScoreText);
                                currentScoreValue++;  // Increment the score
                                currentScore.setText(String.valueOf(currentScoreValue));  // Update the label
                            } catch (NumberFormatException err) {
                                // Handle the case where the text is not a valid integer
                                System.err.println("Error parsing currentScore as an integer: " + err.getMessage());
                            }
                            // Checking if the current score is greater than the gacheived score :
//                            if (currentScoreValue > Integer.parseInt(SuperClass.getInstance().highest_score)) {
//                                updateLabels(currentScoreText, netCherryCount.getText());
//                            }
                        }
                        else{
                            System.out.println("Entered else block");
                            TranslateTransition translate_fall = new TranslateTransition();
                            translate_fall.setNode(new_hero);
                            translate_fall.setDuration(Duration.millis(1000));
                            translate_fall.setByY(-18);
//                            switchToGameOverPage(event);
                        }
                    });
                    translate.play();
                    RotationDoneSunny = false;
                }
            }
        };
        timer2.start();

    }


    // *** Methods related to rod :
//    private void growRod() {
//        timeline_rodGrowth = new Timeline(
//                new KeyFrame(Duration.millis(10), e -> {
//                    if(isGrowing){
//                        rod.setEndY(rod.getEndY() - 1);
//                        rodLength+=1;
//                        // Don't know by what amount the rodLength should be increased.
//                    }
//                    else {
//                        timeline_rodGrowth.stop();
//
//                    }
//                })
//        );
//        if (!isRodGrown) {
//            timeline_rodGrowth.setCycleCount(Timeline.INDEFINITE);
//            isRodGrown = true;
//            timeline_rodGrowth.play();
//            // Rotating the rod :
//            timeline_rodGrowth.setOnFinished(event -> rotateRod());
////            rotateRod();
//        }
//    }
//
//    private void rotateRod() {
//        timeline_rotation = new Timeline(
//                new KeyFrame(Duration.millis(10), e -> {
//                    double endX = rodBaseX;
//                    double endY = rodBaseY - rodLength;
//
//                    rod.setEndX(rodBaseX);
//                    rod.setEndY(rodBaseY);
//
//                    rod.getTransforms().clear();
//                    rod.getTransforms().add(new javafx.scene.transform.Rotate(rotationAngle, rodBaseX, rodBaseY));
//                    rotationAngle += 1;
//
//                    rod.setEndX(endX);
//                    rod.setEndY(endY);
//
//                    if (rotationAngle >= 90) {
//                        isRotating = false;
//                        rotationComplete = true;
//                        if (!firstCycleDone) {
//                            firstCycleDone = true;
//                        }
//                        timeline_rotation.stop();
//                    }
//                })
//        );
//        timeline_rotation.setOnFinished(event -> moveHero());
//        timeline_rotation.setCycleCount(Timeline.INDEFINITE);
//        timeline_rotation.play();
//    }

    private void moveHero() {
    }
    private void rotateRod() {
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
            RotationDoneSunny = true;
            if(!firstCycleDone) {
                firstCycleDone = true;
            }
        }
    }

    private void handleMousePressed(MouseEvent event) {
        if (rotationStart || isGrowing) {
            return;
        }

        if (!rotationComplete) {
            if (!firstCycleDone) {
                isGrowing = true;
//                growRod();
            }
            isRotating = false;
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

    // *** Methods related to rod ends.


    public void switchToHomePage(ActionEvent event) {
        root_home = loadFXML("HomePage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_home = new Scene(root_home);
        primaryStage.setScene(scene_home);
        primaryStage.setTitle("HomePage Screen");
        primaryStage.show();
    }

    public void switchToPausePage(ActionEvent event) {
        root_pause = loadFXML("PausePage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_pause = new Scene(root_pause);
        primaryStage.setScene(scene_pause);
        primaryStage.setTitle("PausePage Screen");
        primaryStage.show();
    }

    public void switchToGameOverPage(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        // Update the score to 0 at the backend
        updateCurrentScore("0");
        // dummy is the label, which will be used by the GameOverPageController to update the current score field.
        dummy.setText(String.valueOf(getCurrentScore()));
        root_gameOver = loadFXML("GameOverPage.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_gameOver = new Scene(root_gameOver);
        primaryStage.setScene(scene_gameOver);
        primaryStage.setTitle("GameOverPage Screen");
        primaryStage.show();
    }

    public void switchToLeaderBoardPage(ActionEvent event) {
        root_leaderBoard = loadFXML("LeaderBoard.fxml");
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_leaderBoard = new Scene(root_leaderBoard);
        primaryStage.setScene(scene_leaderBoard);
        primaryStage.setTitle("LeaderBoard Screen");
        primaryStage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
