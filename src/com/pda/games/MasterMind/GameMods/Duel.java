package com.pda.games.MasterMind.GameMods;

import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Enums.WhoWin;
import com.pda.games.MasterMind.Structure.Game;
import com.pda.games.MasterMind.Structure.MasterMind;
import com.pda.games.MasterMind.Structure.Player;

import java.util.Arrays;

public class Duel extends Partie implements Game {
    private boolean guessPlayerOneCorresponding;
    private boolean guessPlayerTwoCorresponding;

    public Duel(Player playerOne, Player playerTwo, String playerOneName, String playerTwoName) {
        super(playerOne, playerTwo, playerOneName, playerTwoName);
    }


    @Override
    public void initialization() {
        Sout.initializationMessage(playerOneName);
        playerOne.lineToFind();
        Sout.initializationMessage(playerTwoName);
        playerTwo.lineToFind();
        Sout.launchPhrase();
    }

    private void playerOneTurn() {
        Sout.actualRound(getRoundCount());
        if (getRoundCount() > 1) {
            Sout.reminder(playerOne.guess, playerOne.otherPlayerClue);
        }
        Sout.askGuess(playerOneName);
        int[] guess = playerOne.guess();
        Sout.printArray(guess);
        String[] clue;
        Sout.askClue(playerTwoName);
        do {
            clue = playerTwo.clue(playerTwo.lineOfDigits, guess);
        } while (playerOne.notVerifyClue(playerTwo.lineOfDigits, playerOne.guess, clue));
        playerOne.otherPlayerClue = clue;
        Sout.printArray(clue);
        correspondencePlayerOne();
    }

    private void playerTwoTurn() {
        Sout.askGuess(playerTwoName);
        if (getRoundCount() > 1) {
            Sout.reminder(playerTwo.guess, playerTwo.otherPlayerClue);
        }
        int[] guess = playerTwo.guess();
        Sout.printArray(guess);
        String[] clue;
        Sout.askClue(playerOneName);
        do {
            clue = playerOne.clue(playerOne.lineOfDigits, guess);
        } while (playerTwo.notVerifyClue(playerOne.lineOfDigits, playerTwo.guess, clue));
        playerTwo.otherPlayerClue = clue;
        Sout.printArray(clue);
        correspondencePlayerTwo();
    }


    private void correspondencePlayerOne() {
        guessPlayerOneCorresponding = Arrays.equals(playerTwo.lineOfDigits, playerOne.guess);
    }

    private void correspondencePlayerTwo() {
        guessPlayerTwoCorresponding = Arrays.equals(playerOne.lineOfDigits, playerTwo.guess);
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
