package games;

import player.PlayEnum;
import player.PlayerType;

/**
 * @author Jeroen Lammersma
 * Gamefactory class
 * Used to create a game, currently either TicTacToe or Othello
 */
public class GameFactory {

    /**
     * Factory method
     * @param startingPlayer the starting player
     * @param game the game that needs to be created
     * @param playType the playEnum of this game
     * @return a new game 
     */
    public static Game createGame(PlayerType startingPlayer, GameEnum game, PlayEnum playType) {
        switch (game) {
            case TTT: return new TicTacToe(startingPlayer, playType);
            case OTHELLO: return new Othello(startingPlayer, playType);
            default: return null;
        }
    }

}
