package com.example.jolicitefx.Domain;

import java.util.Arrays;

/**
 * A class representing a theater piece
 * dates - an array, with it's first element being the day from which the spectacle will begin, and it's second element
 *         being the end day, or until which day the spectacle will be played.
 * name - the piece's name
 * time - the starting and ending time of the piece, for each day included in the dates array.
 */
public class TheaterPiece implements Spectacle {
    private final Day[] dates;
    private final Name name;
    private final Time time;

    public TheaterPiece(Day[] dates, String name, int[] start_time, int[] end_time) {
        this.dates = dates;
        this.name = new Name(name);
        this.time = new Time(start_time, end_time);
    }

    /**
     *
     * @return The array of starting and ending days
     */
    public Day[] getDay() {
        return dates;
    }

    /**
     *
     * @return The name of the spectacle
     */
    public String getName() {
        return name.get();
    }

    /**
     *
     * @return The starting and end time of all days in which the spectacle takes place
     */
    public Time getTime() {
        return this.time;
    }

    /**
     *
     * @return the type of spectacle. Used mostly for debug purposes.
     */
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

    /**
     * Class to represent an artist name and provide more flexibility, in comparison to a simple string.
     */
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
