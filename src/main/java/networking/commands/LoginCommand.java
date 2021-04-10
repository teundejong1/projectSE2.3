package networking.commands;
/**
 * Commandclass for logging in to the server
 * @author Jeroen Lammersma
 */
public class LoginCommand implements Command {
    
    private String name;

    /**
     * Constructor
     * @param name required for login. Will be shown on the server
     */
    public LoginCommand(String name) {
        this.name = name;
    }

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
        return String.format("LOGIN %s", name);
    }

}
