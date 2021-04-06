package player;

import games.Game;
import games.Move;
import player.inputBehaviour.Input;

class ConcretePlayer implements Player {

    private final Input inputBehaviour;
    private final String name;

    public ConcretePlayer(String name, Input inputBehaviour) {
        this.name = name;
        this.inputBehaviour = inputBehaviour;
    }
    
    public Move requestMove(Game game) {
        return inputBehaviour.requestMove(game);
    }

    public String getName() {
        return name;
    }


    
}
