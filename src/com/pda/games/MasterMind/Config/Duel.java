package com.pda.games.MasterMind.Config;

import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.GameMod.GameStructure;
import com.pda.games.MasterMind.GameMod.Partie;

import java.util.Arrays;

public class Duel extends Partie implements GameStructure {
    private boolean correspondencePlayerOne;
    private boolean correspondencePlayerTwo;

    public Duel(PlayerComportment playerOne, PlayerComportment playerTwo) {
        super(playerOne, playerTwo);
    }


    @Override
    public void initialization() {
        System.out.println("Player one pseudo :");
        playerOneName = playerOne.playerName();
        System.out.println("\nPlayer two pseudo :");
        playerTwoName = playerTwo.playerName();
        System.out.println("\nInitialization secret numbers...");
        playerOne.lineToFind();
        playerTwo.lineToFind();
        System.out.println("\nLet's the game begin !\n");
    }

    private void playerOneTurn() {
        System.out.println("Round " + getRoundCount() + "/" + MasterMind.maximumOfRounds +
                "\n---------");
        System.out.println(playerOneName + " guess :");
        int[] guess = playerOne.guess();
        System.out.println(Arrays.toString(guess));
        String[] clue;
        do {
            clue = playerTwo.clue(playerTwo.lineToFind, guess);
        } while (!playerOne.verifyClue(playerTwo.lineToFind, playerOne.guess, clue));
        playerOne.otherPlayerClue = clue;
        System.out.println(Arrays.toString(clue) + "\n");
        correspondencePlayerOne();
    }

    private void playerTwoTurn() {
        System.out.println(playerTwoName + " guess :");
        int[] guess = playerTwo.guess();
        System.out.println(Arrays.toString(guess));
        String[] clue;
        do {
            clue = playerOne.clue(playerOne.lineToFind, guess);
        } while (!playerTwo.verifyClue(playerOne.lineToFind, playerTwo.guess, clue));
        playerTwo.otherPlayerClue = clue;
        System.out.println(Arrays.toString(clue) + "\n");
        correspondencePlayerTwo();
    }

    private void correspondencePlayerOne() {
        correspondencePlayerOne = Arrays.equals(playerTwo.lineToFind, playerOne.guess);
    }

    private void correspondencePlayerTwo() {
        correspondencePlayerTwo = Arrays.equals(playerOne.lineToFind, playerTwo.guess);
        setRoundCount(getRoundCount() + 1);
    }

    @Override
    public void round() {
        playerOneTurn();
        playerTwoTurn();
    }

    @Override
    public boolean canPlay() {
        return (!correspondencePlayerOne && !correspondencePlayerTwo) && getRoundCount() <= MasterMind.maximumOfRounds;
    }

    @Override
    public WhoWin whoWin() {
        if (getRoundCount() <= MasterMind.maximumOfRounds && (correspondencePlayerOne && correspondencePlayerTwo)) {
            return WhoWin.EX_ÆQUO_WIN;
        } else if (!canPlay() && (!correspondencePlayerOne && !correspondencePlayerTwo)) {
            return WhoWin.EX_ÆQUO_LOSE;
        } else if (getRoundCount() <= MasterMind.maximumOfRounds && (correspondencePlayerOne && !correspondencePlayerTwo)) {
            return WhoWin.PLAYER_ONE_WIN;
        } else if (getRoundCount() <= MasterMind.maximumOfRounds && (!correspondencePlayerOne && correspondencePlayerTwo)) {
            return WhoWin.PLAYER_TWO_WIN;
        } else return WhoWin.GAME_IN_PROGRESS;
    }


}
