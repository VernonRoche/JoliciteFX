package com.example.jolicitefx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class JoliciteFormController {
    @FXML
    private Button button_submit;
    @FXML
    private Button button_next;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_information;
    @FXML
    private Button button_home;
    @FXML
    private TextField spectacle_name;
    @FXML
    private TextField spectacle_capacity;
    @FXML
    private TextField spectacle_date;
    @FXML
    private TextField spectacle_end_date;
    @FXML
    private TextField spectacle_schedule;
    @FXML
    private ComboBox<String> spectacle_type;

    private ArrayList<String[]> events_to_program = new ArrayList<>();

    public void goToHome(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("home.fxml");
    }

    public void goToInformation(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("information.fxml");
    }

    public void exitApplication(ActionEvent event){
        javafx.application.Platform.exit();
    }

    public void nextEvent(ActionEvent event){
        String[] event_to_program = new String[6];
        event_to_program[0] = spectacle_type.getValue().toString();
        event_to_program[1] = spectacle_name.getText().toString();
        event_to_program[2] = spectacle_capacity.getText().toString();
        event_to_program[3] = spectacle_date.getText().toString();
        event_to_program[4] = spectacle_end_date.getText().toString();
        event_to_program[5] = spectacle_schedule.getText().toString();
        events_to_program.add(event_to_program);
        spectacle_name.setText("");
        spectacle_capacity.setText("");
        spectacle_date.setText("");
        spectacle_end_date.setText("");
        spectacle_schedule.setText("");
    }

    public void submitProgram(ActionEvent event){
        JoliciteApplication app = new JoliciteApplication();
        app.programWeek(events_to_program);
    }
}
