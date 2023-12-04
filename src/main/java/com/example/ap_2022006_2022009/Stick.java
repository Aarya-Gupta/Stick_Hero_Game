package com.example.ap_2022006_2022009;

import javafx.scene.shape.Line;

public class Stick {
    private Line rod;
    private double rodBaseX = 10; // X-coordinate of the base of the rod
    private double rodBaseY = 193; // Y-coordinate of the base of the rod
    private boolean isGrowing = false;
    private boolean isRotating = false;
    private boolean rotationComplete = false; // Flag to track rotation completion
    private double rodLength = 0;
    private double growthSpeed = 2; // Velocity of rod growth
    private double rotationAngle = 0;


}
