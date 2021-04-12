package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;

public interface Input {
    public Move requestMove(Game game) throws SetOutOfBoundsException;
    
}
