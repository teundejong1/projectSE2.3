package cli;

import games.*;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

public class Main {

    public static void main(String[] args) {
//        Player p1 = PlayerFactory.createCLIPlayer("Teun");
//        Player p2 = PlayerFactory.createAIPlayer("Esther", GameEnum.TTT);

//        Game game = new TicTacToe(PlayerType.ONE);
        Thread thread = new Thread(new TicTacToe(PlayerType.ONE));
        thread.start();
//        game.start(p1, p2);



    }
}
