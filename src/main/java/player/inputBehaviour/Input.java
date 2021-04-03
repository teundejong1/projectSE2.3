package player.inputBehaviour;

import games.Move;
import games.board.Board;

public interface Input {
    public Move requestMove(Board board);
    
}
