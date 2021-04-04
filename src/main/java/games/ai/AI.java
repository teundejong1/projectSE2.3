package games.ai;

import games.Game;
import games.Move;

public interface AI {
    public Move getMove(Game game);
}
