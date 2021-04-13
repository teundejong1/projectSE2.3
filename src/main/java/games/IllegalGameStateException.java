package games;

public class IllegalGameStateException extends Exception {
    
    private static final long serialVersionUID = 7474711587716493464L;

    public IllegalGameStateException() {
        super();
    }

    public IllegalGameStateException(Exception e) {
        super("");
    }

    public IllegalGameStateException(String message) {
        super(message);
    }

    public IllegalGameStateException(String message, Exception e) {
        super(message, e);
    }

}
