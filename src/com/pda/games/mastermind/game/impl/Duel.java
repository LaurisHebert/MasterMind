package com.pda.games.mastermind.game.impl;

import com.pda.games.mastermind.comportment.Player;
import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.game.enums.WhoWin;
import com.pda.games.mastermind.game.MasterMind;
import com.pda.games.mastermind.game.config.MasterMindConfig;

public class Duel extends MasterMind {

    private final Party gameOne;
    private final Party gameTwo;

    public Duel(Texts texts, Player playerOne, Player playerTwo, MasterMindConfig config) {
        super(texts, playerOne, playerTwo, config);
        this.gameOne = new Party(texts, playerOne, playerTwo, config);
        this.gameTwo = new Party(texts, playerTwo, playerOne, config);
    }

    @Override
    public void init() {
        gameTwo.init();
        gameOne.init();
    }

    @Override
    public void round() {
        gameOne.round();
        gameTwo.round();
    }

    @Override
    public boolean canPlay() {
        return (gameOne.canPlay() && gameTwo.canPlay());
    }

    @Override
    public WhoWin defineWinner() {
        if (gameOne.isCorresponding() && gameTwo.isCorresponding()) {
            return WhoWin.EX_AEQUO_WIN;
        } else if (!canPlay() && (!gameOne.isCorresponding() && !gameTwo.isCorresponding())) {
            return WhoWin.EX_AEQUO_LOSE;
        } else if (gameOne.isCorresponding() && !gameTwo.isCorresponding()) {
            return WhoWin.PLAYER_ONE_WIN;
        } else if (!gameOne.isCorresponding() && gameTwo.isCorresponding()) {
            return WhoWin.PLAYER_TWO_WIN;
        } else return WhoWin.GAME_IN_PROGRESS;
    }

    @Override
    public int getRoundCount() {
        return gameTwo.getRoundCount();
    }

}
