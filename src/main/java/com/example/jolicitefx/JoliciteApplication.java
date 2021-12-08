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

public class JoliciteApplication extends Application {

    private static Stage stg;
    public static CulturalBuilding cultural_building;
    private static int queued_week;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        queued_week=0;
        primaryStage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Cultural Programming");
        primaryStage.setScene(new Scene(root, 1400, 820));
        primaryStage.show();
        cultural_building = new CulturalBuilding("Jolicite",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        System.out.println(cultural_building);
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public void addCulturalBuildingScene(String[] scene_information){
        cultural_building.addScene(scene_information);
    }

    public void addWeek(int week){
        cultural_building.addWeek(week);
    }

    public void programWeek(ArrayList<String[]> events, int week){
        cultural_building.generateWeeklyProgram(events,week);
    }

    public int getQueued_week() {
        return queued_week;
    }

    public void setQueued_week(int queued_week) {
        JoliciteApplication.queued_week = queued_week;
    }

    public ObservableList<EventTableInformation> fetchReservedSchedules() {
        return cultural_building.getEventTableInformationFromReservedSchedules();
    }

    public CulturalBuilding getCultural_building() {
        return cultural_building;
    }

    public static void main(String[] args) {
        launch();
    }


}