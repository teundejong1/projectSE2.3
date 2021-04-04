package cli;

import games.Game;
import games.GameEnum;
import games.TicTacToe;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

public class Main {
    public static void main(String[] args) {
        Player p1 = PlayerFactory.createCLIPlayer("Teun");
        Player p2 = PlayerFactory.createAIPlayer("Esther", GameEnum.TTT);

        Game game = new TicTacToe(PlayerType.ONE);
        game.start(p1, p2);
    }
}