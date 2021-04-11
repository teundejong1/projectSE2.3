package player;

import games.GameEnum;
import gui.View;
import player.inputBehaviour.*;

public class PlayerFactory {
    
    
    public static Player createAIPlayer(String name, GameEnum game) {
        return new ConcretePlayer(name, new InputAI(game));
    }

    public static Player createGUIPlayer(String name, GameEnum gameEnum) {
        InputGUI inputGUI = new InputGUI();
        switch (gameEnum) {
            case TTT:
                inputGUI = new InputGUITTT();
                break;
            case OTHELLO:
                inputGUI =  new InputGUIOthello();
                break;
        }

        return new ConcretePlayer(name, inputGUI);
    }
    
    public static Player createCLIPlayer(String name) {
        return new ConcretePlayer(name, new InputCLI());
    }

    public static Player createRemotePlayer(String name) {
        return new ConcretePlayer(name, new InputRemote());
    }
    
}
