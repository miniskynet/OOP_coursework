import java.util.InputMismatchException;
import java.util.Scanner;

public class FormulaTester {

    public static void main(String[] args) {
        //create new Formula1ChampionshipManager object
        // to make use of the methods inside the class
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
        //call the methods which reads the information in the text documents
        //and automatically updates the program to its previous state
        manager.readDriverInfo();
        manager.readRaceInfo();
        Scanner sc = new Scanner(System.in);
        int userInput = 0;
        //while the user does not enter 9 (entering 9 would exit the program)
        //reiterate the loop to get a valid input from the user
        while(userInput != 9){
            //prints the main menu and prompts the user to enter the preferred choice
            manager.printIntro();
            System.out.print("Please Enter Your choice: ");
            //catch if the users enters a String value instead of an integer
            try{
                userInput = sc.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a value between 1-9");
                sc.nextLine();
            }

            //switch cases to call the functionalities of the
            // appropriate class based on the users preference
            switch (userInput) {
                case (1) -> manager.createNewDriver();
                case (2) -> manager.deleteDriver();
                case (3) -> manager.changeDriver();
                case (4) -> manager.displayStats();
                case (5) -> manager.displayTable();
                case (6) -> manager.addRace();
                case (7) -> {
                    manager.saveDriverInfo();
                    manager.saveRaceInfo();
                }
                case(8) -> manager.openGui();
            }

        }
    }
}
