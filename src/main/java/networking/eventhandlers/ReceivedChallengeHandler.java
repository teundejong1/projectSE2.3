package networking.eventhandlers;
import java.util.Map;

import games.GameEnum;
import gui.View;
import networking.Parser;
import player.PlayEnum;

public class ReceivedChallengeHandler implements Handler {

    public void handle(String response) {
        GameEnum gameEnum;
//        System.out.println("Testttt");
        Map map = Parser.parseMap(response);
//        System.out.println("Map geparst");
        String gameType =  (String) map.get("GAMETYPE");
        if(gameType.equalsIgnoreCase("reversi")) {
            gameEnum = GameEnum.OTHELLO;
        }
        else {
            gameEnum = GameEnum.TTT;
        }
//        System.out.println("we hebben een gametype");
        int challengeNumber = Integer.parseInt((String) map.get("CHALLENGENUMBER"));
//        System.out.println("en een challengenumber");
        View.challengeReceived((String) map.get("CHALLENGER"), challengeNumber);
//        View.startOnlineMatch(gameType);

    }
    
}
