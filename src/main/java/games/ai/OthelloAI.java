package games.ai;

import games.Game;
import games.Move;

import java.util.List;
import java.util.Random;

/**
 * @author Teun de Jong
 * Simple Othello AI class
 */
public class OthelloAI implements AI {
    Random random;
    public OthelloAI () {
        super();
        this.random = new Random();
    }

    /**
     * Method used to get a move from the AI
     * @param game the current game
     * @return a random move from the possible moves
     */
    @Override
    public Move getMove(Game game) {
        List<Move> possibleMoves = game.getPossibleMoves();
        System.out.println(possibleMoves);
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
        }
    }

