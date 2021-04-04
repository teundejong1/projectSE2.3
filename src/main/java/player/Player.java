package player;

import games.Game;
import games.Move;

public interface Player {
    public Move requestMove(Game game);
    public String getName();
    
}
