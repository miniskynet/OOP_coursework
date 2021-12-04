public class Formula1Driver extends Driver {

    private final int[] positionsCount = new int[10];
    private final int[] pointSheet = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
    private int points;
    private int totalRaces =0;

    public void addPosition(int position){
        positionsCount[position] = positionsCount[position] + 1;
    }

    public void setPositionsCount(int positionsCount,int position){
        this.positionsCount[position] = positionsCount;
    }

    public int[] getPositionsCount() {
        int[] newList = new int[10];
        for(int i=0;i<newList.length;i++){
            newList[i] = positionsCount[i];
        }
        return newList;
    }

    public int getPoints() {
        points =0;
        for(int i = 0; i< positionsCount.length; i++){
            points = points + (positionsCount[i] * pointSheet[i]);
        }
        return this.points;
    }

    public int getTotalRaces() {
        return this.totalRaces;
    }

    public void addRaces() {
        this.totalRaces = totalRaces + 1;
    }

    public void setTotalRaces(int totalRaces){
        this.totalRaces = totalRaces;
    }


}
