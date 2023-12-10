package com.example.ap_2022006_2022009;
import javafx.scene.input.MouseEvent;

// Following singleton design pattern

public class SuperClass {
    public String my_score = "0";
    public String highest_score = "0";

    private SuperClass(String my_score, String highest_score) {
        this.my_score = my_score;
        this.highest_score = highest_score;
    }

    private static final class InstanceHolder {
        private static final SuperClass instance = new SuperClass("0", "0");
    }

    public static SuperClass getInstance() {
        return InstanceHolder.instance;
    }

    // Other methods and fields...

    // For example, you can add a method to update scores
    public void updateScores(String newMyScore, String newHighestScore) {
        this.my_score = newMyScore;
        this.highest_score = newHighestScore;
    }
}

//    @Override
//    public void start(Stage primaryStage) {
//        super.primaryStage = primaryStage;
//        Parent root_running = loadFXML("RunningPage.fxml");
//        scene_running = new Scene(root_running);
//
//        // Generating 100 blocks;
//        ArrayList<Rectangle> ListOfBlocks = new ArrayList<>();
//        blocksGenerator(ListOfBlocks, root_running);
//        for (int i = 0; i<100; i++){
////            G.getChildren().add(ListOfBlocks.get(i));
//        }
//
//        // Generating 100 rectangles; not making any function.
//        Random random = new Random();
//        int maxWidth = 100;
//        int maxGap = 50;
//        int minWidth = 5;
//        int minGap = 5;
//        int xPosition = 0;
//        int yPosition = 283;
//        for (int i = 0; i < 100; i++) {
//            double width = minWidth + random.nextDouble() * maxWidth;
//            Rectangle rectangle = new Rectangle(width, 50);
//            rectangle.setHeight(118); // Set height to 150 pixels
//            rectangle.setX(xPosition);
//            rectangle.setY(yPosition);
//            xPosition += (int) (minGap + width + random.nextDouble() * maxGap);
//            // Not able to resolve how to add the rectangle into the scene; the following line is throwing error.
//            G.getChildren().add(rectangle);
//        }
//    }