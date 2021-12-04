package Domain;

import java.util.Objects;

public class Event {
    private final Spectacle spectacle;
    private int capacity_needed;

    public Event(Spectacle spectacle, int capacity_needed){
        this.capacity_needed=capacity_needed;
        this.spectacle=spectacle;
    }

    public Event(String[] event_string){
        if (event_string.length!=6){
            throw new IllegalArgumentException("Wrong event string format provided.");
        }
        String name = event_string[1];
        String[] split_date_string = event_string[3].split("/");
        String[] split_end_date_string = event_string[4].split("/");
        String[] split_schedule_string = event_string[5].split("-");
        int[] date = new int[3];
        int[] end_date = new int[3];
        int[] schedule = new int[2];
        for(int i=0;i<split_date_string.length;i++){
            date[i] = Integer.parseInt(split_date_string[i]);
            end_date[i] = Integer.parseInt(split_date_string[i]);
        }
        for(int i=0;i< split_schedule_string.length;i++){
            schedule[i] = Integer.parseInt(split_schedule_string[i]);
        }
        Spectacle spectacle;
        if (Objects.equals(event_string[0], "Concert")){
            spectacle = new Concert(date,name,schedule);
        }
        else{
            spectacle = new TheaterPiece(new int[][]{date, end_date},name,schedule);
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
}
