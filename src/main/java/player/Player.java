package player;

import games.Move;
import games.board.Board;

public interface Player {
    public Move requestMove(Board board);
    public String getName();
    
}
