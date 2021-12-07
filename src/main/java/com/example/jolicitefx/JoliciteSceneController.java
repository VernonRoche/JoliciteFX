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
    @FXML
    private TextField scene_start_time;
    @FXML
    private TextField scene_end_time;
    @FXML
    private TextField scene_opening_week;

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
        String[] scene_information = new String[4];
        scene_information[0] = scene_capacity.getText();
        scene_information[1] = scene_start_time.getText();
        scene_information[2] = scene_end_time.getText();
        scene_information[3] = scene_opening_week.getText();
        app.addCulturalBuildingScene(scene_information);
        app.changeScene("home.fxml");
    }

    public void exitApplication(ActionEvent event){
        javafx.application.Platform.exit();
    }
}