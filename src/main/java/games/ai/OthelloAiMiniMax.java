package games.ai;

import games.Game;
import games.GameStatus;
import games.Move;
import games.Othello;
import games.board.Board;
import games.board.Mark;
import games.board.OthelloBoard;
import games.board.SetOutOfBoundsException;
import player.PlayerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OthelloAiMiniMax implements AI {

    Random random;

    public OthelloAiMiniMax() {
        super();
        this.random = new Random();
    }

    public int[] scoreBoard(Board board, Mark whoseturn) {
        Mark opponent = Mark.ONE;
        if (whoseturn == Mark.ONE){
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
    public int[] score(Game game) {
        int[] score;
        score = new int[2];
        for (int i = 0; i < game.getBoard().getSize(); i++) {
            for (int j = 0; j < game.getBoard().getSize(); j++) {
                if (game.getBoard().getCell(i, j) == getOpponent(game)) {
                    score[0]++;
                } else if (game.getBoard().getCell(i, j) == getCurrent(game)) {
                    score[1]++;
                }
            }
        }
        return score;
    }

    public Mark getOpponent(Game game) {
        if (game.getCurrentTurn() == PlayerType.ONE) {
            return Mark.TWO;
        } else if (game.getCurrentTurn() == PlayerType.TWO) {
            return Mark.ONE;
        } else {
            System.out.println("Gaat ergens iets fout AMIGO");
            return Mark.EMPTY;
        }
    }

    public Mark getCurrent(Game game) {
        if (game.getCurrentTurn() == PlayerType.ONE) {
            return Mark.ONE;
        } else if (game.getCurrentTurn() == PlayerType.TWO) {
            return Mark.TWO;
        } else {
            System.out.println("Gaat ergens iets fout BROER");
            return Mark.EMPTY;
        }
    }

    public int heuristic(Board board, Mark whoseturn) {
        int[] score = scoreBoard(board, whoseturn);
        System.out.println(Arrays.toString(score));
        int aiScore = score[1];
        int opponentScore = score[0];
        System.out.println(aiScore - opponentScore);
        return (aiScore - opponentScore);
    }

    public Board copyBoard(Game game) throws SetOutOfBoundsException {
        Mark current = getCurrent(game);
        Mark opponent = getOpponent(game);
        Board temp = new OthelloBoard(8);
        for (int i = 0; i < game.getBoard().getSize(); i++) {
            for (int j = 0; j < game.getBoard().getSize(); j++) {
                Mark mark = game.getBoard().getCell(i, j);
                temp.setMove(i, j, mark);
            }
        }
        return temp;
    }

    public Move MiniMaxDecision(Game game) throws SetOutOfBoundsException {
        Move bestMove = new Move(-1, -1);

        if (game.getPossibleMoves().isEmpty()) {
            int x = -1;
            int y = -1;
        } else {
            int bestMoveVal = -99999;
            List<Move> moves = game.getPossibleMoves();
            for (int i = 0; i < moves.size(); i++) {
                Board temp = copyBoard(game);
                Move move = moves.get(i);
                temp.setMove(move.getX(), move.getY(), getCurrent(game));
                int val = miniMaxValue(temp, game, 1, getCurrent(game), getCurrent(game));
                if (val > bestMoveVal) {
                    bestMoveVal = val;
                    System.out.println("Kom je hier minimaxdecision?");
                    bestMove.setX(move.getX());
                    bestMove.setY(move.getY());
                }
            }
        }
        return bestMove;
    }

    public int miniMaxValue(Board board, Game game, int depth, Mark original, Mark currentTurn) throws SetOutOfBoundsException {
        System.out.println("Kom je hier minimaxvalue start?");
        if ((depth == 5) || game.getStatus() == GameStatus.WON) {
            return heuristic(board, getCurrent(game));
        }
        Mark opponent = Mark.ONE;
        if (currentTurn == Mark.ONE){
            opponent = Mark.TWO;
        }
        Mark self = getCurrent(game);
        List<Move> moveList = getPossibleTempMoves(board, (Othello) game);
        if (moveList.isEmpty()) {
            return miniMaxValue(board, game, depth + 1, original, opponent);
        } else {
            int bestMoveVal = -99999;
            if (original != getCurrent(game)) {
                bestMoveVal = 99999;
                for (int i = 0; i < game.getPossibleMoves().size(); i++) {
                    Board tempBoard;
                    tempBoard = copyBoard(game);
                    List<Move> tempMoves = getPossibleTempMoves(tempBoard, (Othello) game);
                    Move move = tempMoves.get(i);
                    tempBoard.setMove(move.getX(), move.getY(), getCurrent(game));
                    int val = miniMaxValue(tempBoard, game, depth + 1, original, opponent);

                    if (original == self) {
                        if (val > bestMoveVal) {
                            bestMoveVal = val;
                        }
                    } else {
                        if (val < bestMoveVal) {
                            bestMoveVal = val;
                        }
                    }
                }
                System.out.println("Kom je hier?");
                return bestMoveVal;
            }
            return -1;
        }
    }

    @Override
    public Move getMove(Game game) throws SetOutOfBoundsException {
        //heuristic(game);
        Move bestMove = null;
//        List<Move> possibleMoves = game.getPossibleMoves();

//        System.out.println(possibleMoves);

        bestMove = MiniMaxDecision(game);
        return bestMove;
//        return possibleMoves.get(random.nextInt(possibleMoves.size()));
    }

    public List<Move> getPossibleTempMoves(Board board, Othello game) {
        ArrayList<Move> listPossibleMoves = new ArrayList<>();
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                if (board.getCell(x, y) == (Mark.EMPTY)) {
                    boolean left = game.CheckforValidMove(x, y, 0, -1);
                    boolean bottomleft = game.CheckforValidMove(x, y, 1, -1);
                    boolean bottom = game.CheckforValidMove(x, y, 1, 0);
                    boolean bottomright = game.CheckforValidMove(x, y, 1, 1);
                    boolean right = game.CheckforValidMove(x, y, 0, 1);
                    boolean upperleft = game.CheckforValidMove(x, y, -1, -1);
                    boolean upperright = game.CheckforValidMove(x, y, -1, 1);
                    boolean upper = game.CheckforValidMove(x, y, -1, 0);

                    if (upper || upperright || right || bottomright || bottom || bottomleft || left || upperleft) {
                        listPossibleMoves.add(new Move(x, y));
                    }
                }
            }
        }
        return listPossibleMoves;
    }
}

