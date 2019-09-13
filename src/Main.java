import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;
import com.pda.games.Mode.Challenger;
import com.pda.games.Mode.Defenseur;
import com.pda.games.Mode.Duel;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String player;
    private static HumanEnter humanEnter = new HumanEnter(new Scanner(System.in));

    public static void main(String[] args) {
        player = humanEnter.pseudo();
        System.out.println("Rules :\n" +
                "-For each \"defense\" or \"attack\" you need to enter " + MasterMind.getSize() + " digits.\n" +
                "-For each \"clue\" you need to enter if your defense number is higther \"+\", lower \"-\" or equal \"=\"");
        party();
    }

    private static int selectGame(HumanEnter humanEnter){
        System.out.println("\nChoose your game mode :\n" +
                "1 -Challenger\n" +
                "2 -Défenseur\n" +
                "3 -Duel\n" +
                "Make your choice :");
        return humanEnter.gameMode();
    }

    private static MasterMind createGame(int gameMode){
        switch (gameMode) {
            case 1:
                return new Challenger(humanEnter);
            case 2:
                return new Defenseur(humanEnter);
            case 3:
                return new Duel(humanEnter);
            default:
                System.out.println("Enter only 1 2 or 3");
                return null;
        }
    }

    private static void executeGame(MasterMind game) {
        while (game.playerOneCanPlayAgain() && game.playerTwoCanPlayAgain()) {
            game.round();
        }
        if (game.humanWin() && game.botWin()){
            System.out.println("Ex aequo.");
        }
        else if (game.humanWin() && game.botLose()) {
            System.out.println("[" + player + "] win.");
        }
        else if (game.botWin() && game.humanLose()) {
            System.out.println("[" + player + "] lose.");
            if (game.getPlayerTwoDefense() != null){
                System.out.println("The answer was" + Arrays.toString(game.getPlayerTwoDefense()) + ".");
            }
        }
        if (game.humanLose() && game.botLose() && !(game.humanWin() && game.botWin())){
            System.out.println("Everyone lose" +
                    "\nThis bot answer was\n" +
                    Arrays.toString(game.getPlayerTwoDefense()) +
                    "\nand human answer was\n" +
                    Arrays.toString(game.getPlayerOneDefense()));
        }
    }

    private static Boolean tryAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nIf you want retry \"r\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
        while (true){
            String tryAgain = sc.nextLine();
            switch (tryAgain.toLowerCase()) {
                case "r":
                    return true;
                case "h":
                    return null;
                case "q":
                    return false;
                default:
                    System.out.println("I'm sorry i don't understand");
                    break;
            }
        }
    }
    private static void party(){
        int gameMode;
        MasterMind game;
        do{
            gameMode = selectGame(humanEnter);
            game = createGame(gameMode);
        }while (game == null);
        executeGame(game);

        Boolean again = tryAgain();
            if (again == null){
            party();
        }
            else if (again) {
            executeGame(createGame(gameMode));
        }
            else {
            System.out.println("CYA");
        }
    }
}