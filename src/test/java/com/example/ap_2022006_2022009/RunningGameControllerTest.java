package com.example.ap_2022006_2022009;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RunningGameControllerTest {
    Pane root_running;

    @Test
    public void  blocksgenertorTester(){
        ArrayList<Rectangle> test_blocks_gen = new ArrayList<>();
        RunningGameControllerFinal rc = new RunningGameControllerFinal();
        rc.blocksGenerator(root_running);
        assertNotNull(test_blocks_gen);
    }
    @Test
    public void SuperClassTester(){
        SuperClass Superobj = SuperClass.getInstance();
        RunningGameControllerFinal rc = new RunningGameControllerFinal();
//        rc.returnSuperObj();
        assertNotNull(Superobj);
    }
}