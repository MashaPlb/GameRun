package com.example.game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicHandler {
    public static MediaPlayer playStartMusic(String filename) {
        Media sound = new Media(new File(filename).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        return mediaPlayer;
    }
    public static void playMusic(String filename) {
        Media sound = new Media(new File(filename).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
