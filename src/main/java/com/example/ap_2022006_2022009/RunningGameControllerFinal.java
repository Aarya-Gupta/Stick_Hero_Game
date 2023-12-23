package com.example.ap_2022006_2022009;
import javafx.animation.*;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class RunningGameControllerFinal extends GameApplication implements Serializable, RectangleInterface {
    public static boolean cherryNeAukaatDikhaadi;
    @FXML
    private static Label currentScore ;
    @FXML
    private static Label netCherryCount;
    @FXML
    protected void onPauseButtonClick(ActionEvent event) throws Exception {
        // Stop the media player
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        stop();
        rotationStart = false;
        isGrowing = false;
        rotationComplete = false; // Flag to track rotation completion
        firstCycleDone = false;
        isRotating = false;
        isRodGrown = false;
        retainOriginalState();
        switchToPausePage(event);
        System.out.println("Pause Button clicked");
    }
    @FXML
    private static MediaView mediaView;
    private static MediaPlayer mediaPlayer;
    private static FXMLLoader new_loader ;

    private static int counter = 0 ;

    private static Label currentScoreLabel, netCherryCountLabel;


    // *** Fields, Methods related to Stage
    public static Stage runningGameStage;
    // *** Fields, Methods related to Stage ENDS.

    // *** Method to update the labels
    public void updateScore(){
        currentScoreLabel.setText(String.valueOf(counter++));
    }
    // *** Fields related to cherry starts
    public static boolean cherryShown = false;
    public void updateNetCherryCount(){
        if (cherryNeAukaatDikhaadi) {
            netCherryCountLabel.setText(String.valueOf(cherry_counter++));
        }
        cherryNeAukaatDikhaadi = false;
    }

    public int shouldCherryGenerate(){
        Random random = new Random();
        // Generating a random no between 0, 1, 2; out of which if the no. is 1, then the cherry will be generated.
        return random.nextInt(3);
    }
    public static ImageView cherry = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\Cherries.png");
    // *** Fields related to cherry ends

    public int getCurrentScore(){
        return Integer.parseInt(currentScore.getText());
    }

    public Label getLabel(){
        return currentScore;
    }
    public int getNetCherryCount(){
        return Integer.parseInt((netCherryCount.getText()));
    }
    public static Label dummy = new Label("0");
    // *** Methods related to labels ended.

    // Methods for Serialisation :

    private static void serializeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Super.ser"))) {
            // Write the object to the file
            oos.writeObject(SuperClass.getInstance());
            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SuperClass deserializeObject() {
        SuperClass deserializedObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Super.ser"))) {
            // Read the object from the file
            deserializedObject = (SuperClass) ois.readObject();
            System.out.println("Object deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }
    // *** Serialisation ends.
    public static final ImageView heroImg = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");

    private static ArrayList<Rectangle> List_Of_Blocks ;

    // Variables related to run music
    public static String mediaPath;
    public static Media media;
    // Music ends.

    // *** Variables related to rod.
    public static Line rod;
    private static double rodBaseX = 72; // X-coordinate of the base of the rod
    private static double rodXfinal; // X-coordinate of the base of the rod
    private static double rodBaseY = 283; // Y-coordinate of the base of the rod
    private static boolean rotationStart = false;
    private static boolean isGrowing = false;
    private static boolean rotationComplete = false; // Flag to track rotation completion
    private static boolean firstCycleDone = false;
    private static boolean isRotating = false;
    private boolean isRodGrown = false;
    private static double rotationAngle = 0;
    private static double rodLength = 0;
    private static boolean rotationDoneFinally = false;

    private static int cherry_counter = 0;
    private static boolean cherryCounted = false;
    AnimationTimer timer;
    AnimationTimer timer2;
    TranslateTransition translate_fall, translate;
    // *** Variables related to rod ends.

    // ***Variables related to man
    public static boolean isUpsideDown = false;
    ImageView new_hero;

    RotateTransition rotateTransition;
    public static boolean manShifted = false;
    // ***Variables related to man ends

    public static Pane root_home, root_pause, root_running, root_gameOver, root_leaderBoard;;
    public static Scene scene_home, scene_pause, scene_running, scene_gameOver, scene_leaderBoard;

    protected ArrayList<Rectangle> blocksGenerator(Parent root) {
        double initialX = 0;
        double initialY = 283;
        return RectangleFactory.generateRectangles(2, initialX, initialY);
    }

    public void initializeNewGame(ActionEvent event) throws InterruptedException, IOException {
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
    // Method which will retain everything to its original/pre-defined state, once the process @ running screen ended

    public void retainOriginalState(){
        // Yahan par next line nahin honi chahiye; as agar cherry kisi particular row mein nahin bhi hogi, tab bhi wo field ko false kar denge, jabki
        // pichli baar hi cherry collect ho gayi thi
        cherryCounted = false;
        // Actually honi chahiye, taaki agar ek baar cherry collect ho chuki hai, now the cherry counted flag is high only. How to see to this.
        isUpsideDown = false;
        rodBaseX = 72; rodXfinal =0; rotationStart = false;isGrowing = false;rotationComplete = false;rotationDoneFinally = false;
        firstCycleDone = false;isRotating = false;isRodGrown = false;rotationAngle = 0;rodLength = 0;manShifted = false;
    }
    public void setOnFinishedFinal(ActionEvent event, SuperClass superObj) throws IOException {;
        timer.stop();
        rotationStart = false;
        isGrowing = false;
        rotationComplete = false; // Flag to track rotation completion
        firstCycleDone = false;
        isRotating = false;
        isRodGrown = false;
//        BufferedWriter writer = new BufferedWriter(new FileWriter("TotalCherryCount.txt"));
//        writer.write(superObj.cherryCount.getText());
//        writer.close();
        switchToGameOverPage(event);
        String cherryFileName = "TotalCherryCount.txt";
        counter = 0;
    }

    public void switchToRunningPage(ActionEvent event) throws InterruptedException, IOException {
        // Looking at the instance of SuperObj
        SuperClass superObj = SuperClass.getInstance();
        cherryCounted = false;
        new_loader = new FXMLLoader(getClass().getResource("RunningPage.fxml"));
        root_running = new_loader.load();
        if (runningGameStage==null){
            runningGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        scene_running = new Scene(root_running);
        scene_running.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //handleMousePressed(mouseEvent);
//                System.out.println(rotationStart);
//                System.out.println(isGrowing);
                if (rotationStart || isGrowing) {
                    return;
                }
                System.out.println("The mouse is pressed ");
                if (!rotationComplete) {
                    if (!firstCycleDone) {
                        isGrowing = true;
                    }
                    isRotating = false;
                }
            }
        }
        );
        scene_running.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //handleMouseReleased(mouseEvent);
                if (rotationStart){
                    return;
                }
                if (!rotationComplete) { // Check if the rotation is complete
                    isGrowing = false;
                    isRotating = true; // Set rotation flag
                }
            }
        });
        System.out.println(runningGameStage);
        runningGameStage.setScene(scene_running);
        runningGameStage.setTitle("PausePage Screen");
        //runningGameStage.show();
