package networking.eventhandlers;

import java.util.List;

import networking.Parser;

public class GameListHandler implements CommandHandler {
    
    public void handle(String response) {
        List<String> games = Parser.parseList(response);
        // doe er iets mee
        System.out.println(games);
    }

    public void handleError(String response) {
        
    }

}
