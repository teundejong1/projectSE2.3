package networking.eventhandlers;

import java.util.Map;

import games.Move;
import networking.Parser;

public class MoveHandler implements CommandHandler {
    
    public void handle(String response) {
        Map<String, String> map = Parser.parseMap(response);
        // parse naar Move object (moet nog helper functie komen om absoluut naar Move te converten)
    }

    public void handleError(String response) {

    }

    private Move createMove(int absoluteCell, int boardSize) {
        int x = absoluteCell / boardSize;
        int y = absoluteCell % boardSize;
        return new Move(x, y);
    }

}
