package com.example.jolicitefx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JoliciteUIController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}