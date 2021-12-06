package Domain;

import java.util.ArrayList;

public class Scene {
    private static int id=0;
    private final int capacity;
    private ArrayList<Event> events = new ArrayList<>();

    public Scene(int capacity, ArrayList<Event> events){
        this.capacity=capacity;
        this.events.addAll(events);
        id++;
    }

    public Scene(int capacity){
        this.capacity=capacity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }


    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event){
        if (event.getCapacity_needed() <= capacity){
            events.add(event);
        }
    }

    public boolean hasFreeWeekend() {
        return false;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id " + id +
                "capacity=" + capacity +
                ", events=" + events +
                '}';
    }
}
