package Game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HumanVsEngine extends MasterMind {
    Scanner sc = new Scanner(System.in);
    private int[] attackNumber = new int[size];
    private int[] savedAttackNumber = {0, 0, 0, 0};
    private String[] clue = {"+", "+", "+", "+"};


    public HumanVsEngine() {
        initialisation(turnMax(), defense());
    }

    private int turnMax() {
        System.out.println("choisir le nombre de tours maximum");
        return sc.nextInt();
    }

    private int[] defense() {
        int[] defenseNmbrs = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("entrer chiffre" + i);
            int n = sc.nextInt();
            defenseNmbrs[i] = n;
        }
        return defenseNmbrs;
    }

    @Override
    public int[] turn() {
        System.out.println(Arrays.toString(savedAttackNumber));
        for (int i = 0; i < size; ++i) {
            switch (clue[i]) {
                case "+":
                    attackNumber[i] = savedAttackNumber[i] + new Random().nextInt(10 - savedAttackNumber[i]);
                    break;
                case "-":
                    attackNumber[i] = new Random().nextInt(savedAttackNumber[i]);
                    break;
                case "=":
                    attackNumber[i] = savedAttackNumber[i];
                    break;
                default:
                    attackNumber[i] = new Random().nextInt(10);

            }
        }
        verifyEnter(attackNumber);
        savedAttackNumber = attackNumber;
        return attackNumber;
    }

    @Override
    public String[] clue() {
        System.out.println(" dire c'est + - ou =");
        for (int i = 0; i < size; i++) {
            clue[i] = sc.next();
            while (!clue[i].equals("+") && !clue[i].equals("-") && !clue[i].equals("=")) {
                System.out.println("veuillez entré l'un des trois éléments ( =, +, -)");
                clue[i] = sc.next();
            }
        }
        return clue;
    }

}
