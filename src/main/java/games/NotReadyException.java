package games;

/**
 * @author Jeroen Lammersma
 * Exception thrown when a player is not ready, used in online play
 */
public class NotReadyException extends Exception {

    private static final long serialVersionUID = -8630724361260449927L;

    /**
     * Constructor without parameters
     */
    public NotReadyException() {
        super();
    }

    /**
     * Constructor with parameter Exception e
     * @param e Exception
     */
    public NotReadyException(Exception e) {
        super("");
    }

    /**
     * Constructor with parameter String
     * @param message string
     */
    public NotReadyException(String message) {
        super(message);
    }
}
