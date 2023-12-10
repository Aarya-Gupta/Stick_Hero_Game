package com.example.ap_2022006_2022009;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class GameApplication extends Application{
    public Stage primaryStage;
    public AnchorPane loadFXML(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
//        G = new AnchorPane();
        this.primaryStage = primaryStage;
        // Setting the icon of the game :
        Image app_icon = new Image("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\app_icon.png");
        primaryStage.getIcons().add(app_icon);

        // Load the HomePage FXML file
        AnchorPane root_home = loadFXML("HomePage.fxml");
        Scene scene_home = new Scene(root_home);

        // Setting the initial scene
        primaryStage.setScene(scene_home);
        primaryStage.setTitle("Home Page");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
