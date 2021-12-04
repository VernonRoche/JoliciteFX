package Domain;

public class Concert extends Spectacle{
    private final DateInterval date;
    private final Name artist_name;
    private final Schedule schedule;

    public Concert(int[] date, String artist_name, int[] schedule){
        this.date= new DateInterval(date[0],date[1],date[2],date[0],date[1],date[2]);
        this.artist_name=new Name(artist_name);
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
        return date.get();
    }

    public String getName() {
        return artist_name.get();
    }

    public int[] getSchedule(){
        return schedule.get();
    }

    public String getType(){
        return "Concert";
    }
}
