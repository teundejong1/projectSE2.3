/**
 * Interface for the multiple boards created (TicTacToe and Othello)
 * @author Eva Jakobs, Teun de Jong
 */
public interface Board {
    public char[][] createBoard(int size);
    public boolean doMove(int row, int col);
    public void printBoard(char[][] board);
}
