package com.example.game;

import com.example.game.handler.KeyboardHandler;
import com.example.game.handler.MusicHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Timeline timeline;

    public static Timeline getting_timeline() {
        return timeline;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1270, 832);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        KeyboardHandler.setOnKeyPressed(scene);
        KeyboardHandler.setOnKeyReleased(scene);
        stage.show();
        MediaPlayer mediaPlayer = MusicHandler.playStartMusic("/music/startmus.mp3");
        stage.setOnCloseRequest(e -> mediaPlayer.stop());
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
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            HelloController.setting_reverse(!HelloController.getting_reverse());
            System.out.println(HelloController.getting_reverse());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}