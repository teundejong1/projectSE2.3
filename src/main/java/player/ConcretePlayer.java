package player;

import games.Game;
import games.Move;
import games.board.SetOutOfBoundsException;
import player.inputBehaviour.Input;

/**
 * @author Teun de Jong, Jeroen Lammersma
 * Class that implements Player, used for creating concrete players in the factory
 */
class ConcretePlayer implements Player {

    private final Input inputBehaviour;
    private final String name;
    private final PlayerType type;

    /**
     * Constructor for ConcretePlayer
     * @param name String name of the player
     * @param inputBehaviour inputbehaviour, i.e. inputGUI
     * @param type ONEe or TWO
     */
    public ConcretePlayer(String name, Input inputBehaviour, PlayerType type) {
        this.name = name;
        this.inputBehaviour = inputBehaviour;
        this.type = type;
    }

    /**
     * Method to request a move from the concreteplayer
     * @param game current game
     * @return Move
     * @throws SetOutOfBoundsException if the move is out of bounds
     */
    public Move requestMove(Game game) throws SetOutOfBoundsException {
        return inputBehaviour.requestMove(game);
    }

    /**
     * Getter for the name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the playertype
     * @return type
     */
    @Override
    public PlayerType getType() {
        return type;
    }


    
}
