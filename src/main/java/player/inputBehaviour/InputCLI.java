package player.inputBehaviour;

import java.util.Scanner;

import games.Game;
import games.Move;

/**
 * @author Teun de Jong, Jeroen Lammersma
 * Class that requests input from the CLI
 */
public class InputCLI implements Input {

    Scanner scanner;

    /**
     * Constructor for InputCLI, builds a scanner
     */
    public InputCLI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method to request a move from the CLI
     * @param game the current game
     * @return a move
     */
    @Override
    public Move requestMove(Game game) {
        System.out.println(game.getBoard());

        System.out.print("Do a move: ");


        // TODO validate input

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Move move = new Move(x, y);

        return move;
    }
    
}
