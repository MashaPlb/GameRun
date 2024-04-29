package com.example.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Themes {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button theme1;

    @FXML
    private Button theme2;

    @FXML
    private Button theme3;

    @FXML
    private Button theme4;
    @FXML
    void btnClick(ActionEvent event) throws IOException {
        Button btn = (Button)event.getSource();
        if (btn == theme1) {
            HelloApplication.changeScene("hello-view.fxml");
        }
    }

    @FXML
    void initialize() {
    }

}
