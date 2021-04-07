package player.inputBehaviour;

import games.Game;
import games.GameEnum;
import games.Move;
import games.ai.AI;
import games.ai.OthelloAI;
import games.ai.TicTacToeAI;

public class InputAI implements Input {

    private AI ai;

    public InputAI(GameEnum game) {
        switch (game) {
            case TTT:
                ai = new TicTacToeAI();
                break;
            case OTHELLO:
                ai = new OthelloAI();
                break;
        }
    }

    @Override
    public Move requestMove(Game game) {
        return ai.getMove(game);
    }
    
}
