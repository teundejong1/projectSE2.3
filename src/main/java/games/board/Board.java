package games.board;

/**
 * @author Jeroen Lammersma, Teun de Jong
 * abstract class board, extended by the concrete board (OthelloBoard and TicTacToeBoard
 */
public abstract class Board {

    private Mark[][] board;
    private int size;
    
    public Board(int size) {
        this.size = size;
        board = new Mark[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    /**
     * abstrac method to get the player Mark
     * @param mark playerMark
     * @return Mark
     */
    public abstract String getMark(Mark mark);

    /**
     * Method used to set a move on the board
     * @param x coordinate
     * @param y coordinate
     * @param marker the marker of the player that wants to place a move on the board
     * @throws SetOutOfBoundsException if the move is out of bounds
     */
    public void setMove(int x, int y, Mark marker) throws SetOutOfBoundsException {
        if (!isInBounds(x, y)) throw new SetOutOfBoundsException("Move on board is out of bounds");
        
        board[x][y] = marker;
    }

    /**
     * Method used to get the cell of the x and y coordinates
     * @param x coordinate
     * @param y coordinate
     * @return the Marker placed in the x and y coordinates
     */
    public Mark getCell(int x, int y) {
        return board[x][y];
    }

    /**
     * Method to return the size of the board
     * @return int size
     */
    public int getSize() {
        return size;
    }

    /**
     * Method used to check if the give coordinates are in the board
     * @param x coordinate
     * @param y coordinate
     * @return Boolean True if in the board, otherwise False
     */
    public boolean isInBounds(int x, int y) {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }

    /**
     * Method to check whether the board is full
     * @return Boolean True if the board is full, otherwise False
     */
    public boolean isFull() {
        boolean isFull = true;

        outerloop:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    isFull = false;
                    break outerloop;
                }
            }
        }
        return isFull;
    }

    /**
     * toString method to print the board
     * @return String Board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Mark mark;

        for (int i = 0; i < size; i++) {
            sb.append("|");
            for (int j = 0; j < size; j++) {
                mark = board[i][j];
                sb.append(getMark(mark) + "|");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
