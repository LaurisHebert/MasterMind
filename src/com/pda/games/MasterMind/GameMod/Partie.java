package com.pda.games.MasterMind.GameMod;

import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.Config.MasterMind;

import java.util.Arrays;

public class Partie extends MasterMind {

    private String playerOneName;
    private String playerTwoName;


    public Partie(PlayerComportment playerOne, PlayerComportment playerTwo) {
        super(playerOne, playerTwo);
    }

    public void initialization() {
        this.playerOneName = playerOne.playerName();
        this.playerTwoName = playerTwo.playerName();
        System.out.println("Initialization secret numbers...\n");
        playerOne.lineToFind();
        System.out.println("Let's the game begin !");
    }

    public void round() {
        System.out.println("Round :" + getRoundCount() + "/" + MasterMind.maximumOfRounds);
        System.out.println(Arrays.toString(playerOne.lineToFind));
        System.out.println(Arrays.toString(playerTwo.lineToFind));

        System.out.println("réponse:");
        int[] guess = playerTwo.guess();
        System.out.println(Arrays.toString(guess));
        String[] clue;
        do {
            clue = playerOne.clue(guess,playerTwo.lineToFind);
        } while (!playerTwo.verifyClue(guess,playerTwo.lineToFind, clue));
            System.out.println(Arrays.toString(clue));
    }
}
