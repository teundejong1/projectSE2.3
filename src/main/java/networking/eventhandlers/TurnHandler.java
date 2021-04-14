package networking.eventhandlers;

import java.util.Map;

import games.GameManager;
import games.IllegalMoveException;
import games.Move;
import games.board.SetOutOfBoundsException;
import networking.NetworkManager;
import networking.Parser;
import networking.states.IllegalStateException;
import player.Player;

public class TurnHandler implements Handler {

    GameManager gm = GameManager.getInstance();
    NetworkManager nm = NetworkManager.getInstance("", 0);

    public void handle(String response) {
        System.out.println("turn");
        Map<String, String> map = Parser.parseMap(response);

        Player AIPlayer = gm.getPlayerOne();
        try {
            Move move = AIPlayer.requestMove(gm.getGame());
//            gm.doMove(move, AIPlayer.getType());
            nm.sendMove(move, gm.getGame().getBoard().getSize());

        } catch (SetOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
    
}
