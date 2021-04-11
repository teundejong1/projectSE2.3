package networking.commands;

import games.GameEnum;
/**
 * Commandclass to challange another player for a match. 
 * @author Jeroen Lammersma
 */

public class ChallengePlayerCommand implements Command {

    private String opponent;
    private GameEnum game;

    /**
     * contructor 
     * @param opponent player you want to play against
     * @param game type of game you want to play against the other player
     */
    public ChallengePlayerCommand(String opponent, GameEnum game) {
        this.opponent = opponent;
        this.game = game;
    }

    /**
     * Check to confirm server received command correctly
     * @Override
     */
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isReponseTwoLines() {
        return false;
    }

    @Override
    public boolean isValidResponse(String response) {
        return response.equalsIgnoreCase("OK");
    }

    /**
     * writes to string to write the command properly
     * @Override
     */
    public String toString() {
        return String.format("CHALLENGE \"%s\" \"%s\"", opponent, game);
    }
    
}
