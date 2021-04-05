package cli;

import games.*;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

public class Main {
    public static void main(String[] args) throws SetOutOfBoundsException {
        Player p1 = PlayerFactory.createCLIPlayer("Teun");
        Player p2 = PlayerFactory.createCLIPlayer("Esther");

        //Game game = new TicTacToe(PlayerType.ONE);
        //game.start(p1, p2);

        //Game game = new TicTacToe(PlayerType.ONE);
        Game game = new Othello(PlayerType.ONE);
        game.start(p1, p2);


    }
}
