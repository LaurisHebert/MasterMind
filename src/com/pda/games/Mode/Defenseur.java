package com.pda.games.Mode;

import com.pda.games.Enters.BotEnter;
import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;

import java.util.Arrays;

public class Defenseur extends MasterMind {

    // voir si il y à plus présentable
    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};

    private int[] botAttack = new int[getSize()];
    private int[] previousAttack = new int[getSize()];
    private String[] humanClue = new String[getSize()];

    public Defenseur() { super(HumanEnter.maxRound(), humanDefense(), null); }

    private static int[] humanDefense() {
        System.out.println("Defense :");
        return HumanEnter.getArray(getSize(), getMinRange(), getMaxRange());
    }
    @Override
    public boolean humanWin() { return botLose(); }
    @Override
    public boolean humanLose() { return botWin(); }
    @Override
    protected void humanClue() { humanClue = HumanEnter.getClue(getSize(),botAttack, getHumanDefense()); }

    @Override
    public boolean botWin() { return isBotAttackCorrespondence() && getRound() <= getMaxRound(); }
    @Override
    public boolean botLose() { return  !isBotAttackCorrespondence() && !botCanPlayAgain(); }
    @Override
    protected void botClue(){}

    @Override
    public void round() {
        System.out.println("Round "+ (getRound() + 1) +"/"+getMaxRound());
        for (int i = 0; i < getSize() ; i++) {
            if(getRound() == 0){
                botAttack = BotEnter.getArray(getSize(), getMinRange(), getMaxRange());
            }
            else{
                previousAttack[i]= botAttack[i];
                switch (humanClue[i]) {
                    case "+":
                        minimum[i] = botAttack[i];
                        botAttack[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "-":
                        maximum[i] = botAttack[i];
                        //attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        botAttack[i] = ((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "=":
                        botAttack[i] = previousAttack[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(botAttack));
        botVerifyEnter(botAttack);
        if (!isBotAttackCorrespondence()) {
            System.out.println("Clue :");
            humanClue();
        }
    }

}
