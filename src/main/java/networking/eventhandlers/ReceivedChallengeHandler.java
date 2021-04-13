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
        View.startOnlineMatch(gameType);

    }
    
}
