package com.example.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyboardHandler {
    public static void setOnKeyPressed(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE && !HelloController.jump) {
                if (!HelloController.isPause)
                    HelloController.jump = true;
            }

            if(e.getCode() == KeyCode.LEFT)
                HelloController.left = true;

            if(e.getCode() == KeyCode.RIGHT)
                HelloController.right = true;

            if(e.getCode() == KeyCode.DOWN) {
                if (!HelloController.isPause)
                    HelloController.small = true;
            }

            if(e.getCode() == KeyCode.UP) {
                if (!HelloController.isPause)
                    HelloController.big = true;
            }
        });
    }

    public static void setOnKeyReleased(Scene scene) {
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.LEFT)
                HelloController.left = false;

            if(e.getCode() == KeyCode.RIGHT)
                HelloController.right = false;

            if(e.getCode() == KeyCode.ESCAPE)
                HelloController.isPause = !HelloController.isPause;
        });
    }
}
