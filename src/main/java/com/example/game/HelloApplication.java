package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 400);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

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

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.LEFT)
                HelloController.left = false;

            if(e.getCode() == KeyCode.RIGHT)
                HelloController.right = false;

            if(e.getCode() == KeyCode.ESCAPE)
                HelloController.isPause = !HelloController.isPause;
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}