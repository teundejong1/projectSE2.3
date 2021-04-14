package player;

import games.GameEnum;
import player.inputBehaviour.*;

public class PlayerFactory {
    
    
    public static Player createAIPlayer(String name, GameEnum game, PlayerType type) {
        return new ConcretePlayer(name, new InputAI(game), type);
    }

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
    
    public static Player createCLIPlayer(String name, PlayerType type) {
        return new ConcretePlayer(name, new InputCLI(), type);
    }

    public static Player createRemotePlayer(String name, PlayerType type) {
        return new ConcretePlayer(name, new InputRemote(), type);
    }
    
}
