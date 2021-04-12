package networking.eventhandlers;

import games.Move;

public class MoveHandler implements CommandHandler {
    
    public void handle(String response) {
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
