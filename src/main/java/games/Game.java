package games;

import games.board.Board;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.PlayerEnum;

public abstract class Game {

    protected Board board;
    protected GameStatus status;

    protected PlayerEnum playerOne;
    protected PlayerEnum playerTwo;
    protected PlayerEnum currentTurn;

    public Game(PlayerEnum startingPlayer) {
        playerOne = PlayerEnum.ONE;
        playerTwo = PlayerEnum.TWO;
        currentTurn = startingPlayer;
    }

    public PlayerEnum whosTurn() {
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
