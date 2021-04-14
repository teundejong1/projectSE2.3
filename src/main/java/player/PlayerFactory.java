package player;

import games.GameEnum;
import player.inputBehaviour.*;

/**
 * @author Jeroen Lammersma, Teun de Jong
 * Class Playerfactory, used for creating players to play the game with
 */
public class PlayerFactory {

    /**
     * Static method to create AI player
     * @param name String name
     * @param game GameEnum game
     * @param type Playertype
     * @return new AI player
     */
    public static Player createAIPlayer(String name, GameEnum game, PlayerType type) {
        return new ConcretePlayer(name, new InputAI(game), type);
    }

    /**
     * Static method to create GUI player
     * @param name String name
     * @param gameEnum GameEnum game
     * @param type Playertype
     * @return new GUI player
     */
    public static Player createGUIPlayer(String name, GameEnum gameEnum, PlayerType type) {
        InputGUI inputGUI = new InputGUI();
        switch (gameEnum) {
            case TTT:
                inputGUI = new InputGUITTT();
                break;
            case OTHELLO:
                inputGUI = new InputGUIOthello();
                break;
        }
        return new ConcretePlayer(name, inputGUI, type);
    }

    /**
     * Static method to create CLI player
     * @param name String name
     * @param type PlayerType
     * @return new CLI player
     */
    public static Player createCLIPlayer(String name, PlayerType type) {
        return new ConcretePlayer(name, new InputCLI(), type);
    }

    /**
     * Static method to create Remote Player
     * @param name String name
     * @param type Playertype
     * @return new Remote player
     */
    public static Player createRemotePlayer(String name, PlayerType type) {
        return new ConcretePlayer(name, new InputRemote(), type);
    }
    
}
