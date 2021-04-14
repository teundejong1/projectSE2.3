package games;

import java.util.concurrent.ThreadPoolExecutor;

import games.board.Mark;
import gui.View;
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

    private GameManager() {}

    public Game getGame() {
        return game;
    }

    /**
     * For placing a move locally
     * @param move
     * @param type
     * @throws IllegalMoveException
     */
    public void doMove(Move move, PlayerType type) throws IllegalMoveException {
        Mark mark = Mark.ONE;
        if (type == PlayerType.TWO) {
            mark = Mark.TWO;
        }

        game.doMove(move, mark);

        if(game.getClass() == Othello.class) {
            View.othelloRefresh(game);
        } else if(game.getClass() == TicTacToe.class) {
            View.tttRefresh(game);
        }

//        System.out.println(game.getBoard()); //  1 print
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

        game.init();

    }

    public void forfeit() {
        //TODO set on loss if that's acceptable
        game.setStatus(GameStatus.WON);
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
