package com.example.jolicitefx;

import Domain.CulturalBuilding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class JoliciteHomeController {
    @FXML
    private Button button_pre_form_small;
    @FXML
    private Button button_pre_form;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_information;

    public void goToForm(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("weekly_pre_form.fxml");
    }

    public void goToInformation(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("information.fxml");
    }

    public void exitApplication(ActionEvent event){
        javafx.application.Platform.exit();
    }
}