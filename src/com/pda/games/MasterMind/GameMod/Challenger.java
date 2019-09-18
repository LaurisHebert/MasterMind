package com.pda.games.MasterMind.GameMod;

import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.Config.MasterMind;

import java.util.Arrays;

public class Challenger extends MasterMind {

    private static PlayerComportment playerOne;
    private static PlayerComportment playerTwo;

    private static String playerOneName;
    private static String playerTwoName;

    private int[] guess = new int[MasterMind.sizeOfLineToFind];
    private String[] clue;

    public Challenger(PlayerComportment playerOne, PlayerComportment playerTwo) {
        super(playerOneName, playerTwoName, playerOne.lineToFind(MasterMind.sizeOfLineToFind, MasterMind.minimalValue, MasterMind.maximumValue));
        Challenger.playerOneName = playerOne.playerName();
        Challenger.playerTwoName = playerTwo.playerName();
        Challenger.playerOne = playerOne;
        Challenger.playerTwo = playerTwo;
    }

    public boolean canPlay() {
        return !correspondence && getRoundCount() <= maximumOfRounds;
    }

    protected int[] guess() {
        return playerTwo.guess(sizeOfLineToFind, MasterMind.minimalValue, MasterMind.maximumValue, getRoundCount(), clue);
    }

    private void clue() {
        clue = playerOne.clue(MasterMind.sizeOfLineToFind, lineToFind, guess);
    }

    @Override
    public void round() {
        System.out.println("Round :" + getRoundCount() + "/" + maximumOfRounds);
        guess = guess();
        System.out.println(Arrays.toString(guess));
        clue = playerOne.clue(MasterMind.sizeOfLineToFind, lineToFind, guess);
        System.out.println(Arrays.toString(clue));
        if (checkCorresponding(guess) && getRoundCount() <= MasterMind.maximumOfRounds)
            System.out.println(playerOneName + " has win");
        else if (!checkCorresponding(guess) && !canPlay())
            System.out.println(playerTwoName + " has win");
    }
}
