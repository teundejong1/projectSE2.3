package games.ai;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;

/**
 * @Author Jeroen Lammersma, Teun de Jong, Eva Jakobs
 * Interface for the AI
 */
public interface AI {
    public Move getMove(Game game) throws SetOutOfBoundsException;
}
