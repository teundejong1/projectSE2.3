package games;

import java.util.List;

import games.board.Mark;
import player.Player;
import player.PlayerType;

public class Othello extends Game {

    public Othello(PlayerType startingPlayer) {
        super(startingPlayer);
    }

    @Override
    protected void validateMove(Move move, Mark marker) throws IllegalMoveException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean checkForWin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Move> getPossibleMoves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void start(Player one, Player two) {
        // TODO Auto-generated method stub
        
    }
    
}
