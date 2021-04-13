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

public abstract class Game {

    protected Board board;
    protected GameStatus status;

    protected PlayerType playerOne;
    protected PlayerType playerTwo;
    protected PlayerType currentTurn;
    protected NetworkManager networkManager;

    protected PlayEnum playType;

    protected final AtomicBoolean running;

    public Game(PlayerType startingPlayer, PlayEnum playType) {
        playerOne = PlayerType.ONE;
        playerTwo = PlayerType.TWO;
        currentTurn = startingPlayer;
        status = GameStatus.READY;
        this.playType = playType;
        running = new AtomicBoolean();
    }

    public Game(PlayerType startingPlayer, PlayEnum playType, NetworkManager networkManager) {
        this(startingPlayer, playType);
        this.networkManager = networkManager;
    }

    public PlayerType whosTurn() {
        return currentTurn;
    }
    
    protected abstract void validateMove(Move move, Mark marker) throws IllegalMoveException;

    public abstract List<Move> getPossibleMoves();

    public abstract boolean checkForWin();

    public abstract void start(Player one, Player two) throws SetOutOfBoundsException;

    public void doMove(Move move, Mark marker) throws IllegalMoveException {
        System.out.println(move);
        validateMove(move, marker);
        
        try {
            board.setMove(move.getX(), move.getY(), marker);
        } catch (SetOutOfBoundsException ime) {
            throw new IllegalMoveException(ime);
        }
    }

    public Board getBoard() {
        return board;
    };

    public GameStatus getStatus() {
        return status;
    };

    public PlayerType getCurrentTurn() {
        return currentTurn;
    }

    public void setRunning(boolean newValue) {
        running.set(newValue);
    }

    public boolean isRunning() {
        return running.get();
    }
	
    public abstract void run () throws SetOutOfBoundsException  ;
}
