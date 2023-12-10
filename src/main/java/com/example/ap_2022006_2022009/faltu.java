package com.example.ap_2022006_2022009;
import javafx.animation.AnimationTimer;
        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Line;
        import javafx.scene.shape.Rectangle;
        import javafx.stage.Stage;

public class faltu extends Application {
    private Line rod;
    private double rodBaseX = 200; // X-coordinate of the base of the rod
    private double rodBaseY = 300; // Y-coordinate of the base of the rod
    private boolean isGrowing = false;
    private boolean isRotating = false;
    private boolean rotationComplete = false; // Flag to track rotation completion
    private double rodLength = 0;
    private double growthSpeed = 3; // Velocity of rod growth

    private Rectangle object;
    private double objectSpeed = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);

        rod = new Line();
        rod.setStartX(rodBaseX);
        rod.setStartY(rodBaseY);
        rod.setEndX(rodBaseX);
        rod.setEndY(rodBaseY);
        rod.setStrokeWidth(3);
        rod.setStroke(Color.BLACK);
        root.getChildren().add(rod);

        object = new Rectangle(20, 20);
        object.setFill(Color.BLUE);
        root.getChildren().add(object);

        scene.setOnMousePressed(this::handleMousePressed);
        scene.setOnMouseReleased(this::handleMouseReleased);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isGrowing) {
                    rodLength += growthSpeed;
                    rod.setEndX(rodBaseX);
                    rod.setEndY(rodBaseY - rodLength);
                }
                if (isRotating) {
                    rotateRod();
                }
                if (rotationComplete) {
                    moveObject();
                }
            }
        };
        timer.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Stick Rotation Game");
        primaryStage.show();
    }

    private void handleMousePressed(MouseEvent event) {
        isGrowing = true;
    }

    private void handleMouseReleased(MouseEvent event) {
        if (!rotationComplete) {
            isGrowing = false;
            isRotating = true;
        }
    }

    private void rotateRod() {
        double endX = rodBaseX;
        double endY = rodBaseY - rodLength;

        rod.setEndX(rodBaseX);
        rod.setEndY(rodBaseY);

        rod.getTransforms().clear();
        rod.getTransforms().add(new javafx.scene.transform.Rotate(90, rodBaseX, rodBaseY));

        rod.setEndX(endX);
        rod.setEndY(endY);

        isRotating = false;
        rotationComplete = true;
    }

    private void moveObject() {
        double deltaX = rodLength;
        double deltaY = 0;

        object.setTranslateX(object.getTranslateX() + deltaX);
        object.setTranslateY(object.getTranslateY() + deltaY);

        rotationComplete = false; // Reset for the next round
    }
}
