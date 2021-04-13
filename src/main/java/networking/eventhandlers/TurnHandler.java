package networking.eventhandlers;

import java.util.Map;

import networking.Parser;

public class TurnHandler implements Handler {

    public void handle(String response) {
        Map<String, String> map = Parser.parseMap(response);
        System.out.println(map.get("YOURTURN"));
    }
    
}
