package Domain;

public class DateInterval{
    private final int[] start_date;
    private final int[] end_date;

    DateInterval(int day, int month, int year, int end_day, int end_month, int end_year){
        if (day<1 || day>31 || month<1 || month>12){
            throw new IllegalArgumentException("Wrong date input.");
        }
        if (end_day<1 || end_day>31 || end_month<1 || end_month>12){
            throw new IllegalArgumentException("Wrong date input.");
        }
        if ((month==end_month && day>end_day) ||month>end_month || year>end_year){
            throw new IllegalArgumentException("Wrong date input.");
        }
        int[] start_date=new int[3];
        int[] end_date=new int[3];
        start_date[0]=day;
        start_date[1]=month;
        start_date[2]=year;
        end_date[0]=end_day;
        end_date[1]=end_month;
        end_date[2]=end_year;
        this.start_date=start_date;
        this.end_date=end_date;
    }

    int[][] get() {
        return new int[][]{start_date,end_date};
    }
}