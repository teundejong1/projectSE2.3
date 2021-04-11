package networking.eventhandlers;

import java.util.Map;

import games.Result;
import networking.Parser;

public class ResultHandler implements Handler {

    public void handle(String response) {
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
