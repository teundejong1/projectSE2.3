package player.inputBehaviour;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;

/**
 * @author Jeroen Lammersma, Teun de Jong
 * Interface for input, extended but the different inputs
 */
public interface Input {
    public Move requestMove(Game game) throws SetOutOfBoundsException;
    
}
