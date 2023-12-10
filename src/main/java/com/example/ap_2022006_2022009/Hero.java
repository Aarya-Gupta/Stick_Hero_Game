package com.example.ap_2022006_2022009;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

//public class Player implements PlayerController{
public class Hero extends ImageView {
    public static double heroBaseX; // X-coordinate of the base of the rod
    public static double heroBaseY; // Y-coordinate of the base of the rod
    private static final ImageView hero_img = new ImageView("C:\\Users\\Shrey Gupta\\Desktop\\AP_2022006_2022009\\src\\main\\resources\\com\\example\\ap_2022006_2022009\\HeroImg.png");
    public static boolean isMoving = false;

    public Hero(double heroBaseX, double heroBaseY) {
        this.heroBaseX = heroBaseX;
        this.heroBaseY = heroBaseY;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
