package com.example.jolicitefx.Domain;

import java.util.Arrays;

public class TheaterPiece implements Spectacle {
    private final Day[] dates;
    private final Name name;
    private final Time time;

    public TheaterPiece(Day[] dates, String name, int[] start_time, int[] end_time) {
        this.dates = dates;
        this.name = new Name(name);
        this.time = new Time(start_time, end_time);
    }

    public Day[] getDay() {
        return dates;
    }

    public String getName() {
        return name.get();
    }

    public Time getTime() {
        return this.time;
    }

    public String getType() {
        return "Theater Piece";
    }

    @Override
    public String toString() {
        return "TheaterPiece{" +
                "dates=" + Arrays.toString(dates) +
                ", name=" + getName() +
                ", time=" + time +
                '}';
    }

    private class Name {
        private final String name;

        Name(String name) {
            this.name = name;
        }

        String get() {
            return name;
        }
    }
}
