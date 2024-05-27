package com.example.game.handler;
import com.example.game.HelloApplication;
import com.example.game.HelloController;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.io.IOException;

public class KeyboardHandler {
    private static boolean isSceneChanged = false;
    public static void setting_isSceneChanged(boolean scene) {
        isSceneChanged = scene;
    }
    public static void setOnKeyPressed(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE && !HelloController.getting_jump()) {
                if (HelloController.isPause())
                    HelloController.setting_jump(true);
            } else if (e.getCode() == KeyCode.ENTER && !isSceneChanged) {
                try {
                    HelloApplication.changeScene("hello-view.fxml");
                    isSceneChanged = true;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (!HelloController.getting_reverse()) {
                if (e.getCode() == KeyCode.LEFT)
                    HelloController.setting_left(true);
                else if (e.getCode() == KeyCode.RIGHT)
                    HelloController.setting_right(true);
                else if (e.getCode() == KeyCode.D)
                    HelloController.setting_right2(true);
                else if (e.getCode() == KeyCode.A)
                    HelloController.setting_left2(true);
                else if (e.getCode() == KeyCode.S)
                    HelloController.setting_down(true);
                else if (e.getCode() == KeyCode.W)
                    HelloController.setting_up(true);
                else if (e.getCode() == KeyCode.DOWN) {
                    if (HelloController.isPause())
                        HelloController.setting_small(true);
                } else if (e.getCode() == KeyCode.UP) {
                    if (HelloController.isPause())
                        HelloController.setting_big(true);
                }
            } else {
                if (e.getCode() == KeyCode.A)
                    HelloController.setting_left(true);
                else if (e.getCode() == KeyCode.D)
                    HelloController.setting_right(true);
                else if (e.getCode() == KeyCode.RIGHT)
                    HelloController.setting_right2(true);
                else if (e.getCode() == KeyCode.LEFT)
                    HelloController.setting_left2(true);
                else if (e.getCode() == KeyCode.DOWN)
                    HelloController.setting_down(true);
                else if (e.getCode() == KeyCode.UP)
                    HelloController.setting_up(true);
                else if (e.getCode() == KeyCode.S) {
                    if (HelloController.isPause())
                        HelloController.setting_small(true);
                } else if (e.getCode() == KeyCode.W) {
                    if (HelloController.isPause())
                        HelloController.setting_big(true);
                }
            }
        });
    }

    public static void setOnKeyReleased(Scene scene) {
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                HelloController.setting_isPause(HelloController.isPause());
            if (!HelloController.getting_reverse()) {
                if (e.getCode() == KeyCode.LEFT)
                    HelloController.setting_left(false);
                else if (e.getCode() == KeyCode.RIGHT)
                    HelloController.setting_right(false);
                else if (e.getCode() == KeyCode.D)
                    HelloController.setting_right2(false);
                else if (e.getCode() == KeyCode.A)
                    HelloController.setting_left2(false);
                else if (e.getCode() == KeyCode.S)
                    HelloController.setting_down(false);
                else if (e.getCode() == KeyCode.W)
                    HelloController.setting_up(false);
            } else {
                if (e.getCode() == KeyCode.A)
                    HelloController.setting_left(false);
                else if (e.getCode() == KeyCode.D)
                    HelloController.setting_right(false);
                else if (e.getCode() == KeyCode.RIGHT)
                    HelloController.setting_right2(false);
                else if (e.getCode() == KeyCode.LEFT)
                    HelloController.setting_left2(false);
                else if (e.getCode() == KeyCode.DOWN)
                    HelloController.setting_down(false);
                else if (e.getCode() == KeyCode.UP)
                    HelloController.setting_up(false);
            }
        });
    }
}
