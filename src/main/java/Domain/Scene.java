package Domain;

import java.util.ArrayList;

public class Scene {
    private static int id=0;
    private final int capacity;
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<DateInterval> dates = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();

    public Scene(int capacity, ArrayList<Schedule> schedules, ArrayList<DateInterval> dates, ArrayList<Event> events){
        this.capacity=capacity;
        this.schedules.addAll(schedules);
        this.dates.addAll(dates);
        this.events.addAll(events);
        id++;
    }

    public Scene(int capacity, ArrayList<Schedule> schedules, ArrayList<DateInterval> dates){
        this.capacity=capacity;
        this.schedules.addAll(schedules);
        this.dates.addAll(dates);
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public ArrayList<DateInterval> getDates() {
        return dates;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
