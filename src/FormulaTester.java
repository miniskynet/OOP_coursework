import java.util.InputMismatchException;
import java.util.Scanner;

public class FormulaTester {

    public static void main(String[] args) {
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
        manager.readDriverInfo();
        manager.readRaceInfo();
        Scanner sc = new Scanner(System.in);
        int userInput = 0;
        while(userInput != 9){
            manager.printIntro();
            System.out.print("Please Enter Your choice: ");
            try{
                userInput = sc.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a value between 1-9");
                sc.nextLine();
            }

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
