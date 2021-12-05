package com.example.jolicitefx;

import Domain.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JoliciteDomainTest {

    public static void main(String[] args) {
        Event test_event = new Event(new String[]{"Concert", "White Stripes", "200", "monday", "monday", "15-30", "18-00"});
        Scene test_scene = new Scene(500);
        ArrayList<Scene> test_scenes = new ArrayList<>();
        Schedule test_schedule = new Schedule(0, Day.MONDAY, new Time(new int[]{14,0}, new int[]{18,30}));
        Pair<Integer, Schedule> test_available_schedule = new Pair<>(test_scene.getId(), test_schedule);
        ArrayList<Pair<Integer, Schedule>> test_available_schedules = new ArrayList<>();
        test_scenes.add(test_scene);
        test_available_schedules.add(test_available_schedule);
        CulturalBuilding test_cultural_building = new CulturalBuilding("Jolicite",
                test_scenes, test_available_schedules, new ArrayList<>());
        test_cultural_building.programEvent(test_event);
        System.out.println(test_cultural_building.getName());
    }
}