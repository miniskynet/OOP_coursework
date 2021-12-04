import java.util.ArrayList;

public class Race {

    private int day;
    private int month;
    private int year;
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

    public void setDriverPosition(ArrayList<String> driverPosition){
        this.driverPosition.addAll(driverPosition);
    }

    public ArrayList<String> getDriverPosition(){
        return this.driverPosition;
    }




}
