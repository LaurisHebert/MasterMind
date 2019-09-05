package com.pda.games;

import java.util.Arrays;

public abstract class MasterMind {

    final static int size = 4;
    final static int minRange = 0;
    final static int maxRange = 9;
    // static car utiliser pour définire d'autres methodes statics

    public final int[] defenseNumber;
    // public car utiliser dans Main
    public final int[] defenseNumber2;

    int maxRound;
    int round = 0;
    // package-private car utilisé dans Round avec maxRound

    boolean correspondence = false;
    boolean correspondence2 = false;
    // package-private car utilisé pour définire les win et lose
    MasterMind(int maxRound, int[] defenseNumber){
        this.maxRound = maxRound;
        this.defenseNumber = defenseNumber;
        this.defenseNumber2 = null;
    }
    MasterMind(int maxRound, int[] defenseNumber, int[] defenseNumber2){
        this.maxRound = maxRound;
        this.defenseNumber = defenseNumber;
        this.defenseNumber2 = defenseNumber2;
    }

    public abstract void round();

    void verifyEnter(int[] attackNumber){
        if (canPlayAgain()){
            correspondence = Arrays.equals(defenseNumber, attackNumber);
        }
    }
    void verifyEnter2(int[] attackNumber){
        if (canPlayAgain()){
            correspondence2 = Arrays.equals(defenseNumber2, attackNumber);
        }
    }

    public boolean canPlayAgain(){
        return !correspondence && !correspondence2 && round < maxRound;
    }

    abstract void clue();

    abstract public boolean hasWon();

    abstract public boolean hasLost();
}

