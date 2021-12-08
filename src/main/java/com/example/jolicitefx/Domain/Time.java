package com.example.jolicitefx.Domain;

import java.util.Arrays;

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

    public int[] getStart_time() {
        return start_time;
    }

    public int[] getEnd_time() {
        return end_time;
    }

    public boolean isTimeBefore(Time time) {
        return start_time[0] < time.start_time[0] ||
                (start_time[0] == time.start_time[0] && start_time[1] <= time.start_time[1]);
    }

    // Checks if parameter is within object time bounds
    public boolean isTimeWithinBoundaries(Time time) {
        if (isTimeBefore(time)) {
            return end_time[0] > time.end_time[0] || (end_time[0] == time.end_time[0] && end_time[1] >= time.end_time[1]);
        }
        return false;
    }

    public boolean isStartTimeEqual(Time time) {
        return this.start_time[0] == time.start_time[0] && this.start_time[1] == time.start_time[1];
    }

    public boolean isEndTimeEqual(Time time) {
        return this.end_time[0] == time.end_time[0] && this.end_time[1] == time.end_time[1];
    }

    @Override
    public String toString() {
        return Arrays.toString(start_time) + "/" + Arrays.toString(end_time);
    }
}
