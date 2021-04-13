package games.ai;

import games.Game;
import games.GameStatus;
import games.Move;
import games.board.Board;
import games.board.Mark;
import games.board.OthelloBoard;
import games.board.SetOutOfBoundsException;
import player.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Teun de Jong
 * Class OthelloMiniMax
 * Used for generating moves during the tournament
 * Looks forward in the tree
 */
public class OthelloAiMiniMax implements AI {
    static final long secToWait = 6;
    long start;
    long end;
    Random random;

    /**
     * Constructor for OthelloMiniMax
     */
    public OthelloAiMiniMax() {
        super();
        this.random = new Random();
    }

    /**
     * Method used to get the current score
     * @param board on which to check the score
     * @param whoseturn Mark
     * @return int[] which displays the score
     */
    public int[] scoreBoard(Board board, Mark whoseturn) {
        Mark opponent = Mark.ONE;
        if (whoseturn == Mark.ONE) {
            opponent = Mark.TWO;
        }
        int[] score;
        score = new int[2];
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == opponent) {
                    score[0]++;
                } else if (board.getCell(i, j) == whoseturn) {
                    score[1]++;
                }
            }
        }
        return score;
    }

    /**
     * Method to get the Mark of the current player
     * @param game current game
     * @return Mark the current player
     */
    public Mark getCurrent(Game game) {
        if (game.getCurrentTurn() == PlayerType.ONE) {
            return Mark.ONE;
        } else if (game.getCurrentTurn() == PlayerType.TWO) {
            return Mark.TWO;
        } else {
            return Mark.EMPTY;
        }
    }

    /**
     * Method used to calculate the heuristic value of the board
     * @param board the board on which the calculation takes place
     * @param whoseturn Mark of current players turn
     * @return the heuristic value (aiScore - opponentscore)
     */
    public int heuristic(Board board, Mark whoseturn) {
        int[] score = scoreBoard(board, whoseturn);
        int aiScore = score[1];
        int opponentScore = score[0];
        return (aiScore - opponentScore);
    }

    /**
     * Method used to copy the current board on which the game is played
     * @param game that is currently played
     * @return a new copy of the board
     * @throws SetOutOfBoundsException when a move is not legit (shouldn't throw)
     */
    public Board copyBoard(Game game) throws SetOutOfBoundsException {
        Board temp = new OthelloBoard(8);
        for (int i = 0; i < game.getBoard().getSize(); i++) {
            for (int j = 0; j < game.getBoard().getSize(); j++) {
                Mark mark = game.getBoard().getCell(i, j);
                temp.setMove(i, j, mark);
            }
        }
        return temp;
    }

    /**
     * Method used to copy a board
     * @param board that needs a copy
     * @return a copy of the board
     * @throws SetOutOfBoundsException for when a move is not legit (should never throw)
     */
    public Board copyTestBoard(Board board) throws SetOutOfBoundsException {
        Board temp = new OthelloBoard(8);
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Mark mark = board.getCell(i, j);
                temp.setMove(i, j, mark);
            }
        }
        return temp;
    }

    /**
     * Method that decides the best move for the AI
     * @param game that is currently played
     * @param whoseTurn the current player
     * @return the best move
     * @throws SetOutOfBoundsException for when a move is not legit (should never throw)
     */
    public Move MiniMaxDecision(Game game, Mark whoseTurn) throws SetOutOfBoundsException {
        List<Move> moves = game.getPossibleMoves();
        Move bestMove = new Move(-1, -1);
        Mark opponent = Mark.ONE;
        if (whoseTurn == Mark.ONE) {
            opponent = Mark.TWO;
        }
        if (moves.isEmpty()) {
            return bestMove;
        } else {
            int bestMoveVal = -99999;
            for (int i = 0; i < moves.size(); i++) {
                Board temp = copyBoard(game);
                Move move = moves.get(i);
                System.out.println("DIT IS DE EERSTE SETMOVE" + move.getX() + "   " + move.getY() + "  "+ whoseTurn);
                temp.setMove(move.getX(), move.getY(), whoseTurn);
                flipMarks(temp, move, whoseTurn);
                System.out.println(temp.toString());
                int val = miniMaxValue(temp, game, 1, whoseTurn, opponent);
                if (val > bestMoveVal) {
                    bestMoveVal = val;
                    bestMove.setX(move.getX());
                    bestMove.setY(move.getY());
                }
            }
            return bestMove;
        }
    }

    /**
     * Method used to calculate the minimum and maximum values in the tree of moves
     * @param board board on which to calculate the best move
     * @param game current game
     * @param depth how deep to go in the tree
     * @param original the original player
     * @param currentTurn the current player
     * @return the value of moves
     * @throws SetOutOfBoundsException if a move is not legit (should never throw)
     */
    public int miniMaxValue(Board board, Game game, int depth, Mark original, Mark currentTurn) throws SetOutOfBoundsException {
        System.out.println(depth+ "         DDDDDDIIIIIIIIIEEEEEEEEEEEEPPPPPPPPPPP");
        if (depth >= 12 || game.getStatus() == GameStatus.WON) { //
            return heuristic(board, original);
        }

        Mark opponent = Mark.ONE;
        if (currentTurn == Mark.ONE) {
            opponent = Mark.TWO;
        }
        System.out.println("CURRENT TURN = " + currentTurn);
        List<Move> moveList = getPossibleTempMoves(currentTurn, board);
        System.out.println(moveList);
        System.out.println("LISTSIZE");
        System.out.println(moveList.size());
        if (moveList.isEmpty()) {
            depth += 1;
            return miniMaxValue(board, game, depth, original, opponent);
        } else {
            int bestMoveVal = -99999;
            if (original != currentTurn) {
                bestMoveVal = 9999;
            }
            for (int i = 0; i < moveList.size(); i++) {
                Board tempBoard = copyTestBoard(board);
                Move move = moveList.get(i);
                tempBoard.setMove(move.getX(), move.getY(), currentTurn);
                System.out.println(move.getX() + "   " + move.getY() + "   " + currentTurn);
                flipMarks(tempBoard, move, currentTurn);
                System.out.println(tempBoard.toString());
                depth = depth +1;
                int val = miniMaxValue(tempBoard, game, depth, original, opponent);
                System.out.println(val);
                if (original == currentTurn) {
                    if (val > bestMoveVal) {
                        bestMoveVal = val;
                    }
                } else {
                    if (val < bestMoveVal) {
                        bestMoveVal = val;
                    }
                }
            }
            return bestMoveVal;
        }
    }


    /**
     * Method used to generate a move for the AI
     * @param game the current game
     * @return the move
     * @throws SetOutOfBoundsException if a move is not legit (should never throw)
     */
    @Override
    public Move getMove(Game game) throws SetOutOfBoundsException {
        start = System.currentTimeMillis();
        end = start + (secToWait * 1000);
        Move bestMove;
        bestMove = MiniMaxDecision(game, getCurrent(game));
        return bestMove;
    }

    /**
     * Method used to generate the possible moves on a temporary board
     * @param currentTurn current turn
     * @param board the board on which to calculate the moves
     * @return
     */
    public List<Move> getPossibleTempMoves(Mark currentTurn, Board board) {
        ArrayList<Move> listPossibleMoves = new ArrayList<>();
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                if (board.getCell(x, y) == (Mark.EMPTY)) {
                    boolean left = CheckforValidMove(board, currentTurn, x, y, 0, -1);
                    boolean bottomleft = CheckforValidMove(board, currentTurn, x, y, 1, -1);
                    boolean bottom = CheckforValidMove(board, currentTurn, x, y, 1, 0);
                    boolean bottomright = CheckforValidMove(board, currentTurn, x, y, 1, 1);
                    boolean right = CheckforValidMove(board, currentTurn, x, y, 0, 1);
                    boolean upperleft = CheckforValidMove(board, currentTurn, x, y, -1, -1);
                    boolean upperright = CheckforValidMove(board, currentTurn, x, y, -1, 1);
                    boolean upper = CheckforValidMove(board, currentTurn, x, y, -1, 0);

                    if (upper || upperright || right || bottomright || bottom || bottomleft || left || upperleft) {
                        listPossibleMoves.add(new Move(x, y));
                    }
                }
            }
        }
        return listPossibleMoves;
    }

    /**
     * Method used to validate moves
     * @param board the board on which to validate moves
     * @param currentTurn the current player
     * @param currentX current X value
     * @param currentY current Y value
     * @param toCheckX X value to check
     * @param toCheckY Y value to check
     * @return boolean True if move is valid, False if not
     */
    public boolean CheckforValidMove(Board board, Mark currentTurn, int currentX, int currentY, int toCheckX, int toCheckY) {
        Mark opponent = Mark.ONE;
        if (currentTurn == Mark.ONE) {
            opponent = Mark.TWO;
        }

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
        return checkLineMatch(board, currentTurn, currentX + 2 * toCheckX, currentY + 2 * toCheckY, toCheckX, toCheckY);
    }

    /**
     * Method used to check if lines match the same Mark of the current player
     * @param board the current board
     * @param player mark of the player
     * @param x current value of X
     * @param y current value of Y
     * @param toCheckX X value to check
     * @param toCheckY Y value to check
     * @return boolean True if there is a match, False if not
     */
    public boolean checkLineMatch(Board board, Mark player, int x, int y, int toCheckX, int toCheckY) {
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
        return checkLineMatch(board, player, x + toCheckX, y + toCheckY, toCheckX, toCheckY);
    }

    /**
     * Method used to flip marks if lines are matching
     * @param board the current board
     * @param move the move to check
     * @param current the current Mark of the player
     * @throws SetOutOfBoundsException if a move is not legit
     */
    public void flipMarks(Board board, Move move, Mark current) throws SetOutOfBoundsException {
        int x = move.getX();
        int y = move.getY();
        System.out.println("GIREUWBHGREWIOUGFHO");
        checkLines(board, current, x, y, 0, -1); // Left
        checkLines(board, current, x, y, 1, -1); //Bottomleft
        checkLines(board, current, x, y, 1, 0); //Bottom
        checkLines(board, current, x, y, 1, 1); // Bottomright
        checkLines(board, current, x, y, 0, 1); // Right
        checkLines(board, current, x, y, -1, -1); // Upperleft
        checkLines(board, current, x, y, -1, 1); // Upperright
        checkLines(board, current, x, y, -1, 0); // Upper
    }

    /**
     * Method used to check lines for matches
     * @param board the current board
     * @param current the Mark of the current player
     * @param currentX the current X of the move
     * @param currentY the current Y of the move
     * @param toCheckX the X value to check
     * @param toCheckY the Y value to check
     * @return boolean True if checklines() is true, False is not
     * @throws SetOutOfBoundsException if a move is not legit
     */
    public boolean checkLines(Board board, Mark current, int currentX, int currentY, int toCheckX, int toCheckY) throws SetOutOfBoundsException {

        if ((currentX + toCheckX < 0) || (currentX + toCheckX >= board.getSize())) {
            return false;
        }
        if ((currentY + toCheckY < 0) || (currentY + toCheckY >= board.getSize())) {
            return false;
        }
        if (board.getCell(currentX + toCheckX, currentY + toCheckY) == Mark.EMPTY) {
            return false;
        }
        if (board.getCell(currentX + toCheckX, currentY + toCheckY) == current) {
            return true;
        } else {
            if (checkLines(board, current, currentX + toCheckX, currentY + toCheckY, toCheckX, toCheckY)) {
                board.setMove(currentX + toCheckX, currentY + toCheckY, current);
                return true;
            } else {
                return false;
            }
        }
    }
}