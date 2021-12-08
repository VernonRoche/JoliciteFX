package com.example.jolicitefx.Domain;

import com.example.jolicitefx.EventTableInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.ListIterator;

public class CulturalBuilding { //ArrayList<Pair<Integer,>>
    private final String name;
    private ArrayList<Pair<Integer, ArrayList<Schedule>>> available_schedules = new ArrayList<>();
    private ArrayList<Pair<Integer, ArrayList<Pair<Schedule, Event>>>> reserved_schedules = new ArrayList<>();
    private ArrayList<Scene> scenes = new ArrayList<>();
    private Scene reserved_scene;

    public CulturalBuilding(String name, ArrayList<Scene> scenes, ArrayList<Pair<Integer, ArrayList<Schedule>>> available_schedules, ArrayList<Pair<Integer, ArrayList<Pair<Schedule, Event>>>> reserved_schedules) {
        this.name = name;
        this.scenes.addAll(scenes);
        this.reserved_schedules.addAll(reserved_schedules);
        this.available_schedules.addAll(available_schedules);
    }

    public String getName() {
        return name;
    }

    public void addScene(String[] scene) {
        if (scene.length != 4) {
            throw new IllegalArgumentException("Wrong scene string input.");
        }
        int capacity = Integer.parseInt(scene[0]);
        String[] start_time_string = scene[1].split("-");
        String[] end_time_string = scene[2].split("-");
        int start_week = Integer.parseInt(scene[3]);

        int[] start_time = new int[2];
        int[] end_time = new int[2];
        for (int i = 0; i < start_time_string.length; i++) {
            start_time[i] = Integer.parseInt(start_time_string[i]);
            end_time[i] = Integer.parseInt(end_time_string[i]);
        }

        Scene new_scene = new Scene(capacity);
        this.scenes.add(new_scene);
        int new_scene_id = new_scene.getId();

        // Iterate through all weeks starting from start_week up to final week of year (53rd week)
        for (int week = start_week; week < 54; week++) {

            // For each week, add a schedule for each day with start_time and end_time
            Day[] possible_days = Day.values();
            ArrayList<Schedule> available_week_schedules = new ArrayList<>();
            for (Day possible_day : possible_days) {
                Schedule new_schedule = new Schedule(new_scene_id, possible_day, new Time(start_time, end_time));
                available_week_schedules.add(new_schedule);
            }

            available_schedules.add(new Pair<>(week, available_week_schedules));
        }
    }

    public void removeScene(int id) {
        scenes.removeIf(x -> x.getId() == id);
    }

    public void setUpReservedScene(Scene scene) {
        this.reserved_scene = scene;
    }

    public void addWeek(int week_number) {
        if (week_number < 1 || week_number > 53) {
            throw new IllegalArgumentException("Wrong week number input");
        }
        reserved_schedules.add(new Pair<>(week_number, new ArrayList<>()));
    }

