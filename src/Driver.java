public abstract class Driver {

    //stores the names of the driver, the location he is hailing from as well
    //the name of the team he is representing in the respective variable order
    private String driverName;
    private String location;
    private String teamName;

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String TeamName) {
        this.teamName = TeamName;
    }
}
