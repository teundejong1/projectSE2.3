package networking.eventhandlers;

import java.util.Map;

import networking.Parser;

public class ReceivedChallengeHandler implements Handler {

    private Map<String, String> map;

    public void handle(String response) {
        map = Parser.parseMap(response);

    }

}