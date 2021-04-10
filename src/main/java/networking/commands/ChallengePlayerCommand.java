package networking.commands;

import games.GameEnum;

public class ChallengePlayerCommand implements Command {

    private String opponent;
    private GameEnum game;

    public ChallengePlayerCommand(String opponent, GameEnum game) {
        this.opponent = opponent;
        this.game = game;
    }

    @Override
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        return String.format("CHALLENGE %s %s", opponent, game);
    }
    
}
