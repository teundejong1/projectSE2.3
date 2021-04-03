package player.inputBehaviour;

import java.util.Scanner;

import games.Move;
import games.board.Board;

public class InputCLI implements Input {

    Scanner scanner;

    public InputCLI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Move requestMove(Board board) {
        System.out.println(board);

        System.out.print("Do a move: ");

        // TODO validate input

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Move move = new Move(x, y);

        return move;
    }
    
}
