package games.board;

public class TicTacToeBoard extends Board {

    public TicTacToeBoard(int size) {
        super(size);
    }

    @Override
    public String getMark(Mark mark) {
        String str = "";

        if (mark == Mark.ONE) str = "X";
        if (mark == Mark.TWO) str = "O";
        if (mark == Mark.EMPTY) str = " ";

        return str;
    }
    
}
