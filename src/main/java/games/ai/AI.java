package games.ai;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;

public interface AI {
    public Move getMove(Game game) throws SetOutOfBoundsException;
}
