package player;

import games.Game;
import games.Move;
import games.board.Mark;
import games.board.SetOutOfBoundsException;

public interface Player {
    public PlayerType getType();
    public Move requestMove(Game game) throws SetOutOfBoundsException;
    public String getName();
    public Mark getMark();
    
}
