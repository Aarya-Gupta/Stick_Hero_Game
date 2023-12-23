package com.example.ap_2022006_2022009;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;
public class RectangleFactory {
    private static final int MAX_WIDTH = 100;
    private static final int MAX_GAP = 300;
    private static final int MIN_WIDTH = 20;
    private static final int MIN_GAP = 165;

    // Factory method to create a single rectangle
    public static Rectangle createRectangle(double width, double height, double x, double y) {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setX(x);
        rectangle.setY(y);
        return rectangle;
    }

    // Factory method to generate a list of rectangles
    public static ArrayList<Rectangle> generateRectangles(int numberOfRectangles, double initialX, double initialY) {
        ArrayList<Rectangle> list_of_blocks = new ArrayList<>();
        Random random = new Random();
        double xPosition = initialX;

        // Creating initial block of custom width
        list_of_blocks.add(createRectangle(72, 118, xPosition, initialY));
        xPosition += (int) (72 + MIN_GAP + random.nextDouble() * (MAX_GAP-MIN_GAP));

        // Generating specified number of blocks
        for (int i = 1; i < numberOfRectangles; i++) {
            double width = MIN_WIDTH + random.nextDouble() * (MAX_WIDTH-MIN_WIDTH);
            list_of_blocks.add(createRectangle(width, 118, xPosition, initialY));
            xPosition += (int) (MIN_GAP + width + random.nextDouble() * MAX_GAP);
        }

        return list_of_blocks;
    }
}
