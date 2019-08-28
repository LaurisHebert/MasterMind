import Game.HumanVsEngine;
import Game.MasterMind;

public class Main {

    public static void main(String[] args) {
        MasterMind first = new HumanVsEngine();

        while (first.canPlayAgain()){
            first.turn();
        }
    }
}