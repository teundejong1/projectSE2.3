package networking.eventhandlers;

public class LoginHandler implements CommandHandler {

    public void handle(String response) {
        // doe er iets mee
        System.out.println("Logged in");
        // stuur iets naar de gui
    }

    public void handleError(String response) {
        // wat gebeurd er bij error
    }
    
}
