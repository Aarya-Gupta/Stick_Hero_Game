package com.example.ap_2022006_2022009;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class dummy2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        Rectangle rectangle = new Rectangle(30, 80, Color.BLUE); // Decreased width
        rectangle.setTranslateX(100);
        rectangle.setTranslateY(150); // Adjusted initial Y position

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), rectangle);
        scaleTransition.setToY(0.5); // Set the final scale in Y direction (decreasing height)

        root.getChildren().add(rectangle);

        Scene scene = new Scene(root, 300, 300); // Increased size of the pane

        scene.setOnMousePressed(event -> {
            // Start the scale transition when mouse is pressed
            scaleTransition.play();
        });

        scene.setOnMouseReleased(event -> {
            // Pause the scale transition when mouse is released
            scaleTransition.pause();
        });

        primaryStage.setTitle("Rectangle Scale Transition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
