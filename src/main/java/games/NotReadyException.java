package games;

public class NotReadyException extends Exception {

    private static final long serialVersionUID = -8630724361260449927L;

    public NotReadyException() {
        super();
    }

    public NotReadyException(Exception e) {
        super("");
    }

    public NotReadyException(String message) {
        super(message);
    }
}
