package com.pda.games.MasterMind.GameMods;

import com.pda.games.MasterMind.Enums.WhoWin;
import com.pda.games.MasterMind.Model.MasterMind;
import com.pda.games.MasterMind.Model.Player;

public class Duel extends MasterMind {

    private final Party gameOne;
    private final Party gameTwo;
    private String playerOneName;
    private String playerTwoName;

    public Duel(Player playerOne, Player playerTwo, String playerOneName, String playerTwoName) {
        super(playerOne, playerTwo, playerOneName, playerTwoName);
        this.gameOne = new Party(playerOne, playerTwo, playerOneName, playerTwoName);
        this.gameTwo = new Party(playerTwo, playerOne, playerTwoName, playerOneName);
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }


    @Override
    public void initialization() {
        gameOne.initialization();
        gameTwo.initialization();
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
    public WhoWin whoWin() {
        if (getRoundCount() <= getMaximumOfRounds() && (gameOne.isCorresponding() && gameTwo.isCorresponding())) {
            return WhoWin.EX_AEQUO_WIN;
        } else if (!canPlay() && (!gameOne.isCorresponding() && !gameTwo.isCorresponding())) {

            return WhoWin.EX_AEQUO_LOSE;
        } else if (getRoundCount() <= getMaximumOfRounds() && (gameOne.isCorresponding() && !gameTwo.isCorresponding())) {

            return WhoWin.PLAYER_ONE_WIN;
        } else if (getRoundCount() <= getMaximumOfRounds() && (!gameOne.isCorresponding() && gameTwo.isCorresponding())) {
            return WhoWin.PLAYER_TWO_WIN;
        } else return WhoWin.GAME_IN_PROGRESS;
    }

    @Override
    public String getPlayerOneName() {
        return playerOneName;
    }

    @Override
    public String getPlayerTwoName() {
        return playerTwoName;
    }

    @Override
    public int getRoundCount() {
        return gameTwo.getRoundCount();
    }

}
