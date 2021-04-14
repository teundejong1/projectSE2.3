package networking.eventhandlers;

import java.util.Map;

import games.Othello;
import games.ai.OthelloAI;
import gui.Controller;
import gui.View;
import networking.NetworkManager;
import networking.Parser;
import player.inputBehaviour.InputAI;

public class TurnHandler implements Handler {

    public void handle(String response) {
        System.out.println(response);
        System.out.println("turn");
        Map<String, String> map = Parser.parseMap(response);
        System.out.println(map.get("YOURTURN"));
        View.ourTurn = true;
    }
    
}
