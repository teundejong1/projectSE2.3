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
        System.out.println("Received Move: " + response);
        gameManager = GameManager.getInstance();
        map = Parser.parseMap(response);
        System.out.println("BOARD NA RECEIVED MOVE: \n" + gameManager.getGame().getBoard());
        Move move = getMove();
        String player = getPlayer();

//        if (player.equals(gameManager.getPlayerTwo().getName())) {

//                System.out.println(gameManager.getGame().getBoard());
        try {
            System.out.println("Komen we voor de gameManager.doMove?");
            gameManager.doMove(move, gameManager.getGame().getCurrentTurn());
            System.out.println("Komen we er na?");
            System.out.println("BOARD NA DO MOVE: \n" + gameManager.getGame().getBoard());
            System.out.println("do a move " + move);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }

//      System.out.println(gameManager.getGame().getBoard());

            //TODO DIKKE CHECK VOOR DUBBEL TURNS

//        else System.out.println("player is not equal to gamemanagergetplayertwo");
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