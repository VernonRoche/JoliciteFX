package com.example.jolicitefx;

import com.example.jolicitefx.Domain.CulturalBuilding;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main application, and center of the project.
 */
public class JoliciteApplication extends Application {

    public static CulturalBuilding cultural_building;
    private static Stage stg;
    private static int queued_week;

    public static void main(String[] args) {
        launch();
    }

    /**
     * Called when the program is launched. Instantiates a static aggregate, which will be used by the other application
     * files.
     *
     * @param primaryStage The window
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        queued_week = 0;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Cultural Programming");
        primaryStage.setScene(new Scene(root, 1400, 820));
        primaryStage.show();
        cultural_building = new CulturalBuilding("Jolicite",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        System.out.println(cultural_building);
        cultural_building.loadInitialTestData();
    }

    /**
     * Changes the window and shows either the home page, the weekly program form or the scene creator form.
     *
     * @param fxml The file/window to be loaded
     * @throws IOException
     */
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    /**
     * Calls the addScene method of our aggregate to add a scene
     *
     * @param scene_information The scene and it's information to instantiate
     */
    public void addCulturalBuildingScene(String[] scene_information) {
        cultural_building.addScene(scene_information);
    }

    /**
     * Calls the addWeek method of our aggregate to prepare a week for scheduling.
     *
     * @param week The week to prepare.
     */
    public void addWeek(int week) {
        cultural_building.addWeek(week);
    }

    /**
     * Calls the generateWeeklyProgram method of our aggregate to reserve events for a given week
     *
     * @param events The events passed by the user
     * @param week The week asked
     */
    public void programWeek(ArrayList<String[]> events, int week) {
        cultural_building.generateWeeklyProgram(events, week);
    }

    /**
     * Used to pass the week information from our pre-form to our form page.
     * Without this information cannot be correcty passed from one controller to the other.
     *
     * @return The week which is currently getting it's form prepared
     */
    public int getQueued_week() {
        return queued_week;
    }

    /**
     * Sets the week that is currently being prepared in the form
     *
     * @param queued_week A week
     */
    public void setQueued_week(int queued_week) {
        JoliciteApplication.queued_week = queued_week;
    }

    /**
     * Gets the reserved schedules list from the aggregate and converts it into usable data for our application.
     * Updates the information shown in the UI.
     *
     * @return A list of UI usable data
     */
    public ObservableList<EventTableInformation> fetchReservedSchedules() {
        return cultural_building.getEventTableInformationFromReservedSchedules();
    }

    /**
     * Gets the aggregate
     *
     * @return A CulturalBuilding object
     */
    public CulturalBuilding getCultural_building() {
        return cultural_building;
    }


}