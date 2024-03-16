package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public static long startTime;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 400);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        scene.setOnKeyPressed(e -> { //обрабатываем момент нажатия клавиши
            if(e.getCode() == KeyCode.SPACE && !HelloController.jump)
                HelloController.jump = true;

            if(e.getCode() == KeyCode.LEFT)
                HelloController.left = true;

            if(e.getCode() == KeyCode.RIGHT)
                HelloController.right = true;

            if(e.getCode() == KeyCode.DOWN)
                HelloController.small = true;
            if(e.getCode() == KeyCode.UP)
                HelloController.big = true;
        });

        scene.setOnKeyReleased(e -> { //обрабатываем момент отпускания клавиши
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