package com.example.game;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
