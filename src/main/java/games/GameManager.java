package games;

import java.util.concurrent.ThreadPoolExecutor;

import player.PlayEnum;
import player.Player;
import player.PlayerType;
import threadpool.ThreadPool;

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
            Player playerOne, Player playerTwo, PlayEnum playType) {
        
        this.game = GameFactory.createGame(startingPlayer, game, playType);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        return this.game;
    }
    
    public void start() throws NotReadyException, IllegalGameStateException {
        if (!isGameReady()) throw new NotReadyException("Game is null");
        if (!isPlayerOneReady()) throw new NotReadyException("PlayerOne is null");
        if (!isPlayerTwoReady()) throw new NotReadyException("PlayerTwo is null");

        ThreadPoolExecutor executor = ThreadPool.getInstance();

        executor.submit(() -> {
            try {
                game.start(playerOne, playerTwo);
            } catch (IllegalGameStateException e) {
                // Stop the game
                e.printStackTrace();
            }
        });
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
    
}
