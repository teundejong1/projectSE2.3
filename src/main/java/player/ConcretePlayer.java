package player;

import games.Move;
import games.board.Board;
import player.inputBehaviour.Input;

public class ConcretePlayer implements Player {

    private final Input inputBehaviour;
    private final String name;

    public ConcretePlayer(String name, Input inputBehaviour) {
        this.name = name;
        this.inputBehaviour = inputBehaviour;
    }
    
    public Move requestMove(Board board) {
        return inputBehaviour.requestMove(board);
    }

    public String getName() {
        return name;
    }
    
}
