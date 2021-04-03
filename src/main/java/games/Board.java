package games;

/**
 * Interface for the multiple boards created (TicTacToe and Othello)
 * @author Eva Jakobs, Teun de Jong
 */
public interface Board {
    public char[][] createBoard(int size);
    public boolean checkMove(int row, int col);
    public void placeMove(int row, int col);
    public void printBoard(char[][] board);
}
