package networking.eventhandlers;

import java.util.Map;

import networking.Parser;

public class MatchHandler implements Handler {
    
    public void handle(String response) {
        Map<String, String> map = Parser.parseMap(response);
        // doe iets met de map
    }

}
