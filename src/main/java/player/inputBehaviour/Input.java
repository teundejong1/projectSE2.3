package player.inputBehaviour;

import games.Game;
import games.Move;

public interface Input {
    public Move requestMove(Game game);
    
}
