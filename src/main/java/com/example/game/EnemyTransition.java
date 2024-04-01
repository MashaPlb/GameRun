package com.example.game;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class EnemyTransition {
    static TranslateTransition create(double duration, double setToX, ImageView thisEnemy) {
        TranslateTransition thisTransition = new TranslateTransition(Duration.millis(duration), thisEnemy);
        thisTransition.setFromX(0);
        thisTransition.setToX(setToX);
        thisTransition.setInterpolator(Interpolator.LINEAR);
        thisTransition.setCycleCount(Animation.INDEFINITE);
        thisTransition.play();
        return thisTransition;
    }

    static void pause() {
        HelloController.playerSpeed = 0;
        HelloController.parallelTransition.pause();
        HelloController.enemyTransition.pause();
        HelloController.enemy1Transition.pause();
        HelloController.enemy2Transition.pause();
        HelloController.enemy3Transition.pause();
        HelloController.newEnemyTransition.pause();
        HelloController.newEnemy1Transition.pause();
        HelloController.newEnemy2Transition.pause();
        HelloController.newEnemy3Transition.pause();
    }

    static void play() {
        HelloController.playerSpeed = 4;
        HelloController.parallelTransition.play();
        HelloController.enemyTransition.play();
        HelloController.enemy1Transition.play();
        HelloController.enemy2Transition.play();
        HelloController.enemy3Transition.play();
        HelloController.newEnemyTransition.play();
        HelloController.newEnemy1Transition.play();
        HelloController.newEnemy2Transition.play();
        HelloController.newEnemy3Transition.play();
    }
}
