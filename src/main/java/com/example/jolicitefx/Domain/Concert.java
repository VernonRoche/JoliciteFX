package com.example.jolicitefx.Domain;

/**
 * A class representing a Concert, defined by
 * day - the day the concert takes place
 * artist_name - the name of the artist
 * time - the start and end time of the spectacle
 */
public class Concert implements Spectacle {
    private final Day day;
    private final Name artist_name;
    private final Time time;

    public Concert(Day day, String artist_name, int[] start_time, int[] end_time) {
        this.day = day;
        this.artist_name = new Name(artist_name);
        this.time = new Time(start_time, end_time);
    }

    /**
     *
     * @return an array of Day objects, which includes the attribute this.day twice.
     * This is used to have a uniform return value, and to conform with the Spectacle interface.
     */

    public Day[] getDay() {
        return new Day[]{day, day};
    }

    /**
     *
     * @return a String representing the concert's artist name
     */

    public String getName() {
        return artist_name.get();
    }

    /**
     *
     * @return the start and end time of the concert.
     */

    public Time getTime() {
        return time;
    }

    /**
     *
     * @return the type of spectacle. Used mostly for debug purposes.
     */

    public String getType() {
        return "Concert";
    }

    @Override
    public String toString() {
        return "Concert{" +
                "day=" + day +
                ", artist_name=" + getName() +
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
