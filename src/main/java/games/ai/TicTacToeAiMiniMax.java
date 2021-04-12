package games.ai;

import games.Game;
import games.Move;
import games.board.Board;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import player.PlayerType;

import java.util.List;
import java.util.Random;

public class TicTacToeAiMiniMax implements AI {

    Random random;

    public TicTacToeAiMiniMax() {
        super();
        this.random = new Random();
    }

    @Override
    public Move getMove(Game game)   {
        System.out.println("HIERONDER DE WAARDE VAN BORD");
        System.out.println(evaluate(game.getBoard(), game));
        Move bestMove = null;
        try {
            bestMove = findBestMove(game.getBoard(),game);
        } catch (SetOutOfBoundsException e) {
            e.printStackTrace(); //TODO
        }
        System.out.println(bestMove);
        return bestMove;
    }


    public int evaluate(Board board, Game game) {
        Mark markSelf;
        Mark markOpponent;
        if (game.getCurrentTurn() == PlayerType.ONE){
            markSelf = Mark.ONE;
            markOpponent = Mark.TWO;
        }
        else {
            markSelf = Mark.TWO;
            markOpponent = Mark.ONE;
        }


        //check rows for victory
        for (int row = 0; row < 3; row++) {
            if (board.getCell(row, 0) == board.getCell(row, 1) && board.getCell(row, 1) == board.getCell(row, 2)) {
                if (board.getCell(row, 0) == markSelf) {
                    return +10;
                } else if (board.getCell(row, 0) == markOpponent) {
                    return -10;
                }
            }
            //check cols for victory
            for (int col = 0; col < 3; col++) {
                if (board.getCell(0, col) == board.getCell(1, col) && board.getCell(1, col) == board.getCell(2, col)) {
                    if (board.getCell(0, col) == markSelf) {
                        return +10;
                    } else if (board.getCell(0, col) == markOpponent) {
                        return -10;
                    }
                }
            }
            //check diagonals for victory
            if (board.getCell(0, 0) == board.getCell(1, 1) && board.getCell(1, 1) == board.getCell(2, 2)) {
                if (board.getCell(0, 0) == markSelf) {
                    return +10;
                } else if (board.getCell(0, 0) == markOpponent) {
                    return -10;
                }
            }
            if (board.getCell(0, 2) == board.getCell(1, 1) && board.getCell(1, 1) == board.getCell(2, 0)) {
                if (board.getCell(0, 2) == markSelf) {
                    return +10;
                } else if (board.getCell(0, 2) == markOpponent) {
                    return -10;
                }
            }
        }
        return 0;
    }

    public int minimax(Board board, int depth, Boolean check, Game game) throws SetOutOfBoundsException {
        Mark markSelf;
        Mark markOpponent;
        if (game.getCurrentTurn() == PlayerType.ONE){
            markSelf = Mark.ONE;
            markOpponent = Mark.TWO;
        }
        else {
            markSelf = Mark.TWO;
            markOpponent = Mark.ONE;
        }
        int score = evaluate(board, game);
        if (score == 10){
            return score;
        }

        if (score == -10){
            return score;
        }
        if (board.isFull()){
            return 0;
        }
        if (check){
            int best = -1000;
            for (int x = 0; x < board.getSize(); x++){
                for (int y = 0; y < board.getSize(); y++){
                    if (board.getCell(x, y) == Mark.EMPTY){
                        board.setMove(x, y, markSelf);
                        best = Math.max(best, minimax(board, depth+1, !check, game));
                        //reset
                        board.setMove(x, y, Mark.EMPTY);
                    }
                }
            }
            return best;
        }else {
            int best = 1000;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    if (board.getCell(i, j) == Mark.EMPTY) {
                        board.setMove(i, j, markOpponent);
                        best = Math.min(best, minimax(board, depth + 1, !check, game));
                        //reset
                        board.setMove(i, j, Mark.EMPTY);
                    }
                }
            }
            return best;
        }
    }

    public Move findBestMove(Board board, Game game) throws SetOutOfBoundsException {
        Mark markSelf;
        Move bestMove = new Move(-1, -1);
        if (game.getCurrentTurn() == PlayerType.ONE){
            markSelf = Mark.ONE;
        }
        else {
            markSelf = Mark.TWO;
        }

        int best = -1000;


        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j ++){
                if (board.getCell(i, j) == Mark.EMPTY){
                    board.setMove(i, j, markSelf);
                    int moveVal = minimax(board, 0, false, game);
                    //reset
                    board.setMove(i, j, Mark.EMPTY);
                    if (moveVal > best){
                        bestMove.setX(i);
                        bestMove.setY(j);
                        best = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}

