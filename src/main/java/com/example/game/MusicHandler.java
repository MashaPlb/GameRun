package com.example.game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;

public class MusicHandler {
    public static MediaPlayer playStartMusic(String filename) {
        Media sound = new Media(Objects.requireNonNull(MusicHandler.class.getResource(filename)).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        return mediaPlayer;
    }
    public static void playMusic(String filename) {
        Media sound = new Media(Objects.requireNonNull(MusicHandler.class.getResource(filename)).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
