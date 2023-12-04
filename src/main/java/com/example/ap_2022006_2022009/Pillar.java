package com.example.ap_2022006_2022009;

public class Pillar {
    private static float pillarHeight; // Will be a final field w.r.t. a game.
    private static float pillarWidth;
    private static float bonus_point_position = (float) pillarWidth/2;
    private String pillarColour; // Will be by default set to black.

    // Only get method for pillarHeight field, as the height of the pillar is set to be constant for a particular game.

    public static float getPillarHeight() {
        return pillarHeight;
    }

    public static float getpillarWidth() {
        return pillarWidth;
    }

    public static void setpillarWidth(float pillarWidth) {
        Pillar.pillarWidth = pillarWidth;
    }

    public static float getBonus_point_position() {
        return bonus_point_position;
    }

    public static void setBonus_point_position(float bonus_point_position) {
        Pillar.bonus_point_position = bonus_point_position;
    }
}
