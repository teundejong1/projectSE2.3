package networking.eventhandlers;

import java.util.Map;

import games.GameManager;
import games.IllegalMoveException;
import games.Move;
import networking.Parser;
import player.PlayerType;

public class ReceivedMoveHandler implements Handler {

    private GameManager gameManager;
    private Map<String, String> map;

    public void handle(String response) {
        System.out.println(response);
        gameManager = GameManager.getInstance();
        map = Parser.parseMap(response);
        
        Move move = getMove();
        String player = getPlayer();
        
        if (player.equals(gameManager.getPlayerTwo().getName())) {
            try {
                gameManager.doMove(move, PlayerType.TWO);
                System.out.println("do a move " + move);
            } catch (IllegalMoveException e) {
                e.printStackTrace();
            }
        }        
        
    }

    private String getPlayer() {
        return map.get("PLAYER");
    }

    private String getDetails() {
        return map.get("DETAILS");
    }

    private Move getMove() {
        int absoluteCell = Integer.parseInt(map.get("MOVE"));
        int boardSize = gameManager.getGame().getBoard().getSize();

        return createMove(absoluteCell, boardSize);
    }

    private Move createMove(int absoluteCell, int boardSize) {
        int x = absoluteCell / boardSize;
        int y = absoluteCell % boardSize;
        return new Move(x, y);
    }
    
}