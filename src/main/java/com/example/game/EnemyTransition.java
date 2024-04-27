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
        HelloController.plat1Transition.pause();
        HelloController.plat2Transition.pause();
        HelloController.plat3Transition.pause();
        HelloController.enemyAngryTransition.pause();
        HelloController.moneyTransition.pause();
        HelloController.money1Transition.pause();
        HelloController.money2Transition.pause();
        HelloController.money3Transition.pause();
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
        HelloController.plat1Transition.play();
        HelloController.plat2Transition.play();
        HelloController.plat3Transition.play();
        HelloController.enemyAngryTransition.play();
        HelloController.moneyTransition.play();
        HelloController.money1Transition.play();
        HelloController.money2Transition.play();
        HelloController.money3Transition.play();
    }
}
