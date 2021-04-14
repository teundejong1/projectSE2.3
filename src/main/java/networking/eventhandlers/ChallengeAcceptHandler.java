package networking.eventhandlers;

import games.GameEnum;
import gui.View;
import networking.Parser;

import java.util.Map;

public class ChallengeAcceptHandler implements CommandHandler {

    public void handle(String response) {
        // doe er iets mee
//        Map map = Parser.parseMap(response);
//        GameEnum gameType = (GameEnum) map.get("GAMETYPE");
//        int challengeNumber = Integer.parseInt((String) map.get("CHALLENGENUMBER"));
//        View.challengeReceived((String) map.get("CHALLENGER"), challengeNumber);

    }
    
    public void handleError(String response) {
        
    }
    
}
