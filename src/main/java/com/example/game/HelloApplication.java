package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1270, 832);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        KeyboardHandler.setOnKeyPressed(scene);
        KeyboardHandler.setOnKeyReleased(scene);

        stage.show();
    }

    public static void changeScene(String source) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(source));
        Scene scene = new Scene(fxmlLoader.load(), 1270, 832);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        KeyboardHandler.setOnKeyPressed(scene);
        KeyboardHandler.setOnKeyReleased(scene);

        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}