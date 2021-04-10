package networking.commands;
/**
 * Commandclass to send a HELP request to the server
 * @author Jeroen Lammersma
 */
public class HelpCommand implements Command {

    public HelpCommand() {}

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
        return "HELP";
    }
    
}
