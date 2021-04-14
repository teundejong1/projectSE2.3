package games;

import player.PlayEnum;
import player.PlayerType;

/**
 * @author Jeroen Lammersma
 * Gamefactory class
 * Used to create a game, currently either TicTacToe or Othello
 */
public class GameFactory {

    public static Game createGame(PlayerType startingPlayer, GameEnum game, PlayEnum playType) {
        switch (game) {
            case TTT: return new TicTacToe(startingPlayer, playType);
            case OTHELLO: return new Othello(startingPlayer, playType);
            default: return null;
        }
    }

    // TODO remove constructors, moove javadoc to createGame
    // /**
    //  * Method to create a tictactoe game
    //  * @param startingPlayer the starting player
    //  * @param playType the playEnum of this game
    //  * @return a new TicTacToe game
    //  */
    // public static Game createTicTacToeGame(PlayerType startingPlayer, PlayEnum playType) {
    //     return new TicTacToe(startingPlayer, playType);
    // }

    // /**
    //  * Method used to create an Othello game
    //  * @param startingPlayer the starting player
    //  * @param playType the playEnum of this game
    //  * @return a new Othello game
    //  */
    // public static Game createOthelloGame(PlayerType startingPlayer, PlayEnum playType) {
    //     return new Othello(startingPlayer, playType);
    // }

}
