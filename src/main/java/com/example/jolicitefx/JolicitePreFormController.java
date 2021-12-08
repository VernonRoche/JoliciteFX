package com.example.jolicitefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class JolicitePreFormController {
    @FXML
    private Button button_small_form;
    @FXML
    private Button button_form;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_scene;
    @FXML
    private TextField schedule_week;

    public void goToHome(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("home.fxml");
    }

    public void goToForm(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.addWeek(Integer.parseInt(schedule_week.getText()));
        app.setQueued_week(Integer.parseInt(schedule_week.getText()));
        System.out.println("Queued week is now "+app.getQueued_week());
        app.changeScene("weekly_form.fxml");
    }

    public void goToScene(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("scene.fxml");
    }

    public void exitApplication(ActionEvent event){
        javafx.application.Platform.exit();
    }
}