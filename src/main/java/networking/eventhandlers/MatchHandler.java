package networking.eventhandlers;

import java.util.Map;

import networking.NetworkManager;
import networking.Parser;
import networking.states.PlayingState;

public class MatchHandler implements Handler {

    NetworkManager manager = NetworkManager.getInstance("", 0);
    
    public void handle(String response) {
        manager.setState(new PlayingState());
        Map<String, String> map = Parser.parseMap(response);
        // doe iets met de map
    }

}
