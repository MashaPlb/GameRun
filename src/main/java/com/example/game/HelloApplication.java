package com.example.game;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloApplication extends Application {
    private static final Logger LOGGER = Logger.getLogger(HelloApplication.class.getName());
    private static Timeline timeline;
    public static Timeline getting_timeline() {
        return timeline;
    }
    private GameClient gameClient;
    @Override
    public void start(Stage stage) throws IOException {
        gameClient = new GameClient();
        try {
            gameClient.startConnection("localhost", 6666);
            System.out.println("Подключение к серверу успешно установлено.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Не удалось подключиться к серверу:", e);
        }
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
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            HelloController.setting_reverse(!HelloController.getting_reverse());
            System.out.println(HelloController.getting_reverse());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @Override
    public void stop() {
        if (gameClient != null) {
            try {
                gameClient.stopConnection();
                System.out.println("Соединение с сервером закрыто.");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Ошибка при закрытии соединения", e);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}