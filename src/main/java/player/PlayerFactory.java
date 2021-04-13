package player;

import games.GameEnum;
import player.inputBehaviour.InputAI;
import player.inputBehaviour.InputCLI;
import player.inputBehaviour.InputGUI;
import player.inputBehaviour.InputRemote;

public class PlayerFactory {
    
    
    public static Player createAIPlayer(String name, GameEnum game) {
        return new ConcretePlayer(name, new InputAI(game));
    }

    public static Player createGUIPlayer(String name) {
        return new ConcretePlayer(name, new InputGUI());
    }
    
    public static Player createCLIPlayer(String name) {
        return new ConcretePlayer(name, new InputCLI());
    }

    public static Player createRemotePlayer(String name) {
        return new ConcretePlayer(name, new InputRemote());
    }
    
}
