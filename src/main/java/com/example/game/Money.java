package com.example.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Money {
    public static void gettingMoney(ImageView myMoney) {
        myMoney.setFitWidth(1);
        myMoney.setFitHeight(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    myMoney.setFitWidth(48);
                    myMoney.setFitHeight(39);
                    HelloController.setting_isMoneyCollected(false);
                })
        );
        timeline.play();
    }
    public static void gettingBigMoney(ImageView myMoney) {
        myMoney.setFitWidth(1);
        myMoney.setFitHeight(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    myMoney.setFitWidth(48);
                    myMoney.setFitHeight(39);
                    HelloController.setting_isBigMoneyCollected(false);
                })
        );
        timeline.play();
    }
}
