package games;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import games.board.Board;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import networking.NetworkManager;
import player.PlayEnum;
import player.Player;
import player.PlayerType;

/**
 * @author Jeroen Lammersma, Teun de Jong
 * Abstract class Game
 */
public abstract class Game {

    protected Board board;
    protected GameStatus status;

    protected PlayerType playerOne;
    protected PlayerType playerTwo;
    protected PlayerType currentTurn;
    protected NetworkManager networkManager;

    protected PlayEnum playType;

    protected final AtomicBoolean running;

    /**
     * Constructor for Game with 2 parameters, used for offline play
     * @param startingPlayer the starting player
     * @param playType PlayEnum of the playtype (Currently TicTacToe or Othello)
     */
    public Game(PlayerType startingPlayer, PlayEnum playType) {
        playerOne = PlayerType.ONE;
        playerTwo = PlayerType.TWO;
        currentTurn = startingPlayer;
        status = GameStatus.READY;
        this.playType = playType;
        running = new AtomicBoolean();
    }

    /**
     * Constructor for Game with 3 parameters, used for online play
     * @param startingPlayer the starting player
     * @param playType PlayEnum of the playtype (Currently TicTacToe or Othello)
     * @param networkManager the networkmanager
     */
    public Game(PlayerType startingPlayer, PlayEnum playType, NetworkManager networkManager) {
        this(startingPlayer, playType);
        this.networkManager = networkManager;
    }

    /**
     * Method used to return who's turn it currently os
     * @return Playertype currentTurn
     */
    public PlayerType whosTurn() {
        return currentTurn;
    }

    /**
     * abstrac method to validate moves. Check if a move is a legit move.
     * @param move The move to validate
     * @param marker to which the move belongs
     * @throws IllegalMoveException if the move is out of bounds
     */
    protected abstract void validateMove(Move move, Mark marker) throws IllegalMoveException;

    /**
     * Absrtact method used to return the possible moves for the current player
     * @return List containing the possible moves
     */
    public abstract List<Move> getPossibleMoves();

    /**
     * Abstract method to check for a win in this current game
     * @return Boolean True if there is a win, otherwise False
     */
    public abstract boolean checkForWin();

    /**
     * Abstract method used to start the game
     * @param one Player one
     * @param two Player two
     * @throws SetOutOfBoundsException if a move is out of bounds
     */
    public abstract void start(Player one, Player two) throws IllegalGameStateException;

    /**
     * Method used to do a move on the board
     * @param move Move to do on the board
     * @param marker The marker of the player to do a move
     * @throws IllegalMoveException if the move is illegal
     */
    public void doMove(Move move, Mark marker) throws IllegalMoveException {
        System.out.println(move);
        validateMove(move, marker);
        
        try {
            board.setMove(move.getX(), move.getY(), marker);
        } catch (SetOutOfBoundsException ime) {
            throw new IllegalMoveException(ime);
        }
    }

    /**
     * Method used to return the board
     * @return Board board
     */
    public Board getBoard() {
        return board;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Method used to return the gamestatus
     * @return Gamestatus status
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Method used to get the current player
     * @return Playertype current player
     */
    public PlayerType getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Method used to set the Running state of the game
     * @param newValue boolean
     */
    public void setRunning(boolean newValue) {
        running.set(newValue);
    }

    /**
     * Method used to check if the game is running
     * @return boolean Tru if the game is running
     */
    public boolean isRunning() {
        return running.get();
    }

}
