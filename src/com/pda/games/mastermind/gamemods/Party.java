package com.pda.games.mastermind.gamemods;

import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.enums.Winner;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.MasterMindConfig;
import com.pda.games.mastermind.model.Player;

public class Party extends MasterMind {


    public Party(Player playerOne, Player playerTwo, MasterMindConfig config) {
        super(playerOne, playerTwo, config);
    }

    @Override
    public void initialization() {
        playerTwo.lineToFind();
        if (config.isDevMod()) {
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
        checkingCorrespondenceGuessAndLineToFind(playerTwo.getLineToFind(), playerOne.getGuess());
    }

    public Winner defineWinner() {
        if (isCorresponding()) {
            return Winner.PLAYER_ONE_WIN;
        }
        if (!isCorresponding() && !canPlay()) {
            return Winner.PLAYER_TWO_WIN;
        } else {
            return Winner.GAME_IN_PROGRESS;
        }
    }
}