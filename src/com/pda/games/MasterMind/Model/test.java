package com.pda.games.MasterMind.Model;

import com.pda.games.MasterMind.Enums.WhoWin;

public interface test {
    void initialization();

    void round();

    WhoWin whoWin();
}
