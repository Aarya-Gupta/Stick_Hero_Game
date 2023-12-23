package com.example.ap_2022006_2022009;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;


public class GameApplication extends Application{
    public Stage primaryStage;
    @FXML
    private static MediaView mediaView;
    private static MediaPlayer mediaPlayer;

    public static String mediaPath;
    public static Media media;
    AnchorPane root_home;
    public AnchorPane loadFXML(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
        root_home.getChildren().add(mediaView);
        // Set the MediaPlayer to play
        mediaPlayer.play();
        //***Code to add music ends.
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        this.primaryStage = primaryStage;
        // Setting the icon of the game :
        Image app_icon = new Image("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\app_icon.png");
        primaryStage.getIcons().add(app_icon);

        // Load the HomePage FXML file
        root_home = loadFXML("HomePage.fxml");
        Scene scene_home = new Scene(root_home);
        addMusic();
        // Setting the initial scene
        primaryStage.setScene(scene_home);
        primaryStage.setTitle("Home Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
