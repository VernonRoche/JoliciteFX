package com.example.jolicitefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Controller for the home page
 */
public class JoliciteHomeController {
    @FXML
    private Button button_pre_form_small;
    @FXML
    private Button button_pre_form;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_scene;

    @FXML
    private TableView<EventTableInformation> home_table;
    @FXML
    private TableColumn<EventTableInformation, String> table_scene;
    @FXML
    private TableColumn<EventTableInformation, String> table_event;
    @FXML
    private TableColumn<EventTableInformation, String> table_week;
    @FXML
    private TableColumn<EventTableInformation, String> table_day;
    @FXML
    private TableColumn<EventTableInformation, String> table_time;
    @FXML
    private TableColumn<EventTableInformation, String> table_capacity;
    @FXML
    private Button button_refresh;

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
     * Exits application
     *
     * @param event
     */
    public void exitApplication(ActionEvent event) {
        javafx.application.Platform.exit();
    }

    /**
     * Fetches information from the aggregate and visualizes it in the UI.
     *
     * @param event
     */
    public void refreshTable(ActionEvent event) {
        JoliciteApplication app = new JoliciteApplication();
        table_scene.setCellValueFactory(new PropertyValueFactory<EventTableInformation, String>("scene"));
        table_event.setCellValueFactory(new PropertyValueFactory<EventTableInformation, String>("event_name"));
        table_week.setCellValueFactory(new PropertyValueFactory<EventTableInformation, String>("week"));
        table_day.setCellValueFactory(new PropertyValueFactory<EventTableInformation, String>("day"));
        table_time.setCellValueFactory(new PropertyValueFactory<EventTableInformation, String>("time"));
        table_capacity.setCellValueFactory(new PropertyValueFactory<EventTableInformation, String>("capacity"));

        System.out.println(app.getCultural_building());
        home_table.setItems(app.fetchReservedSchedules());
    }
}