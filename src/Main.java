import com.pda.games.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static String player;
    private static MasterMind mode;
    private static int gameMode = 0;
    // soucis entrée chiffre, trop ou pas cause des bugs voir des crash

    public static void main(String[] args) {
        player = GetIn.pseudo();
        System.out.println("Rules :\n" +
                "-For each \"defense\" or \"attack\" you need to enter " + MasterMind.getSize() + " digits.\n" +
                "-For each \"clue\" you need to enter if your defense number is higther \"+\", lower \"-\" or equal \"=\"");
        System.out.print("\nChoose your game mode :\n" +
                "1 -Challenger\n" +
                "2 -Défenseur\n" +
                "3 -Duel\n" +
                "Make your choice :");
        mode = chooseGame();
        executeGame();
    }

    private static void executeGame() {

        while (mode.humanCanPlayAgain() && mode.botCanPlayAgain()) {
            mode.round();
        }
        if (mode.humanWin() && mode.botWin()){
            System.out.println("Ex aequo.");
        }
        else if (mode.humanWin() && mode.botLose()) {
             System.out.println("[" + player + "] win.");
        }
        else if (mode.botWin() && mode.humanLose()) {
            System.out.println("[" + player + "] lose.");
            if (mode.getBotDefense() != null){
                System.out.println("The answer was" + Arrays.toString(mode.getBotDefense()) + ".");
            }
        }
        if (mode.humanLose() && mode.botLose() && !(mode.humanWin() && mode.botWin())){
            System.out.println("Everyone lose" +
                    "\nThis bot answer was\n" +
                    Arrays.toString(mode.getBotDefense()) +
                    "\nand human answer was\n" +
                    Arrays.toString(mode.getHumanDefense()));
        }
        System.out.println("\nIf you want retry \"r\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
        tryAgain();
    }

    private static MasterMind chooseGame(){
        Scanner sc = new Scanner(System.in);
            try {
            gameMode = sc.nextInt();
            selectGameMode(gameMode);
        }catch (InputMismatchException e){
            sc.next();
        }return mode;
    }

    private static MasterMind selectGameMode(int gameMode){
        switch (gameMode) {
            case 1:
                mode = new Challenger();
                break;
            case 2:
                mode = new Defenseur();
                break;
            case 3:
                mode = new Duel();
                break;
            default:
                System.out.println("Enter only 1 2 or 3");
        }return mode;
    }

    private static void tryAgain() {
        Scanner sc = new Scanner(System.in);
        String tryAgain = sc.nextLine();
        switch (tryAgain.toLowerCase()) {
            case "r":
                mode = selectGameMode(gameMode);
                executeGame();
                break;
            case "h":
                System.out.print("\nChoose your game mode :\n" +
                        "1 -Challenger\n" +
                        "2 -Défenseur\n" +
                        "3 -Duel\n" +
                        "Make your choice :");
                mode = chooseGame();
                executeGame();
                break;
            case "q":
                System.out.println("good bye");
                break;
            default:
                System.out.println("I'm sorry i don't understand");
                tryAgain();
                break;
        }
    }
}