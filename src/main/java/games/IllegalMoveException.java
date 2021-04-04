package games;

public class IllegalMoveException extends Exception {
    
    private static final long serialVersionUID = -8510036369422750408L;

    public IllegalMoveException() {
        super();
    }

    public IllegalMoveException(Exception e) {
        super("");
    }

    public IllegalMoveException(String message) {
        super(message);
    }
}
