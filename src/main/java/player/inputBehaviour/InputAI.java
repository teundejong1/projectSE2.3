package player.inputBehaviour;

import games.Game;
import games.GameEnum;
import games.Move;
import games.ai.*;
import games.board.SetOutOfBoundsException;
/**
 * @author Teun de Jong, Jeroen Lammersma
 * Class inputAI
 * Creates the AI used for the implemented
 */
public class InputAI implements Input {

    private AI ai;

    /**
     * Method to set the input for the AI
     * If you'd like to play vs an easier AI, uncomment the second line in the case
     * @param game GameEnum (TTT ot Othello)
     */
    public InputAI(GameEnum game) {
        switch (game) {
            case TTT:
                ai = new TicTacToeAiMiniMax();
//                ai = new TicTacToeAI();
                break;
            case OTHELLO:
                 ai = new OthelloAiMiniMax();
//                ai = new OthelloAI();
                break;
        }
    }

    /**
     * Method to request a move from the AI
     * @param game on which to request a move
     * @return the move from the AI
     * @throws SetOutOfBoundsException when a move is out of bounds
     */
    @Override
    public Move requestMove(Game game) throws SetOutOfBoundsException {
        return ai.getMove(game);
    }
    
}
