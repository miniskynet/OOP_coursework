public class Formula1Driver extends Driver {

    //stores the number of positions earned by each respective driver
    // (includes up until the top 10 positions)
    private final int[] positionsCount = new int[10];
    //stores the points which will be granted in order of positions earned
    private final int[] pointSheet = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
    private int points;
    private int totalRaces =0;

    //iterates a specific position by 1, once called
    public void addPosition(int position){
        positionsCount[position] = positionsCount[position] + 1;
    }

    //sets the total number earned for each respective position
    public void setPositionsCount(int positionsCount,int position){
        this.positionsCount[position] = positionsCount;
    }

    //adds all the elements from the positionsCount list,
    // to a new list and returns it
    public int[] getPositionsCount() {
        int[] newList = new int[10];
        for(int i=0;i<newList.length;i++){
            newList[i] = positionsCount[i];
        }
        return newList;
    }

    //calculates the total points earned by the particular driver,
    //based on his/her total number of positions earned and returns that value
    public int getPoints() {
        points =0;
        for(int i = 0; i< positionsCount.length; i++){
            points = points + (positionsCount[i] * pointSheet[i]);
        }
        return this.points;
    }

    //returns the total number of races,
    //which a particular driver has participated in
    public int getTotalRaces() {
        return this.totalRaces;
    }

    //iterates the total number of races participated in by 1
    public void addRaces() {
        this.totalRaces = totalRaces + 1;
    }

    //sets the total number of races participated in by 1
    public void setTotalRaces(int totalRaces){
        this.totalRaces = totalRaces;
    }


}
