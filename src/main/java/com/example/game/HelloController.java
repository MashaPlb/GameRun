package com.example.game;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class HelloController {
    public static long startTime, endTime, playTime, pauseStartTime, pauseTime;

    @FXML
    private ImageView bg1, bg2, player, enemy, enemy1, enemy2, enemy3, newEnemy, newEnemy1, newEnemy2, newEnemy3, player2, plane;
    @FXML
    private ImageView platform1, platform2, platform3, money, money1, money2, money3, enemyAngry;
    @FXML
    private Label labelPause;
    @FXML
    private Label labelLose;
    @FXML
    private Label labelTime;
    @FXML
    private Label labelRecord;

    public static ParallelTransition parallelTransition;
    public static TranslateTransition enemyTransition;
    public static TranslateTransition enemy1Transition;
    public static TranslateTransition enemy2Transition;
    public static TranslateTransition enemy3Transition;
    public static TranslateTransition newEnemyTransition;
    public static TranslateTransition newEnemy1Transition;
    public static TranslateTransition newEnemy2Transition;
    public static TranslateTransition newEnemy3Transition;
    public static TranslateTransition plat1Transition;
    public static TranslateTransition plat2Transition;
    public static TranslateTransition plat3Transition;
    public static TranslateTransition moneyTransition;
    public static TranslateTransition money1Transition;
    public static TranslateTransition money2Transition;
    public static TranslateTransition money3Transition;
    public static TranslateTransition enemyAngryTransition;

    public static boolean jump = false;
    public static boolean small = false;
    public static boolean big = false;
    public static boolean right = false;
    public static boolean left = false;
    public static boolean right2 = false;
    public static boolean left2 = false;
    public static boolean down2 = false;
    public static boolean up2 = false;
    public static boolean yes = false;
    public static boolean yes2 = false;
    public static boolean isPause = false;
    public boolean magic = false;
    public int moneyCounter = 0;
    public static int playerSpeed = 4;
    public static int player2Speed = 4, playerNSpeed = 3;
    public void fileHandler(String filename) {
        List<String> data;
        try {
            data = Files.readAllLines(new File(filename).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long bestTime = Long.parseLong(data.get(0));
        if (playTime > bestTime) {
            labelRecord.setVisible(true);
            try {
                FileWriter writer = new FileWriter(filename);
                writer.write(String.valueOf(playTime));
                writer.close();
            } catch (IOException e) {
                System.err.println("Произошла ошибка при записи в файл: " + e.getMessage());
            }

        } else {
            labelLose.setVisible(true);
        }
    }
    public void gettingMoney(ImageView myMoney) {
        myMoney.setFitWidth(1);
        myMoney.setFitHeight(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    myMoney.setFitWidth(48);
                    myMoney.setFitHeight(39);
                    yes2 = false;
                })
        );
        timeline.play();
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (jump && player.getFitHeight() == 131 && player.getFitWidth() == 76 && player.getLayoutY() > 440f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if (player.getFitHeight() == 131 && player.getFitWidth() == 76 && player.getLayoutY() <= 546f) {
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerSpeed);
            }
            if (jump && player.getFitHeight() == 57 && player.getFitWidth() == 53 && player.getLayoutY() > 390f)
                player.setLayoutY(player.getLayoutY() - playerNSpeed);
            else if (player.getFitHeight() == 57 && player.getFitWidth() == 53 && player.getLayoutY() <= 479f) {
                jump = false;
                player.setLayoutY(player.getLayoutY() + playerNSpeed);
            }
            if (small && player.getFitHeight() == 131 && player.getFitWidth() == 76) {
                player.setFitHeight(57);
                player.setFitWidth(53);
                player.setLayoutY(480);
                small = false;
            }
            if (big && player.getFitHeight() == 57 && player.getFitWidth() == 53) {
                player.setLayoutY(547);
                player.setFitHeight(131);
                player.setFitWidth(76);
                big = false;
            }
            if (right && player.getLayoutX() < 200f)
                player.setLayoutX(player.getLayoutX() + playerSpeed);
            if (left && player.getLayoutX() > 28f)
                player.setLayoutX(player.getLayoutX() - playerSpeed);

            if (right2 && player2.getLayoutX() < 200f) {
                player2.setLayoutX(player2.getLayoutX() + player2Speed);
                plane.setLayoutX(plane.getLayoutX() + player2Speed);
            }
            if (left2 && player2.getLayoutX() > 28f) {
                player2.setLayoutX(player2.getLayoutX() - player2Speed);
                plane.setLayoutX(plane.getLayoutX() - player2Speed);
            }
            if (down2 && player2.getLayoutY() < 152f) {
                player2.setLayoutY(241);
                plane.setLayoutY(270);
            }
            if (up2 && player2.getLayoutY() > 240f) {
                player2.setLayoutY(151);
                plane.setLayoutY(180);
            }
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
            if (!magic && (player.getBoundsInParent().intersects(enemy.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy1.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy2.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy3.getBoundsInParent()))) {
                EnemyTransition.pause();
                timer.stop();
                endTime = System.currentTimeMillis();
                playTime = endTime - startTime;
                labelTime.setText("Game time: " + playTime + " ms");
                labelTime.setVisible(true);
//                repeatImage.setX(12);
//                repeatImage.setY(9);
                fileHandler("best_time.txt");
            }
            if (plane.getBoundsInParent().intersects(money.getBoundsInParent()) && !yes) {
                yes = true;
                money.setFitWidth(1);
                money.setFitHeight(1);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(3), event -> {
                            money.setFitWidth(48);
                            money.setFitHeight(39);
                            yes = false;
                        })
                );
                timeline.play();
                moneyCounter = moneyCounter + 10;
                System.out.println(moneyCounter);
                if (moneyCounter >=50) {
                    moneyCounter -= 50;
                    magic = true;
                    Timeline timeline2 = new Timeline(
                            new KeyFrame(Duration.seconds(7), event -> {
                                magic = false;
                            })
                    );
                    timeline2.play();
                }
            }
            if (plane.getBoundsInParent().intersects(money1.getBoundsInParent()) && !yes2) {
                yes2 = true;
                gettingMoney(money1);
                gettingMoney(money2);
                gettingMoney(money3);
                moneyCounter = moneyCounter + 30;
                System.out.println(moneyCounter);
                if (moneyCounter >=50) {
                    moneyCounter -= 50;
                    magic = true;
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(7), event -> {
                                magic = false;
                            })
                    );
                    timeline.play();
                }
            }
            if (plane.getBoundsInParent().intersects(enemyAngry.getBoundsInParent())) {
                EnemyTransition.pause();
                timer.stop();
                endTime = System.currentTimeMillis();
                playTime = endTime - startTime;
                labelTime.setText("Game time: " + playTime + " ms");
                labelTime.setVisible(true);
//                repeatImage.setX(12);
//                repeatImage.setY(9);
                fileHandler("best_time.txt");
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
//        repeatImage.setX(1000);
//        repeatImage.setY(1000);
        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        int BG_WIDTH = 1482;
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
        plat1Transition = EnemyTransition.create(6500, -BG_WIDTH - 1750, platform1);
        plat2Transition = EnemyTransition.create(6500, -BG_WIDTH - 1750, platform2);
        plat3Transition = EnemyTransition.create(6500, -BG_WIDTH - 1750, platform3);
        moneyTransition = EnemyTransition.create(6500, -BG_WIDTH - 1750, money);
        money1Transition = EnemyTransition.create(6500, -BG_WIDTH - 1750, money1);
        money2Transition = EnemyTransition.create(6500, -BG_WIDTH - 1750, money2);
        money3Transition = EnemyTransition.create(6500, -BG_WIDTH - 1750, money3);
        enemyAngryTransition = EnemyTransition.create(6500, -BG_WIDTH - 1750, enemyAngry);



        parallelTransition = new ParallelTransition(bgTwoTransition, bgOneTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
        startTime = System.currentTimeMillis();
    }

}