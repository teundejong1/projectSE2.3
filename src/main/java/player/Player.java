package player;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;

/**
 * @author Teun de Jong, Jeroen Lammersma
 * Interface for Player
 */
public interface Player {
    public PlayerType getType();
    public Move requestMove(Game game) throws SetOutOfBoundsException;
    public String getName();
    
}
