package games;

import player.Player;
import player.PlayerType;

public class GameManager {

    private volatile static GameManager manager;

    private Game game;
    private Player playerOne;
    private Player playerTwo;

    public static GameManager getInstance() {
        if (manager == null) {
            synchronized (GameManager.class) {
                if (manager == null) {
                    manager = new GameManager();
                }
            }
        }

        return manager;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerOne;
    }

    public Game createGame(PlayerType startingPlayer, GameEnum game,
            Player playerOne, Player playerTwo) {
        
        this.game = GameFactory.createGame(startingPlayer, game);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        return this.game;
    }
    
    public void start() throws NotReadyException, IllegalGameStateException {
        if (!isGameReady()) throw new NotReadyException("Game is null");
        if (!isPlayerOneReady()) throw new NotReadyException("PlayerOne is null");
        if (!isPlayerTwoReady()) throw new NotReadyException("PlayerTwo is null");

        game.start(playerOne, playerTwo);
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

    public static void main(String[] args) {
        PlayerType pt;

        
    }
    
}
