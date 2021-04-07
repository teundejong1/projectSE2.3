package games;

import games.ai.OthelloAI;
import games.board.Mark;
import games.board.OthelloBoard;
import games.board.SetOutOfBoundsException;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Othello extends Game implements Runnable {

    public Othello(PlayerType startingPlayer) {
        super(startingPlayer);
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
        System.out.println(move.getX());
        System.out.println(move.getY());
        //System.out.println(getPossibleMoves());
        if (!isLegitMove(move)) throw new IllegalMoveException("Move mag niet uitgevoerd worden");
//        if (!(getPossibleMoves().contains(move))) {
//            System.out.println("zit niet in de lijst1?!?!?!");
//        }
    }
    public boolean isLegitMove(Move move){
        int x = move.getX();
        int y = move.getY();
        for (Move moveInList : getPossibleMoves()) {
            if (moveInList.getX() == move.getX() && moveInList.getY() == move.getY()) {
                System.out.println("TESTINGS, MOET NOG AANPASSEN");
                return true;
            }
        }
        return false;
    }

    public void changeTurn() {
        if (currentTurn == PlayerType.ONE) currentTurn = PlayerType.TWO;
        else currentTurn = PlayerType.ONE;
        System.out.println("DEZE GUY IS NU AAN DE BEURT");
        System.out.println(currentTurn);
    }

    @Override
    public boolean checkForWin() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Move> getPossibleMoves() {
        ArrayList<Move> listPossibleMoves = new ArrayList<>();
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                if (board.getCell(x, y) == (Mark.EMPTY)) {
                    boolean left = CheckforValidMove(x, y, 0, -1);
                    boolean bottomleft = CheckforValidMove(x, y, 1, -1);
                    boolean bottom = CheckforValidMove(x, y, 1, 0);
                    boolean bottomright = CheckforValidMove(x, y, 1, 1);
                    boolean right = CheckforValidMove(x, y, 0, 1);
                    boolean upperleft = CheckforValidMove(x, y, -1, -1);
                    boolean upperright = CheckforValidMove(x, y, -1, 1);
                    boolean upper = CheckforValidMove(x, y, -1, 0);

                    if (upper || upperright || right || bottomright || bottom || bottomleft || left || upperleft) {
                        listPossibleMoves.add(new Move(x, y));
                    }
                }
            }
        }
        return listPossibleMoves;
    }

    public boolean CheckforValidMove(int currentX, int currentY, int toCheckX, int toCheckY) {
        Mark opponent = getOpponent();

        if ((currentX + toCheckX < 0) || (currentX + toCheckX >= board.getSize())) {
            return false;
        }
        if ((currentY + toCheckY < 0) || (currentY + toCheckY >= board.getSize())) {
            return false;
        }
        if (board.getCell(currentX + toCheckX, currentY + toCheckY) != opponent) {
            return false;
        }
        if ((currentX + 2 * toCheckX < 0) || (currentX + 2 * toCheckX >= board.getSize())) {
            return false;
        }
        if ((currentY + 2 * toCheckY < 0) || (currentY + 2 * toCheckY >= board.getSize())) {
            return false;
        }
        return checkLineMatch(currentX + 2 * toCheckX, currentY + 2 * toCheckY, toCheckX, toCheckY);
    }

    public boolean checkLineMatch(int x, int y, int toCheckX, int toCheckY) {
        Mark player = getCurrent();
        if (board.getCell(x, y) == player) {
            return true;
        }
        if (board.getCell(x, y) == Mark.EMPTY) {
            return false;
        }
        if ((toCheckX + x < 0) || (toCheckX + x >= board.getSize())) {
            return false;
        }
        if ((toCheckY + y < 0) || (toCheckY + y >= board.getSize())) {
            return false;
        }
        return checkLineMatch(x + toCheckX, y + toCheckY, toCheckX, toCheckY);
    }

    public Mark getOpponent() {
        if (currentTurn == PlayerType.ONE) {
            return Mark.TWO;
        } else if (currentTurn == PlayerType.TWO) {
            return Mark.ONE;
        } else {
            System.out.println("Gaat ergens iets fout AMIGO");
            return Mark.EMPTY;
        }
    }

    public Mark getCurrent() {
        if (currentTurn == PlayerType.ONE) {
            return Mark.ONE;
        } else if (currentTurn == PlayerType.TWO) {
            return Mark.TWO;
        } else {
            System.out.println("Gaat ergens iets fout BROER");
            return Mark.EMPTY;
        }
    }

    public void flipMarks(Move move) throws SetOutOfBoundsException {
        int x = move.getX();
        int y = move.getY();
        System.out.println("GIREUWBHGREWIOUGFHO");
        checkLines(x, y, 0, -1); // Left
        checkLines(x, y, 1, -1); //Bottomleft
        checkLines(x, y, 1, 0); //Bottom
        checkLines(x, y, 1, 1); // Bottomright
        checkLines(x, y, 0, 1); // Right
        checkLines(x, y, -1, -1); // Upperleft
        checkLines(x, y, -1, 1); // Upperright
        checkLines(x, y, -1, 0); // Upper
    }

    public boolean checkLines(int currentX, int currentY, int toCheckX, int toCheckY) throws SetOutOfBoundsException {

        if ((currentX + toCheckX < 0) || (currentX + toCheckX >= board.getSize())) {
            return false;
        }
        if ((currentY + toCheckY < 0) || (currentY + toCheckY >= board.getSize())) {
            return false;
        }
        if (board.getCell(currentX + toCheckX, currentY + toCheckY) == Mark.EMPTY) {
            return false;
        }
        if (board.getCell(currentX + toCheckX, currentY + toCheckY) == getCurrent()) {
            return true;
        } else {
            if (checkLines(currentX + toCheckX, currentY + toCheckY, toCheckX, toCheckY)) {
                board.setMove(currentX + toCheckX, currentY + toCheckY, getCurrent());
                System.out.println("FLIPTATIONS"); //testshit moet uit
                return true;
            } else {
                System.out.println("MINDER FLIPTATIONS"); //same
                return false;
            }
        }
    }

    public int[] score() {
        int[] score;
        score = new int[2];

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == getOpponent()) {
                    score[0]++;
                } else if (board.getCell(i, j) == getCurrent()) {
                    score[1]++;
                }
            }
        }
        return score;
    }


    @Override
    public void start(Player one, Player two) throws SetOutOfBoundsException {
        System.out.println("Othello"); // zwart = x, wit = O ZWART BEGINT ALTIJD https://www.ultraboardgames.com/othello/game-rules.php
                status = GameStatus.PLAYING;
                board = new OthelloBoard(8);
                Move move;
                Mark mark;
                //init board, willen we probbaly niet hier
                // TODO
                board.setMove(3, 4, Mark.ONE);
                board.setMove(4, 3, Mark.ONE);
                board.setMove(3, 3, Mark.TWO);
                board.setMove(4, 4, Mark.TWO);

                do {
                    System.out.println(getPossibleMoves());
                    System.out.println("TUSSENSTAND");
                    System.out.println(Arrays.toString(score()));
                    if (getPossibleMoves().isEmpty()) {
                        System.out.println("HEBT GEEN MOVES ATM");
                changeTurn();
                        System.out.println(getPossibleMoves());
                if (getPossibleMoves().isEmpty()) {
                            System.out.println("No more moves left for both players");
                            System.out.println(Arrays.toString(score()));
                        }
            }
            move = (currentTurn == PlayerType.ONE) ? one.requestMove(this) : two.requestMove(this);
            mark = (currentTurn == PlayerType.ONE ? Mark.ONE : Mark.TWO);
            try {
                doMove(move, mark);
                flipMarks(move);
                //if (checkForWin()) status = GameStatus.WON;
                if (board.isFull()){
                    status = GameStatus.WON;
                    System.out.println(Arrays.toString(score()));
                }
                else changeTurn();


            } catch (IllegalMoveException e) {
                e.printStackTrace(); // TODO
            }
        }
        while (status == GameStatus.PLAYING);
    }

    @Override
    public void run() {
        Player p1 = PlayerFactory.createGUIPlayer("Frankenstein");
        Player p2 = PlayerFactory.createGUIPlayer("Monster");
        try {
            start(p1, p2);
        } catch (SetOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
