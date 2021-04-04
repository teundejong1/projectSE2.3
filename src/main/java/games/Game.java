package games;

import java.util.List;

import games.board.Board;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.Player;
import player.PlayerType;

public abstract class Game {

    protected Board board;
    protected GameStatus status;

    protected PlayerType playerOne;
    protected PlayerType playerTwo;
    protected PlayerType currentTurn;

    public Game(PlayerType startingPlayer) {
        playerOne = PlayerType.ONE;
        playerTwo = PlayerType.TWO;
        currentTurn = startingPlayer;
        status = GameStatus.READY;
    }

    public PlayerType whosTurn() {
        return currentTurn;
    }
    
    protected abstract void validateMove(Move move, Mark marker) throws IllegalMoveException;

    public abstract List<Move> getPossibleMoves();

    public abstract boolean checkForWin();

    public abstract void start(Player one, Player two);

    public void doMove(Move move, Mark marker) throws IllegalMoveException {
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

    public abstract void run();
}
