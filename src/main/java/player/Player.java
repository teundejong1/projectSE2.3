package player;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;

public interface Player {
    public Move requestMove(Game game) throws SetOutOfBoundsException;
    public String getName();
    
}
