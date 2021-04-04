package games.ai;

import java.util.List;
import java.util.Random;

import games.Game;
import games.Move;

public class TicTacToeAI implements AI {

    Random random;

    public TicTacToeAI () {
        super();
        this.random = new Random();
    }

    @Override
    public Move getMove(Game game) {
        List<Move> possibleMoves = game.getPossibleMoves();
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
    
}