//        addMusic(); //Adding Music

        currentScoreLabel = new Label("0");
        currentScoreLabel.setLayoutX(299.0);
        currentScoreLabel.setLayoutY(37.0);
        currentScoreLabel.setFont(Font.font(24));

        // Defining netCherryCountLabel Label
        netCherryCountLabel = new Label(String.valueOf(cherry_counter));
        netCherryCountLabel.setLayoutX(575);
        netCherryCountLabel.setLayoutY(12);
        netCherryCountLabel.setFont(Font.font(18));
        // Adding net cherry count from the text file into the scene
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\java\\com\\example\\ap_2022006_2022009\\TotatCherryCount.txt"));
        String line = br.readLine();
        netCherryCountLabel.setText(line);
        // Updating cherry counter as well, so that wherever it is fetched, updated value is put into it.
        cherry_counter = Integer.parseInt(line);
        br.close();
        root_running.getChildren().add(netCherryCountLabel);


        //*** Main Functionality starts :
        new_hero = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");
        new_hero.setLayoutX(9);
        new_hero.setLayoutY(193);
        new_hero.setFitWidth(90);
        new_hero.setFitHeight(107);
        new_hero.setPreserveRatio(true);
        root_running.getChildren().add(currentScoreLabel);
        updateScore();
        root_running.getChildren().add(new_hero);

        List_Of_Blocks = blocksGenerator(root_running);
        root_running.getChildren().add(List_Of_Blocks.get(0));
        if (shouldCherryGenerate() == 1){
            cherry.setLayoutY(300);
            cherry.setLayoutX(200);
            cherry.setFitWidth(50);
            cherry.setFitHeight(50);
            cherry.setPreserveRatio(true);
            root_running.getChildren().add(cherry);
            cherryShown = true;
        }
        else{
            cherryShown = false;
        }
        root_running.getChildren().add(List_Of_Blocks.get(1));
        // Till the above code, 2 blocks and hero are placed.

        // Initialising the current score by 0
        if (currentScore ==null) {
            currentScore = new Label();
            currentScore.setText("0");
        }
        rod = new Line(rodBaseX, rodBaseY, rodBaseX, rodBaseY);
        rod.setStrokeWidth(2.5);
        root_running.getChildren().add(rod); // Added the instance of rod

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isGrowing) {
                    rodLength += 3; // Standard set speed
                    rod.setEndX(rodBaseX);
                    rod.setEndY(rodBaseY - rodLength);
                }
                if (isRotating) {
                    firstCycleDone = true;
                    rotationStart = true;
                    rotateRod();
                }
            }
        };
        timer.start();
        // timer handled growth of rod and rotation of rod.

        timer2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (rotationDoneFinally){
                    // Adding cherry if the following conditions is satisfied :
                    cherry.setLayoutX(200);
                    new_hero.boundsInParentProperty().addListener((obs, init_pos,final_pos) -> {
                        if (final_pos.intersects(cherry.getBoundsInParent()) && cherryShown){
                            superObj.cherryCount = netCherryCountLabel;
//                            serializeObject();
//                            System.out.println(superObj.cherryCount);
                            BufferedWriter writer = null;
                            try {
                                writer = new BufferedWriter(new FileWriter("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\java\\com\\example\\ap_2022006_2022009\\TotatCherryCount.txt"));
                                writer.write(String.valueOf(cherry_counter));
                                writer.close();
                                cherryNeAukaatDikhaadi = true;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            root_running.getChildren().remove(cherry);
                            if (!cherryCounted) {
                                cherry_counter++;
                            }
                            cherryCounted = true;
                        }
                    });

                    translate = new TranslateTransition();
                    translate.setNode(new_hero);
                    translate.setDuration(Duration.millis(1000));
                    translate.setByX(rodLength+20);
                    manShifted = true;
                    scene_running.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.W) {
                            handleWKeyPress(event);
                            System.out.println("W key pressed");

                        }
                    });
                    translate.setOnFinished(e-> {
                        System.out.println("Man has finished running.");
                        // Below 3 parameters are same.
//                        System.out.println(rod.getEndX());
//                        System.out.println(rod.getStartX());
//                        System.out.println(rodBaseX);
                        rodXfinal = rodLength + rodBaseX + 20;
                        System.out.println(); // x-coord of the tip of rod.
                        
                        // *** The below three comments are use to understand the positioning of block and stick, using their coordinates.
//                        System.out.println(List_Of_Blocks.get(0).getLayoutX());
//                        System.out.println(List_Of_Blocks.get(1).getLayoutX());
//                        System.out.println(List_Of_Blocks.get(0).getWidth());
//                        System.out.println(List_Of_Blocks.get(1).getX());
//                        System.out.println(List_Of_Blocks.get(1).getWidth());
//                        System.out.println("This is the value: "+ rod.getEndX() );

                        if (rodXfinal-10> List_Of_Blocks.get(1).getX() && rodXfinal < List_Of_Blocks.get(1).getX() + 15 + List_Of_Blocks.get(1).getWidth()) {
                            System.out.println("Landed on the block safely.");
                            try {
//                                runningGameStage.close();
//                                initializeNewGame(event);
                                retainOriginalState();
                                timer.stop();
                                stop();
                                if (mediaPlayer != null) {
                                    mediaPlayer.stop();
                                }
                                cherryCounted = false;
                                // Removing the cherry which was added, so that the object's identity is not confused by java on the next screen.
                                root_running.getChildren().remove(cherry);
                                switchToRunningPage(event);
                            } catch (InterruptedException | IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else{
                            if (manShifted) {
                                System.out.println("Man landed outside the block.");
                                translate_fall = new TranslateTransition();
                                translate_fall.setNode(new_hero);
                                translate_fall.setDuration(Duration.millis(1000));
                                translate_fall.setByY(283);
                                // Checking if the current score is greater than the maximum score :
                                if (getCurrentScore() > Integer.parseInt(SuperClass.getInstance().highest_score.getText())) {
                                    SuperClass.getInstance().highest_score.setText(String.valueOf((getCurrentScore())));
                                }
                                translate_fall.setOnFinished(eventNew -> {
                                    try {
                                        setOnFinishedFinal(event, superObj);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                });
                                translate_fall.play();
                                manShifted = false;
                                retainOriginalState();
                            }
                        }
                    });
                    translate.play();
                    rotationDoneFinally = false;
                    stop();
                }
            }
        };
        timer2.start();

    }

