package com.example.jolicitefx.Domain;

import com.example.jolicitefx.EventTableInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Aggregate and main calculation class for our cultural organization.
 * It aggregates schedules, as well as scenes.
 * @attribute name  the name of the organization
 * @attribute available_schedules   It represents the available schedules over a year. It is a list of pairs, each representing
 *                                  a week as a key, and as a value has a list with all available schedules for it.
 * @attribute reserved_schedules    Similarly to available_schedules, it is a list of pairs, each representing a week
 *                                  as a key, and it's value is a list of pairs, with a schedule as a key and an event
 *                                  as it's value.
 * @attribute scenes                A list of the available scenes in which we can reserve events.
 * @attribute reserved_scene        A special scene in which we do not reserve any event. Can be used for programming
 *                                  last-minute events.
 */

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

    /**
     *
     * @return the name of the organization
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the list of reserved schedules for the year
     */
    public ArrayList<Pair<Integer, ArrayList<Pair<Schedule, Event>>>> getReserved_schedules() {
        return reserved_schedules;
    }

    /**
     * With the parameter passed, parse the information from strings, initialize a new scene, adding it to our scene
     * list, and add it's schedules to our available_schedules list. From starting week passed as a parameter, until
     * the end of the year, we create for each day of the week a schedule corresponding to the opening time and closing
     * time passed as parameters.
     *
     * @param scene Information passed by a user in order to instantiate a new scene. Contains the capacity, opening
     *              week, as well as opening and closing times.
     */
    public void addScene(String[] scene) {
        if (scene.length != 4) {
            throw new IllegalArgumentException("Wrong scene string input.");
        }
        // Parse the string array into usable data
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

    /**
     *
     * @param id The identifier of the scene to remove.
     */

    public void removeScene(int id) {
        scenes.removeIf(x -> x.getId() == id);
    }

    /**
     * Creates a reserved scene, to be used for last-minute reservations.
     * @param scene The scene to use as a special case.
     */

    public void setUpReservedScene(Scene scene) {
        this.reserved_scene = scene;
    }

    /**
     * Adds a pair in our list of reserved schedules, ready to accept new event reservations.
     *
     * @param week_number  The week to instantiate.
     */
    public void addWeek(int week_number) {
        if (week_number < 1 || week_number > 53) {
            throw new IllegalArgumentException("Wrong week number input");
        }
        reserved_schedules.add(new Pair<>(week_number, new ArrayList<>()));
    }

    /**
     * Main method which programs a given event for a specific week. After passing different checks to see if we can
     * reserve the event, we add the asked schedule to our reserved schedules list. Finally, we replace our old
     * available schedule with the remaining time for the day.
     *
     * For example if we open at 14h00 and close at 18h00, and must reserve a concert at 15h00-16h30, our resulting
     * available schedule will be 14h00-15h00 and 16h30-18h00.
     * This can be useful for reserving multiple events in the same day.
     *
     * @param event The event to reserve
     * @param week  The week in which to reserve the event
     */
    public void programEvent(Event event, int week) {
        // Iterate through all weeks
        ListIterator<Pair<Integer, ArrayList<Schedule>>> week_iterator = available_schedules.listIterator();
        while (week_iterator.hasNext()) {
            Pair<Integer, ArrayList<Schedule>> week_schedules = week_iterator.next();
            int week_key = week_schedules.getKey();

            // Go to week asked
            if (week_key == week) {
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
    }

    /**
     * For the given week, call the programEvent method to reserve all events given in string_events. Before calling
     * the method, each string array in the list is parsed into an Event object.
     *
     * @param string_events A list of events to reserve
     * @param week  The week in which we want to reserve the events
     */
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

    /**
     * Iterates through our reserved schedules, creating a list of EventTableInformation objects that can be used
     * by the application layer to visualize data.
     *
     * @return An observable list, that can be used by the application layer to visualize our organization's data.
     */
    public ObservableList<EventTableInformation> getEventTableInformationFromReservedSchedules() {
        ObservableList<EventTableInformation> event_table_information = FXCollections.observableArrayList();
        for (Pair<Integer, ArrayList<Pair<Schedule, Event>>> reserved_schedule : reserved_schedules) {
            int week = reserved_schedule.getKey();
            for (Pair<Schedule, Event> schedule : reserved_schedule.getValue()) {
                String[] string_reserved_schedule = new String[6];
                System.out.println("ARTIST NAMES: "+schedule.getValue().getSpectacle().getName());
                String scene_id = String.valueOf(schedule.getKey().getScene_id());
                String name = String.valueOf(schedule.getValue().getSpectacle().getName());
                String string_week = String.valueOf(week);
                String day = schedule.getKey().getDay().name();
                String time = String.valueOf(schedule.getKey().getTime().toString());
                String capacity = String.valueOf(schedule.getValue().getCapacity_needed());
                event_table_information.add(new EventTableInformation(scene_id,name,string_week,day,time,capacity));
            }

        }
        return event_table_information;
    }

    /**
     * Method that populates our organization with initial data.
     */
    public void loadInitialTestData() {
        TestData.loadTestData(this);
    }
}
