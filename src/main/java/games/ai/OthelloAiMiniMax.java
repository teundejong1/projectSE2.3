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

    public int heuristic(Game game) {
        int[] score = score(game);
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
                if (game.getBoard().getCell(i, j) == current) {
                    temp.setMove(i, j, current);
                } else if (game.getBoard().getCell(i, j) == opponent) {
                    temp.setMove(i, j, opponent);
                } else {
                    temp.setMove(i, j, Mark.EMPTY);
                }
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
            for (int i = 0; i < game.getPossibleMoves().size(); i++) {
                Board temp = copyBoard(game);
                Move move = moves.get(i);
                temp.setMove(move.getX(), move.getY(), getCurrent(game));
                int val = miniMaxValue(temp, game, 1, getCurrent(game));
                if (val > bestMoveVal) {
                    bestMoveVal = val;
                    bestMove.setX(move.getX());
                    bestMove.setY(move.getY());
                }
            }
        }
        return bestMove;
    }

    public int miniMaxValue(Board board, Game game, int depth, Mark original) throws SetOutOfBoundsException {
        if ((depth == 5) || game.getStatus() == GameStatus.WON) {
            return heuristic(game);
        }
        Mark opponent = getOpponent(game);
        Mark self = getCurrent(game);
        List<Move> moveList = getPossibleTempMoves(board, (Othello) game);
        if (moveList.isEmpty()) {
            return miniMaxValue(board, game, depth + 1, original);
        } else {
            int bestMoveVal = -99999;
            if (original != getCurrent(game)) {
                bestMoveVal = 99999;
                for (int i = 0; i < game.getPossibleMoves().size(); i++) {
                    Board tempBoard = new OthelloBoard(8);
                    tempBoard = copyBoard(game);
                    List<Move> tempMoves = getPossibleTempMoves(tempBoard, (Othello) game);
                    Move move = tempMoves.get(i);
                    tempBoard.setMove(move.getX(), move.getY(), getCurrent(game));
                    int val = miniMaxValue(tempBoard, game, depth + 1, self);

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
            }
            return bestMoveVal;
        }

    }

    @Override
    public Move getMove(Game game) throws SetOutOfBoundsException {
        heuristic(game);
        Move bestMove = null;
        List<Move> possibleMoves = game.getPossibleMoves();
        System.out.println("AI THINKING");
//        System.out.println(possibleMoves);
        System.out.println("AI THINKING");
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

