package networking.eventhandlers;

import java.util.Map;

import games.GameManager;
import games.Move;
import gui.View;
import networking.Parser;

/**
 * Handles Moves received from the server
 * @author Jeroen Lammersma, Esther Zigterman Rustenburg, Tom Beugels, Eva Jakobs
 */
public class ReceivedMoveHandler implements Handler {

    private GameManager gameManager;
    private Map<String, String> map;

    public void handle(String response) {
        System.out.println(response);
        gameManager = GameManager.getInstance();

        map = Parser.parseMap(response);

        // stuur move naar gameManager
        gameManager.receiveMove(getMove(), getPlayer());

        updateView();
        
    }

    /**
     * Updates the view for when it's a remote move set
     */
    private void updateView(){
        // als de speler
        if(!getPlayer().equalsIgnoreCase(View.spelernaam)) {
            View.remoteMove = getMove();
            View.remoteMoveSet = true;
        }
    }

    /**
     * @return player that made the move
     */
    private String getPlayer() {
        return map.get("PLAYER");
    }

    /**
     * @return Move for the board
     */
    private Move getMove() {
        int absoluteCell = Integer.parseInt(map.get("MOVE"));
        int boardSize = gameManager.getGame().getBoard().getSize();

        return createMove(absoluteCell, boardSize);
    }

    /**
     *
     * @param absoluteCell value of cell returned from the server
     * @param boardSize size of the playingboard
     * @return Move for local board
     */
    private Move createMove(int absoluteCell, int boardSize) {
        int x = absoluteCell / boardSize;
        int y = absoluteCell % boardSize;
        return new Move(x, y);
    }
    
}