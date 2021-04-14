package networking.commands;

import games.GameEnum;
/**
 * Commandclass to challange another player for a match. 
 * @author Jeroen Lammersma
 */

public class ChallengePlayerCommand implements Command {

    private String opponent;
    private String game;

    /**
     * contructor 
     * @param opponent player you want to play against
     * @param game type of game you want to play against the other player
     */
    public ChallengePlayerCommand(String opponent, GameEnum game) {
        this.opponent = opponent;
        switch(game){
            case OTHELLO:
                this.game = "Reversi";
                break;
            case TTT:
                this.game = "Tic-tac-toe";
        }
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
