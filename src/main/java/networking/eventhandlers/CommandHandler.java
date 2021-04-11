package networking.eventhandlers;

public interface CommandHandler extends Handler {
    
    public abstract void handleError(String response);

}
