package com.pda.games;

import java.util.Arrays;
import java.util.Scanner;

import static com.pda.games.GetIn.getRandomArray;

public class Duel extends MasterMind{
    private Scanner sc = new Scanner(System.in);

    private int[] humanAttack = new int[getSize()];
    private String[] humanClue = new String[getSize()];

    private int[] botAttack = new int[getSize()];
    private String[] botClue = new String[getSize()];
    private int [] previousBotAttack = new int[getSize()];

    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};

    public Duel() { super(GetIn.maxRound(), humanDefenseNumber(), getRandomArray(getSize(), getMinRange(), getMaxRange())); }

    private static int[] humanDefenseNumber(){
        System.out.println("Defense :");
        return GetIn.getHumanArray(getSize(), getMinRange(), getMaxRange());
    }

    @Override
    public boolean humanWin() { return isHumanAttackCorrespondence() && getRound() <= getMaxRound(); }
    @Override
    public boolean botWin() { return isBotAttackCorrespondence() && getRound() <= getMaxRound(); }
    @Override
    public boolean humanLose() { return isBotAttackCorrespondence() || (!isHumanAttackCorrespondence() && getRound() == getMaxRound()); }
    @Override
    public boolean botLose() { return isHumanAttackCorrespondence() || (isBotAttackCorrespondence() && getRound() == getMaxRound()); }

    private void humanTurn(){
        System.out.println(Arrays.toString(getBotDefense()));
        System.out.println("Round " + (getRound() + 1) + "/" + getMaxRound() +
                "\nHuman Turn\n" +
                "Attack");
        humanAttack = GetIn.getHumanArray(getSize(), getMinRange(), getMaxRange());
        humanVerifyEnter(humanAttack);
        clue();
    }

    private void botTurn(){
        System.out.println("Bot turn");
        for (int i = 0; i < getSize() ; i++) {
            if(getRound() == 0){
                botAttack = GetIn.getRandomArray(getSize(), getMinRange(), getMaxRange());
            }
            else{
                previousBotAttack[i]= botAttack[i];
                switch (humanClue[i]) {
                    case "+":
                        minimum[i] = botAttack[i];
                        botAttack[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "-":
                        maximum[i] = botAttack[i];
                        botAttack[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "=":
                        botAttack[i] = previousBotAttack[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(botAttack));
        botVerifyEnter(botAttack);
        if (!isBotAttackCorrespondence()){
            System.out.println("Clue :");
            humanClue();
        }
    }

    @Override
    public void round() {
        humanTurn();
        setRound(getRound()-1);
        botTurn();
    }

    @Override
    protected void clue() {
        for (int i = 0; i < getSize(); i++) {
            if (humanAttack[i] == getBotDefense()[i]) {
                botClue[i] = "=";
            }
            if (humanAttack[i] > getBotDefense()[i]) {
                botClue[i] = "-";
            }
            if (humanAttack[i] < getBotDefense()[i]) {
                botClue[i] = "+";
            }
        }
        System.out.println(Arrays.toString(botClue));
    }
    private void humanClue(){
        String clues = sc.nextLine();
        humanClue = clues.split(" ");
        for (int i = 0; i < getSize(); i++) {
            if (!verify(humanClue[i], i)){
                System.out.println("A cake is a lie");
                humanClue();
            }
        }
    }

    private boolean verify(String clue, int i){
        switch (clue){
            case "+":
                return botAttack[i] < getHumanDefense()[i];
            case "=":
                return botAttack[i] == getHumanDefense()[i];
            case "-":
                return botAttack[i] > getHumanDefense()[i];
            default:
                System.out.println("invalid clue : \"" + clue + "\"");
                return false;
        }
    }

}