    public void programEvent(Event event, int week) {
        // Iterate through all weeks
        System.out.println("Event's week" + week + "\n");
        ListIterator<Pair<Integer, ArrayList<Schedule>>> week_iterator = available_schedules.listIterator();
        while (week_iterator.hasNext()) {
            Pair<Integer, ArrayList<Schedule>> week_schedules = week_iterator.next();
            int week_key = week_schedules.getKey();

            // Go to week asked
            if (week_key == week) {
                System.out.println("Week is good\n");
                ArrayList<Schedule> week_available_schedules = week_schedules.getValue();
                ListIterator<Schedule> available_schedule_iterator = week_available_schedules.listIterator();

                // Iterate through the week's available schedules
                while (available_schedule_iterator.hasNext()) {
                    Schedule available_schedule = available_schedule_iterator.next();
                    int scene_id = available_schedule.getScene_id();

                    // Check if we there is an available slot for the event
                    if (available_schedule.getDay() == event.getSpectacle().getDay()[0]) {
                        System.out.println("Day is good for " + event.getSpectacle().getName() + "\n");
                        if (available_schedule.getTime().isTimeWithinBoundaries(event.getSpectacle().getTime())) {
                            System.out.println("Time is good for " + event.getSpectacle().getName() + "\n");
                            // Check if scene capacity is enough for our event
                            for (Scene scene : scenes) {
                                if (scene_id == scene.getId()) {
                                    if (scene.getCapacity() >= event.getCapacity_needed()) {
                                        System.out.println("Capacity is good for " + event.getSpectacle().getName() + "\n");

                                        // It's good, we can add the event to our reserved schedules
                                        ListIterator<Pair<Integer, ArrayList<Pair<Schedule, Event>>>> reserved_week_schedule_iterator = reserved_schedules.listIterator();
                                        while (reserved_week_schedule_iterator.hasNext()) {
                                            Pair<Integer, ArrayList<Pair<Schedule, Event>>> reserved_week_schedule = reserved_week_schedule_iterator.next();

                                            if (reserved_week_schedule.getKey() == week) {
                                                reserved_week_schedule_iterator.remove();
                                                ArrayList<Pair<Schedule, Event>> new_reserved_week_schedule = reserved_week_schedule.getValue();
                                                new_reserved_week_schedule.add(new Pair<>(available_schedule, event));
                                                reserved_week_schedule_iterator.add(new Pair<>(week, new_reserved_week_schedule));
                                                break;
                                            }
                                        }

                                        available_schedule_iterator.remove();

                                        // After reserving the schedule, add the remaining free time to available schedules
                                        if (!available_schedule.getTime().isStartTimeEqual(event.getSpectacle().getTime())) {
                                            int[] start_time = available_schedule.getTime().getStart_time();
                                            int[] end_time = event.getSpectacle().getTime().getStart_time();
                                            Schedule schedule = new Schedule(scene_id, available_schedule.getDay(), new Time(start_time, end_time));
                                            available_schedule_iterator.add(schedule);
                                        }
                                        if (!available_schedule.getTime().isEndTimeEqual(event.getSpectacle().getTime())) {
                                            int[] start_time = event.getSpectacle().getTime().getEnd_time();
                                            int[] end_time = available_schedule.getTime().getEnd_time();
                                            Schedule schedule = new Schedule(scene_id, available_schedule.getDay(), new Time(start_time, end_time));
                                            available_schedule_iterator.add(schedule);
                                        }
                                        week_iterator.remove();
                                        week_iterator.add(new Pair<>(week, week_available_schedules));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("APRES:" + reserved_schedules + "\n");
        System.out.println("AVAILABLE APRES: " + available_schedules);
    }

    public void generateWeeklyProgram(ArrayList<String[]> string_events, int week) {
        for (String[] string_event : string_events) {
            Event new_event = new Event(string_event);
            programEvent(new_event, week);
        }
    }

    @Override
    public String toString() {
        return "CulturalBuilding{" +
                "name='" + name + '\'' +
                ", available_schedules=" + available_schedules +
                ", reserved_schedules=" + reserved_schedules +
                ", scenes=" + scenes +
                ", reserved_scene=" + reserved_scene +
                '}';
    }

    public ObservableList<EventTableInformation> getEventTableInformationFromReservedSchedules() {
        ArrayList<String[]> string_reserved_schedules = new ArrayList<>();
        for (Pair<Integer, ArrayList<Pair<Schedule, Event>>> reserved_schedule : reserved_schedules) {
            int week = reserved_schedule.getKey();
            String[] string_reserved_schedule = new String[6];
            for (Pair<Schedule, Event> schedule : reserved_schedule.getValue()) {
                string_reserved_schedule[0] = String.valueOf(schedule.getKey().getScene_id());
                string_reserved_schedule[1] = String.valueOf(schedule.getValue().getSpectacle().getName());
                string_reserved_schedule[2] = String.valueOf(week);
                string_reserved_schedule[3] = schedule.getKey().getDay().name();
                string_reserved_schedule[4] = String.valueOf(schedule.getKey().getTime().toString());
                string_reserved_schedule[5] = String.valueOf(schedule.getValue().getCapacity_needed());
            }
            string_reserved_schedules.add(string_reserved_schedule);
        }

        ObservableList<EventTableInformation> event_table_information = FXCollections.observableArrayList();
        for (String[] string_info : string_reserved_schedules) {
            event_table_information.add(new EventTableInformation(string_info[0], string_info[1], string_info[2], string_info[3], string_info[4],
                    string_info[5]));
        }
        return event_table_information;
    }

    public void loadInitialTestData() {
        TestData.loadTestData(this);
    }
}
