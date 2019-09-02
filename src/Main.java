import com.pda.games.Challenger;
import com.pda.games.Defenseur;
import com.pda.games.MasterMind;

import java.util.Arrays;
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
        int gameMode = 0;
        Scanner sc = new Scanner(System.in);
        try {
            gameMode = sc.nextInt(3);
        }catch (java.util.InputMismatchException e){
            sc.next();
        }
        switch (gameMode) {

            case 1:
                MasterMind challenger = new Challenger();

                while (!challenger.win() && challenger.canPlayAgain()) {
                    challenger.turn();
                }
                if (challenger.win()) {
                    System.out.println("Well played");
                }
                if (challenger.lose()) {
                    System.out.println("\nYou lose \nthe answer was" + Arrays.toString(challenger.defenseNumber));
                }
                System.out.println("try Again ? (Y) (N)");
                tryAgain();
                break;

            case 2:
                MasterMind defenseur = new Defenseur();

                while (!defenseur.win() && defenseur.canPlayAgain()) {
                    defenseur.turn();
                }
                if (defenseur.win()) {
                    System.out.println("You lose !");
                }
                if (defenseur.lose()) {
                    System.out.println("Nooo");
                }
                System.out.println("try Again ? (Y) (N)");
                tryAgain();
                break;
            default:
                System.out.println("Enter only 1 2 or 3");
                chooseGame();
        }
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