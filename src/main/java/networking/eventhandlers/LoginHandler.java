package networking.eventhandlers;

import networking.NetworkManager;
import networking.states.LoggedInState;

public class LoginHandler implements CommandHandler {

    NetworkManager manager = NetworkManager.getInstance("", 0);

    public void handle(String response) {
        manager.setState(new LoggedInState());
        System.out.println("Logged in");
        // stuur iets naar de gui
    }

    public void handleError(String response) {
        // wat gebeurd er bij error
    }
    
}
