package Domain;

import java.util.Locale;
import java.util.Objects;

public class Event {
    private final Spectacle spectacle;
    private int capacity_needed;

    public Event(Spectacle spectacle, int capacity_needed){
        this.capacity_needed=capacity_needed;
        this.spectacle=spectacle;
    }

    public Event(String[] event_string){
        if (event_string.length!=7){
            throw new IllegalArgumentException("Wrong event string format provided.");
        }
        String name = event_string[1];
        String start_day_string = event_string[3].toUpperCase();
        String end_day_string = event_string[4].toUpperCase();
        String[] split_time_string = event_string[5].split("-");
        String[] split_end_time_string = event_string[6].split("-");


        Day start_day = Day.valueOf(start_day_string);
        Day end_day = Day.valueOf(end_day_string);
        int[] start_time = new int[2];
        int[] end_time = new int[2];
        for(int i=0;i< split_time_string.length;i++){
            start_time[i] = Integer.parseInt(split_time_string[i]);
            end_time[i] = Integer.parseInt(split_end_time_string[i]);
        }
        Spectacle spectacle;
        if (Objects.equals(event_string[0], "Concert")){
            spectacle = new Concert(start_day,name,start_time, end_time);
        }
        else{
            spectacle = new TheaterPiece(new Day[]{start_day, end_day},name,start_time, end_time);
        }
        this.spectacle=spectacle;
        this.capacity_needed=Integer.parseInt(event_string[2]);
    }

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public int getCapacity_needed(){
        return capacity_needed;
    }

    public void setCapacity_needed(int capacity_needed) {
        this.capacity_needed = capacity_needed;
    }

    @Override
    public String toString() {
        return "Event{" +
                "spectacle=" + spectacle +
                ", capacity_needed=" + capacity_needed +
                '}';
    }
}
