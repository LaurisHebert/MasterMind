package com.pda.games.MasterMind.GameMod;

import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.Config.MasterMind;
import com.pda.games.MasterMind.Config.WhoWin;

import java.util.Arrays;

public class Partie extends MasterMind implements GameStructure {

    public String playerOneName;
    public String playerTwoName;
    private boolean correspondence = false;

    public Partie(PlayerComportment playerOne, PlayerComportment playerTwo) {
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
        System.out.println("\nLet's the game begin !\n");
    }

    @Override
    public void round() {
        System.out.println("Round " + getRoundCount() + "/" + MasterMind.maximumOfRounds +
                "\n---------");
        System.out.println(playerTwoName + " guess :");
        int[] guess = playerTwo.guess();
        System.out.println(Arrays.toString(guess));
        String[] clue;
        do {
            clue = playerOne.clue(playerOne.lineToFind, guess);
        } while (!playerTwo.verifyClue(playerOne.lineToFind, playerTwo.guess, clue));
        playerTwo.otherPlayerClue = clue;
        System.out.println(Arrays.toString(clue) + "\n");
        correspondence();
    }

    private void correspondence() {
        if(canPlay()) {
            correspondence = Arrays.equals(playerOne.lineToFind, playerTwo.guess);
            setRoundCount(getRoundCount() + 1);
        }
    }

    @Override
    public boolean canPlay() {
        return !correspondence && getRoundCount() <= MasterMind.maximumOfRounds;
    }

    @Override
    public WhoWin whoWin() {
        if (correspondence && getRoundCount() <= MasterMind.maximumOfRounds) {
            return WhoWin.PLAYER_TWO_WIN;
        }
        if (!correspondence && !canPlay()) {
            return WhoWin.PLAYER_ONE_WIN;
        } else {
            return WhoWin.GAME_IN_PROGRESS;
        }
    }
}
