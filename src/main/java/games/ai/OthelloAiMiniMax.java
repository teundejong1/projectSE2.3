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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class OthelloAiMiniMax implements AI {
    long start;
    long end;
    long secToWait = 6;
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
//        Mark current = getCurrent(game);
//        Mark opponent = getOpponent(game);
        Board temp = new OthelloBoard(8);
        for (int i = 0; i < game.getBoard().getSize(); i++) {
            for (int j = 0; j < game.getBoard().getSize(); j++) {
                Mark mark = game.getBoard().getCell(i, j);
                temp.setMove(i, j, mark);
            }
        }
        return temp;
    }

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

    public Move MiniMaxDecision(Game game, Mark whoseTurn) throws SetOutOfBoundsException {
        Move bestMove = new Move(-1, -1);
        Mark opponent = Mark.ONE;
        if (whoseTurn == Mark.ONE){
            opponent = Mark.TWO;
        }
        if (game.getPossibleMoves().isEmpty()) {
            System.out.println("GEEN MOVES");
//            int x = -1;
//            int y = -1;
        } else {
            int bestMoveVal = -99999;
            List<Move> moves = game.getPossibleMoves();
            for (int i = 0; i < moves.size(); i++) {
                Board temp = copyBoard(game);
                Move move = moves.get(i);
                temp.setMove(move.getX(), move.getY(), getCurrent(game));
                int val = miniMaxValue(temp, game, 1, whoseTurn, opponent);
                if (val > bestMoveVal) {
                    bestMoveVal = val;
                    System.out.println("Kom je hier minimaxdecision?");
                    bestMove.setX(move.getX());
                    bestMove.setY(move.getY());
                }
            }
        }
        System.out.println("regel voor return bestMove");
        return bestMove;
    }

    public int miniMaxValue(Board board, Game game, int depth, Mark original, Mark currentTurn) throws SetOutOfBoundsException {


        if (System.currentTimeMillis() > end){
            System.out.println("TIJD IS OM");
            return heuristic(board, original);
        }
        System.out.println("Kom je hier minimaxvalue start?");
        System.out.println("DIEPTE: " + depth);
//        List<Move> list = game.getPossibleMoves();
        if (depth >= 500 || game.getStatus() == GameStatus.WON) { //
            System.out.println("Test op depth, DEPTH REACHED, YOU DID IT");
            return heuristic(board, original);
        }

        Mark opponent = Mark.ONE;
        if (currentTurn == Mark.ONE) {
            opponent = Mark.TWO;
        }
//        Mark self = getCurrent(game);
        List<Move> moveList = getPossibleTempMoves(board, (Othello) game);
        if (moveList.isEmpty()) {
            System.out.println("oeps, tempmoves leeg");
            depth = depth + 1;
            return miniMaxValue(board, game, depth, original, opponent);
        } else {
            int bestMoveVal = -99999;
            if (original != currentTurn) { //was current
                bestMoveVal = 99999;
            }
            // HIER BRACKET
                for (int i = 0; i < moveList.size(); i++) {
                    Board tempBoard;
                    tempBoard = copyTestBoard(board);
                    List<Move> tempMoves = getPossibleTempMoves(tempBoard, (Othello) game);
                    //System.out.println(Arrays.toString(tempMoves.toArray()));
                    Move move = tempMoves.get(i);
//                    System.out.println("TESTEST");
//                    System.out.println(move.getX());
//                    System.out.println(move.getY());
                    //tempBoard.setMove(g);
                    tempBoard.setMove(move.getX(), move.getY(), currentTurn);
                    System.out.println("Regel vóór depth +1");
                    depth = depth+1;
                    System.out.println(depth);
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    int val = miniMaxValue(tempBoard, game, depth, original, opponent);
                    System.out.println("Hoe vaak kom je hier, for loop minimaxvalue");

                    if (original == currentTurn) {
                        System.out.println("original == currentturn");
                        if (val > bestMoveVal) {
                            bestMoveVal = val;
                        }
                    }
                    else {
                        if (val < bestMoveVal) {
                            bestMoveVal = val;
                        }
                    }
                }
            System.out.println("Kom je hier return bestmoveval?");
            return bestMoveVal;
                }
//        System.out.println("Hier mag je niet komen");
//        return -1;
            }





    @Override
    public Move getMove(Game game) throws SetOutOfBoundsException {
        start = System.currentTimeMillis();
        end = start + (secToWait*1000);

        //heuristic(game);
        Move bestMove = null;
//        List<Move> possibleMoves = game.getPossibleMoves();

//        System.out.println(possibleMoves);
//        long start = System.currentTimeMillis();
//        long end = start + (3*1000);

//        while(System.currentTimeMillis() < end ) {
            bestMove = MiniMaxDecision(game, getCurrent(game));
            return bestMove;
//        }
//        System.out.println("HIER MAG JE NIET KOMEN");
//        return bestMove;
    }

    public List<Move> getPossibleTempMoves(Board board, Othello game) {
        System.out.println("Test get possibleTempMoves");
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
        System.out.println("LISTPOSSIBLEMOVES");
        System.out.println(listPossibleMoves);
        System.out.println("LISTPOSSIBLEMOVES");
        return listPossibleMoves;
    }
}

