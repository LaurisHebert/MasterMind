package com.pda.games.mastermind.comportment.bot;

import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.game.config.MasterMindConfig;

public class MediumBotPlayer extends BotPlayer {

    public MediumBotPlayer(Texts texts, MasterMindConfig config, String playerName) {
        super(texts, config, playerName);
    }

    @Override
    public void guess() {
        if (adversaryClue == null) {
            guess = buildArrayInt();
        } else {
            for (int i = 0; i < config.getSizeOfLineToFind(); i++) {
                switch (adversaryClue[i]) {
                    case "+":
                        lowestRange[i] = guess[i];
                        guess[i] = (highestRange[i] - lowestRange[i]) / 2 + lowestRange[i];
                        break;
                    case "-":
                        highestRange[i] = guess[i];
                        guess[i] = (highestRange[i] - lowestRange[i]) / 2 + lowestRange[i];
                        break;
                    case "=":
                        break;
                }
            }
        }
    }
}