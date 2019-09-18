package com.pda.games;

import com.pda.games.MasterMind.Comportment.BotComportment;
import com.pda.games.MasterMind.Comportment.HumanComportment;
import com.pda.games.MasterMind.Config.Game;
import com.pda.games.MasterMind.GameMod.Challenger;
import com.pda.games.MasterMind.GameMod.Duel;
import com.pda.games.MasterMind.GameMode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Mastermind !");
        initialisation();
    }

    private static void initialisation() {
        GameMode gameMode = choosingMode();
        Game game = selectGameMode(gameMode);
        do {
            game.round();
        }while (game.canPlay());

    }

    private static GameMode choosingMode() {
        System.out.println("Please choose your game mod:" +
                "\n1- For select challenger ( you have to find a secrets digits generated by the computer ) " +
                "\n2- For select defender ( the computer try to find your secrets digits )" +
                "\n3- For select duel ( the two previous mods simultaneously");
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

    private static Game selectGameMode(GameMode gameMode) {
        switch (gameMode) {
            case CHALLENGER:
                return new Challenger(new BotComportment(), new HumanComportment());
            case DEFENDER:
                return new Challenger(new HumanComportment(), new BotComportment()) {
                };
            case DUEL:
                return new Duel();
            default:
                return null;
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
