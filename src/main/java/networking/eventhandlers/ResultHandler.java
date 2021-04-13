package networking.eventhandlers;

import java.util.Map;

import games.Result;
import networking.NetworkManager;
import networking.Parser;
import networking.states.LoggedInState;

public class ResultHandler implements Handler {

    NetworkManager manager = NetworkManager.getInstance("", 0);

    public void handle(String response) {
        manager.setState(new LoggedInState());

        Result result = getResult(response);
        Map<String, String> map = Parser.parseMap(response);

    }

    private Result getResult(String response) {
        String[] words = response.split(" ");
        String result = words[2];
        
        if (result.equalsIgnoreCase("WIN")) return Result.WIN;
        if (result.equalsIgnoreCase("LOSS")) return Result.LOSS;
        return Result.DRAW;
    }
    
}
