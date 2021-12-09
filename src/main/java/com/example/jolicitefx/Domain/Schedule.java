package com.example.jolicitefx.Domain;

/**
 * Class which represents a day, as well as starting and ending time of a schedule, for a specific scene.
 *
 * This is used to handle most if not all time and date related information.
 */
public class Schedule {
    private final int scene_id;
    private final Day day;
    private final Time time;

    public Schedule(int scene_id, Day day, Time time) {
        this.scene_id = scene_id;
        this.time = time;
        this.day = day;
    }

    /**
     *
     * @return The identifier of the scene holding the schedule information
     */
    public int getScene_id() {
        return scene_id;
    }

    /**
     *
     * @return The day of the week in which the schedule is in
     */
    public Day getDay() {
        return day;
    }

    /**
     *
     * @return The starting and ending time of the specific schedule.
     */
    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scene_id=" + scene_id +
                ", day=" + day +
                ", time=" + time +
                '}';
    }
}
