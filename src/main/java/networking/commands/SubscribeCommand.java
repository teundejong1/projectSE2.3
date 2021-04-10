package networking.commands;

import games.GameEnum;

public class SubscribeCommand implements Command {

    private GameEnum game;

    public SubscribeCommand(GameEnum game) {
        this.game = game;
    }

    @Override
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        return String.format("SUBSCRIBE %s", getGameString());
    }

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
