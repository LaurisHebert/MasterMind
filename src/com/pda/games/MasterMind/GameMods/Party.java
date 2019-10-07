package com.pda.games.MasterMind.GameMods;

import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Enums.WhoWin;
import com.pda.games.MasterMind.Model.MasterMind;
import com.pda.games.MasterMind.Model.Player;

public class Party extends MasterMind {


    public Party(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public void initialization() {
        playerTwo.lineToFind();
    }

    @Override
    public void round() {
        if (getRoundCount() > 1) {
            Sout.memo(playerOne.getGuess(), playerOne.getAdversaryClue());
        }
        Sout.askGuess(playerOne.getPlayerName());
        playerOne.guess();
        Sout.printArray(playerOne.getGuess());
        Sout.askClue(playerTwo.getPlayerName());
        boolean firstTime = true;
        do {
            if (!firstTime)
                Sout.memo(playerTwo.getLineToFind());
            playerOne.setAdversaryClue(playerTwo.clue(playerTwo.getLineToFind(), playerOne.getGuess()));
            firstTime = false;
        } while (playerOne.notVerifyClue(playerTwo.getLineToFind(), playerOne.getGuess(), playerOne.getAdversaryClue()));
        Sout.printArray(playerOne.getAdversaryClue());
        correspondence(playerTwo.getLineToFind(), playerOne.getGuess());
    }

    public WhoWin whoWin() {
        if (isCorresponding() && getRoundCount() <= MasterMind.getMaximumOfRounds()) {
            return WhoWin.PLAYER_ONE_WIN;
        }
        if (!isCorresponding() && !canPlay()) {
            return WhoWin.PLAYER_TWO_WIN;
        } else {
            return WhoWin.GAME_IN_PROGRESS;
        }
    }
}
