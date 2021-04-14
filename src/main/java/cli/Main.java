package cli;

import games.*;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.PlayEnum;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

/**
 * test class for playing the game in the CLI
 */
public class Main {

    public static void main(String[] args) {
//        Player p1 = PlayerFactory.createCLIPlayer("Teun");
//        Player p2 = PlayerFactory.createAIPlayer("Esther", GameEnum.TTT);

//        Game game = new TicTacToe(PlayerType.ONE);
//        Thread thread = new Thread(new TicTacToe(PlayerType.ONE));
        Thread thread = new Thread(new Othello(PlayerType.ONE, PlayEnum.PVE));
        thread.start();
//        game.start(p1, p2);



    }
}
