package player;

import games.Game;
import games.Move;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.inputBehaviour.Input;

class ConcretePlayer implements Player {

    private final Input inputBehaviour;
    private final String name;
    private final PlayerType type;
    private final Mark mark;

    public ConcretePlayer(String name, Input inputBehaviour, PlayerType type, Mark mark) {
        this.name = name;
        this.inputBehaviour = inputBehaviour;
        this.type = type;
        this.mark = mark;
    }
    
    public Move requestMove(Game game) throws SetOutOfBoundsException {
        return inputBehaviour.requestMove(game);
    }

    public String getName() {
        return name;
    }

    @Override
    public PlayerType getType() {
        return type;
    }

    @Override
    public Mark getMark() {
        return mark;
    }


    
}
