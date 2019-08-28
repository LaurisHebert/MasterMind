package Game;

import java.util.Arrays;

public abstract class MasterMind {
    final int size = 4;
    private int turn = 0;
    private int[] defense;
    private boolean correspondence = false;
    private int turnMax;

    protected void initialisation(int turnMax, int[] defenseNmbrs){
        this.turnMax = turnMax;
        this.defense = defenseNmbrs;
    }

    public abstract int[] turn();

    protected void verifyEnter(int[] attack){
        turn++;
        System.out.println(Arrays.toString(attack));
        correspondence = Arrays.equals(defense, attack);
        System.out.println(Arrays.toString(clue()));
    }

    public abstract String[] clue();

    public boolean canPlayAgain(){
        return !correspondence && turn < turnMax;
    }
}
