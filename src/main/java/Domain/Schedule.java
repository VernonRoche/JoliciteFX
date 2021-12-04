package Domain;

public class Schedule{
    private final int[] time;

    public Schedule(int hour, int minute){
        if (hour>23 || hour<0 || minute<0 || minute>59){
            throw new IllegalArgumentException("Wrong time input.");
        }
        int[] time=new int[2];
        time[0]=hour;
        time[1]=minute;
        this.time=time;
    }

    public Schedule(int[] time){
        this.time=time;
    }

    public int[] get(){
        return time;
    }
}
