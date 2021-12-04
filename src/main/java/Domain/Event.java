package Domain;

public class Event {
    private final Spectacle spectacle;
    private int capacity_needed;

    public Event(Spectacle spectacle, int capacity_needed){
        this.capacity_needed=capacity_needed;
        this.spectacle=spectacle;
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
