package networking.eventhandlers;

import java.util.Map;

import games.GameManager;
import games.GameStatus;
import networking.NetworkManager;
import networking.Parser;
import networking.states.LoggedInState;

public class ResultHandler implements Handler {

    GameManager gameManager = GameManager.getInstance();
    NetworkManager networkManager = NetworkManager.getInstance("", 0);
    Map<String, String> map;

    public void handle(String response) {
        networkManager.setState(new LoggedInState());
        map = Parser.parseMap(response);

        gameManager.getGame().setStatus(getResult(response));

    }

    private GameStatus getResult(String response) {
        String[] words = response.split(" ");
        String result = words[2];
        
        if (result.equalsIgnoreCase("WIN")) return GameStatus.WON;
        if (result.equalsIgnoreCase("LOSS")) return GameStatus.LOSS;
        return GameStatus.DRAW;
    }
    
}
