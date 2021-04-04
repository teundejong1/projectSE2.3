package games;

import games.board.Board;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
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
    }

    public PlayerType whosTurn() {
        return currentTurn;
    }
    
    protected abstract void validateMove(int x, int y, Mark marker) throws IllegalMoveException;

    public abstract boolean checkForWin();

    public void doMove(int x, int y, Mark marker) throws IllegalMoveException {
        validateMove(x, y, marker);
        
        try {
            board.setMove(x, y, marker);
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
    
}
