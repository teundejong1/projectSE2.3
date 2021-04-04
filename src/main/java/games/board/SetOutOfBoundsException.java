package games.board;

public class SetOutOfBoundsException extends Exception {
    
    private static final long serialVersionUID = -8510036369422750408L;

    public SetOutOfBoundsException() {
        super();
    }

    public SetOutOfBoundsException(Exception e) {
        super(e);
    }

    public SetOutOfBoundsException(String message) {
        super(message);
    }
}
