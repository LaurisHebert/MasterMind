package com.pda.games;

import com.pda.games.mastermind.comportment.HumanPlayer;
import com.pda.games.mastermind.comportment.Player;
import com.pda.games.mastermind.comportment.bot.BotPlayer;
import com.pda.games.mastermind.comportment.bot.HardBotPlayer;
import com.pda.games.mastermind.comportment.bot.MediumBotPlayer;
import com.pda.games.mastermind.entry.Errors;
import com.pda.games.mastermind.entry.Inputs;
import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.entry.languages.TextsEN;
import com.pda.games.mastermind.entry.languages.TextsFR;
import com.pda.games.mastermind.game.enums.GameMod;
import com.pda.games.mastermind.game.enums.WhoWin;
import com.pda.games.mastermind.game.impl.Duel;
import com.pda.games.mastermind.game.impl.Party;
import com.pda.games.mastermind.game.MasterMind;
import com.pda.games.mastermind.game.config.MasterMindConfig;

import java.util.Random;

public class Main {

    private static final MasterMindConfig config = new MasterMindConfig();
    private static Player playerOne;
    private static String playerOneName;
    private static Player playerTwo;
    private static String playerTwoName;
    private static Texts texts = buildTexts();

    public static void main(String[] args) {
        texts.gameIntroduce();
        texts.rules(config.getSizeOfLineToFind());
        initPlayersNames();
        gameInit();
    }

    private static String humanPlayerName() {
        return Inputs.nextLine();
    }

    /**
     * used for create bot name
     *
     * @return the bot name
     */
    public static String botPlayerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        id = id % 10000;
        return "Bot" + id;
    }

    private static Texts buildTexts() {
        if (config.getLanguage().equals("FR")) {
            return new TextsFR();
        } else {
            return new TextsEN();
        }
    }

    /**
     * Used for named the players with corresponding entry
     */
    private static void initPlayersNames() {
        switch (config.getNumberOfBot()) {
            case 0:
                texts.pseudoEntry(1);
                playerOneName = humanPlayerName();
                texts.pseudoEntry(2);
                playerTwoName = humanPlayerName();
                break;
            case 1:
                if (config.getPlayerOneName() != null) {
                    playerOneName = config.getPlayerOneName();
                } else {
                    texts.pseudoEntry(1);
                    playerOneName = humanPlayerName();
                }
                if (config.getPlayerTwoName() != null) {
                    playerTwoName = config.getPlayerTwoName();
                } else {
                    texts.pseudoEntry(2);
                    playerTwoName = botPlayerName();
                    texts.botName(playerTwoName);
                }
                break;
            case 2:
                texts.pseudoEntry(1);
                playerOneName = botPlayerName();
                texts.botName(playerOneName);
                texts.pseudoEntry(2);
                playerTwoName = botPlayerName();
                texts.botName(playerTwoName);
                break;
        }
    }

    /**
     * Initialise the game
     */
    private static void gameInit() {
        GameMod gameMode = selectMod();
        MasterMind game = createGame(gameMode);
        runGame(game, gameMode);
        while (true) {
            Boolean again = tryAgain();
            if (again == null) {
                gameInit();
            } else if (again) {
                runGame(createGame(gameMode), gameMode);
            } else {
                texts.end();
                System.exit(0);
            }
        }
    }

    /**
     * @return the selected game mod
     */
    private static GameMod selectMod() {
        texts.explainGameMod();
        boolean error;
        int entry;
        do {
            error = false;
            entry = Inputs.nextPositiveInt();
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
                return new Party(texts, playerOne, playerTwo, config);
            case DEFENDER:
                return new Party(texts, playerTwo, playerOne, config);
            case DUEL:
                return new Duel(texts, playerOne, playerTwo, config);
            default:
                throw new IllegalStateException("Unexpected value: ");
        }
    }

    /**
     * execute the course of the game
     *
     * @param game used to know which game to launch
     */
    private static void runGame(MasterMind game, GameMod gameMod) {
        game.init();
        texts.launchPhrase();
        do {
            texts.actualRound(game.getRoundCount(), config.getMaximumOfRounds());
            game.round();
        } while (game.canPlay());
        switch (game.defineWinner()) {
            case PLAYER_ONE_WIN:
                texts.win(game.getPlayerOneName());
                break;
            case PLAYER_TWO_WIN:
                texts.win(game.getPlayerTwoName());
                break;
            case EX_AEQUO_WIN:
                texts.Equally("win");
                break;
            case EX_AEQUO_LOSE:
                texts.Equally("lose");
                break;
        }
        switch (gameMod) {
            case CHALLENGER:
                if (game.defineWinner() == WhoWin.PLAYER_TWO_WIN)
                    texts.lineToFind(playerTwoName, playerTwo.getLineToFind());
                break;
            case DEFENDER:
                if (game.defineWinner() == WhoWin.PLAYER_TWO_WIN)
                    texts.lineToFind(playerOneName, playerOne.getLineToFind());
                break;
            case DUEL:
                if (game.defineWinner() == WhoWin.PLAYER_ONE_WIN)
                    texts.lineToFind(playerOneName, playerOne.getLineToFind());
                if (game.defineWinner() == WhoWin.PLAYER_TWO_WIN)
                    texts.lineToFind(playerTwoName, playerTwo.getLineToFind());
                if (game.defineWinner() == WhoWin.EX_AEQUO_LOSE)
                    texts.lineToFind(playerOneName, playerOne.getLineToFind(), playerTwoName, playerTwo.getLineToFind());
                break;
        }
    }

    /**
     * Used for know if the player is human or IA
     */
    private static void selectPlayerComportment() {
        switch (config.getNumberOfBot()) {
            case 0:
                playerOne = new HumanPlayer(texts, config, playerOneName);
                playerTwo = new HumanPlayer(texts, config, playerTwoName);
                break;
            case 1:
                playerOne = new HumanPlayer(texts, config, playerOneName);
                playerTwo = buildBot(config.getBotDifficulties()[0], playerTwoName);
                break;
            case 2:
                playerOne = buildBot(config.getBotDifficulties()[0], playerOneName);
                playerTwo = buildBot(config.getBotDifficulties()[1], playerTwoName);
                break;
            default:
                Errors.playerMax();
                break;
        }
    }

    private static BotPlayer buildBot(int i, String playerName) {
        switch (i) {
            case 1:
                return new MediumBotPlayer(texts, config, playerName);
            case 2:
                return new HardBotPlayer(texts, config, playerName);
            default:
                return new BotPlayer(texts, config, playerName);
        }
    }


    private static Boolean tryAgain() {
        texts.tryAgainMenu();
        while (true) {
            String tryAgain = Inputs.nextLine();
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
