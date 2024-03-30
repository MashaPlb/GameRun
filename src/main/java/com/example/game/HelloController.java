package com.example.game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    public static long startTime, endTime;

    @FXML
    private ImageView bg1, bg2, player, enemy, enemy1, enemy2, enemy3, newEnemy, newEnemy1, newEnemy2, newEnemy3;

    @FXML
    private Label labelPause, labelLose;
    private final int BG_WIDTH = 724;

    private ParallelTransition parallelTransition;
    private TranslateTransition enemyTransition, enemy1Transition, enemy2Transition, enemy3Transition, newEnemyTransition, newEnemy1Transition, newEnemy2Transition, newEnemy3Transition;

    public static boolean jump = false;
    public static boolean small = false; //для меньшения персонажа
    public static boolean big = false;
    public static boolean right = false;
    public static boolean left = false;
    public static boolean isPause = false;
    private int playerSpeed = 4;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (jump && player.getFitHeight() == 131 && player.getFitWidth() == 76 && player.getLayoutY() > 73f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if(player.getFitHeight() == 131 && player.getFitWidth() == 76 && player.getLayoutY() <= 191f){
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerSpeed);
            }

            if (jump && player.getFitHeight() == 57 && player.getFitWidth() == 53 && player.getLayoutY() > 50f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if(player.getFitHeight() == 57 && player.getFitWidth() == 53 && player.getLayoutY() <= 171f){
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerSpeed);
            }

            if(small && player.getFitHeight() == 131 && player.getFitWidth() == 76) {
                player.setFitHeight(57);
                player.setFitWidth(53);
                player.setLayoutY(170);
                small = false;
            }

            if (big && player.getFitHeight() == 57 && player.getFitWidth() == 53){
                player.setLayoutY(192);
                player.setFitHeight(131);
                player.setFitWidth(76);
                big = false;
            }


            if(right && player.getLayoutX() < 200f)
                player.setLayoutX(player.getLayoutX() + playerSpeed);
            if(left && player.getLayoutX() > 28f)
                player.setLayoutX(player.getLayoutX() - playerSpeed);

            if(isPause && !labelPause.isVisible()) {
                playerSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
                enemy1Transition.pause();
                enemy2Transition.pause();
                enemy3Transition.pause();
                newEnemyTransition.pause();
                newEnemy1Transition.pause();
                newEnemy2Transition.pause();
                newEnemy3Transition.pause();
                labelPause.setVisible(true);
            }
            else if(!isPause && labelPause.isVisible()) {
                labelPause.setVisible(false);
                playerSpeed = 4;
                parallelTransition.play();
                enemyTransition.play();
                enemy1Transition.play();
                enemy2Transition.play();
                enemy3Transition.play();
                newEnemyTransition.play();
                newEnemy1Transition.play();
                newEnemy2Transition.play();
                newEnemy3Transition.play();
            }

            if(player.getBoundsInParent().intersects(enemy.getBoundsInParent()) || player.getBoundsInParent().intersects(enemy1.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy2.getBoundsInParent()) || player.getBoundsInParent().intersects(enemy3.getBoundsInParent())){
                labelLose.setVisible(true);
                playerSpeed = 0;
                parallelTransition.pause();
                enemyTransition.pause();
                enemy1Transition.pause();
                enemy2Transition.pause();
                enemy3Transition.pause();
                newEnemyTransition.pause();
                newEnemy1Transition.pause();
                newEnemy2Transition.pause();
                newEnemy3Transition.pause();
                timer.stop();

                endTime = System.currentTimeMillis();
                long playTime = endTime - startTime;
                System.out.println(playTime);
//                Label playTimeLabel = new Label();

                // Устанавливаем текст в Label
//                playTimeLabel.setText(String.valueOf(playTime));
            }
        }
    };


    @FXML
    void initialize() {
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        bgOneTransition.setToX(BG_WIDTH * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(BG_WIDTH * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);

        enemyTransition = new TranslateTransition(Duration.millis(3500), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(BG_WIDTH * -1 - 100);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();

        newEnemyTransition = new TranslateTransition(Duration.millis(3500), newEnemy);
        newEnemyTransition.setFromX(0);
        newEnemyTransition.setToX(BG_WIDTH * -1 - 100);
        newEnemyTransition.setInterpolator(Interpolator.LINEAR);
        newEnemyTransition.setCycleCount(Animation.INDEFINITE);
        newEnemyTransition.play();

        enemy3Transition = new TranslateTransition(Duration.millis(4500), enemy3);
        enemy3Transition.setFromX(0);
        enemy3Transition.setToX(BG_WIDTH * -1 - 350);
        enemy3Transition.setInterpolator(Interpolator.LINEAR);
        enemy3Transition.setCycleCount(Animation.INDEFINITE);
        enemy3Transition.play();

        newEnemy3Transition = new TranslateTransition(Duration.millis(4500), newEnemy3);
        newEnemy3Transition.setFromX(0);
        newEnemy3Transition.setToX(BG_WIDTH * -1 - 350);
        newEnemy3Transition.setInterpolator(Interpolator.LINEAR);
        newEnemy3Transition.setCycleCount(Animation.INDEFINITE);
        newEnemy3Transition.play();

        enemy1Transition = new TranslateTransition(Duration.millis(6000), enemy1);
        enemy1Transition.setFromX(0);
        enemy1Transition.setToX(BG_WIDTH * -1 - 700);
        enemy1Transition.setInterpolator(Interpolator.LINEAR);
        enemy1Transition.setCycleCount(Animation.INDEFINITE);
        enemy1Transition.play();

        newEnemy1Transition = new TranslateTransition(Duration.millis(6000), newEnemy1);
        newEnemy1Transition.setFromX(0);
        newEnemy1Transition.setToX(BG_WIDTH * -1 - 700);
        newEnemy1Transition.setInterpolator(Interpolator.LINEAR);
        newEnemy1Transition.setCycleCount(Animation.INDEFINITE);
        newEnemy1Transition.play();

        enemy2Transition = new TranslateTransition(Duration.millis(6000), enemy2);
        enemy2Transition.setFromX(0);
        enemy2Transition.setToX(BG_WIDTH * -1 - 700);
        enemy2Transition.setInterpolator(Interpolator.LINEAR);
        enemy2Transition.setCycleCount(Animation.INDEFINITE);
        enemy2Transition.play();

        newEnemy2Transition = new TranslateTransition(Duration.millis(6000), newEnemy2);
        newEnemy2Transition.setFromX(0);
        newEnemy2Transition.setToX(BG_WIDTH * -1 - 700);
        newEnemy2Transition.setInterpolator(Interpolator.LINEAR);
        newEnemy2Transition.setCycleCount(Animation.INDEFINITE);
        newEnemy2Transition.play();

        parallelTransition = new ParallelTransition(bgTwoTransition, bgOneTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
        startTime = System.currentTimeMillis();
    }

}
