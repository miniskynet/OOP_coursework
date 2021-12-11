import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {

    ArrayList<Formula1Driver> driverList = new ArrayList<>();
    ArrayList<Race> raceList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //prints the main menu to console
    public void printIntro() {
        System.out.println("\n------Welcome to Formula 1 championship manager------\n");
        System.out.println("1. Enter new Driver\n" +
                "2. Delete a driver\n" +
                "3. Change a driver\n" +
                "4. Display statistics\n" +
                "5. Display driver table\n" +
                "6. Add a race\n" +
                "7. Save information\n" +
                "8. Open GUI\n" +
                "9. Exit");
    }

    //checks if a certain driver name already exists within the driverList array list
    public boolean checkName(String driverName) {
        boolean nameExists = false;
        for (Formula1Driver i : driverList) {
            if (i.getDriverName().equalsIgnoreCase(driverName)) {
                nameExists = true;
                break;
            }
        }
        return nameExists;
    }

    //separate method to check if a certain team already exists within the driverList array list
    public boolean checkTeams(String teamName) {
        boolean teamExists = false;
        for (Formula1Driver i : driverList) {
            if (i.getTeamName().equalsIgnoreCase(teamName)) {
                teamExists = true;
                break;
            }
        }
        return teamExists;
    }

    //checks if driver has participated in any races
    public boolean checkParticipation(String driverName){
        boolean hasParticipated = false;
        for(Formula1Driver i: driverList){
            if(i.getDriverName().equalsIgnoreCase(driverName) && i.getTotalRaces()>0){
                hasParticipated = true;
            }
        }
        return  hasParticipated;
    }

    //sets the Formula1Driver objects in descending order according to points earned by each driver
    static class driverSortDescending implements Comparator<Formula1Driver>{
        public int compare(Formula1Driver driver1, Formula1Driver driver2){
            if(driver1.getPoints()==driver2.getPoints()){
                if(driver1.getPositionsCount()[0]>driver2.getPositionsCount()[0]){
                    return -1;
                }
                else{
                    return 1;
                }
            }
            else if(driver1.getPoints()>driver2.getPoints()){
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    //sets the Formula1Driver object in ascending order according to points earned by each driver
    static class driverSortAscending implements Comparator<Formula1Driver>{
        public int compare(Formula1Driver driver1, Formula1Driver driver2){
            if(driver1.getPoints()==driver2.getPoints()){
                if(driver1.getPositionsCount()[0]>driver2.getPositionsCount()[0]){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else if(driver1.getPoints()>driver2.getPoints()){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    //sets the Formula1Driver objects in descending order according to first positions earned
    static class driverSortFirstPositions implements Comparator<Formula1Driver>{
        public int compare(Formula1Driver driver1, Formula1Driver driver2){
            if(driver1.getPositionsCount()[0]==driver2.getPositionsCount()[0]){
                return 0;
            }
            else if (driver1.getPositionsCount()[0]>driver2.getPositionsCount()[0]){
                return -1;
            }else{
                return 1;
            }
        }
    }

    //sets the Race objects in ascending order according to the date
    static class raceSort implements Comparator<Race>{
        public int compare(Race race1, Race race2){
            if(race1.getYear()==race2.getYear()){
                if(race1.getMonth()==race2.getMonth()){
                    if(race1.getDay()==race2.getDay()){
                        return 0;
                    }
                    else if(race1.getDay()>race2.getDay()){
                        return 1;
                    }
                    else{
                        return  -1;
                    }
                }
                else if(race1.getMonth()>race2.getMonth()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else if(race1.getYear()>race2.getYear()){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    //stores new Driver object in the driverlist based on user entered details
    @Override
    public void createNewDriver() {

        System.out.println("Enter Driver name: ");
        String driverName = sc.next();

        while(checkName(driverName)){
            System.out.println("Driver with that name already exists. Please enter another name : ");
            driverName = sc.next();
        }

        System.out.println("Enter Driver location: ");
        String location = sc.next();
        System.out.println("Enter Driver team name: ");
        String teamName = sc.next();

        while(checkTeams(teamName)){
            System.out.println("Team name already exists. Please enter new name : ");
            teamName = sc.next();
        }

        Formula1Driver driver = new Formula1Driver();
        driver.setDriverName(driverName);
        driver.setLocation(location);
        driver.setTeamName(teamName);
        driverList.add(driver);
        System.out.println("Driver added to the roster successfully");

}

    //Removes the respective Formula1Driver object from the driverList array list based on users choice
    @Override
    public void deleteDriver() {
        if(driverList.isEmpty()){
            System.out.println("\nDriver list is empty. Please add drivers to continue ...\n");
        }
        else{
            System.out.println("Enter drivers name which you wish to delete : ");
            String driverName = sc.next();
            if(checkName(driverName)){
                for(Formula1Driver i: driverList){
                    if(i.getDriverName().equalsIgnoreCase(driverName)){
                        driverList.remove(i);
                        System.out.println("Driver has been successfully deleted");
                        break;
                    }
                }
            }
            else{
                System.out.println("No driver with that name");
            }
        }
    }

    //Sets the new driver name for the respective Driver object based on the user selected team
    @Override
    public void changeDriver() {
        if(driverList.isEmpty()){
            System.out.println("\nDriver list is empty. Please add drivers to continue ...\n");
        }
        else{
            System.out.println("Enter team name : ");
            String teamName = sc.next();
            if (checkTeams(teamName)){
                for(Formula1Driver i: driverList){
                    if(i.getTeamName().equalsIgnoreCase(teamName)){
                        System.out.println("Enter the Driver name: ");
                        String driverName = sc.next();
                        i.setDriverName(driverName);
                        System.out.println("Driver has been successfully changed");
                    }
                }
            }
            else{
                System.out.println("No team with that name");
            }

        }
    }

    //displays the statistics of the specified driver
    @Override
    public void displayStats() {
        if(driverList.isEmpty()){
            System.out.println("\nDriver list is empty. Please add drivers to continue ...\n");
        }
        else{
            System.out.println("Enter driver name: ");
            String driverName = sc.next();
            if(checkName(driverName)){
                for(Formula1Driver i: driverList){
                    if(i.getDriverName().equalsIgnoreCase(driverName)){
                        System.out.println("\nName of Driver : " + i.getDriverName()
                                +"\nDrivers location : " + i.getLocation()
                                +"\nDrivers team name : " + i.getTeamName()
                                + "\nFirst positions earned :" +  i.getPositionsCount()[0] +
                                        "\nSecond positions earned : " + i.getPositionsCount()[1]
                                +"\nThird positions earned : " + i.getPositionsCount()[2] +
                                "\nPoints earned : " + i.getPoints()
                                +"\nTotal races participated in : " + i.getTotalRaces());
                    }
                }
            }
            else{
                System.out.println("No driver with that name");
            }
        }

    }

    //displays the drivers and their respective details in a tabular format
    @Override
    public void displayTable() {
        driverList.sort(new driverSortDescending());
        System.out.printf("%100s%n%n","-----Formula 1 Driver Table-----");
        System.out.printf("%15s%15s%20s%20s%20s%20s%20s%20s%n%n","Driver Name","Location","Team Name",
                "1st Positions", "2nd Positions", "3rd Positions",
                "Total points","Total races");
        for(Formula1Driver i: driverList){
            System.out.printf("%10s%18s%20s%20s%20s%20s%20s%20s%n",i.getDriverName(),i.getLocation(),i.getTeamName(),
                    i.getPositionsCount()[0], i.getPositionsCount()[1], i.getPositionsCount()[2],
                    i.getPoints(),i.getTotalRaces());
        }
    }

    //Adds a new race according to user entered information
    @Override
    public void addRace() {
        int day = 0;
        int month = 0;
        int year = 0;
        if (driverList.isEmpty()) {
            System.out.println("\nDriver list is empty. Please add drivers to continue ...\n");
        } else {
            ArrayList<String> temporaryDriverList = new ArrayList<>();
            Race race = new Race();
            System.out.println("Please enter the date (Day should be a value between 1-31)" +
                    "Month should be a value between 1-12" +
                    " and year should be a value between 1900-2022");
            System.out.println("Day : ");
            while(day<1 || day>31){
                try{
                    day = sc.nextInt();
                    if(day<1 || day>31){
                        System.out.println("Invalid input. Please enter a value between 1-31 : ");
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid input. Please enter an integer : ");
                    sc.nextLine();
                }
            }
            race.setDay(day);

            System.out.println("Month : ");
            while(month<1 || month>12){
                try{
                    month = sc.nextInt();
                    if(month<1 || month>12){
                        System.out.println("Invalid input. Please enter a value between 1-12 : ");
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid input. Please enter an integer : ");
                    sc.nextLine();
                }
            }
            race.setMonth(month);

            System.out.println("Year : ");
            while(year<1900||year>2022){
                try{
                    year = sc.nextInt();
                    if(year<1900||year>2022){
                        System.out.println("Invalid input. Please enter a value between 1900-2022 : ");
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid input. Please enter an integer : ");
                    sc.nextLine();
                }
            }
            race.setYear(year);

            for (int i = 0; i < driverList.size(); i++) {
                boolean allCases = false;
                while (!allCases) {
                    System.out.println("Enter Driver name for position " + (i+1) + " : ");
                    String driverName = sc.next();
                    if(checkName(driverName) && !temporaryDriverList.contains(driverName)){
                        temporaryDriverList.add(driverName);
                        for(Formula1Driver driver: driverList){
                            if(driver.getDriverName().equalsIgnoreCase(driverName)){
                                driver.addPosition(i);
                                driver.addRaces();
                                allCases=true;
                            }
                        }
                    }
                    else if(!checkName(driverName)){
                        System.out.println("Driver with that name does not exist in the roster" +
                                " please enter a valid name");
                    }
                    else if(temporaryDriverList.contains(driverName)){
                        System.out.println("The same driver cannot be entered more than once" +
                                " for a position");
                    }
                }

            }
            raceList.add(race);
            race.setDriverPosition(temporaryDriverList);
        }
    }

    //saves the information of the drivers into a text file
    @Override
    public void saveDriverInfo() {
        try{
            File drivers = new File("Drivers.txt");
            if(drivers.createNewFile()){
                System.out.println("Driver file has been created");
            }
            else{
                System.out.println("Driver file already exists");
            }
        }catch (IOException e){
            System.out.println("An error has occurred while creating Drivers.txt file");
            e.printStackTrace();
        }
        try{
            FileWriter writeDrivers = new FileWriter("Drivers.txt");
            for(Formula1Driver driver: driverList){
                writeDrivers.write(driver.getDriverName() + " " +
                        driver.getLocation() + " " + driver.getTeamName());
                for(int count: driver.getPositionsCount()){
                    writeDrivers.write(" "+ count);
                }
                writeDrivers.write(" " + driver.getTotalRaces() + "\n");
            }
            writeDrivers.close();
            System.out.println("Driver info has been stored successfully!");
        }
        catch (IOException e){
            System.out.println("An error has occurred while storing driver information");
            e.printStackTrace();
        }

    }

    //saves the information of the races in a text file
    @Override
    public void saveRaceInfo(){
        try{
            File races = new File("Races.txt");
            if(races.createNewFile()){
                System.out.println("Race file has been created");
            }
            else{
                System.out.println("Race file already exists");
            }
        }catch (IOException e){
            System.out.println("An error has occurred while creating Races.txt file");
            e.printStackTrace();
        }
        try{
            FileWriter writeRaces = new FileWriter("Races.txt");
            for(Race race: raceList){
                writeRaces.write(race.getDay() + " " + race.getMonth()
                        + " " + race.getYear());
                for(String position:race.getDriverPosition()){
                    writeRaces.write(" "+ position);
                }
                writeRaces.write( "\n");
            }
            writeRaces.close();
            System.out.println("Race info has been stored successfully!");
        }
        catch (IOException e){
            System.out.println("An error has occurred while storing race information");
            e.printStackTrace();
        }
    }

    //reads the information from the Drivers text file and resumes the program from the previous state
    @Override
    public void readDriverInfo() {

        try{
            File readObject = new File("Drivers.txt");
            Scanner sc = new Scanner(readObject);
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                String[] dataArray = data.split(" ");
                Formula1Driver newDriver = new Formula1Driver();
                newDriver.setDriverName(dataArray[0]);
                newDriver.setLocation(dataArray[1]);
                newDriver.setTeamName(dataArray[2]);
                int positionIndex = 0;
                for(int i=3;i<13;i++){
                    newDriver.setPositionsCount(Integer.parseInt(dataArray[i]),positionIndex);
                    positionIndex = positionIndex + 1;
                }
                newDriver.setTotalRaces(Integer.parseInt(dataArray[13]));
                driverList.add(newDriver);
            }

        }
        catch(FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }

    //reads the information from the Races text file and resumes the program from the previous state
    @Override
    public void readRaceInfo() {
        try{
            File readObject = new File("Races.txt");
            Scanner sc = new Scanner(readObject);
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                String[] dataArray = data.split(" ");
                Race newRace = new Race();
                newRace.setDay(Integer.parseInt(dataArray[0]));
                newRace.setMonth(Integer.parseInt(dataArray[1]));
                newRace.setYear(Integer.parseInt(dataArray[2]));
                ArrayList<String> driverPosition = new ArrayList<>();
                for(int i=3;i<dataArray.length;i++){
                    driverPosition.add(dataArray[i]);
                }
                newRace.setDriverPosition(driverPosition);
                raceList.add(newRace);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    //common method which can be called to build a table for driver sorting GUIs
    public void buildTableGui(String tableTitle){
        String[][] data = new String[driverList.size()][8];
        int index=0;
        for(Formula1Driver driver:driverList){
            data[index][0] = driver.getDriverName();
            data[index][1] = driver.getLocation();
            data[index][2] = driver.getTeamName();
            data[index][3] = String.valueOf(driver.getPositionsCount()[0]);
            data[index][4] = String.valueOf(driver.getPositionsCount()[1]);
            data[index][5] = String.valueOf(driver.getPositionsCount()[2]);
            data[index][6] = String.valueOf(driver.getPoints());
            data[index][7] = String.valueOf(driver.getTotalRaces());
            index++;
        }

        String[] columnName = {"Driver Name","Location","Team Name","1st Positions","2nd Positions","3rd Positions","Total Points","Total Races"};

        JTable table = new JTable(data,columnName);
        JFrame frame = new JFrame(tableTitle);
        frame.add(new JScrollPane(table));
        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    //creates the main menu, where the user can select different options
    @Override
    public void openGui(){
        JFrame menu=new JFrame("Main menu");

        JButton sortDescending = new JButton("Sort : Descending");
        sortDescending.setBounds(50,50,180,30);
        sortDescending.addActionListener(e -> {
            driverList.sort(new driverSortDescending());
            buildTableGui("Driver Table Descending");
        });

        JButton sortAscending=new JButton("Sort : Ascending");
        sortAscending.setBounds(300,50,180,30);
        sortAscending.addActionListener(e -> {
            driverList.sort(new driverSortAscending());
            buildTableGui("Driver Table Ascending");
        });

        JButton sortFirstPosition=new JButton("Sort : 1st Positions won");
        sortFirstPosition.setBounds(50,100,180,30);
        sortFirstPosition.addActionListener(e -> {
            driverList.sort(new driverSortFirstPositions());
            buildTableGui("Driver Table First Positions");
        });

        JButton generateRace=new JButton("Generate Race");
        generateRace.setBounds(50,150,180,30);
        generateRace.addActionListener(e -> {
            Collections.shuffle(driverList);
            ArrayList<String> shuffledDrivers = new ArrayList<>();
            int index = 0;
            for(Formula1Driver driver:driverList){
                shuffledDrivers.add(index,driver.getDriverName());
                index++;
            }
            int randomDay = (int) Math.floor(Math.random()*(31-1+1)+1);
            int randomMonth = (int) Math.floor(Math.random()*(12-1+1)+1);
            int randomYear = (int) Math.floor(Math.random()*(2022-1900+1)+1900);
            String[][] data = new String[1][13];
            data[0][0] = String.valueOf(randomDay);
            data[0][1] = String.valueOf(randomMonth);
            data[0][2] = String.valueOf(randomYear);
            Race randomRace = new Race();
            randomRace.setDay(randomDay);
            randomRace.setMonth(randomMonth);
            randomRace.setYear(randomYear);
            int positionIndex = 3;
            index = 0;
            for(Formula1Driver driver:driverList){
                driver.addRaces();
                if(index<11&&positionIndex<13){
                    data[0][positionIndex] = driver.getDriverName();
                    driver.addPosition(index);
                }
                positionIndex++;
                index++;
            }
            randomRace.setDriverPosition(shuffledDrivers);
            raceList.add(randomRace);

            String[] columnName = {"Day","Month","Year","1st Position","2nd Position","3rd Position","4th Position","5th Position",
                    "6th Position","7th Position","8th Position","9th Position","10th Position"};

            JTable table = new JTable(data,columnName);
            JFrame frame = new JFrame("Sorted Races");
            frame.add(new JScrollPane(table));
            frame.setSize(640,480);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

        });

        JButton generateRaceMod=new JButton("Generate Race : Type II");
        generateRaceMod.setBounds(300,150,180,30);

        JButton sortRace=new JButton("Sort : Races");
        sortRace.setBounds(300,100,180,30);
        sortRace.addActionListener(e -> {
            raceList.sort(new raceSort());
            String[][] data = new String[raceList.size()][13];
            int index=0;
            for(Race race1:raceList){
                data[index][0] = String.valueOf(race1.getDay());
                data[index][1] = String.valueOf(race1.getMonth());
                data[index][2] = String.valueOf(race1.getYear());
                int positionCount = 0;
                for(int i=3;i<(race1.getDriverPosition().size()+3);i++){
                    if(i<13){
                        data[index][i] = race1.getDriverPosition().get(positionCount);
                    }
                    positionCount++;
                }
                index++;
            }

            String[] columnName = {"Day","Month","Year","1st Position","2nd Position","3rd Position","4th Position","5th Position",
            "6th Position","7th Position","8th Position","9th Position","10th Position"};

            JTable table = new JTable(data,columnName);
            JFrame frame = new JFrame("Sorted Races");
            frame.add(new JScrollPane(table));
            frame.setSize(640,480);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        });

        JButton searchRace=new JButton("Search race");
        searchRace.setBounds(180,200,180,30);
        JTextField userInputField = new JTextField();
        userInputField.setBounds(180,250,180,30);
        searchRace.addActionListener(e -> {
            String driverSearch = userInputField.getText();
            if(!checkName(driverSearch)){
                JOptionPane.showMessageDialog(menu,"Driver with that name does not exist","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(!checkParticipation(driverSearch)){
                JOptionPane.showMessageDialog(menu,"Driver has not participated in any race","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String[][] data = new String[raceList.size()][4];
                int index=0;
                for(Race race1:raceList){
                    for(String name:race1.getDriverPosition()){

                        if(name.equalsIgnoreCase(driverSearch)){
                            data[index][0] = String.valueOf(race1.getDay());
                            data[index][1] = String.valueOf(race1.getMonth());
                            data[index][2] = String.valueOf(race1.getYear());
                            data[index][3] = String.valueOf(race1.getDriverPosition().indexOf(name)+1);
                            index++;
                        }
                    }
                }
                String[] columnName = {"Day","Month","Year","Position"};
                JTable table = new JTable(data,columnName);
                JFrame frame = new JFrame("Participated Races of " + driverSearch);
                frame.add(new JScrollPane(table));
                frame.setSize(640,480);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }

        });

        menu.add(sortDescending);
        menu.add(sortAscending);
        menu.add(sortFirstPosition);
        menu.add(generateRace);
        menu.add(generateRaceMod);
        menu.add(sortRace);
        menu.add(searchRace);
        menu.add(userInputField);
        menu.setSize(550,350);
        menu.setLayout(null);
        menu.setVisible(true);

    }
}
