package networking.eventhandlers;

import java.util.List;

import gui.View;
import networking.Parser;

public class PlayerListHandler implements CommandHandler {
    
    public void handle(String response) {
        List<String> players = Parser.parseList(response);
//        System.out.println("ben aan't hand'len");
        View.refreshLobby(players);

    }

    public void handleError(String response) {
        System.out.println(response);
    }

}
