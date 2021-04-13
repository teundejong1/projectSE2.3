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

    public void start() throws NotReadyException {
        if (!isGameReady()) throw new NotReadyException("Game is null");
        if (!isPlayerOneReady()) throw new NotReadyException("PlayerOne is null");
        if (!isPlayerTwoReady()) throw new NotReadyException("PlayerTwo is null");

        game.start(playerOne, playerTwo);
    }

    private boolean isReady() {
        return (game != null) && (playerOne != null) && (playerTwo != null);
    }

    private boolean isGameReady() {
        return game != null;
    }

    private boolean isPlayerOneReady() {
        return playerOne != null;
    }

    private boolean isPlayerTwoReady() {
        return playerTwo != null;
    }

    public void createGame(PlayerType startingPlayer, GameEnum game,
            Player playerOne, Player playerTwo) {
        
        this.game = GameFactory.createGame(startingPlayer, game);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public static void main(String[] args) {
        PlayerType pt;

    }

}
