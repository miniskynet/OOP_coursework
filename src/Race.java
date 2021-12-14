import java.util.ArrayList;

public class Race {

    //day,month and year variables store the date information on which the race occurred
    private int day;
    private int month;
    private int year;
    //stores the positions of all drivers who participated in the current occurrence of the race
    private ArrayList<String> driverPosition = new ArrayList<>();

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //the elements of the argument arraylist are added to the arraylist,
    //which holds the positions of the drivers
    public void setDriverPosition(ArrayList<String> driverPosition){
        this.driverPosition.addAll(driverPosition);
    }

    public ArrayList<String> getDriverPosition(){
        return this.driverPosition;
    }

}
