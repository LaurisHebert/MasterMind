package com.pda.games;

import com.pda.games.MasterMind.Comportment.BotComportment;
import com.pda.games.MasterMind.Comportment.HumanComportment;
import com.pda.games.MasterMind.Config.Duel;
import com.pda.games.MasterMind.Config.GameMode;
import com.pda.games.MasterMind.GameMod.Partie;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // gérer qui est joueur un et deux dans le main ? pour pouvoir crée nos propres mods ?
        // mettre variable nom de joueur ici pour ne pas a avoir à l'écrire à chaques games ?
        // crée fichier configuration
        // mettre la java doc quand tous sera fini
        System.out.println("Welcome to \"Mastermind\" !" +
                "\n-------------------------");
        initialisation();
    }

    private static void initialisation() {
        boolean loop = true;
        GameMode gameMode = choosingMode();
        Partie game = selectGameMode(gameMode);
        executeGame(game);
        while (loop) {
            Boolean again = tryAgain();
            if (again == null) {
                initialisation();
            } else if (again) {
                executeGame(selectGameMode(gameMode));
                loop = true;
            } else {
                System.out.println("CYA");
                loop = false;
            }
        }
    }


    private static Boolean tryAgain() {
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

    private static GameMode choosingMode() {
        System.out.println("Please select your game mod:" +
                "\n1- Challenger ( you have to find a secrets digits generated by the computer ) " +
                "\n2- Defender ( the computer try to find your secrets digits )" +
                "\n3- Duel ( the two previous mods simultaneously )");
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
                return GameMode.CHALLENGER;
            case 2:
                return GameMode.DEFENDER;
            case 3:
                return GameMode.DUEL;
            default:
                throw new IllegalStateException("Unexpected value: " + entry);
        }
    }

    private static Partie selectGameMode(GameMode gameMode) {
        switch (gameMode) {
            case CHALLENGER:
                return new Partie(new BotComportment(), new HumanComportment());
            case DEFENDER:
                return new Partie(new HumanComportment(), new BotComportment());
            case DUEL:
                return new Duel(new HumanComportment(), new BotComportment());
            default:
                throw new IllegalStateException("Unexpected value: ");
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
}