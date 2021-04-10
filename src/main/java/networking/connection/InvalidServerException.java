package networking.connection;
/**
 * Class for throwing an exception if it is not a valid server
 * extends the ConnectionFailedException
 * @author Jeroen Lammersma
 */
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
