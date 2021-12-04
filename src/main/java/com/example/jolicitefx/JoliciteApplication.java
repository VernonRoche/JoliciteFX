package com.example.jolicitefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JoliciteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JoliciteApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1400, 820);
        stage.setTitle("Programmation Culturelle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}