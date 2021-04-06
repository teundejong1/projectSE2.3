package games.ai;

import games.Game;
import games.Move;

import java.util.List;
import java.util.Random;

public class OthelloAI implements AI {

    Random random;

    public OthelloAI () {
        super();
        this.random = new Random();
    }

    @Override
    public Move getMove(Game game) {
        List<Move> possibleMoves = game.getPossibleMoves();
        System.out.println("AI THINKING");
        System.out.println("AI THINKING");
        System.out.println("AI THINKING");
        System.out.println(possibleMoves);
        System.out.println("AI THINKING");
        System.out.println("AI THINKING");
        System.out.println("AI THINKING");
        return possibleMoves.get(random.nextInt(possibleMoves.size()));
        }
    }

