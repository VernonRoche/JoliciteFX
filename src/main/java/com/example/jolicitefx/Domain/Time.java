package com.example.jolicitefx.Domain;

import java.util.Arrays;

/**
 * Class aimed to represent a time period within a day
 * start_time - an array of 2 elements, with it's first one representing the hours and second the minutes
 * end_time - an array of 2 elements, with it's first one representing the hours and second the minutes
 */
public class Time {
    private final int[] start_time;
    private final int[] end_time;

    public Time(int hour, int minute, int end_hour, int end_minute) {
        if (hour > 23 || hour < 0 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Wrong time input.");
        }
        if (end_hour > 23 || end_hour < 0 || end_minute < 0 || end_minute > 59) {
            throw new IllegalArgumentException("Wrong time input.");
        }
        if (hour > end_hour || (hour == end_hour && minute > end_minute)) {
            throw new IllegalArgumentException("Wrong time input.");
        }
        int[] start_time = new int[2];
        int[] end_time = new int[2];
        start_time[0] = hour;
        start_time[1] = minute;
        end_time[0] = end_hour;
        end_time[1] = end_minute;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Time(int[] start_time, int[] end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }

    /**
     *
     * @return An array of 2 elements representing the starting time
     */
    public int[] getStart_time() {
        return start_time;
    }

    /**
     *
     * @return An array of 2 elements representing the ending time
     */
    public int[] getEnd_time() {
        return end_time;
    }

    /**
     * Checks if the method object is situated before the parameter (in terms of time signification).
     *
     * For example check if 14h00 is before 16h00
     *
     * @param time The time period to check
     * @return  A boolean which is true if the method object is before the parameter, else false.
     */
    public boolean isTimeBefore(Time time) {
        return start_time[0] < time.start_time[0] ||
                (start_time[0] == time.start_time[0] && start_time[1] <= time.start_time[1]);
    }


    /**
     * Checks if the parameter is within the time bounds of the method object.
     *
     * For example check if 15h00-16h00 is within 14h00-18h00
     *
     * @param time The time period to check if it's within the method object
     * @return  A boolean which is true if the parameter is within the method object, else false.
     */
    public boolean isTimeWithinBoundaries(Time time) {
        if (isTimeBefore(time)) {
            return end_time[0] > time.end_time[0] || (end_time[0] == time.end_time[0] && end_time[1] >= time.end_time[1]);
        }
        return false;
    }

    /**
     * Checks if the method object and the parameter have the same starting times.
     *
     * @param time The time period to be checked
     * @return A boolean
     */
    public boolean isStartTimeEqual(Time time) {
        return this.start_time[0] == time.start_time[0] && this.start_time[1] == time.start_time[1];
    }

    /**
     * Checks if the method object and the parameter have the same ending times.
     *
     * @param time The time period to be checked
     * @return A boolean
     */
    public boolean isEndTimeEqual(Time time) {
        return this.end_time[0] == time.end_time[0] && this.end_time[1] == time.end_time[1];
    }

    @Override
    public String toString() {
        return Arrays.toString(start_time) + "/" + Arrays.toString(end_time);
    }
}
