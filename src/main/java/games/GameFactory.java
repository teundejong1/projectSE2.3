package games;

import player.PlayEnum;
import player.PlayerType;

public class GameFactory {

    public static Game createTicTacToeGame(PlayerType startingPlayer, PlayEnum playType) {
        return new TicTacToe(startingPlayer, playType);
    }

    public static Game createOthelloGame(PlayerType startingPlayer, PlayEnum playType) {
        return new Othello(startingPlayer, playType);
    }

}
