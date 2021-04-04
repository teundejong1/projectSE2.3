package player.inputBehaviour;

import java.util.Scanner;

import games.Game;
import games.Move;

public class InputCLI implements Input {

    Scanner scanner;

    public InputCLI() {
        this.scanner = new Scanner(System.in);
    }

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
