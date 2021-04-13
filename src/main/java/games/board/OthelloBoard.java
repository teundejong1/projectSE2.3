package games.board;

/**
 * @author Jeroen Lammersma, Teun de Jong
 * Class for OthelloBoard
 * Contains a getMark method
 */
public class OthelloBoard extends Board {

    /**
     * Constructor for Othelloboard
     * @param size int (should be 8)
     */
    public OthelloBoard(int size) {
        super(size);
    }

    /**
     * Method to turn the playerMark into a string
     * @param mark playerMark
     * @return string "X", "O" or  " "
     */
    @Override
    public String getMark(Mark mark) {
        String str = "";

        if (mark == Mark.ONE) str = "X";
        if (mark == Mark.TWO) str = "O";
        if (mark == Mark.EMPTY) str = " ";
        return str;
    }
}

