package networking.states;

import networking.NetworkManager;
import networking.commands.LoginCommand;

/**
 * 
 * @author Jeroen Lammersma
 */
public class LoggedOutState extends State {
    
    @Override
    public void login(NetworkManager manager, String name) throws IllegalStateException {
        manager.sendCommand(new LoginCommand(name));
        manager.setUsername(name);
    }

}
