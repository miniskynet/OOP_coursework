public abstract class Driver {

    private String driverName;
    private String location;
    private String teamName;

    //returns the drivers name
    public String getDriverName() {
        return this.driverName;
    }

    //sets the name of the driver
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    //gets the location on where the driver is based
    public String getLocation() {
        return this.location;
    }

    //sets the location of the driver
    public void setLocation(String location) {
        this.location = location;
    }

    //gets the team name of the particular driver
    public String getTeamName() {
        return this.teamName;
    }

    //sets the team name of the driver
    public void setTeamName(String TeamName) {
        this.teamName = TeamName;
    }




}
