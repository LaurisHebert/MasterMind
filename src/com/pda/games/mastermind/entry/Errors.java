package com.pda.games.mastermind.entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Errors {
    protected static final Logger logger = LogManager.getLogger();

    public static void wrongEntry(int i) {
        logger.error("Your " + (i + 1) + getNumberSuffix(i + 1) + "entry was wrong");
    }

    private static String getNumberSuffix(int i) {
        if (i >= 11 && i <= 13) {
            return "th";
        }
        switch (i % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static void notEnough(int i) {
        logger.error(i + " entry are needed");
    }

    public static void onlyBetween(int min, int max) {
        logger.error("You can choose only between " + min + " and " + max);
    }

    public static void onlyDigits() {
        logger.error("you have to enter only digits");
    }

    public static void onlySymbol() {
        logger.error("you only need to enter the required symbols ");
    }

    public static void error() {
        logger.error("we encountered an error please retry");
    }

    public static void playerMax() {
        logger.error("Mastermind can only be played by two players, so the answer can only be between zero and two");
    }
}
