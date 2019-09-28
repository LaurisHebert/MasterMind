package com.pda.games;

import com.pda.games.MasterMind.Comportment.BotComportment;
import com.pda.games.MasterMind.Comportment.HumanComportment;
import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.Config.Duel;
import com.pda.games.MasterMind.Config.GameMod;
import com.pda.games.MasterMind.GameMod.Partie;

import java.util.Scanner;


// crée fichier configuration
// mettre la java doc quand tous sera fini

public class Main {
    // crée joueur dans initialiastion et dans retry dans le cas d'un retry, l'enlever de selectNumberOfHumanAndName()
    private static int numberOfHuman;
    private static PlayerComportment playerOne;
    private static String playerOneName;
    private static PlayerComportment playerTwo;
    private static String playerTwoName;

    public static void main(String[] args) {
        System.out.println("Welcome in mastermind !" +
                "\n-----------------------");
        numberOfHuman();
        playerNames();
        initialisation();
    }

    /**
     * Used for know how many human we have
     */
    private static void numberOfHuman() {
        System.out.println("\nHow many player human we have ?");
        boolean firstLoop = true;
        do {
            if (!firstLoop)
                System.out.println("You can choose only between 0, 1 or 2");
            numberOfHuman = readInt();
            firstLoop = false;
        } while (numberOfHuman < 0 || numberOfHuman > 2);
    }

    /**
     * Used for named the players with corresponding entry
     */
    private static void playerNames() {
        switch (numberOfHuman) {
            case 0:
                System.out.println("Player one pseudo :");
                playerOneName = BotComportment.playerName();
                System.out.println("\nPlayer two pseudo :");
                playerTwoName = BotComportment.playerName();
                break;
            case 1:
                System.out.println("Player one pseudo :");
                playerOneName = HumanComportment.playerName();
                System.out.println("\nPlayer two pseudo :");
                playerTwoName = BotComportment.playerName();
                break;
            case 2:
                System.out.println("Player one pseudo :");
                playerOneName = HumanComportment.playerName();
                System.out.println("\nPlayer two pseudo :");
                playerTwoName = HumanComportment.playerName();
                break;
        }
    }

    /**
     * Initialise the game
     */
    private static void initialisation() {
        GameMod gameMode = choosingMod();
        Partie game = selectGameMod(gameMode);
        executeGame(game);
        boolean loop = true;
        while (loop) {
            Boolean again = tryAgain();
            if (again == null) {
                initialisation();
            } else if (again) {
                executeGame(selectGameMod(gameMode));
                loop = true;
            } else {
                System.out.println("CYA");
                loop = false;
            }
        }
    }

    /**
     * @return the selected game mod
     */
    private static GameMod choosingMod() {
        System.out.println("\nPlease select your game mod:" +
                "\n1- Challenger ( player one try to guess the player two hidden number ) " +
                "\n2- Defender ( player two try to guess the player one hidden number )" +
                "\n3- Duel ( both players try to find the opponent's hidden number )");
        boolean error;
        int entry;
        do {
            error = false;
            entry = readInt();
            if (entry < 1 || entry > 3) {
                error = true;
                System.out.println("only 1 2 or 3 are accepted");
            }
        } while (error);
        switch (entry) {
            case 1:
                return GameMod.CHALLENGER;
            case 2:
                return GameMod.DEFENDER;
            case 3:
                return GameMod.DUEL;
            default:
                throw new IllegalStateException("Unexpected value: " + entry);
        }
    }

    /**
     * use the gameMod for generate the object with corresponding parameter
     * @param gameMod the selected game mod
     * @return the object used to generate the game
     */
    private static Partie selectGameMod(GameMod gameMod) {
        selectPlayerComportment();
        switch (gameMod) {
            case CHALLENGER:
                return new Partie(playerTwo, playerOne, playerTwoName, playerOneName);
            case DEFENDER:
                return new Partie(playerOne, playerTwo, playerOneName, playerTwoName);
            case DUEL:
                return new Duel(playerOne, playerTwo, playerOneName, playerTwoName);
            default:
                throw new IllegalStateException("Unexpected value: ");
        }
    }

    /**
     * Used for know if the player is human or IA
     */
    private static void selectPlayerComportment() {
        switch (numberOfHuman) {
            case 0:
                playerOne = new BotComportment();
                playerTwo = new BotComportment();
                break;
            case 1:
                playerOne = new HumanComportment();
                playerTwo = new BotComportment();
                break;
            case 2:
                playerOne = new HumanComportment();
                playerTwo = new HumanComportment();
                break;
            default:
                System.out.println("Mastermind can only be played by two players, so the answer can only be between zero and two");
                break;
        }
    }

    /**
     * execute the course of the game
     * @param game used to know which game to launch
     */
    private static void executeGame(Partie game) {
        game.initialization();
        do {
            game.round();
        } while (game.canPlay());
        switch (game.whoWin()) {
            case PLAYER_ONE_WIN:
                System.out.println(game.playerOneName + " Win !");
                break;
            case PLAYER_TWO_WIN:
                System.out.println(game.playerTwoName + " Win! ");
                break;
            case EX_ÆQUO_WIN:
                System.out.println("EveryOne Win !");
                break;
            case EX_ÆQUO_LOSE:
                System.out.println("No winner :/");
                break;
        }
    }

    private static Boolean tryAgain() {
        // peut-être enlever les Scanner d'ici ?
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------" +
                "\nIf you want retry \"R\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
        while (true) {
            String tryAgain = sc.nextLine();
            switch (tryAgain.toLowerCase()) {
                case "r":
                    return true;
                case "h":
                    return null;
                case "q":
                    return false;
                default:
                    System.out.println("I'm sorry i don't understand");
                    break;
            }
        }
    }

    private static int readInt() {
        Scanner sc = new Scanner(System.in);
        boolean error;
        int i = -1;
        do {
            error = false;
            try {
                i = sc.nextInt();
                if (i < 0) {
                    System.out.println(i + "is to low");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("input error");
                error = true;
                sc.next();
            }
        } while (error);
        return i;
    }
}
