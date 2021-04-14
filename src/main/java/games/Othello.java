package games;

import games.board.Mark;
import games.board.OthelloBoard;
import games.board.SetOutOfBoundsException;
import gui.View;
import networking.NetworkManager;
import networking.states.IllegalStateException;
import player.PlayEnum;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Teun de Jong
 * Class Othello, extends the Game class
 */

public class Othello extends Game implements Runnable {

    /**
     * Constructor for Othello for offline play
     * @param startingPlayer the starting player
     * @param playType the PlayEnum (OTHELLO)
     */
    public Othello(PlayerType startingPlayer, PlayEnum playType) {
        super(startingPlayer, playType);
    }

    /**
     * Constructor for Othello for online play
     * @param startingPlayer the starting player
     * @param playType the PlayEnum (OTHELLO)
     * @param networkManager the networkmanager
     */
    public Othello(PlayerType startingPlayer, PlayEnum playType, NetworkManager networkManager) {
        super(startingPlayer, playType, networkManager);
    }

    /**
     * Method used to validate a players moves
     * @param move The move to validate
     * @param marker to which the move belongs
     * @throws IllegalMoveException when a move is illegal
     */
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


        if (!isLegitMove(move)) throw new IllegalMoveException("Move mag niet uitgevoerd worden");
    }

    /**
     * Method used to check if a move is a legit move. Checks the list of possible moves to see if the move played
     * is in the list
     * @param move the move played
     * @return True if the move is in the list, otherwise False.
     */
    public boolean isLegitMove(Move move) {
        for (Move moveInList : getPossibleMoves()) {
            if (moveInList.getX() == move.getX() && moveInList.getY() == move.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to change the players turn
     * If the current turn is player one, then the next turn is player two and the other way 'round.
     */
    public void changeTurn() {
        if (currentTurn == PlayerType.ONE) currentTurn = PlayerType.TWO;
        else currentTurn = PlayerType.ONE;
        System.out.println(currentTurn);
    }

    /**
     * No need for this method in Othello
     * @return False
     */
    @Override
    public boolean checkForWin() {
        return false;
    }

    /**
     * Method to generate all possible moves for the player
     * @return an ArrayList containing the possible moves for the player
     */
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

    /**
     * Method to check if a move is a valid move
     * @param currentX the X of the move
     * @param currentY the Y of the move
     * @param toCheckX the X coordinate to check
     * @param toCheckY the Y coordinate to check
     * @return boolean True if the move is valid, otherwise False
     */
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

    /**
     * Method to check if the lines in which the move is played have a match regarding the Mark
     * @param x coordinate of the move
     * @param y coordinate of the move
     * @param toCheckX coordinate to check
     * @param toCheckY coordinate to check
     * @return boolean True if there is a match, otherwise False
     */
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

    /**
     * Method to get the opposing player
     * @return Mark of the opposing player
     */
    public Mark getOpponent() {
        if (currentTurn == PlayerType.ONE) {
            return Mark.TWO;
        } else if (currentTurn == PlayerType.TWO) {
            return Mark.ONE;
        } else {
            return Mark.EMPTY;
        }
    }

    /**
     * Method to get the mark of the current player
     * @return Mark of the current player
     */
    public Mark getCurrent() {
        if (currentTurn == PlayerType.ONE) {
            return Mark.ONE;
        } else if (currentTurn == PlayerType.TWO) {
            return Mark.TWO;
        } else {
            return Mark.EMPTY;
        }
    }

    /**
     * Method used to flip the marks after a move has been set on the board
     * @param move The move played
     * @throws SetOutOfBoundsException if a move is out of bounds
     */
    public void flipMarks(Move move) throws SetOutOfBoundsException {
        int x = move.getX();
        int y = move.getY();
        checkLines(x, y, 0, -1); // Left
        checkLines(x, y, 1, -1); //Bottomleft
        checkLines(x, y, 1, 0); //Bottom
        checkLines(x, y, 1, 1); // Bottomright
        checkLines(x, y, 0, 1); // Right
        checkLines(x, y, -1, -1); // Upperleft
        checkLines(x, y, -1, 1); // Upperright
        checkLines(x, y, -1, 0); // Upper
    }

    /**
     * Method to check the lines for a match of the player Mark.
     * @param currentX the X coordinate of the current X
     * @param currentY the Y coordinate of the current Y
     * @param toCheckX the X coordinate to check
     * @param toCheckY the Y coordinate to check
     * @return boolean True if there is a match, otherwise False
     * @throws SetOutOfBoundsException if a move is out of bounds
     */
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
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Method to retrieve the current score of a game
     * @return the current score
     */
    public int[] score() {
        int[] score;
        score = new int[2];

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == Mark.ONE) {
                    score[0]++;
                } else if (board.getCell(i, j) == Mark.TWO) {
                    score[1]++;
                }
            }
        }
        return score;
    }

    /**
     * Method used to start a game, also initializes the board.
     * @param one Player one
     * @param two Player two
     * @throws SetOutOfBoundsException if a move is out of bounds
     */
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
        View.othelloRefresh(this);

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
                    status = GameStatus.WON;
                }
            }

            if (playType == PlayEnum.ONLINEAI || playType == PlayEnum.ONLINEPLAYER) {
                if (currentTurn == PlayerType.ONE) {
                    System.out.println("speler één is aan de beurt");
                    move = one.requestMove(this);
                } else {
                    System.out.println("dit zou de remote speler moeten zijn");
                    try {
                        while (!View.remoteMoveSet) {
                            Thread.sleep(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    move = View.remoteMove;
                }
            } else {
                move = (currentTurn == PlayerType.ONE) ? one.requestMove(this) : two.requestMove(this);
            }
            mark = (currentTurn == PlayerType.ONE ? Mark.ONE : Mark.TWO);
            if (!isRunning()) {
                break;
            }
            try {
                System.out.println("in OThello");

                doMove(move, mark);

                if (playType == PlayEnum.ONLINEAI || playType == PlayEnum.ONLINEPLAYER) {
                    networkManager.sendMove(move, View.OTHELLO_SIZE);
                }
                flipMarks(move);
                //if (checkForWin()) status = GameStatus.WON;
                if (board.isFull()) {
                    status = GameStatus.WON;
                    System.out.println(Arrays.toString(score()));
                } else changeTurn();
                View.othelloRefresh(this);
            } catch (IllegalMoveException e) {
                e.printStackTrace(); // TODO
            } catch (IllegalStateException e) {
                View.illegalStateException();
            }

        }
        while (status == GameStatus.PLAYING && isRunning());
    }

    /**
     * Method used to run the game in a thread.
     */
    @Override
    public void run() {
        running.set(true);
        Player p1;
        Player p2;
        if (playType == PlayEnum.PVP) {
            p1 = PlayerFactory.createGUIPlayer("Frankenstein", GameEnum.OTHELLO);
            p2 = PlayerFactory.createGUIPlayer("Monster", GameEnum.OTHELLO);
        } else if (playType == PlayEnum.PVE) {
            p1 = PlayerFactory.createGUIPlayer("Frankenstein", GameEnum.OTHELLO);
            p2 = PlayerFactory.createAIPlayer("Monster", GameEnum.OTHELLO);
        } else if (playType == PlayEnum.ONLINEPLAYER) {
            p1 = PlayerFactory.createGUIPlayer(View.spelernaam, GameEnum.OTHELLO);
            p2 = PlayerFactory.createRemotePlayer("poephoofd");
        } else {
            p1 = PlayerFactory.createAIPlayer(View.spelernaam, GameEnum.OTHELLO);
            p2 = PlayerFactory.createRemotePlayer("poephoofd");
        }

        try {
            start(p1, p2);
        } catch (SetOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}


