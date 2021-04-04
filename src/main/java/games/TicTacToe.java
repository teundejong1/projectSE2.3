package games;

import games.board.Mark;
import games.board.TicTacToeBoard;
import gui.FxmlTest;
import javafx.fxml.FXML;
import player.Player;
import player.PlayerType;
import player.PlayerFactory;
import threadpool.ThreadPool;

public class TicTacToe extends Game {

    public TicTacToe(PlayerType StartingPlayer) {
        super(StartingPlayer);
    }

    @Override
    protected void validateMove(int x, int y, Mark marker) throws IllegalMoveException {

        if (currentTurn == PlayerType.ONE && !(marker == Mark.ONE))
                throw new IllegalMoveException("Its not player ones turn");
        if (currentTurn == PlayerType.TWO && !(marker == Mark.TWO))
                throw new IllegalMoveException("Its not player twos turn");

        if (!board.isInBounds(x, y)) throw new IllegalMoveException("Out of bounds");

        if (!(board.getCell(x, y) == Mark.EMPTY)) throw new IllegalMoveException("Already occupied");

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
                    board.getCell(i, 2)) == true) {
                
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalForWin() {
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRowCol(board.getCell(0, i), board.getCell(1, i),
                    board.getCell(2, i)) == true) {
                
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalForWin() {
        return ((checkRowCol(board.getCell(0, 0), board.getCell(1, 1), board.getCell(2, 2)) == true) ||
                (checkRowCol(board.getCell(0, 2), board.getCell(1, 1), board.getCell(2, 0)) == true));
    }

    public void changeTurn() {
        if (currentTurn == PlayerType.ONE) currentTurn = PlayerType.TWO;
        else currentTurn = PlayerType.ONE;
    }

    public void start(Player one, Player two) {
        System.out.println("Tic Tac Toe!");
        status = GameStatus.PLAYING;
        board = new TicTacToeBoard(3);

        Move move;
        Mark mark;

        do {
            System.out.println(currentTurn);

            move = (currentTurn == PlayerType.ONE) ? one.requestMove(board) : two.requestMove(board);
            mark = (currentTurn == PlayerType.ONE ? Mark.ONE : Mark.TWO);

            try {
                doMove(move.getX(), move.getY(), mark);
            } catch (IllegalMoveException e) {
                e.printStackTrace();
            }

            if (checkForWin()) status = GameStatus.WON;
            else if (board.isFull()) status = GameStatus.DRAW;
            else changeTurn();
        } while (status == GameStatus.PLAYING);

    }

    public static void main(String[] args) {
        FxmlTest fxmlTest =  new FxmlTest();
        Thread thread =  new Thread(fxmlTest);
        thread.start();

        Player p1 = PlayerFactory.createGUIPlayer("Teun");
        Player p2 = PlayerFactory.createGUIPlayer("Esther");

        TicTacToe game = new TicTacToe(PlayerType.ONE);
        game.start(p1, p2);
    }
    
}
