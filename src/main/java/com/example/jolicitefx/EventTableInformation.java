package com.example.jolicitefx;

import javafx.beans.property.SimpleStringProperty;

public class EventTableInformation {
    private SimpleStringProperty scene;
    private SimpleStringProperty event_name;
    private SimpleStringProperty week;
    private SimpleStringProperty day;
    private SimpleStringProperty time;
    private SimpleStringProperty capacity;

    public EventTableInformation(String scene, String event_name, String week, String day, String time, String capacity) {
        this.scene = new SimpleStringProperty(scene);
        this.event_name = new SimpleStringProperty(event_name);
        this.week = new SimpleStringProperty(week);
        this.day = new SimpleStringProperty(day);
        this.time = new SimpleStringProperty(time);
        this.capacity = new SimpleStringProperty(capacity);
    }

    public String getScene() {
        return scene.get();
    }

    public SimpleStringProperty sceneProperty() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene.set(scene);
    }

    public String getEvent_name() {
        return event_name.get();
    }

    public SimpleStringProperty event_nameProperty() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name.set(event_name);
    }

    public String getWeek() {
        return week.get();
    }

    public SimpleStringProperty weekProperty() {
        return week;
    }

    public void setWeek(String week) {
        this.week.set(week);
    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getCapacity() {
        return capacity.get();
    }

    public SimpleStringProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }
}
