package com.example.ap_2022006_2022009;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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


public class RunningGameController extends GameApplication implements Initializable{
//public class RunningGameController extends GameApplication{
    // Defining the labels
    @FXML
    private static Label currentScore;
    @FXML
    private static Label netCherryCount;
    @FXML
    private static ImageView HeroImg;
    public static Stage primaryStage;
    public static double heroBaseX; // X-coordinate of the base of the rod
    public static double heroBaseY; // Y-coordinate of the base of the rod
    private static ImageView hero_img = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");
    public static boolean isHeroMoving = false;
    public static Pane G;
    public static Pane pane_home, pane_running, pane_game_over, pane_app_exit, pane_leaderBoard, pane_pause;
    private static Line rod;

    public static boolean RotationDoneSunny;
    private double rodBaseX = 72; // X-coordinate of the base of the rod
    private double rodBaseY = 283; // Y-coordinate of the base of the rod
    private boolean isGrowing = false;
    private Timeline timer_rodExtend;
    private boolean isRotating = false;
    private boolean rotationComplete = false; // Flag to track rotation completion
    private static double rodLength = 0;
    private static double displacement = 0;
    private double growthSpeed = 3; // Velocity of rod growth
    private double rotationAngle = 0;
    private boolean firstCycleDone = false;
    private boolean rotationStart = false;
    private Scene scene_running ;
    private Pane root_running ;

    private static ArrayList<Rectangle> List_Of_Blocks ;

    private static Rectangle rect2nd ;

    private double setX = 0 ;
    private double setY = 0;

    @FXML
    protected void onPauseButtonClick(ActionEvent event) {
        switchToPausePage(event);
        System.out.println("Pause Button clicked");
    }

//     Defining switch to methods :

    private void initializeNewGame() {
        // Reset any game state variables or objects here
        isGrowing = false;
        isRotating = false;
        rotationComplete = false;
        rodLength = 0;
        displacement = 0;
        rotationAngle = 0;
        firstCycleDone = false;
        rotationStart = false;

        // Clear existing blocks and create a new list
        root_running.getChildren().removeAll(List_Of_Blocks);
        List_Of_Blocks = blocksGenerator(root_running);

        // Set initial position of hero and rod
//        ImageView new_hero = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\Her`oImg.png");
//
//        new_hero.setLayoutX(9);
//        new_hero.setLayoutY(193);
//
//        root_running.getChildren().add(new_hero);
//
////        root_running.getChildren().remove(hero_img);
//
//        hero_img = new_hero;

        ImageView new_hero = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");
        new_hero.setX(setX);
        new_hero.setY(setY);
        new_hero.setLayoutX(9);
        new_hero.setLayoutY(193);
        new_hero.setFitWidth(90);
        new_hero.setFitHeight(107);
        new_hero.setPreserveRatio(true);
        root_running.getChildren().add(new_hero);

        root_running.getChildren().remove(hero_img);
        hero_img = new_hero;

        Line new_rod = new Line();
        new_rod.setStartX(rodBaseX);
        new_rod.setStartY(rodBaseY);
        new_rod.setEndX(rodBaseX);
        new_rod.setEndY(rodBaseY);
        root_running.getChildren().add(new_rod);

        root_running.getChildren().remove(rod);
        rod = new_rod ;
        ArrayList<Rectangle> new_List_Of_Blocks ;
        new_List_Of_Blocks = blocksGenerator(root_running);

        root_running.getChildren().add(new_List_Of_Blocks.get(1));
        rect2nd = new_List_Of_Blocks.get(1);


        // Add the first block to the scene
        root_running.getChildren().add(List_Of_Blocks.get(0));
    }

