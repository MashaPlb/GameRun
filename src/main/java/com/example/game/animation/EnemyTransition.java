package com.example.game.animation;

import com.example.game.scene.HelloApplication;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class EnemyTransition {
    public static TranslateTransition create(double duration, double setToX, ImageView thisEnemy) {
        TranslateTransition thisTransition = new TranslateTransition(Duration.millis(duration), thisEnemy);
        thisTransition.setFromX(0);
        thisTransition.setToX(setToX);
        thisTransition.setInterpolator(Interpolator.LINEAR);
        thisTransition.setCycleCount(Animation.INDEFINITE);
        thisTransition.play();
        return thisTransition;
    }

    public static void pause() {
        HelloController.setting_playerSpeed(0);
        HelloController.getting_parallelTransition().pause();
        HelloController.getting_enemyTransition().pause();
        HelloController.getting_enemy1Transition().pause();
        HelloController.getting_enemy2Transition().pause();
        HelloController.getting_enemy3Transition().pause();
        HelloController.getting_newEnemyTransition().pause();
        HelloController.getting_newEnemy1Transition().pause();
        HelloController.getting_newEnemy2Transition().pause();
        HelloController.getting_newEnemy3Transition().pause();
        HelloController.getting_plat1Transition().pause();
        HelloController.getting_plat2Transition().pause();
        HelloController.getting_plat3Transition().pause();
        HelloController.getting_enemyAngryTransition().pause();
        HelloController.getting_moneyTransition().pause();
        HelloController.getting_money1Transition().pause();
        HelloController.getting_money2Transition().pause();
        HelloController.getting_money3Transition().pause();
        HelloApplication.getting_timeline().stop();
    }

    public static void play() {
        HelloController.setting_playerSpeed(4);
        HelloController.getting_parallelTransition().play();
        HelloController.getting_enemyTransition().play();
        HelloController.getting_enemy1Transition().play();
        HelloController.getting_enemy2Transition().play();
        HelloController.getting_enemy3Transition().play();
        HelloController.getting_newEnemyTransition().play();
        HelloController.getting_newEnemy1Transition().play();
        HelloController.getting_newEnemy2Transition().play();
        HelloController.getting_newEnemy3Transition().play();
        HelloController.getting_plat1Transition().play();
        HelloController.getting_plat2Transition().play();
        HelloController.getting_plat3Transition().play();
        HelloController.getting_enemyAngryTransition().play();
        HelloController.getting_moneyTransition().play();
        HelloController.getting_money1Transition().play();
        HelloController.getting_money2Transition().play();
        HelloController.getting_money3Transition().play();
        HelloApplication.getting_timeline().play();
    }
}
