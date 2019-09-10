package com.pda.games;

import java.util.Arrays;
import java.util.Scanner;

public class Defenseur extends MasterMind {

    private static Scanner staticScan = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);

    // voir si il y à plus présentable
    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};

    private int[] attackNumber = new int[getSize()];
    private int[] previousNumber= new int[getSize()];
    private String[] clue = new String[getSize()];

    public Defenseur() { super(GetIn.maxRound(), humanDefense(), null); }

    private static int[] humanDefense() {
        System.out.println("Defense :");
        return GetIn.getHumanArray(getSize(), getMinRange(), getMaxRange());
    }

    @Override
    public boolean botWin() { return isBotAttackCorrespondence() && getRound() <= getMaxRound(); }
    @Override
    public boolean botLose() { return  !isBotAttackCorrespondence() && !botCanPlayAgain(); }
    @Override
    public boolean humanWin() { return botLose(); }
    @Override
    public boolean humanLose() { return botWin(); }
    @Override
    public void round() {
        System.out.println("Round "+ (getRound() + 1) +"/"+getMaxRound());
        for (int i = 0; i < getSize() ; i++) {
            if(getRound() == 0){
                attackNumber = GetIn.getRandomArray(getSize(), getMinRange(), getMaxRange());
            }
            else{
                previousNumber[i]=attackNumber[i];
                switch (clue[i]) {
                    case "+":
                        minimum[i] = attackNumber[i];
                        attackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "-":
                        maximum[i] = attackNumber[i];
                        //attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        attackNumber[i] = ((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "=":
                        attackNumber[i] = previousNumber[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(attackNumber));
        botVerifyEnter(attackNumber);
        if (!isBotAttackCorrespondence()) {
            System.out.println("Clue :");
            clue();
        }
    }

    @Override
    protected void clue() {
        String clues = sc.nextLine();
        clue = clues.split(" ");
        try{
            for (int i = 0; i < getSize(); i++) {
                if (!verify(clue[i], i)){
                    System.out.println("A cake is a lie");
                    clue();
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Need more input");
            clue();
        }
    }

    private boolean verify(String clue, int i){
        switch (clue){
            case "+":
                return attackNumber[i] < getHumanDefense()[i];
            case "=":
                return attackNumber[i] == getHumanDefense()[i];
            case "-":
                return attackNumber[i] > getHumanDefense()[i];
            default:
                System.out.println("invalid clue : \"" + clue + "\"");
                return false;
        }
    }
}