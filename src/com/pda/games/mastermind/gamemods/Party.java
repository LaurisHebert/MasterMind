package com.pda.games.mastermind.gamemods;

import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.enums.WhoWin;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.Player;

public class Party extends MasterMind {


    public Party(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
    }

    @Override
    public void initialization() {
        playerTwo.lineToFind();
        if (MasterMind.devMod) {
            Texts.printArray(playerTwo.getLineToFind());
        }
    }

    @Override
    public void round() {
        if (getRoundCount() > 1) {
            Texts.memo(playerOne.getGuess(), playerOne.getAdversaryClue());
        }
        Texts.askGuess(playerOne.getPlayerName());
        playerOne.guess();
        Texts.printArray(playerOne.getGuess());
        Texts.askClue(playerTwo.getPlayerName());
        boolean firstTime = true;
        do {
            if (!firstTime)
                Texts.memo(playerTwo.getLineToFind());
            playerOne.setAdversaryClue(playerTwo.clue(playerTwo.getLineToFind(), playerOne.getGuess()));
            firstTime = false;
        } while (playerOne.notVerifyClue(playerTwo.getLineToFind(), playerOne.getGuess(), playerOne.getAdversaryClue()));
        Texts.printArray(playerOne.getAdversaryClue());
        correspondence(playerTwo.getLineToFind(), playerOne.getGuess());
    }

    public WhoWin winner() {
        if (isCorresponding()) {
            return WhoWin.PLAYER_ONE_WIN;
        }
        if (!isCorresponding() && !canPlay()) {
            return WhoWin.PLAYER_TWO_WIN;
        } else {
            return WhoWin.GAME_IN_PROGRESS;
        }
    }
}
