package com.pda.games;

import java.util.Arrays;

public abstract class MasterMind {

<<<<<<< HEAD
    final static int size = 4;
    final static int minRange = 0;
    final static int maxRange = 9;
    // static car utiliser pour définire d'autres methodes statics

    public final int[] defenseNumber;
    // public car utiliser dans Main

    int maxRound;
    int round = 0;
    // package-private car utilisé dans Round avec maxRound

    boolean correspondence = false;
    // package-private car utilisé pour définire les win et lose

    MasterMind(int maxRound, int[] defenseNumber){
        this.maxRound = maxRound;
        this.defenseNumber=defenseNumber;
    }

    public abstract void round();

    void verifyEnter(int[] attackNumber){
        if (canPlayAgain()){
            correspondence = Arrays.equals(defenseNumber, attackNumber);
            clue();
        }
        round++;
    }

    public boolean canPlayAgain(){
        return !correspondence && round < maxRound;
    }

    abstract void clue();

    abstract public boolean hasWon();

    abstract public boolean hasLost();
=======
    static final int size = 4;
    static int maxTurn;
    public final int[] defenseNumber;

    int turn = 0;

    boolean correspondence;

    MasterMind(int maxTurn, int[] defenseNumber){
        MasterMind.maxTurn = maxTurn;
        this.defenseNumber=defenseNumber;
    }

    public abstract void turn();
    abstract void clue();

    void verifyEnter(int[] attackNumber){
        correspondence=Arrays.equals(defenseNumber, attackNumber);
    }

    public boolean canPlayAgain(){
        return !correspondence && turn < maxTurn;
    }
    public boolean win(){
        return correspondence && turn <= maxTurn;
    }
    public boolean lose(){
        return !correspondence && turn >= maxTurn;
    }
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
}

