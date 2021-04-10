package networking.commands;

import games.Move;
/**
 * Commandclass to send a move to the server
 * @author Jeroen Lammersma
 */
public class MoveCommand implements Command {

    private int cell;

    /**
     * Constructs a move using an absolute value
     * of a cell.
     * 
     * @param cell ranges from 0 to N
     */
    public MoveCommand(int cell) {
        this.cell = cell;
    }

    /**
     * Constructs a move using x, y coordinates. It needs
     * the size of the board to calculate the absolute cell.
     * 
     * @param x ranges from 0 to N
     * @param y ranges from 0 to N
     * @param boardSize ranges from 1 to N
     */
    public MoveCommand(int x, int y, int boardSize) {
        cell = convertToCell(x, y, boardSize);
    }
    
    /**
     * 
     * @param move Move object which contains the x and y
     * @param boardSize int of the size of the board
     */
    public MoveCommand(Move move, int boardSize) {
        cell = convertToCell(move.getX(), move.getY(), boardSize);
    }

    /**
     * Check to confirm server received command correctly
     * @Override
     */
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    /**
     * writes the command to be send into string form
     * @Override
     */
    public String toString() {
        return String.format("MOVE %d", cell);
    }

    /**
     * Converts x and y coordinates to an absolute cell
     * @param x ranges from 0 to N
     * @param y ranges from 0 to N
     * @param boardSize ranges from 1 to N
     * @return absolute cell
     */
    private int convertToCell(int x, int y, int boardSize) {
        return (x * boardSize + y);
    }

}
