package player;

import games.GameEnum;
import games.board.Mark;
import player.inputBehaviour.*;

public class PlayerFactory {
    
    
    public static Player createAIPlayer(String name, GameEnum game, PlayerType type, Mark mark) {
        return new ConcretePlayer(name, new InputAI(game), type, mark);
    }

    public static Player createGUIPlayer(String name, GameEnum gameEnum, PlayerType type, Mark mark) {
        InputGUI inputGUI = new InputGUI();
        switch (gameEnum) {
            case TTT:
                inputGUI = new InputGUITTT();
                break;
            case OTHELLO:
                inputGUI = new InputGUIOthello();
                break;
        }

        return new ConcretePlayer(name, inputGUI, type, mark);
    }
    
    public static Player createCLIPlayer(String name, PlayerType type, Mark mark) {
        return new ConcretePlayer(name, new InputCLI(), type, mark);
    }

    public static Player createRemotePlayer(String name, PlayerType type, Mark mark) {
        return new ConcretePlayer(name, new InputRemote(), type, mark);
    }
    
}
