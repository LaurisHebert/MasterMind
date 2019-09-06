import com.pda.games.*;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        executeGame();
    }

    private static void executeGame() {
        System.out.print("choose your game mode \n" +
                "1 -Challenger\n" +
                "2 -Défenseur\n" +
                "3 -Duel\n");
        MasterMind mode = chooseGame();
        if (mode != null){
            while (mode.canPlayAgain()) {
                mode.round();
            }
            if (mode.hasLost() && !mode.hasWon()) {
                System.out.println("\nYou lose \nThe answer was" + Arrays.toString(mode.defenseNumber));
            }
            if (mode.hasWon() && !mode.hasLost()){
                System.out.println("Ex aequo");
            }
            if (mode.hasWon() && mode.hasLost()) {
                 System.out.println("We have a winner !");
            }
            if (!mode.canPlayAgain() && !mode.hasWon() && !mode.hasLost()){
                System.out.println("Everyone lose" +
                        "\nThis IA answer was\n" +
                        Arrays.toString(mode.defenseNumber) +
                        "\nand human answer was\n" +
                        Arrays.toString(mode.defenseNumber2));
            }
        }
        System.out.println("try Again ? (Y) (N)");
        tryAgain();
    }

    private static MasterMind chooseGame(){
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
                mode = new Duel();
                break;
            default:
                System.out.println("Enter only 1 2 or 3");
                chooseGame();
        }return mode;
    }

    private static void tryAgain() {
        Scanner sc = new Scanner(System.in);
        String tryAgain = sc.nextLine();
        if (tryAgain.toLowerCase().equals("y")) {
            executeGame();
        }
        else if (tryAgain.toLowerCase().equals("n")) {
            System.out.println("good bye");
        } else {
            System.out.println("I'm sorry i don't understand");
            tryAgain();
        }
    }
}