import com.pda.games.Challenger;
import com.pda.games.Defenseur;
import com.pda.games.MasterMind;
import com.pda.games.GetIn;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("choose your game mode \n" +
                "1 -Challenger\n" +
                "2 -Défenseur\n" +
                "3 -Duel\n");
        chooseGame();
    }

    private static void chooseGame(){
        MasterMind mode = null;
        int gameMode = 0;
        Scanner sc = new Scanner(System.in);
        try {
            gameMode = sc.nextInt(4);
        }catch (InputMismatchException e){
            sc.next();
        }
        switch (gameMode) {

            case 1:
                mode = new Challenger();
                break;
            case 2:
                mode = new Defenseur();
                break;
            case 3:
                System.out.println("Not valid right now" +
                        "\n Choose an other game mode ( Y / N )");
                tryAgain();
                break;
            default:
                System.out.println("Enter only 1 2 or 3");
                chooseGame();
        }
        while (mode.canPlayAgain()) {
            mode.round();
        }
        if (mode.hasWon()) {
            System.out.println("Well played");
        }
        if (mode.hasLost()) {
            System.out.println("\nYou lose \nthe answer was" + Arrays.toString(mode.defenseNumber));
        }
        System.out.println("try Again ? (Y) (N)");
        tryAgain();
    }

    private static void tryAgain() {
        Scanner sc = new Scanner(System.in);
        String tryAgain = sc.nextLine();
        if (tryAgain.toLowerCase().equals("y")) {
            System.out.print("choose your game mode \n" +
                    "1 -Challenger\n" +
                    "2 -Défenseur\n" +
                    "3 -Duel\n");
            chooseGame();
        }
        if (tryAgain.toLowerCase().equals("n")) {
            System.out.println("good bye");
        } else {
            System.out.println("I'm sorry i don't understand");
            tryAgain();
        }
    }
}