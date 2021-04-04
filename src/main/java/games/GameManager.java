package games;

import player.Player;
import player.PlayerType;

public class GameManager {

    private Game game;
    private Player playerOne;
    private Player playerTwo;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player, PlayerType playerType) {
        switch(playerType) {
            case ONE: 
                playerOne = player;
                break;
            case TWO:
                playerTwo = player;
                break;
        }
    }

    public void start() {

    }

    public boolean isReady() {
        return (game != null) && (playerOne != null) && (playerTwo != null);
    }


    public static void main(String[] args) {
        PlayerType pt;

        
    }
    
}
