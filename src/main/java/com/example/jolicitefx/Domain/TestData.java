package com.example.jolicitefx.Domain;

import com.example.jolicitefx.Domain.*;

import java.util.ArrayList;
import com.example.jolicitefx.Repository.Repository;

/**
 * Class which instantiates initial test data, simulating user input.
 */
public class TestData {

    public static void loadTestData(CulturalBuilding culturalBuilding) {
        // Initialize some scenes
        String[] test_scene_1 = new String[]{"1000", "12-00", "19-00", "5"};
        String[] test_scene_2 = new String[]{"300", "14-00", "18-00", "2"};

        // Initialize some events
        String[] test_event_1 = new String[]{"Concert", "White Stripes", "200", "monday", "monday", "15-30", "17-00"};
        String[] test_event_2 = new String[]{"Concert", "Babar", "200", "wednesday", "wednesday", "16-30", "18-30"};
        String[] test_event_3 = new String[]{"Concert", "Benabar", "350", "saturday", "saturday", "14-30", "15-00"};
        String[] test_event_4 = new String[]{"Concert", "AC/DC", "740", "sunday", "sunday", "15-45", "18-00"};
        String[] test_event_5 = new String[]{"Theater Piece", "Romeo et Juliette", "200", "friday", "sunday", "16-25", "18-45"};
        String[] test_event_6 = new String[]{"Concert", "Noir Desir", "420", "saturday", "saturday", "17-00", "18-00"};
        String[] test_event_7 = new String[]{"Concert", "Angele", "800", "monday", "monday", "12-15", "14-30"};
        String[] test_event_8 = new String[]{"Concert", "Woodkid", "200", "saturday", "saturday", "14-30", "17-00"};
        String[] test_event_9 = new String[]{"Concert", "Aerosmith", "500", "wednesday", "wednesday", "16-00", "18-30"};
        String[] test_event_10 = new String[]{"Concert", "Berywam", "300", "sunday", "sunday", "13-00", "18-00"};

        // Prepare data
        culturalBuilding.addScene(test_scene_1);
        culturalBuilding.addScene(test_scene_2);

        ArrayList<String[]> first_test_week = new ArrayList<>();
        first_test_week.add(test_event_1);
        first_test_week.add(test_event_3);
        first_test_week.add(test_event_5);

        ArrayList<String[]> second_test_week = new ArrayList<>();
        second_test_week.add(test_event_4);
        second_test_week.add(test_event_2);
        second_test_week.add(test_event_7);

        ArrayList<String[]> third_test_week = new ArrayList<>();
        third_test_week.add(test_event_6);
        third_test_week.add(test_event_8);
        third_test_week.add(test_event_9);
        third_test_week.add(test_event_10);

        // Initialize the weeks in which we will reserve our events
        culturalBuilding.addWeek(2);
        culturalBuilding.addWeek(5);
        culturalBuilding.addWeek(6);

        // Generate weekly programs with our test data
        culturalBuilding.generateWeeklyProgram(first_test_week, 2);
        culturalBuilding.generateWeeklyProgram(second_test_week, 5);
        culturalBuilding.generateWeeklyProgram(third_test_week, 6);

        // Save our information in a file
        Repository repository = new Repository();
        repository.saveInFile(culturalBuilding);
    }
}