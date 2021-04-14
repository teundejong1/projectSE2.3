package networking.states;

/**
 * 
 * @author Jeroen Lammersma
 */
public class IllegalStateException extends Exception {

    private static final long serialVersionUID = -7703397292218210950L;
    
    public IllegalStateException() {
        super();
    }

    public IllegalStateException(Exception e) {
        super(e);
    }

    public IllegalStateException(String message) {
        super(message);
    }
}
