package games.board;

public class OthelloBoard extends Board {

    public OthelloBoard(int size) {
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

