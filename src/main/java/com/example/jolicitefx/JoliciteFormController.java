package com.example.jolicitefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller which holds information about our weekly program form.
 */
public class JoliciteFormController {
    @FXML
    private Button button_submit;
    @FXML
    private Button button_next;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_scene;
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
    private TextField spectacle_start_time;
    @FXML
    private ComboBox<String> spectacle_type;
    @FXML
    private TextField spectacle_end_time;
    @FXML
    private Button button_pre_form;

    private ArrayList<String[]> events_to_program = new ArrayList<>();

    /**
     * Switches windows to home page
     *
     * @param event
     * @throws IOException
     */
    public void goToHome(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("home.fxml");
    }

    /**
     * Switches windows to pre-form
     *
     * @param event
     * @throws IOException
     */
    public void goToForm(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("weekly_pre_form.fxml");
    }

    /**
     * Switches windows to scene creator
     *
     * @param event
     * @throws IOException
     */
    public void goToScene(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("scene.fxml");
    }

    /**
     * Exits the application
     *
     * @param event
     */
    public void exitApplication(ActionEvent event) {
        javafx.application.Platform.exit();
    }

    /**
     * Adds an event written in the form into a list, which can be later passed on when submitting the weekly program.
     *
     * @param event
     */
    public void nextEvent(ActionEvent event) {
        String[] event_to_program = new String[7];
        event_to_program[0] = spectacle_type.getValue();
        event_to_program[1] = spectacle_name.getText();
        event_to_program[2] = spectacle_capacity.getText();
        event_to_program[3] = spectacle_date.getText();
        event_to_program[4] = spectacle_end_date.getText();
        event_to_program[5] = spectacle_start_time.getText();
        event_to_program[6] = spectacle_end_time.getText();
        events_to_program.add(event_to_program);
        spectacle_name.setText("");
        spectacle_capacity.setText("");
        spectacle_date.setText("");
        spectacle_end_date.setText("");
        spectacle_start_time.setText("");
        spectacle_end_time.setText("");
    }

    /**
     * Submits all events written in the form, using the nextEvent method and programs them. Sends back to homepage.
     *
     * @param event
     * @throws IOException
     */
    public void submitProgram(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        System.out.println("Submitting Program with week " + app.getQueued_week());
        app.programWeek(events_to_program, app.getQueued_week());
        app.setQueued_week(0);
        goToHome(event);
    }
}