    public void switchToRunningPage(ActionEvent event, Stage primaryStage) throws InterruptedException {
        root_running = loadFXML("RunningPage.fxml");
        scene_running = new Scene(root_running);
        primaryStage.setScene(scene_running);
        final int[] index = {1};
        // Creating a list of 100 blocks, which will be accessed iteratively;
        List_Of_Blocks = blocksGenerator(root_running);
        // Creating an object of hero
//        Hero hero = new Hero(9, 193);
        root_running.getChildren().add(hero_img);
        hero_img.setLayoutX(9);
        hero_img.setLayoutY(193);
        hero_img.setFitWidth(90);
        hero_img.setFitHeight(107);
        hero_img.setPreserveRatio(true);
        root_running.getChildren().add(List_Of_Blocks.get(0));
        rect2nd = List_Of_Blocks.get(1);
        root_running.getChildren().add(List_Of_Blocks.get(1));

        Line new_rod = new Line();

        setX = hero_img.getX();
        setY = hero_img.getY();
        new_rod.setStartX(rodBaseX);
        new_rod.setStartY(rodBaseY);
        new_rod.setEndX(rodBaseX);
        new_rod.setEndY(rodBaseY);
        new_rod.setStrokeWidth(3); // Set a wider stroke width
        new_rod.setStroke(Color.BLACK); // Set a darker stroke color


        rod = new_rod;

        root_running.getChildren().add(rod);

        scene_running.setOnMousePressed(this::handleMousePressed);
        scene_running.setOnMouseReleased(this::handleMouseReleased);

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
//                displacement = rodLength;
            }
        };
        timer.start();

        AnimationTimer timer2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (RotationDoneSunny){
                    TranslateTransition translate = new TranslateTransition();
                    translate.setNode(hero_img);
                    translate.setDuration(Duration.millis(1000));
                    translate.setByX(rodLength+20);
                    translate.setOnFinished(e-> {
//                         call blocksGenerator() here, and generate them;
//                                    blockGenerator(root_running);
                        System.out.println("Man has finished running.");
                        System.out.println(rod.getEndX());
                        System.out.println(rect2nd.getLayoutX());
                        System.out.println(rect2nd.getLayoutX());
                        System.out.println(rect2nd.getWidth());
                        System.out.println("This is the value: "+ rod.getEndX() );

                        if (rod.getEndX() > rect2nd.getX() && rod.getEndX() < rect2nd.getX() + rect2nd.getWidth()) {
                            //                                primaryStage.close();


                            System.out.println("Entered try block");
                            initializeNewGame();
                        }
                        else{
                            System.out.println("Entered else block");
                            TranslateTransition translate_fall = new TranslateTransition();
                            translate_fall.setNode(hero_img);
                            translate_fall.setDuration(Duration.millis(1000));
                            translate_fall.setByY(-18);
                            switchToGameOverPage(event);
                        }
                    });
                    translate.play();
                    RotationDoneSunny = false;
                }
            }
        };
        timer2.start();

        primaryStage.setScene(scene_running);
        primaryStage.setTitle("Running Page Screen");
        primaryStage.show();




//        // Timeline to extend rod
//        timer_rodExtend = new Timeline(
//                new KeyFrame(Duration.ZERO, e -> {
//                    index[0]++;
//                    rod = new Line();
//                    rod.setStartX(hero_img.getLayoutX());
//                    rod.setStartY(hero_img.getLayoutY());
//                    rod.setEndX(hero_img.getLayoutX());
//                    rod.setEndY(hero_img.getLayoutY());
//                    rod.setStrokeWidth(3);
//                    rod.setStroke(Color.BLACK);
//                    root_running.getChildren().add(rod);
//                    scene_running.setOnMousePressed(this::handleMousePressed);
//                    scene_running.setOnMouseReleased(this::handleMouseReleased);
//                }),
//                new KeyFrame(Duration.millis(1000))
//        );
//
//        // OnFinished handler for the rod extension timeline
//        timer_rodExtend.setOnFinished(e -> {
//            // Rotating the rod
//            rotateRod();
//
//            // Timeline for the movement of hero across the rod
//            Timeline timer_hero = new Timeline(
//                    new KeyFrame(Duration.ZERO, f -> {
//                        if (rotationComplete) {
//                            TranslateTransition translate = new TranslateTransition();
//                            translate.setNode(HeroImg);
//                            translate.setDuration(Duration.millis(10000));
//                            translate.setByX(displacement);
//                            translate.setOnFinished(g -> {
//                                Platform.runLater(() -> {
//                                    blockGenerator(root_running, 300);
//                                    rotationComplete = false;
//                                });
//                            });
//                            translate.play();
//                        }
//                    })
//            );
//            timer_hero.play();
//        });
//
//        // Repeat the timeline indefinitely
//        timer_rodExtend.setCycleCount(Timeline.INDEFINITE);
//        timer_rodExtend.play();
//
//        primaryStage.setTitle("RunningPage Screen");
//        primaryStage.show();
//    }


//     Timer to extend rod;
//        AnimationTimer timer_rodExtend = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                // Adding a block into the pane
//                root_running.getChildren().add(List_Of_Blocks.get(index[0]));
//                index[0]++;
//                rod = new Line();
//                rod.setStartX(hero_img.getLayoutX());
//                rod.setStartY(hero_img.getLayoutY());
//                rod.setEndX(hero_img.getLayoutX());
//                rod.setEndY(hero_img.getLayoutY());
//                rod.setStrokeWidth(3); // Set a wider stroke width
//                rod.setStroke(Color.BLACK); // Set a darker stroke color
//                root_running.getChildren().add(rod);
//                scene_running.setOnMousePressed(this::handleMousePressed);
//                scene_running.setOnMouseReleased(this::handleMouseReleased);
//            }
//            private void handleMousePressed(MouseEvent event) {
//                // Function to make rod
//                if (rotationStart){
//                    return;
//                }
//                if (!rotationComplete) { // Check if the rotation is complete
//                    if(!firstCycleDone) {
//                        isGrowing = true;
//                    }
//                    isRotating = false; // Reset rotation flag
//                }
//            }

