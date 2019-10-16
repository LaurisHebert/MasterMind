package com.pda.games;

import com.pda.games.mastermind.comportment.BotPlayer;
import com.pda.games.mastermind.comportment.HumanPlayer;
import com.pda.games.mastermind.entry.Errors;
import com.pda.games.mastermind.entry.Sc;
import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.enums.GameMod;
import com.pda.games.mastermind.enums.WhoWin;
import com.pda.games.mastermind.gamemods.Duel;
import com.pda.games.mastermind.gamemods.Party;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.MasterMindConfig;
import com.pda.games.mastermind.model.Player;

public class Main {

    private static final MasterMindConfig config = new MasterMindConfig();
    private static int numberOfHuman;
    private static Player playerOne;
    private static String playerOneName;
    private static Player playerTwo;
    private static String playerTwoName;

    public static void main(String[] args) {
        Texts.gameIntroduce();
        Texts.rules(config.getSizeOfLineToFind());
        if (config.isDevMod()) {
            initialisationNumberOfHumans();
        } else {
            numberOfHuman = 1;
        }
        initialisationThePlayersNames();
        gameInitialisation();
    }

    /**
     * Used in dev mod for testing program
     */
    private static void initialisationNumberOfHumans() {
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
    private static void initialisationThePlayersNames() {
        switch (numberOfHuman) {
            case 0:
                Texts.pseudoEntry(1);
                playerOneName = BotPlayer.playerName();
                Texts.pseudoEntry(2);
                playerTwoName = BotPlayer.playerName();
                break;
            case 1:
                Texts.pseudoEntry(1);
                playerOneName = HumanPlayer.playerName();
                Texts.pseudoEntry(2);
                playerTwoName = BotPlayer.playerName();
                break;
            case 2:
                Texts.pseudoEntry(1);
                playerOneName = HumanPlayer.playerName();
                Texts.pseudoEntry(2);
                playerTwoName = HumanPlayer.playerName();
                break;
        }
    }

    /**
     * Initialise the game
     */
    private static void gameInitialisation() {
        GameMod gameMode = selectMod();
        MasterMind game = createGame(gameMode);
        runGame(game, gameMode);
        boolean loop = true;
        while (loop) {
            Boolean again = tryAgain();
            if (again == null) {
                gameInitialisation();
            } else if (again) {
                runGame(createGame(gameMode), gameMode);
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
    private static GameMod selectMod() {
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
    private static MasterMind createGame(GameMod gameMod) {
        selectPlayerComportment();
        switch (gameMod) {
            case CHALLENGER:
                return new Party(playerOne, playerTwo, config);
            case DEFENDER:
                return new Party(playerTwo, playerOne, config);
            case DUEL:
                return new Duel(playerOne, playerTwo, config);
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
                playerOne = new BotPlayer(config, playerOneName);
                playerTwo = new BotPlayer(config, playerTwoName);
                break;
            case 1:
                playerOne = new HumanPlayer(config, playerOneName);
                playerTwo = new BotPlayer(config, playerTwoName);
                break;
            case 2:
                playerOne = new HumanPlayer(config, playerOneName);
                playerTwo = new HumanPlayer(config, playerTwoName);
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
    private static void runGame(MasterMind game, GameMod gameMod) {
        game.initialization();
        Texts.launchPhrase();
        do {
            Texts.actualRound(game.getRoundCount(), config.getMaximumOfRounds());
            game.round();
        } while (game.canPlay());
        switch (game.defineWinner()) {
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
                if (game.defineWinner() == WhoWin.PLAYER_TWO_WIN)
                    Texts.lineToFind(playerTwoName, playerTwo.getLineToFind());
                break;
            case DEFENDER:
                if (game.defineWinner() == WhoWin.PLAYER_TWO_WIN)
                    Texts.lineToFind(playerOneName, playerOne.getLineToFind());
                break;
            case DUEL:
                if (game.defineWinner() == WhoWin.PLAYER_ONE_WIN)
                    Texts.lineToFind(playerOneName, playerOne.getLineToFind());
                if (game.defineWinner() == WhoWin.PLAYER_TWO_WIN)
                    Texts.lineToFind(playerTwoName, playerTwo.getLineToFind());
                if (game.defineWinner() == WhoWin.EX_AEQUO_LOSE)
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
