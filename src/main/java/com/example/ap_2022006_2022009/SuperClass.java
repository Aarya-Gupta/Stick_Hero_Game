package com.example.ap_2022006_2022009;
import javafx.scene.control.Label;

import java.io.*;

// Following singleton design pattern

public class SuperClass implements Serializable {
    // Contains fields of type label only, so that it's easier to update and access directly.
    public Label cherryCount;
    public Label highest_score;


    public SuperClass(Label cherryCount, Label highest_score) {
        this.cherryCount = cherryCount;
        this.highest_score = highest_score;
    }

    private static final class InstanceHolder {
        // Creating new labels if it doesn't exist.
        private static final SuperClass instance = new SuperClass(new Label("0"), new Label("0"));
    }

    public static SuperClass getInstance() {
        return InstanceHolder.instance;
    }

    // Method to update scores in the text file
    public void updateScores(Label newCherryCount, Label newHighestScore) {
        this.cherryCount = newCherryCount;
        this.highest_score = newHighestScore;
        // Save updated values to file
        saveParametersToFile();
    }

    // Method to load parameters from file
    private void loadParametersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Super.ser"))) {
            cherryCount.setText(reader.readLine());
            highest_score.setText(reader.readLine());
//            cherryCount = reader.readLine();
//            highest_score = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file reading exception
        }
    }

    // Method to save parameters to file;
    private void saveParametersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Super.ser"))) {
            writer.write(cherryCount.getText());
            writer.newLine();
            writer.write(highest_score.getText());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file writing exception
        }
    }
}