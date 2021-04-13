package networking.eventhandlers;

import java.util.Map;

import games.Move;
import gui.View;
import networking.Parser;

public class ReceivedMoveHandler implements Handler {

    public void handle(String response) {
        Map<String, String> map = Parser.parseMap(response);
        String player = map.get("PLAYER");
        if(!player.equalsIgnoreCase(View.spelernaam)) {
            Move move = createMove(Integer.parseInt(map.get("MOVE")), View.OTHELLO_SIZE);
            View.remoteMove = move;
            View.remoteMoveSet = true;
        }
    }


    private Move createMove(int absoluteCell, int boardSize) {
        int x = absoluteCell / boardSize;
        int y = absoluteCell % boardSize;
        return new Move(x, y);
    }
    
}
