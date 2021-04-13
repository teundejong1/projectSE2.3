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
//        Map<String, String> map = Parser.parseMap(response);
//      System.out.println("Move handler ");
        // parse naar Move object (moet nog helper functie komen om absoluut naar Move te converten)
//      View.moveSet = true;
//      View.ourTurn = false;
//

    }

    public void handleError(String response) {

    }

}
