package networking.connection;

public class InvalidServerException extends ConnectionFailedException {

    private static final long serialVersionUID = 5729346672013551767L;

    public InvalidServerException() {
        super();
    }

    public InvalidServerException(Exception e) {
        super(e);
    }

    public InvalidServerException(String message) {
        super(message);
    }
    
}
