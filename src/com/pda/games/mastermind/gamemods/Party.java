package com.pda.games.mastermind.gamemods;

import com.pda.games.mastermind.comportment.Player;
import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.enums.WhoWin;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.MasterMindConfig;

public class Party extends MasterMind {


    public Party(Texts texts, Player playerOne, Player playerTwo, MasterMindConfig config) {
        super(texts, playerOne, playerTwo, config);
    }

    @Override
    public void init() {
        playerTwo.lineToFind();
        if (config.isDevMod()) {
            texts.printArray(playerTwo.getLineToFind());
        }
    }

    @Override
    public void round() {
        if (getRoundCount() > 1) {
            texts.memo(playerOne.getGuess(), playerOne.getAdversaryClue());
        }
        texts.askGuess(playerOne.getPlayerName());
        playerOne.guess();
        texts.printArray(playerOne.getGuess());
        texts.askClue(playerTwo.getPlayerName());
        boolean firstTime = true;
        do {
            if (!firstTime)
                texts.memo(playerTwo.getLineToFind());
            playerOne.setAdversaryClue(playerTwo.clue(playerTwo.getLineToFind(), playerOne.getGuess()));
            firstTime = false;
        } while (config.isAntiCheat() && playerOne.notVerifyClue(playerTwo.getLineToFind(), playerOne.getGuess(), playerOne.getAdversaryClue()));
        texts.printArray(playerOne.getAdversaryClue());
        checkingCorrespondenceGuessAndLineToFind(playerTwo.getLineToFind(), playerOne.getGuess());
    }

    public WhoWin defineWinner() {
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