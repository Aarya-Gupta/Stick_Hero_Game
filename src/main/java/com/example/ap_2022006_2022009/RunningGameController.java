package com.example.ap_2022006_2022009;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Random;

//public class RunningGameController implements StickController, PausePageController, AppExitPageController{
//public class RunningGameController implements StickController{
public class RunningGameController extends GameApplication implements Initializable{
    // Defining the labels
    @FXML
    private Label currentScore;
    @FXML
    private Label netCherryCount;
    @FXML
    private ImageView hero_img;
    private Scene scene_running;
//    private Pane root_running;

    private ArrayList<Rectangle> List_Of_Blocks;

    private Parent loadFXML(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    @FXML
    protected void onPauseButtonClick(ActionEvent event) {
        // Will show the leaderboard, which will show the top 3 scores.
        // The leaderboard must have a back button, which takes the user back to the pause page
        // This means that Leaderboard screen is also to be created.
        // This method will call a switchToLeaderboard() method.
        switchToPausePage(event);
        System.out.println("Pause Button clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // translate
        // Moving hero
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(hero_img);
        translate.setDuration(Duration.millis(5000));
        translate.setByX(1000);
        translate.setOnFinished(e->{
            // blocksGenerator();
        });
        translate.play();
    }
    @Override
    public void start(Stage primaryStage) {
        super.primaryStage = primaryStage;
        Parent root_running = loadFXML("RunningPage.fxml");
        scene_running = new Scene(root_running);

        // Generating 100 blocks;
        ArrayList<Rectangle> ListOfBlocks = new ArrayList<>();
        blocksGenerator(ListOfBlocks, root_running);
        for (int i = 0; i<100; i++){
//            G.getChildren().add(ListOfBlocks.get(i));
        }

        // Generating 100 rectangles; not making any function.
        Random random = new Random();
        int maxWidth = 100;
        int maxGap = 50;
        int minWidth = 5;
        int minGap = 5;
        int xPosition = 0;
        int yPosition = 283;
        for (int i = 0; i < 100; i++) {
            double width = minWidth + random.nextDouble() * maxWidth;
            Rectangle rectangle = new Rectangle(width, 50);
            rectangle.setHeight(118); // Set height to 150 pixels
            rectangle.setX(xPosition);
            rectangle.setY(yPosition);
            xPosition += (int) (minGap + width + random.nextDouble() * maxGap);
            // Not able to resolve how to add the rectangle into the scene; the following line is throwing error.
            G.getChildren().add(rectangle);
        }
    }
}
