package games;

import java.util.concurrent.ThreadPoolExecutor;

import games.board.Mark;
import networking.NetworkManager;
import networking.states.IllegalStateException;
import player.PlayEnum;
import player.Player;
import player.PlayerType;
import threadpool.ThreadPool;

/**
 * Class that handles communication between Game, Server, and GUI
 * @author Jeroen Lammersma, Esther Zigterman Rustenburg
 */
public class GameManager {

    private volatile static GameManager manager;

    private Game game;
    private Player playerOne;
    private Player playerTwo;

    /**
     * Singleton for gameManger object.
     * @return gameManager
     */
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

    /**
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return PlayerOne
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    /**
     * @return PlayerTwo
     */
    public Player getPlayerTwo() {
        return playerOne;
    }

    /**
     * creates a new game
     * @param startingPlayer playerType ONE or TWO depending on who starts
     * @param game Enum of game to be played
     * @param playerOne Player set as player one
     * @param playerTwo Player set as player two
     * @param playType what plays against what. (PvP, OnlinevAI, etc)
     * @return a new game ready to be played
     */
    public Game createGame(PlayerType startingPlayer, GameEnum game,
            Player playerOne, Player playerTwo, PlayEnum playType) {
        
        this.game = GameFactory.createGame(startingPlayer, game, playType);
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        return this.game;
    }

    /**
     * starts a game if game and players exist.
     * @throws NotReadyException if the game or one of the players doesn't exist
     */
    public void start() throws NotReadyException {
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

    /**
     * Method to send move to the server
     * @param move to be made
     * @param boardSize size of the board
     */
    public void sendMove(Move move, int boardSize) {
        //TODO Roep aan in de game of door de speler
        NetworkManager nManager = NetworkManager.getInstance("", 0);
        try{
            nManager.sendMove(move, boardSize);
        } catch (IllegalStateException e){
           e = new IllegalStateException("Wrong State" + nManager.getState());
//           System.out.println(e);
        }

    }

    /**
     * receive move from server and send it to the game
     * @param move to be placed on the board
     * @param playerName name of player making a move
     */
    public void receiveMove(Move move, String playerName){
        // getting the current player
        Player current = (playerOne.getName().equalsIgnoreCase(playerName)) ? playerOne : playerTwo;

        // Speler 1 heeft altijd mark 1 en speler 2 mark 2.
        Mark mark = (playerOne.getName().equalsIgnoreCase(playerName)) ? Mark.ONE : Mark.TWO;

        try {
            game.doMove(move, mark);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return false if game doesn't exist (is null)
     */
    private boolean isGameReady() {
        return game != null;
    }

    /**
     * @return false if playerOne doesn't exist (is null)
     */
    private boolean isPlayerOneReady() {
        return playerOne != null;
    }

    /**
     * @return false if playerTwo doesn't exist (is null)
     */
    private boolean isPlayerTwoReady() {
        return playerTwo != null;
    }
    
}
