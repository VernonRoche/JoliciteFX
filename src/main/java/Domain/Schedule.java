package Domain;

public class Schedule {
    private final int scene_id;
    private final Day day;
    private final Time time;

    public Schedule(int scene_id, Day day, Time time){
        this.scene_id = scene_id;
        this.time = time;
        this.day = day;
    }

    public int getScene_id() {
        return scene_id;
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }
}
