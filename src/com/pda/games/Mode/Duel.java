package com.pda.games.Mode;

import com.pda.games.Enters.BotEnter;
import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;

import java.util.Arrays;

public class Duel extends MasterMind {

    private int[] humanAttack = new int[getSize()];
    private String[] humanClue = new String[getSize()];

    private int[] botAttack = new int[getSize()];
    private int [] previousBotAttack = new int[getSize()];

    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};

    public Duel() { super(HumanEnter.maxRound(), humanDefenseNumber(), BotEnter.getArray(getSize(), getMinRange(), getMaxRange())); }

    private static int[] humanDefenseNumber(){
        System.out.println("Defense :");
        return HumanEnter.getArray(getSize(), getMinRange(), getMaxRange());
    }

    @Override
    public boolean humanWin() { return isHumanAttackCorrespondence() && getRound() <= getMaxRound(); }

    @Override
    public boolean humanLose() { return isBotAttackCorrespondence() || (!isHumanAttackCorrespondence() && getRound() == getMaxRound()); }

    @Override
    protected void humanClue(){ humanClue = HumanEnter.getClue(getSize(),botAttack, getHumanDefense()); }

    private void humanTurn(){
        System.out.println("Round " + (getRound() + 1) + "/" + getMaxRound() +
                "\nHuman Turn\n" +
                "Attack");
        humanAttack = HumanEnter.getArray(getSize(), getMinRange(), getMaxRange());
        humanVerifyEnter(humanAttack);
        botClue();
    }

    @Override
    public boolean botWin() { return isBotAttackCorrespondence() && getRound() <= getMaxRound(); }

    @Override
    public boolean botLose() { return isHumanAttackCorrespondence() || (isBotAttackCorrespondence() && getRound() == getMaxRound()); }

    @Override
    protected void botClue() { System.out.println(Arrays.toString(BotEnter.getClue(getSize(), humanAttack, getBotDefense()))); }

    private void botTurn(){
        System.out.println("Bot turn");
        for (int i = 0; i < getSize() ; i++) {
            if(getRound() == 0){
                botAttack = BotEnter.getArray(getSize(), getMinRange(), getMaxRange());
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

}
