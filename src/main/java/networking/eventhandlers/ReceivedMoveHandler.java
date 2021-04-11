package networking.eventhandlers;

import java.util.Map;

import networking.Parser;

public class ReceivedMoveHandler implements Handler {

    public void handle(String response) {
        Map<String, String> map = Parser.parseMap(response);

    }
    
}
