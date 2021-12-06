package com.example.jolicitefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class JoliciteSceneController {
    @FXML
    private Button button_home;
    @FXML
    private Button button_form;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_add_scene;
    @FXML
    private TextField scene_capacity;

    public void goToHome(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("home.fxml");
    }

    public void goToForm(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("weekly_pre_form.fxml");
    }

    public void addScene(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.addCulturalBuildingScene(Integer.parseInt(scene_capacity.getText()));
        app.changeScene("home.fxml");
    }

    public void exitApplication(ActionEvent event){
        javafx.application.Platform.exit();
    }
}