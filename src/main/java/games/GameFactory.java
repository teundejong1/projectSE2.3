package games;

import player.PlayerType;

public class GameFactory {

    public static Game createTicTacToeGame(PlayerType startingPlayer) {
        return new TicTacToe(startingPlayer);
    }

    public static Game createOthelloGame(PlayerType startingPlayer) {
        return new Othello(startingPlayer);
    }

}
