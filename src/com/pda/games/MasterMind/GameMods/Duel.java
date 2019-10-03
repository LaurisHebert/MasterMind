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
        playerOne.lineToFind = playerOne.lineToFind();
        Sout.initializationMessage(playerTwoName);
        playerTwo.lineToFind = playerTwo.lineToFind();
        Sout.launchPhrase();
    }

    private void playerOneTurn() {
        Sout.actualRound(getRoundCount());
        if (getRoundCount() > 1) {
            Sout.memo(playerOne.guess, playerOne.adversaryClue);
        }
        Sout.askGuess(playerOneName);
        Sout.printArray(playerOne.guess());
        Sout.askClue(playerTwoName);
        boolean firstTime = true;
        do {
            if (!firstTime)
                Sout.memo(playerTwo.lineToFind);
            playerOne.adversaryClue = playerTwo.clue(playerTwo.lineToFind, playerOne.guess);
            firstTime = false;
        } while (playerOne.notVerifyClue(playerTwo.lineToFind, playerOne.guess, playerOne.adversaryClue));
        Sout.printArray(playerOne.adversaryClue);
        correspondencePlayerOne();
    }

    private void playerTwoTurn() {
        Sout.askGuess(playerTwoName);
        if (getRoundCount() > 1) {
            Sout.memo(playerTwo.guess, playerTwo.adversaryClue);
        }
        playerTwo.guess();
        Sout.printArray(playerTwo.guess);
        Sout.askClue(playerOneName);
        boolean firstTime = true;
        do {
            if (!firstTime)
                Sout.memo(playerOne.lineToFind);
            playerTwo.adversaryClue = playerOne.clue(playerOne.lineToFind, playerTwo.guess);
            firstTime = false;
        } while (playerTwo.notVerifyClue(playerOne.lineToFind, playerTwo.guess, playerTwo.adversaryClue));
        Sout.printArray(playerTwo.adversaryClue);
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
