package Domain;

import java.util.ArrayList;

public class TheaterPiece extends Spectacle{
    private final DateInterval dates;
    private final Name name;
    private final Schedule schedule;

    public TheaterPiece(int[][] dates, String name, int[] schedule){
        this.dates= new DateInterval(dates[0][0],dates[0][1],dates[0][2],dates[1][0],dates[1][1],dates[1][2]);
        this.name=new Name(name);
        this.schedule=new Schedule(schedule[0],schedule[1]);
    }

    private class Name{
        private final String name;

        Name(String name){
            this.name=name;
        }

        String get() {
            return name;
        }
    }


    public int[][] getDate() {
        return dates.get();
    }

    public String getName() {
        return name.get();
    }

    public int[] getSchedule(){
        return this.schedule.get();
    }

    public String getType(){
        return "Theater Piece";
    }
}
