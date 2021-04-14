package networking.eventhandlers;

import java.util.Map;

import games.Game;
import games.GameManager;
import games.IllegalMoveException;
import games.Move;
import networking.Parser;
import player.PlayerType;

public class ReceivedMoveHandler implements Handler {

    private GameManager gameManager = GameManager.getInstance();
    private Map<String, String> map;
    Game game = gameManager.getGame();

    public void handle(String response) {
        System.out.println(response);
        System.out.println(game.getBoard());
        map = Parser.parseMap(response);
        
        Move move = getMove();
        String player = getPlayer();
        
        try {
            System.out.println("!!!!! do move " + move + " " + gameManager.getGame().getCurrentTurn());
            gameManager.doMove(move, gameManager.getGame().getCurrentTurn());
            System.out.println("do a move " + move);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
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