package com.example.jolicitefx;

import Domain.CulturalBuilding;
import Domain.TheaterPiece;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class JoliciteApplication extends Application {

    private static Stage stg;
    public static CulturalBuilding cultural_building;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setTitle("Cultural Programming");
        primaryStage.setScene(new Scene(root, 1400, 820));
        primaryStage.show();
        cultural_building = new CulturalBuilding("Jolicite",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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

    public void programWeek(ArrayList<String[]> events){
        cultural_building.generateWeeklyProgram(events,1);
    }

    public static void main(String[] args) {
        launch();
    }
}