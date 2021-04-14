package games;

/**
 * @author Jeroen Lammersma
 */
public class IllegalMoveException extends Exception {
    
    private static final long serialVersionUID = -8510036369422750408L;

    /**
     * Constructor without parameters for IllegalMoveException
     */
    public IllegalMoveException() {
        super();
    }

    /**
     * Constructor with parameter Exception for IllegalMoveException
     * @param e Exception
     */
    public IllegalMoveException(Exception e) {
        super("");
    }

    /**
     * Constructor with parameter String for IllegalMoveException
     * @param message String to display
     */
    public IllegalMoveException(String message) {
        super(message);
    }
}
