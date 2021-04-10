package networking.commands;
/**
 * Commandclass for logging out of the server and disconnect
 * @author Jeroen Lammersma
 */
public class LogoutCommand implements Command {

    public LogoutCommand() {}

    /**
     * Check to confirm server received command correctly
     * @Override
     */
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    /**
     * writes the command to be send into string form
     * @Override
     */
    public String toString() {
        return "LOGOUT";
    }

}
