package networking.eventhandlers;

import java.util.List;

import networking.Parser;

public class PlayerListHandler implements CommandHandler {
    
    public void handle(String response) {
        List<String> players = Parser.parseList(response);
        // doe er iets mee
    }

    public void handleError(String response) {
        
    }

}
