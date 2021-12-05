package Domain;

public class Concert extends Spectacle{
    private final Day day;
    private final Name artist_name;
    private final Time time;

    public Concert(Day day, String artist_name, int[] start_time, int[] end_time){
        this.day = day;
        this.artist_name=new Name(artist_name);
        this.time =new Time(start_time, end_time);
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


    public Day[] getDay() {
        return new Day[]{day, day};
    }

    public String getName() {
        return artist_name.get();
    }

    public Time getTime(){
        return time;
    }

    public String getType(){
        return "Concert";
    }
}
