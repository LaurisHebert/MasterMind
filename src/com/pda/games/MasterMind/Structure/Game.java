package com.pda.games.MasterMind.Structure;

import com.pda.games.MasterMind.Enums.WhoWin;

public interface Game {
    void initialization();

    void round();

    boolean canPlay();

    WhoWin whoWin();
}