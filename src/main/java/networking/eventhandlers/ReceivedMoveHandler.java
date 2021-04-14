package networking.eventhandlers;

import java.util.Map;

import games.GameManager;
import games.Move;
import gui.View;
import networking.Parser;

public class ReceivedMoveHandler implements Handler {

    private GameManager gameManager;
    private Map<String, String> map;

    public void handle(String response) {
        gameManager = GameManager.getInstance();
        map = Parser.parseMap(response);
        
        Move move = getMove();
        String player = getPlayer();

        if(!player.equalsIgnoreCase(View.spelernaam)) {
            View.remoteMove = move;
            View.remoteMoveSet = true;
        }
        
    }

    private String getPlayer() {
        return map.get("PLAYER");
    }

    private String getDetails() {
        return map.get("PLAYER");
    }

    private Move getMove() {
        int absoluteCell = Integer.parseInt(map.get("MOVE"));
        int boardSize = gameManager.getGame().getBoard().getSize();

        return createMove(absoluteCell, boardSize);
    }

    private Move createMove(int absoluteCell, int boardSize) {
        int x = absoluteCell / boardSize;
        int y = absoluteCell % boardSize;
        return new Move(x, y);
    }
    
}