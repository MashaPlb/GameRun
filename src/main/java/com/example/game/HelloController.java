package com.example.game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg2, player, enemy, enemy1, enemy2, enemy3;

    @FXML
    private Label labelPause;

    private final int BG_WIDTH = 724;

    private ParallelTransition parallelTransition;
    private TranslateTransition enemyTransition, enemy1Transition, enemy2Transition, enemy3Transition;

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
            if (jump && player.getLayoutY() > 73f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if(player.getLayoutY() <= 181f){
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerSpeed);
            }

            if(small && player.getFitHeight() == 172 && player.getFitWidth() == 87) {
                player.setFitHeight(57);
                player.setFitWidth(53);
                player.setLayoutY(220);
                small = false;
            }
            if (big && player.getFitHeight() == 57 && player.getFitWidth() == 53){
                player.setLayoutY(166);
                player.setFitHeight(172);
                player.setFitWidth(87);
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

        enemy3Transition = new TranslateTransition(Duration.millis(4500), enemy3);
        enemy3Transition.setFromX(0);
        enemy3Transition.setToX(BG_WIDTH * -1 - 350);
        enemy3Transition.setInterpolator(Interpolator.LINEAR);
        enemy3Transition.setCycleCount(Animation.INDEFINITE);
        enemy3Transition.play();

        enemy1Transition = new TranslateTransition(Duration.millis(6000), enemy1);
        enemy1Transition.setFromX(0);
        enemy1Transition.setToX(BG_WIDTH * -1 - 700);
        enemy1Transition.setInterpolator(Interpolator.LINEAR);
        enemy1Transition.setCycleCount(Animation.INDEFINITE);
        enemy1Transition.play();

        enemy2Transition = new TranslateTransition(Duration.millis(6000), enemy2);
        enemy2Transition.setFromX(0);
        enemy2Transition.setToX(BG_WIDTH * -1 - 700);
        enemy2Transition.setInterpolator(Interpolator.LINEAR);
        enemy2Transition.setCycleCount(Animation.INDEFINITE);
        enemy2Transition.play();


        parallelTransition = new ParallelTransition(bgTwoTransition, bgOneTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
    }

}
