package networking.eventhandlers;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        System.out.println("sleep 1 sec");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        System.out.println("do turn event now");
        Map<String, String> map = Parser.parseMap(response);

        Player AIPlayer = gm.getPlayerOne();
        try {
            Move move = AIPlayer.requestMove(gm.getGame());
            // gm.doMove(move, AIPlayer.getType());
            nm.sendMove(move, gm.getGame().getBoard().getSize());

        } catch (SetOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }
    
}
