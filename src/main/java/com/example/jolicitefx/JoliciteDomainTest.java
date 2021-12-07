package com.example.jolicitefx;

import Domain.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JoliciteDomainTest {

    public static void main(String[] args) {
        Event test_event = new Event(new String[]{"Concert", "White Stripes", "200", "monday", "monday", "15-30", "18-00"});
        System.out.println("test_event = " + test_event.toString());
        Scene test_scene = new Scene(500);
        System.out.println("test_scene = " + test_scene.toString());
        ArrayList<Scene> test_scenes = new ArrayList<>();
        Schedule test_schedule = new Schedule(0, Day.MONDAY, new Time(new int[]{14,0}, new int[]{18,30}));
        System.out.println("test schedule = " + test_schedule);
        ArrayList<Schedule> test_available_schedules = new ArrayList<>();
        test_scenes.add(test_scene);
        System.out.println("test_scenes = " + test_scenes);
        test_available_schedules.add(test_schedule);
        System.out.println("test_available_schedules = " + test_available_schedules);
        /*CulturalBuilding test_cultural_building = new CulturalBuilding("Jolicite",
                test_scenes, test_available_schedules, new ArrayList<>());
        System.out.println("test_cultural_building = " + test_cultural_building);
        test_cultural_building.programEvent(test_event);
        System.out.println("Après test_cultural_building = " + test_cultural_building);
        System.out.println(test_cultural_building.getName());*/
    }
}