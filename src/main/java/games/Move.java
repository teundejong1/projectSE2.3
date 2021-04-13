package games;

/**
 * @author Teun de Jong, Jeroen Lammersma
 * Class Move, used in the different games in this client
 */
public class Move {

    private int x, y;

    /**
     * Constructor for Move class
     * @param x coordinate
     * @param y coordinate
     */
    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to return the X coordinate of the move
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Method to return the Y coordinate of the move
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Method to set the X coordinate of the move
     * @param x coordinate
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Method to set the Y coordinate of the move
     * @param y coordinate
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * toString method for Moves
     * @return String with the x and y coordinates.
     */
    @Override
    public String toString() {
        return String.format("Move(%d, %d)", x, y);
    }
    
}
