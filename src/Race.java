import java.util.ArrayList;

public class Race {

    private int day;
    private int month;
    private int year;
    private ArrayList<String> driverPosition = new ArrayList<>();

    //returns the day on which the race occurred
    public int getDay() {
        return this.day;
    }

    //sets the day on which the race occurred
    public void setDay(int day) {
        this.day = day;
    }

    //returns the month on which the race occurred
    public int getMonth() {
        return this.month;
    }

    //sets the month on which the race occurred
    public void setMonth(int month) {
        this.month = month;
    }

    //returns the year on which the race occurred
    public int getYear() {
        return this.year;
    }

    //sets the year on which the race occurred
    public void setYear(int year) {
        this.year = year;
    }

    //takes an arraylist of strings as an argument
    //the elements of the argument arraylist are added to the arraylist,
    //which holds the positions of the drivers
    public void setDriverPosition(ArrayList<String> driverPosition){
        this.driverPosition.addAll(driverPosition);
    }

    //returns the arraylist of driver positions
    public ArrayList<String> getDriverPosition(){
        return this.driverPosition;
    }




}
