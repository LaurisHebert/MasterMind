package com.pda.games.MasterMind.GameMods;

import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Enums.WhoWin;
import com.pda.games.MasterMind.Structure.Game;
import com.pda.games.MasterMind.Structure.MasterMind;
import com.pda.games.MasterMind.Structure.Player;

import java.util.Arrays;

public class Partie extends MasterMind implements Game {

    public String playerOneName;
    public String playerTwoName;
    private boolean correspondence = false;

    public Partie(Player playerOne, Player playerTwo, String playerOneName, String playerTwoName) {
        super(playerOne, playerTwo);
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    @Override
    public void initialization() {
        Sout.initializationMessage(playerTwoName);
        playerTwo.lineToFind = playerTwo.lineToFind();
        Sout.launchPhrase();
    }

    @Override
    public void round() {
        Sout.actualRound(getRoundCount());
        if (getRoundCount() > 1) {
            Sout.memo(playerOne.guess, playerOne.adversaryClue);
        }
        Sout.askGuess(playerOneName);
        playerOne.guess();
        Sout.printArray(playerOne.guess);
        Sout.askClue(playerTwoName);
        boolean firstTime = true;
        do {
            if (!firstTime)
                Sout.memo(playerTwo.lineToFind);
            playerOne.adversaryClue = playerTwo.clue(playerTwo.lineToFind, playerOne.guess);
            firstTime = false;
        } while (playerOne.notVerifyClue(playerTwo.lineToFind, playerOne.guess, playerOne.adversaryClue));
        Sout.printArray(playerOne.adversaryClue);
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
