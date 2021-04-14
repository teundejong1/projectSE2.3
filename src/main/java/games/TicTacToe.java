package games;

import java.util.ArrayList;
import java.util.List;

import games.board.Mark;
import games.board.TicTacToeBoard;
import networking.NetworkManager;
import player.PlayEnum;
import player.PlayerType;

/**
 * @author Teun de Jong, Eva Jakobs
 *
 */
public class TicTacToe extends Game {


    public TicTacToe(PlayerType StartingPlayer, PlayEnum playType) {
        super(StartingPlayer, playType);
    }

    public TicTacToe(PlayerType StartingPlayer, PlayEnum playType, NetworkManager networkManager) {
        super(StartingPlayer, playType, networkManager);
    }

    @Override
    protected void validateMove(Move move, Mark marker) throws IllegalMoveException {
        int x = move.getX();
        int y = move.getY();

        if (currentTurn == PlayerType.ONE && !(marker == Mark.ONE))
            throw new IllegalMoveException("Its not player ones turn");
        if (currentTurn == PlayerType.TWO && !(marker == Mark.TWO))
            throw new IllegalMoveException("Its not player twos turn");

        if (!board.isInBounds(x, y)) throw new IllegalMoveException("Out of bounds");

        if (!(board.getCell(x, y) == Mark.EMPTY)) throw new IllegalMoveException("Already occupied");
    }

    @Override
    public List<Move> getPossibleMoves() {
        List<Move> possibleMoves = new ArrayList<>();

        // TODO remove for-for loop, implement Iterator

        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                Mark mark = board.getCell(x, y);
                if (mark == Mark.EMPTY) possibleMoves.add(new Move(x, y));
            }
        }

        return possibleMoves;
    }

    @Override
    public void doMove(Move move, Mark marker) throws IllegalMoveException {
        super.doMove(move, marker);

        if (checkForWin()) {
            status = GameStatus.WON;
            running.set(false);
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
            running.set(false);
        } else changeTurn();
    }

    @Override
    public boolean checkForWin() {
        return (checkHorizonForWin() || checkVerticalForWin() || checkDiagonalForWin());
    }

    private boolean checkRowCol(Mark m1, Mark m2, Mark m3) {
        return ((m1 != Mark.EMPTY) && (m1 == m2) && (m2 == m3));
    }

    private boolean checkHorizonForWin() {
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRowCol(board.getCell(i, 0), board.getCell(i, 1),
                    board.getCell(i, 2))) {

                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalForWin() {
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRowCol(board.getCell(0, i), board.getCell(1, i),
                    board.getCell(2, i))) {

                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalForWin() {
        return ((checkRowCol(board.getCell(0, 0), board.getCell(1, 1), board.getCell(2, 2))) ||
                (checkRowCol(board.getCell(0, 2), board.getCell(1, 1), board.getCell(2, 0))));
    }

    @Override
    public void init() throws IllegalGameStateException {
        status = GameStatus.PLAYING;
        board = new TicTacToeBoard(3);
    }
}