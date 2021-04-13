package games.ai;

import java.util.List;
import java.util.Random;

import games.Game;
import games.Move;

/**
 * @author Teun de Jong
 * A simple TicTacToeAI class
 */
public class TicTacToeAI implements AI {
    Random random;
    public TicTacToeAI () {
        super();
        this.random = new Random();
    }

    /**
     * Method used to get a random move from the AI
     * @param game the current game
     * @return A random move
     */
    @Override
    public Move getMove(Game game) {
        List<Move> possibleMoves = game.getPossibleMoves();
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }
    
}
