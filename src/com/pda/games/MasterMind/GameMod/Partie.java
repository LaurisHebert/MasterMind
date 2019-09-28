package com.pda.games.MasterMind.GameMod;

import com.pda.games.MasterMind.Comportment.PlayerComportment;
import com.pda.games.MasterMind.Config.MasterMind;
import com.pda.games.MasterMind.Config.WhoWin;

import java.util.Arrays;

public class Partie extends MasterMind implements GameStructure {

    public String playerOneName;
    public String playerTwoName;
    private boolean correspondence = false;

    public Partie(PlayerComportment playerOne, PlayerComportment playerTwo, String playerOneName, String playerTwoName) {
        super(playerOne, playerTwo);
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    @Override
    public void initialization() {
        System.out.println("\nInitialization of secret digits of " + playerTwoName + ":");
        playerTwo.lineToFind();
        System.out.println("\nLet's the game begin !\n");
    }

    @Override
    public void round() {
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
        correspondence();
    }

    private void correspondence() {
        if (canPlay()) {
            correspondence = Arrays.equals(playerTwo.lineToFind, playerOne.guess);
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
            return WhoWin.PLAYER_ONE_WIN;
        }
        if (!correspondence && !canPlay()) {
            return WhoWin.PLAYER_TWO_WIN;
        } else {
            return WhoWin.GAME_IN_PROGRESS;
        }
    }
}
