package networking.commands;

import games.GameEnum;
/**
 * Commandclass for subscribing to a specific game
 * @author Jeroen Lammersma
 */
public class SubscribeCommand implements Command {

    private GameEnum game;

    public SubscribeCommand(GameEnum game) {
        this.game = game;
    }

    /**
     * Check to confirm server received command correctly
     * @Override
     */
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    /**
     * writes the command to be send into string form
     * @Override
     */
    public String toString() {
        return String.format("SUBSCRIBE %s", getGameString());
    }

    /**
     * 
     * @return gameString for the wanted game from the Enum to what the server understands
     */
    private String getGameString() {
        String gameString = "";

        switch (game) {
            case TTT:
                gameString = "Tic-tac-toe";
                break;
            case OTHELLO:
                gameString = "Reversi";
                break;
        }

        return gameString;
    }

}
