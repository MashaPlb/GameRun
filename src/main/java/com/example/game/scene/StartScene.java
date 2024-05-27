package com.example.game.scene;

import com.example.game.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartScene {
    @FXML
    private Button startButton;
    @FXML
    void btnClick(ActionEvent event) throws IOException {
        Button btn = (Button)event.getSource();
        if (btn == startButton) {
            HelloApplication.changeScene("hello-view.fxml");
        }
    }
    @FXML
    void initialize() {}
}
