package com.example.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import java.io.IOException;

public class KeyboardHandler {
    public static void setOnKeyPressed(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.SPACE && !HelloController.jump) {
                if (!HelloController.isPause)
                    HelloController.jump = true;
            } else if(e.getCode() == KeyCode.ENTER && !StartScene.isSceneChanged) {
                try {
                    HelloApplication.changeScene("hello-view.fxml");
                    StartScene.isSceneChanged = true;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (!HelloController.reverse) {
                if (e.getCode() == KeyCode.LEFT)
                    HelloController.left = true;
                else if (e.getCode() == KeyCode.RIGHT)
                    HelloController.right = true;

                else if (e.getCode() == KeyCode.D)
                    HelloController.right2 = true;

                else if (e.getCode() == KeyCode.A)
                    HelloController.left2 = true;

                else if (e.getCode() == KeyCode.S)
                    HelloController.down2 = true;

                else if (e.getCode() == KeyCode.W)
                    HelloController.up2 = true;

                else if (e.getCode() == KeyCode.DOWN) {
                    if (!HelloController.isPause)
                        HelloController.small = true;
                } else if (e.getCode() == KeyCode.UP) {
                    if (!HelloController.isPause)
                        HelloController.big = true;
                }
            } else {
                if (e.getCode() == KeyCode.A)
                    HelloController.left = true;
                else if (e.getCode() == KeyCode.D)
                    HelloController.right = true;
                else if (e.getCode() == KeyCode.RIGHT)
                    HelloController.right2 = true;
                else if (e.getCode() == KeyCode.LEFT)
                    HelloController.left2 = true;
                else if (e.getCode() == KeyCode.DOWN)
                    HelloController.down2 = true;
                else if (e.getCode() == KeyCode.UP)
                    HelloController.up2 = true;
                else if (e.getCode() == KeyCode.S) {
                    if (!HelloController.isPause)
                        HelloController.small = true;
                } else if (e.getCode() == KeyCode.W) {
                    if (!HelloController.isPause)
                        HelloController.big = true;
                }
            }
        });
    }

    public static void setOnKeyReleased(Scene scene) {
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.ESCAPE)
                HelloController.isPause = !HelloController.isPause;
            if (!HelloController.reverse) {
                if(e.getCode() == KeyCode.LEFT)
                    HelloController.left = false;

                else if(e.getCode() == KeyCode.RIGHT)
                    HelloController.right = false;

                else if(e.getCode() == KeyCode.D)
                    HelloController.right2 = false;

                else if(e.getCode() == KeyCode.A)
                    HelloController.left2 = false;

                else if(e.getCode() == KeyCode.S)
                    HelloController.down2 = false;

                else if(e.getCode() == KeyCode.W)
                    HelloController.up2 = false;
            } else {
                if(e.getCode() == KeyCode.A)
                    HelloController.left = false;

                else if(e.getCode() == KeyCode.D)
                    HelloController.right = false;

                else if(e.getCode() == KeyCode.RIGHT)
                    HelloController.right2 = false;

                else if(e.getCode() == KeyCode.LEFT)
                    HelloController.left2 = false;

                else if(e.getCode() == KeyCode.DOWN)
                    HelloController.down2 = false;

                else if(e.getCode() == KeyCode.UP)
                    HelloController.up2 = false;
            }
        });
    }
}
