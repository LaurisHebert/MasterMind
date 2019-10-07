package com.pda.games;

import com.pda.games.MasterMind.Comportment.BotComportment;
import com.pda.games.MasterMind.Comportment.HumanComportment;
import com.pda.games.MasterMind.Entry.Sc;
import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Enums.GameMod;
import com.pda.games.MasterMind.Enums.WhoWin;
import com.pda.games.MasterMind.GameMods.Duel;
import com.pda.games.MasterMind.GameMods.Party;
import com.pda.games.MasterMind.Model.MasterMind;
import com.pda.games.MasterMind.Model.Player;

public class Main {

    private static int numberOfHuman;
    private static Player playerOne;
    private static String playerOneName;
    private static Player playerTwo;
    private static String playerTwoName;

    public static void main(String[] args) {
        Sout.gameIntroduce();
        Sout.rules(MasterMind.sizeOfLineToFind);
        numberOfHuman();
        playerNames();
        initialisation();
    }

    /**
     * Used for know how many human we have
     */
    private static void numberOfHuman() {
        Sout.askingHowManyPlayer();
        boolean firstLoop = true;
        do {
            if (!firstLoop) {
                Sout.onlyBetween(0, 2);
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
                Sout.pseudoEntry(1);
                playerOneName = BotComportment.playerName();
                Sout.pseudoEntry(2);
                playerTwoName = BotComportment.playerName();
                break;
            case 1:
                Sout.pseudoEntry(1);
                playerOneName = HumanComportment.playerName();
                Sout.pseudoEntry(2);
                playerTwoName = BotComportment.playerName();
                break;
            case 2:
                Sout.pseudoEntry(1);
                playerOneName = HumanComportment.playerName();
                Sout.pseudoEntry(2);
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
                System.out.println("CYA");
                loop = false;
            }
        }
    }

    /**
     * @return the selected game mod
     */
    private static GameMod choosingMod() {
        Sout.explainGameMod();
        boolean error;
        int entry;
        do {
            error = false;
            entry = Sc.nextPositiveInt();
            if (entry < 1 || entry > 3) {
                error = true;
                Sout.onlyBetween(1, 3);
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
                System.out.println("Mastermind can only be played by two players, so the answer can only be between zero and two");
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
        Sout.launchPhrase();

        do {
            Sout.actualRound(game.getRoundCount());
            game.round();
        } while (game.canPlay());

        switch (game.whoWin()) {

            case PLAYER_ONE_WIN:
                System.out.println(game.getPlayerOneName() + " Win !");
                if (playerOne.getLineToFind() != null)
                    break;
            case PLAYER_TWO_WIN:
                System.out.println(game.getPlayerTwoName() + " Win! ");
                if (playerTwo.getLineToFind() != null)
                    break;
            case EX_AEQUO_WIN:
                System.out.println("EveryOne Win !");
                break;
            case EX_AEQUO_LOSE:
                System.out.println("No winner :/");
                break;
        }
        switch (gameMod) {

            case CHALLENGER:
                if (game.whoWin() == WhoWin.PLAYER_TWO_WIN)
                    Sout.lineToFind(playerTwoName, playerTwo.getLineToFind());
                break;
            case DEFENDER:
                if (game.whoWin() == WhoWin.PLAYER_TWO_WIN)
                    Sout.lineToFind(playerOneName, playerOne.getLineToFind());
                break;
            case DUEL:
                if (game.whoWin() == WhoWin.PLAYER_ONE_WIN)
                    Sout.lineToFind(playerOneName, playerOne.getLineToFind());
                if (game.whoWin() == WhoWin.PLAYER_TWO_WIN)
                    Sout.lineToFind(playerTwoName, playerTwo.getLineToFind());
                if (game.whoWin() == WhoWin.EX_AEQUO_LOSE)
                    Sout.lineToFind(playerOneName, playerOne.getLineToFind(), playerTwoName, playerTwo.getLineToFind());
                break;
        }
    }

    private static Boolean tryAgain() {
        Sout.tryAgainMenu();
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
                    Sout.onlySymbol();
                    break;
            }
        }
    }

}
