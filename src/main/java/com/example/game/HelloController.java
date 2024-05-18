package com.example.game;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class HelloController {
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
    @FXML
    private Label labelCount;
    @FXML
    private Label labelBonus;
    private static ParallelTransition parallelTransition;
    public static ParallelTransition getting_parallelTransition() {
        return parallelTransition;
    }
    private static TranslateTransition enemyTransition;
    public static TranslateTransition getting_enemyTransition() {
        return enemyTransition;
    }
    private static TranslateTransition enemy1Transition;
    public static TranslateTransition getting_enemy1Transition() {
        return enemy1Transition;
    }
    private static TranslateTransition enemy2Transition;
    public static TranslateTransition getting_enemy2Transition() {
        return enemy2Transition;
    }
    private static TranslateTransition enemy3Transition;
    public static TranslateTransition getting_enemy3Transition() {
        return enemy3Transition;
    }
    private static TranslateTransition newEnemyTransition;
    public static TranslateTransition getting_newEnemyTransition() {
        return newEnemyTransition;
    }
    private static TranslateTransition newEnemy1Transition;
    public static TranslateTransition getting_newEnemy1Transition() {
        return newEnemy1Transition;
    }
    private static TranslateTransition newEnemy2Transition;
    public static TranslateTransition getting_newEnemy2Transition() {
        return newEnemy2Transition;
    }
    private static TranslateTransition newEnemy3Transition;
    public static TranslateTransition getting_newEnemy3Transition() {
        return newEnemy3Transition;
    }
    private static TranslateTransition plat1Transition;
    public static TranslateTransition getting_plat1Transition() {
        return plat1Transition;
    }
    private static TranslateTransition plat2Transition;
    public static TranslateTransition getting_plat2Transition() {
        return plat2Transition;
    }
    private static TranslateTransition plat3Transition;
    public static TranslateTransition getting_plat3Transition() {
        return plat3Transition;
    }
    private static TranslateTransition moneyTransition;
    public static TranslateTransition getting_moneyTransition() {
        return moneyTransition;
    }
    private static TranslateTransition money1Transition;
    public static TranslateTransition getting_money1Transition() {
        return money1Transition;
    }
    private static TranslateTransition money2Transition;
    public static TranslateTransition getting_money2Transition() {
        return money2Transition;
    }
    private static TranslateTransition money3Transition;
    public static TranslateTransition getting_money3Transition() {
        return money3Transition;
    }
    private static TranslateTransition enemyAngryTransition;
    public static TranslateTransition getting_enemyAngryTransition() {
        return enemyAngryTransition;
    }
    private static long startTime, endTime, playTime, pauseStartTime, pauseTime;
    private static boolean reverse = false;
    public static boolean getting_reverse() {
        return reverse;
    }
    public static void setting_reverse(boolean new_reverse) {
        reverse = new_reverse;
    }
    private static boolean jump = false;
    public static boolean getting_jump() {
        return jump;
    }
    public static void setting_jump(boolean new_jump) {
        jump = new_jump;
    }
    private static boolean small = false;
    public static void setting_small(boolean new_small) {
        small = new_small;
    }
    private static boolean big = false;
    public static void setting_big(boolean new_big) {
        big = new_big;
    }
    private static boolean right = false;
    public static void setting_right(boolean new_right) {
        right = new_right;
    }
    private static boolean left = false;
    public static void setting_left(boolean new_left) {
        left = new_left;
    }
    private static boolean right2 = false;
    public static void setting_right2(boolean new_right2) {
        right2 = new_right2;
    }
    private static boolean left2 = false;
    public static void setting_left2(boolean new_left2) {
        left2 = new_left2;
    }
    private static boolean down = false;
    public static void setting_down(boolean new_down) {
        down = new_down;
    }
    private static boolean up = false;
    public static void setting_up(boolean new_up) {
        up = new_up;
    }
    public static boolean isMoneyCollected = false;
    public static void setting_isMoneyCollected(boolean money) {
        isMoneyCollected = money;
    }
    private static boolean isBigMoneyCollected = false;
    public static void setting_isBigMoneyCollected(boolean money) {
        isBigMoneyCollected = money;
    }
    private static boolean isPause = false;
    public static boolean isPause() {
        return !isPause;
    }
    public static void setting_isPause(boolean pause) {
        isPause = pause;
    }
    private boolean isInvulnerable = false;
    private int moneyCounter = 0;
    private static int playerSpeed = 4, player2Speed = 4, playerNSpeed = 3;
    public static void setting_playerSpeed(int number) {
        playerSpeed = number;
    }
    private final int BG_WIDTH = 1482;

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
    public void changeParameters(int height, int width, int layoutY) {
        player.setFitHeight(height);
        player.setFitWidth(width);
        player.setLayoutY(layoutY);
    }
    public void settingPause() {
        EnemyTransition.pause();
        labelPause.setVisible(true);
        endTime = System.currentTimeMillis();
        playTime = endTime - startTime - pauseTime;
        labelTime.setText("Game time: " + playTime + " ms");
        labelTime.setVisible(true);
    }
    public void actionAfterPause() {
        if (pauseStartTime != 0) {
            pauseTime += System.currentTimeMillis() - pauseStartTime;
            pauseStartTime = 0;
        }
        endTime = System.currentTimeMillis();
        playTime = endTime - startTime - pauseTime;
        labelTime.setText("Game time: " + playTime + " ms");
    }
    public void moneyHandler() {
        isMoneyCollected = true;
        Money.gettingMoney(money);
        MusicHandler.playMusic("/music/money.mp3");

        moneyCounter = moneyCounter + 10;
        labelCount.setText("\uD83D\uDCB0" + moneyCounter);
        labelCount.setVisible(true);
        if (moneyCounter >=50) {
            gettingBonus();
        }
        labelCount.setText("\uD83D\uDCB0" + moneyCounter);
        labelCount.setVisible(true);
    }
    public void bigMoneyHandler() {
        isBigMoneyCollected = true;
        Money.gettingBigMoney(money1);
        Money.gettingBigMoney(money2);
        Money.gettingBigMoney(money3);
        MusicHandler.playMusic("/music/money.mp3");

        moneyCounter = moneyCounter + 30;
        labelCount.setText("\uD83D\uDCB0" + moneyCounter);
        labelCount.setVisible(true);
        if (moneyCounter >= 50) {
            gettingBonus();
        }
        labelCount.setText("\uD83D\uDCB0" + moneyCounter);
        labelCount.setVisible(true);
    }
    public void gettingBonus() {
        moneyCounter = moneyCounter - 50;
        labelBonus.setText("INVULNERABLE FOR 7s");
        showBonusAnimation();
        isInvulnerable = true;
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(7), event -> isInvulnerable = false)
        );
        timeline2.play();
        Timeline endTimeline = new Timeline(
                new KeyFrame(Duration.seconds(6), event -> {
                    labelBonus.setText("INVULNERABLE END");
                    showBonusAnimation();
                })
        );
        endTimeline.play();
    }
    public void showBonusAnimation() {
        labelBonus.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> labelBonus.setVisible(false)));
        timeline.play();
    }
    public void createPauseBeforeRestart() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> {
            Stage stage = (Stage) player.getScene().getWindow();
            stage.close();
            resetGame();
        });
        pause.play();
    }
    public void loseGame() {
        EnemyTransition.pause();
        timer.stop();
        endTime = System.currentTimeMillis();
        playTime = endTime - startTime;
        labelTime.setText("Game time: " + playTime + " ms");
        labelTime.setVisible(true);
        fileHandler("best_time.txt");
        createPauseBeforeRestart();
    }
    public void resetGame() {
        KeyboardHandler.isSceneChanged = false;
        HelloApplication.timeline.stop();
        jump = false;
        small = false;
        big = false;
        right = false;
        left = false;
        right2 = false;
        left2 = false;
        down = false;
        up = false;
        isMoneyCollected = false;
        isBigMoneyCollected = false;
        isPause = false;
        isInvulnerable = false;
        reverse = false;
        moneyCounter = 0;
        playerSpeed = 0;
        player2Speed = 0;
        playerNSpeed = 0;
        pauseTime = 0;
        labelBonus.setText("0");
        labelCount.setText("0");
        EnemyTransition.play();
        startTime = System.currentTimeMillis();
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
                changeParameters(57, 53, 480);
                small = false;
            }
            if (big && player.getFitHeight() == 57 && player.getFitWidth() == 53) {
                changeParameters(131, 76, 547);
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
            if (down && player2.getLayoutY() < 152f) {
                player2.setLayoutY(241);
                plane.setLayoutY(270);
            }
            if (up && player2.getLayoutY() > 240f) {
                player2.setLayoutY(151);
                plane.setLayoutY(180);
            }
            if (isPause && !labelPause.isVisible())
                settingPause();
            else if (!isPause && labelPause.isVisible()) {
                EnemyTransition.play();
                labelPause.setVisible(false);
            }
            if (!isInvulnerable && (player.getBoundsInParent().intersects(enemy.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy1.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy2.getBoundsInParent()) ||
                    player.getBoundsInParent().intersects(enemy3.getBoundsInParent())))
                loseGame();
            if (plane.getBoundsInParent().intersects(money.getBoundsInParent()) && !isMoneyCollected && !isPause)
                moneyHandler();
            if (plane.getBoundsInParent().intersects(money1.getBoundsInParent()) && !isBigMoneyCollected && !isPause)
                bigMoneyHandler();
            if (plane.getBoundsInParent().intersects(enemyAngry.getBoundsInParent()))
                loseGame();
            if (isPause) {
                if (pauseStartTime == 0)
                    pauseStartTime = System.currentTimeMillis();
            } else actionAfterPause();
        }
    };
    void createAllEnemiesTransitions() {
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
    }
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

        createAllEnemiesTransitions();

        parallelTransition = new ParallelTransition(bgTwoTransition, bgOneTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();
        startTime = System.currentTimeMillis();
    }
}