package com.example.game;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    private static MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("themes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1270, 832);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        KeyboardHandler.setOnKeyPressed(scene);
        KeyboardHandler.setOnKeyReleased(scene);

        stage.show();
        String musicFile = "src/main/resources/music/startmus.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.play();

        stage.setOnCloseRequest(e -> {
            mediaPlayer.stop();
        });

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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            HelloController.reverse = !HelloController.reverse;
//            HelloController.labelReverse.setText("REVERSE");
//            HelloController.reverseAnimation();
            System.out.println(HelloController.reverse);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}