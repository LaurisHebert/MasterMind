package com.pda.games.MasterMind.Config;

import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.GameMod.GameStructure;
import com.pda.games.MasterMind.GameMod.Partie;

import java.util.Arrays;

public class Duel extends Partie implements GameStructure {
    private boolean guessPlayerOneCorresponding;
    private boolean guessPlayerTwoCorresponding;

    public Duel(PlayerComportment playerOne, PlayerComportment playerTwo, String playerOneName, String playerTwoName) {
        super(playerOne, playerTwo, playerOneName, playerTwoName);
    }


    @Override
    public void initialization() {
        System.out.println("\nInitialization of secret digits of " + playerOneName + ":");
        playerOne.lineToFind();
        System.out.println("\nInitialization of secret digits of " + playerTwoName + ":");
        playerTwo.lineToFind();
        System.out.println("\nLet's the game begin !\n");
    }

    private void playerOneTurn() {
        System.out.println("Round " + getRoundCount() + "/" + MasterMind.maximumOfRounds +
                "\n---------");
        if (getRoundCount() > 1) {
            System.out.println("Precedent guess : " + Arrays.toString(playerOne.guess) +
                    "\nPrecedent clue : " + Arrays.toString(playerOne.otherPlayerClue));
        }
        System.out.println(playerOneName + " guess :");
        int[] guess = playerOne.guess();
        System.out.println(Arrays.toString(guess));
        String[] clue;
        do {
            clue = playerTwo.clue(playerTwo.lineToFind, guess);
        } while (playerOne.verifyClue(playerTwo.lineToFind, playerOne.guess, clue));
        playerOne.otherPlayerClue = clue;
        System.out.println(Arrays.toString(clue) + "\n");
        correspondencePlayerOne();
    }

    private void playerTwoTurn() {
        System.out.println(playerTwoName + " guess :");
        if (getRoundCount() > 1) {
            System.out.println("Precedent guess : " + Arrays.toString(playerTwo.guess) +
                    "\nPrecedent clue : " + Arrays.toString(playerTwo.otherPlayerClue));
        }
        int[] guess = playerTwo.guess();
        System.out.println(Arrays.toString(guess));
        String[] clue;
        do {
            clue = playerOne.clue(playerOne.lineToFind, guess);
        } while (playerTwo.verifyClue(playerOne.lineToFind, playerTwo.guess, clue));
        playerTwo.otherPlayerClue = clue;
        System.out.println(Arrays.toString(clue) + "\n");
        correspondencePlayerTwo();
    }


    private void correspondencePlayerOne() {
        guessPlayerOneCorresponding = Arrays.equals(playerTwo.lineToFind, playerOne.guess);
    }

    private void correspondencePlayerTwo() {
        guessPlayerTwoCorresponding = Arrays.equals(playerOne.lineToFind, playerTwo.guess);
        setRoundCount(getRoundCount() + 1);
    }

    @Override
    public void round() {
        playerOneTurn();
        playerTwoTurn();
    }

    @Override
    public boolean canPlay() {
        return (!guessPlayerOneCorresponding && !guessPlayerTwoCorresponding) && getRoundCount() <= MasterMind.maximumOfRounds;
    }

    @Override
    public WhoWin whoWin() {
        if (getRoundCount() <= MasterMind.maximumOfRounds && (guessPlayerOneCorresponding && guessPlayerTwoCorresponding)) {
            return WhoWin.EX_ÆQUO_WIN;
        } else if (!canPlay() && (!guessPlayerOneCorresponding && !guessPlayerTwoCorresponding)) {
            return WhoWin.EX_ÆQUO_LOSE;
        } else if (getRoundCount() <= MasterMind.maximumOfRounds && (guessPlayerOneCorresponding && !guessPlayerTwoCorresponding)) {
            return WhoWin.PLAYER_ONE_WIN;
        } else if (getRoundCount() <= MasterMind.maximumOfRounds && (!guessPlayerOneCorresponding && guessPlayerTwoCorresponding)) {
            return WhoWin.PLAYER_TWO_WIN;
        } else return WhoWin.GAME_IN_PROGRESS;
    }


}
