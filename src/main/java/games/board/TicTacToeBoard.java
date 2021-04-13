package games.board;

/**
 * @author Jeroen Lammersma, Teun de Jong, Eva Jakobs
 */
public class TicTacToeBoard extends Board {

    /**
     * Constructor for TicTacToeBoard
     * @param size int (should be 3)
     */
    public TicTacToeBoard(int size) {
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
