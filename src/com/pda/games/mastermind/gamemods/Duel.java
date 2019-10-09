package com.pda.games.mastermind.gamemods;

import com.pda.games.mastermind.enums.WhoWin;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.Player;

public class Duel extends MasterMind {

    private final Party gameOne;
    private final Party gameTwo;

    public Duel(Player playerOne, Player playerTwo) {
        super(playerOne, playerTwo);
        this.gameOne = new Party(playerOne, playerTwo);
        this.gameTwo = new Party(playerTwo, playerOne);
    }

    @Override
    public void initialization() {
        gameTwo.initialization();
        gameOne.initialization();
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
    public WhoWin winner() {
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