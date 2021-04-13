package networking.eventhandlers;
import java.util.Map;

import games.GameEnum;
import gui.View;
import networking.Parser;
import player.PlayEnum;

public class ReceivedChallengeHandler implements Handler {

    public void handle(String response) {
        Map map = Parser.parseMap(response);
        GameEnum gameType = (GameEnum) map.get("GAMETYPE");
        int challengeNumber = Integer.parseInt((String) map.get("CHALLENGENUMBER"));
        View.challengeReceived((String) map.get("CHALLENGER"), challengeNumber);
//        View.startOnlineMatch(gameType);

    }
    
}
