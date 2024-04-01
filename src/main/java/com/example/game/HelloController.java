package com.example.game;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {
    public static long startTime, endTime, playTime, pauseStartTime, pauseTime;

    @FXML
    private ImageView bg1, bg2, player, enemy, enemy1, enemy2, enemy3, newEnemy, newEnemy1, newEnemy2, newEnemy3;

    @FXML
    private Label labelPause;
    @FXML
    private Label labelLose;
    @FXML
    private Label labelTime;

    public static ParallelTransition parallelTransition;
    public static TranslateTransition enemyTransition;
    public static TranslateTransition enemy1Transition;
    public static TranslateTransition enemy2Transition;
    public static TranslateTransition enemy3Transition;
    public static TranslateTransition newEnemyTransition;
    public static TranslateTransition newEnemy1Transition;
    public static TranslateTransition newEnemy2Transition;
    public static TranslateTransition newEnemy3Transition;

    public static boolean jump = false;
    public static boolean small = false;
    public static boolean big = false;
    public static boolean right = false;
    public static boolean left = false;
    public static boolean isPause = false;
    public static int playerSpeed = 4;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (jump && player.getFitHeight() == 131 && player.getFitWidth() == 76 && player.getLayoutY() > 73f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if (player.getFitHeight() == 131 && player.getFitWidth() == 76 && player.getLayoutY() <= 191f){
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerSpeed);
            }
            if (jump && player.getFitHeight() == 57 && player.getFitWidth() == 53 && player.getLayoutY() > 50f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if (player.getFitHeight() == 57 && player.getFitWidth() == 53 && player.getLayoutY() <= 171f){
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerSpeed);
            }
            if (small && player.getFitHeight() == 131 && player.getFitWidth() == 76) {
                player.setFitHeight(57);
                player.setFitWidth(53);
                player.setLayoutY(170);
                small = false;
            }
            if (big && player.getFitHeight() == 57 && player.getFitWidth() == 53) {
                player.setLayoutY(192);
                player.setFitHeight(131);
                player.setFitWidth(76);
                big = false;
            }
            if (right && player.getLayoutX() < 200f)
                player.setLayoutX(player.getLayoutX() + playerSpeed);
            if (left && player.getLayoutX() > 28f)
                player.setLayoutX(player.getLayoutX() - playerSpeed);
            if (isPause && !labelPause.isVisible()) {
                EnemyTransition.pause();
                labelPause.setVisible(true);
                endTime = System.currentTimeMillis();
                playTime = endTime - startTime - pauseTime;
                labelTime.setText("Game time: " + playTime + " ms");
                labelTime.setVisible(true);
            } else if (!isPause && labelPause.isVisible()) {
                EnemyTransition.play();
                labelPause.setVisible(false);
            }
            if (player.getBoundsInParent().intersects(enemy.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy1.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy2.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy3.getBoundsInParent())) {
                EnemyTransition.pause();
                labelLose.setVisible(true);
                timer.stop();
                endTime = System.currentTimeMillis();
                playTime = endTime - startTime;
                labelTime.setText("Game time: " + playTime + " ms");
                labelTime.setVisible(true);
            }
            if (isPause) {
                if (pauseStartTime == 0)
                    pauseStartTime = System.currentTimeMillis();
            } else {
                if (pauseStartTime != 0) {
                    pauseTime += System.currentTimeMillis() - pauseStartTime;
                    pauseStartTime = 0;
                }
                endTime = System.currentTimeMillis();
                playTime = endTime - startTime - pauseTime;
                labelTime.setText("Game time: " + playTime + " ms");
            }
        }
    };


    @FXML
    void initialize() {
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        int BG_WIDTH = 724;
        bgOneTransition.setToX(BG_WIDTH * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(BG_WIDTH * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);

        enemyTransition = EnemyTransition.create(3500, -BG_WIDTH - 100, enemy);
        newEnemyTransition = EnemyTransition.create(3500, -BG_WIDTH - 100, newEnemy);
        enemy3Transition = EnemyTransition.create(4500, -BG_WIDTH - 350, enemy3);
        newEnemy3Transition = EnemyTransition.create(4500, -BG_WIDTH - 350, newEnemy3);
        enemy1Transition = EnemyTransition.create(6000, -BG_WIDTH - 700, enemy1);
        newEnemy1Transition = EnemyTransition.create(6000, -BG_WIDTH - 700, newEnemy1);
        enemy2Transition = EnemyTransition.create(6000, -BG_WIDTH - 700, enemy2);
        newEnemy2Transition = EnemyTransition.create(6000, -BG_WIDTH - 700, newEnemy2);

        parallelTransition = new ParallelTransition(bgTwoTransition, bgOneTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
        startTime = System.currentTimeMillis();
    }

}
