package com.example.jolicitefx;

import Domain.CulturalBuilding;
import Domain.EventTableInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JoliciteHomeController{
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

    public void goToForm(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("weekly_pre_form.fxml");
    }

    public void goToScene(ActionEvent event) throws IOException {
        JoliciteApplication app = new JoliciteApplication();
        app.changeScene("scene.fxml");
    }

    public void exitApplication(ActionEvent event){
        javafx.application.Platform.exit();
    }

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