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
     * Method to create a tictactoe game
     * @param startingPlayer the starting player
     * @param playType the playEnum of this game
     * @return a new TicTacToe game
     */
    public static Game createTicTacToeGame(PlayerType startingPlayer, PlayEnum playType) {
        return new TicTacToe(startingPlayer, playType);
    }

    /**
     * Method used to create an Othello game
     * @param startingPlayer the starting player
     * @param playType the playEnum of this game
     * @return a new Othello game
     */
    public static Game createOthelloGame(PlayerType startingPlayer, PlayEnum playType) {
        return new Othello(startingPlayer, playType);
    }

}
