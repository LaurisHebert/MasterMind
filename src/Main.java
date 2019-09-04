import com.pda.games.Challenger;
import com.pda.games.Defenseur;
import com.pda.games.MasterMind;
<<<<<<< HEAD
import com.pda.games.GetIn;

import java.util.Arrays;
import java.util.InputMismatchException;
=======

import java.util.Arrays;
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
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
<<<<<<< HEAD
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
=======
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
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
                tryAgain();
                break;
            default:
                System.out.println("Enter only 1 2 or 3");
                chooseGame();
        }
<<<<<<< HEAD
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
=======
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
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