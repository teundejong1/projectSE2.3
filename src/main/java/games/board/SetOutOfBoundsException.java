package games.board;

/**
 * @author Jeroen Lammersma
 * Class SetOutOfBoundsException
 * Used when a move is out of bounds
 */
public class SetOutOfBoundsException extends Exception {
    
    private static final long serialVersionUID = -8510036369422750408L;

    /**
     * Constructor for class without parameters
     */
    public SetOutOfBoundsException() {
        super();
    }

    /**
     * Constructor for class with parameter Exception
     * @param e Expection
     */
    public SetOutOfBoundsException(Exception e) {
        super(e);
    }

    /**
     * Constructor with parameter String message
     * @param message String containing the error message
     */
    public SetOutOfBoundsException(String message) {
        super(message);
    }
}