//    private void gameEnded(ActionEvent event) throws InterruptedException {
//        switchToRunningPage(event);
//    }

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
            rotationDoneFinally = true;
            if(!firstCycleDone) {
                firstCycleDone = true;
            }
        }
    }

    private void handleWKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.W) {
            if (!isUpsideDown) {
                rotateUpsideDown();
            } else {
                restoreOriginalState();
            }
        }
    }

    private void rotateUpsideDown() {
        new_hero.setLayoutY(283);
        new_hero.setScaleY(-1);
        isUpsideDown = true;
    }

    private void restoreOriginalState() {
        new_hero.setLayoutY(193);
        new_hero.setScaleY(1);
        isUpsideDown = false;
    }

//    The following 2 functions though show that they don't have any usage, but their functionality is used by directly putting their code wherever needed.
//    This is done to resolve some errors due to the scope of the function.
    private void handleMousePressed(MouseEvent event) {
        if (rotationStart || isGrowing) {
            return;
        }
        System.out.println("The mouse is pressed ");
        if (!rotationComplete) {
            if (!firstCycleDone) {
                isGrowing = true;
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
        runningGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_home = new Scene(root_home);
        runningGameStage.setScene(scene_home);
        runningGameStage.setTitle("HomePage Screen");
        runningGameStage.show();
    }

    public void switchToPausePage(ActionEvent event) throws Exception {
        stop();
        root_pause = loadFXML("PausePage.fxml");
        runningGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_pause = new Scene(root_pause);
        runningGameStage.setScene(scene_pause);
        runningGameStage.setTitle("PausePage Screen");
        runningGameStage.show();
    }

    public void switchToGameOverPage(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
//        initializeStage(runningGameStage);
        // Update the score to 0 at the backend
//        updateCurrentScore("0");
        // dummy is the label, which will be used by the GameOverPageController to update the current score field.
//        dummy.setText(String.valueOf(getCurrentScore()));
        root_gameOver = loadFXML("GameOverPage.fxml");
//        runningGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_gameOver = new Scene(root_gameOver);
        runningGameStage.setScene(scene_gameOver);

        runningGameStage.setTitle("GameOverPage Screen");
        // Defining currentScore Label in Game Over Page Screen
        Label currentScoreLabel = new Label(String.valueOf(counter-1));
        currentScoreLabel.setLayoutX(292.0);
        currentScoreLabel.setLayoutY(120.0);
        currentScoreLabel.setFont(Font.font(24));
        root_gameOver.getChildren().add(currentScoreLabel);


    }

    public void switchToLeaderBoardPage(ActionEvent event) {
        root_leaderBoard = loadFXML("LeaderBoard.fxml");
        runningGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene_leaderBoard = new Scene(root_leaderBoard);
        runningGameStage.setScene(scene_leaderBoard);
        runningGameStage.setTitle("LeaderBoard Screen");
        runningGameStage.show();
    }



    @Override
    public void create() {

    }
}
