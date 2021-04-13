package games;

import player.PlayEnum;
import player.PlayerType;

public class GameFactory {


    public static Game createGame(PlayerType startingPlayer, GameEnum game) {
        switch (game) {
            case TTT: return new TicTacToe(startingPlayer);
            case OTHELLO: return new Othello(startingPlayer);
            default: return null;
        }
    }

}
