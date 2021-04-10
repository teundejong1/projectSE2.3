package player.inputBehaviour;

import games.Game;
import games.GameEnum;
import games.Move;
import games.ai.*;
import games.board.SetOutOfBoundsException;

public class InputAI implements Input {

    private AI ai;

    public InputAI(GameEnum game) {
        switch (game) {
            case TTT:
                ai = new TicTacToeAiMiniMax();
//                ai = new TicTacToeAI();
                break;
            case OTHELLO:
//                ai = new OthelloAiMiniMax();
                ai = new OthelloAI();
                break;
        }
    }

    @Override
    public Move requestMove(Game game) throws SetOutOfBoundsException {
        return ai.getMove(game);
    }
    
}
