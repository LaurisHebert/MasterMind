package com.pda.games.MasterMind.GameMod;

import com.pda.games.MasterMind.Config.WhoWin;

public interface GameStructure {
    void initialization();
    void round();
    boolean canPlay();
    WhoWin whoWin();
}

