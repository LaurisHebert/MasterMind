package com.pda.games;

import com.pda.games.mastermind.comportment.BotComportment;
import com.pda.games.mastermind.comportment.HumanComportment;
import com.pda.games.mastermind.entry.Errors;
import com.pda.games.mastermind.entry.Sc;
import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.enums.GameMod;
import com.pda.games.mastermind.enums.WhoWin;
import com.pda.games.mastermind.gamemods.Duel;
import com.pda.games.mastermind.gamemods.Party;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.Player;

public class Main {

    private static int numberOfHuman;
    private static Player playerOne;
    private static String playerOneName;
    private static Player playerTwo;
    private static String playerTwoName;

    public static void main(String[] args) {
        Texts.gameIntroduce();
        Texts.rules(MasterMind.sizeOfLineToFind);
        if (MasterMind.devMod) {
            numberOfHuman();
        } else {
            numberOfHuman = 1;
        }
        playerNames();
        initialisation();
    }

    /**
     * Used in dev mod for testing program
     */
    private static void numberOfHuman() {
        Texts.askingHowManyPlayer();
        boolean firstLoop = true;
        do {
            if (!firstLoop) {
                Errors.onlyBetween(0, 2);
            }
            numberOfHuman = Sc.nextPositiveInt();
            firstLoop = false;
        } while (numberOfHuman < 0 || numberOfHuman > 2);
    }

    /**
     * Used for named the players with corresponding entry
     */
    private static void playerNames() {
        switch (numberOfHuman) {
            case 0:
                Texts.pseudoEntry(1);
                playerOneName = BotComportment.playerName();
                Texts.pseudoEntry(2);
                playerTwoName = BotComportment.playerName();
                break;
            case 1:
                Texts.pseudoEntry(1);
                playerOneName = HumanComportment.playerName();
                Texts.pseudoEntry(2);
                playerTwoName = BotComportment.playerName();
                break;
            case 2:
                Texts.pseudoEntry(1);
                playerOneName = HumanComportment.playerName();
                Texts.pseudoEntry(2);
                playerTwoName = HumanComportment.playerName();
                break;
        }
    }

    /**
     * Initialise the game
     */
    private static void initialisation() {
        GameMod gameMode = choosingMod();
        MasterMind game = selectGameMod(gameMode);
        executeGame(game, gameMode);
        boolean loop = true;
        while (loop) {
            Boolean again = tryAgain();
            if (again == null) {
                initialisation();
            } else if (again) {
                executeGame(selectGameMod(gameMode), gameMode);
                loop = true;
            } else {
                Texts.end();
                loop = false;
            }
        }
    }

    /**
     * @return the selected game mod
     */
    private static GameMod choosingMod() {
        Texts.explainGameMod();
        boolean error;
        int entry;
        do {
            error = false;
            entry = Sc.nextPositiveInt();
            if (entry < 1 || entry > 3) {
                error = true;
                Errors.onlyBetween(1, 3);
            }
        } while (error);
        switch (entry) {
            case 1:
                return GameMod.CHALLENGER;
            case 2:
                return GameMod.DEFENDER;
            case 3:
                return GameMod.DUEL;
            default:
                throw new IllegalStateException("Unexpected value: " + entry);
        }
    }

    /**
     * use the gameMod for generate the object with corresponding parameter
     *
     * @param gameMod the selected game mod
     * @return the object used to generate the game
     */
    private static MasterMind selectGameMod(GameMod gameMod) {
        selectPlayerComportment();
        switch (gameMod) {
            case CHALLENGER:
                return new Party(playerOne, playerTwo);
            case DEFENDER:
                return new Party(playerTwo, playerOne);
            case DUEL:
                return new Duel(playerOne, playerTwo);
            default:
                throw new IllegalStateException("Unexpected value: ");
        }
    }

    /**
     * Used for know if the player is human or IA
     */
    private static void selectPlayerComportment() {
        switch (numberOfHuman) {
            case 0:
                playerOne = new BotComportment(playerOneName);
                playerTwo = new BotComportment(playerTwoName);
                break;
            case 1:
                playerOne = new HumanComportment(playerOneName);
                playerTwo = new BotComportment(playerTwoName);
                break;
            case 2:
                playerOne = new HumanComportment(playerOneName);
                playerTwo = new HumanComportment(playerTwoName);
                break;
            default:
                Errors.playerMax();
                break;
        }
    }

    /**
     * execute the course of the game
     *
     * @param game used to know which game to launch
     */
    private static void executeGame(MasterMind game, GameMod gameMod) {
        game.initialization();
        Texts.launchPhrase();
        do {
            Texts.actualRound(game.getRoundCount());
            game.round();
        } while (game.canPlay());
        switch (game.winner()) {
            case PLAYER_ONE_WIN:
                Texts.win(game.getPlayerOneName());
                break;
            case PLAYER_TWO_WIN:
                Texts.win(game.getPlayerTwoName());
                break;
            case EX_AEQUO_WIN:
                Texts.Equally("win");
                break;
            case EX_AEQUO_LOSE:
                Texts.Equally("lose");
                break;
        }
        switch (gameMod) {
            case CHALLENGER:
                if (game.winner() == WhoWin.PLAYER_TWO_WIN)
                    Texts.lineToFind(playerTwoName, playerTwo.getLineToFind());
                break;
            case DEFENDER:
                if (game.winner() == WhoWin.PLAYER_TWO_WIN)
                    Texts.lineToFind(playerOneName, playerOne.getLineToFind());
                break;
            case DUEL:
                if (game.winner() == WhoWin.PLAYER_ONE_WIN)
                    Texts.lineToFind(playerOneName, playerOne.getLineToFind());
                if (game.winner() == WhoWin.PLAYER_TWO_WIN)
                    Texts.lineToFind(playerTwoName, playerTwo.getLineToFind());
                if (game.winner() == WhoWin.EX_AEQUO_LOSE)
                    Texts.lineToFind(playerOneName, playerOne.getLineToFind(), playerTwoName, playerTwo.getLineToFind());
                break;
        }
    }

    private static Boolean tryAgain() {
        Texts.tryAgainMenu();
        while (true) {
            String tryAgain = Sc.nextLine();
            switch (tryAgain.toLowerCase()) {
                case "r":
                    return true;
                case "h":
                    return null;
                case "q":
                    return false;
                default:
                    Errors.onlySymbol();
                    break;
            }
        }
    }

}
