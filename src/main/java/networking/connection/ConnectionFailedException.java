package networking.connection;

import java.io.IOException;

public class ConnectionFailedException extends IOException {

    private static final long serialVersionUID = -8156227286971895495L;

    public ConnectionFailedException() {
        super();
    }

    public ConnectionFailedException(Exception e) {
        super(e);
    }

    public ConnectionFailedException(String message) {
        super(message);
    }

    public ConnectionFailedException(String message, Exception e) {
        super(message, e);
    }
    
}
