package networking.eventhandlers;

import games.IllegalMoveException;
import games.Move;
import gui.View;
import networking.Parser;
import java.util.Map;
import games.Game;
import games.Othello;



public class MoveHandler implements CommandHandler {
    
    public void handle(String response) {
        Map map = Parser.parseMap(response);
        // parse naar Move object (moet nog helper functie komen om absoluut naar Move te converten)
        String player = (String) map.get("PLAYER");
        if(!player.equalsIgnoreCase(View.spelernaam)) {
            Move move = (Move) map.get("MOVE");
            View.remoteMove = move;
            View.remoteMoveSet = true;

        }

    }

    public void handleError(String response) {

    }

    private Move createMove(int absoluteCell, int boardSize) {
        int x = absoluteCell / boardSize;
        int y = absoluteCell % boardSize;
        return new Move(x, y);
    }

}