//            private void handleMousePressed(MouseEvent event) {
//                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), rod);
//                scaleTransition.setToY(1.0 + growthSpeed / 10); // Increase the Y scale for vertical growth
//                scaleTransition.setOnFinished(e -> {
//                    // Rotating the rod.
//                    rotateRod();
//
//                    // Now adding an animation timer for the movement of hero across the rod
//                    AnimationTimer timer_hero = new AnimationTimer() {
//                        @Override
//                        public void handle(long l) {
//                            if (rotationComplete){
//                                // Code to move the hero by the length of the rod
//                                TranslateTransition translate = new TranslateTransition();
//                                translate.setNode(HeroImg);
//                                System.out.println(HeroImg);
//                                translate.setDuration(Duration.millis(10000));
//                                translate.setByX(displacement);
//                                translate.setOnFinished(e->{
//                                    Platform.runLater(()->{
//                                        // call blocksGenerator() here, and generate them;
//                                        blockGenerator(root_running, 300);
//                                        // Once the hero is moved, then set the rotationComplete field as false.
//                                        rotationComplete = false;
//                                        // Resetting the length of the Rod
//                                    });
//                                });
//                                translate.play();
//                            }
//                        }
//                    };
//                });
//                scaleTransition.play();
//            }

//            private void handleMouseReleased(MouseEvent event) {
//                if (rotationStart){
//                    return;
//                }
//                if (!rotationComplete) { // Check if the rotation is complete
//                    isGrowing = false;
//                    isRotating = true; // Set rotation flag
//                }
//            }
//        };

//         Scale transition, translate transition, rotate transition

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if (isGrowing) {
//                    rodLength += growthSpeed;
//                    rod.setEndX(rodBaseX);
//                    rod.setEndY(rodBaseY - rodLength);
//                }
//                if (isRotating) {
//                    firstCycleDone = true;
//                    rotationStart = true;
//                    rotateRod();
//                }
//                displacement = rodLength;
////                System.out.println(displacement);
//            }
//        };
//        timer_rodExtend.start();
//        timer.start();
//        timer.wait(1000);
//        timer_hero.start();
//        timer.start();


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
//        primaryStage.setTitle("RunningPage Screen");
//        primaryStage.show();
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
            RotationDoneSunny = true;
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

    protected ArrayList<Rectangle> blocksGenerator(Parent root) {
        ArrayList<Rectangle> List_Of_Blocks = new ArrayList<>();
        Random random = new Random();
        int maxWidth = 100;
        int maxGap = 2;
        int minWidth = 5;
        int minGap = 5;
        int x_initial_pos = 0;  // Initial position for the first rectangle.

        // Creating initial block of custom width
        Rectangle rectangle_ini = new Rectangle();
        rectangle_ini.setWidth(72);
        rectangle_ini.setHeight(118);
        rectangle_ini.setY(283);  // Set y-coordinate to 283
        List_Of_Blocks.add(rectangle_ini);

        // Generating 100 blocks.
        for (int i = 0; i < 100; i++) {
            double width = minWidth + random.nextDouble() * maxWidth;
            // Create the rectangle with the random width, but constant height.
            Rectangle rectangle = new Rectangle();
            rectangle.setWidth(width);
            rectangle.setHeight(118);
            rectangle.setY(283);  // Set y-coordinate to 283
            List_Of_Blocks.add(rectangle);
            // Update xPosition for the next rectangle (with a random gap)
            x_initial_pos += (int) (150+ random.nextDouble() * maxGap);
            rectangle.setX(x_initial_pos);
        }
        return List_Of_Blocks;
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
//                        TranslateTransition translate = new TranslateTransition();
//                        translate.setNode(HeroImg);
//                        translate.setDuration(Duration.millis(1000));
//                        translate.setByX(rodLength);
//                        translate.setOnFinished(e->{
//                            // call blocksGenerator() here, and generate them;
//                            //            blockGenerator(root_running);
//                        });
//                        translate.play();                    }
//                })
//        );
//        delayTimer.play();

    }
    private void resetGameState() {
        // Reset any game state variables or objects here
        isGrowing = false;
        isRotating = false;
        rotationComplete = false;
        rodLength = 0;
        displacement = 0;
        rotationAngle = 0;
        firstCycleDone = false;
        rotationStart = false;

        // Clear existing blocks and create a new list
        root_running.getChildren().removeAll(List_Of_Blocks);
        List_Of_Blocks = blocksGenerator(root_running);

        // Set initial position of hero and rod
        hero_img.setLayoutX(9);
        hero_img.setLayoutY(193);


        rod.setStartX(rodBaseX);
        rod.setStartY(rodBaseY);
        rod.setEndX(rodBaseX);
        rod.setEndY(rodBaseY);

        // Add the first block to the scene
        root_running.getChildren().add(List_Of_Blocks.get(0));
    }
}
