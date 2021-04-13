package games;

import java.util.ArrayList;
import java.util.List;

import games.board.Mark;
import games.board.SetOutOfBoundsException;
import games.board.TicTacToeBoard;
import gui.View;
import networking.NetworkManager;
import player.PlayEnum;
import player.Player;
import player.PlayerType;
import player.PlayerFactory;

/**
 * @author Teun de Jong, Eva Jakobs
 *
 */
public class TicTacToe extends Game implements Runnable {


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

    public void changeTurn() {
        if (currentTurn == PlayerType.ONE) currentTurn = PlayerType.TWO;
        else currentTurn = PlayerType.ONE;
    }


    @Override
    public void start(Player one, Player two) throws SetOutOfBoundsException {
        System.out.println("Tic Tac Toe!");
        status = GameStatus.PLAYING;
        board = new TicTacToeBoard(3);

        Move move;
        Mark mark;

        do {
            move = (currentTurn == PlayerType.ONE) ? one.requestMove(this) : two.requestMove(this);
            mark = (currentTurn == PlayerType.ONE ? Mark.ONE : Mark.TWO);

            if(!isRunning()) {
                break;
            }

            try {
                doMove(move, mark);
                if (checkForWin()){
                    System.out.println(currentTurn + " has won!111!!!1@!");
                    System.out.println(getBoard());

                    status = GameStatus.WON;
                }
                else if (board.isFull()) status = GameStatus.DRAW;
                else changeTurn();
            } catch (IllegalMoveException e) {
                e.printStackTrace();
            }


        } while (status == GameStatus.PLAYING && isRunning());

    }


    @Override
    public void run() {
        running.set(true);
        Player p1 = PlayerFactory.createGUIPlayer("Frankenstein", GameEnum.TTT);;
        Player p2;
        if(playType == PlayEnum.PVE) {
            p2 = PlayerFactory.createAIPlayer("Monster", GameEnum.TTT);
        } else {
            p2 = PlayerFactory.createGUIPlayer("Monster", GameEnum.TTT);
        }
        
        try {
            start(p1, p2);
        } catch (SetOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}