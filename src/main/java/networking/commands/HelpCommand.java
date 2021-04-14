package networking.commands;
/**
 * Commandclass to send a HELP request to the server
 * @author Jeroen Lammersma
 */
public class HelpCommand implements Command {

    public HelpCommand() {}

    @Override
    public boolean isReponseTwoLines() {
        return false;
    }
    
    @Override
    public boolean isValidResponse(String response) {
        return response.equalsIgnoreCase("OK");
    }

    /**
     * writes the command to be send into string form
     * @Override
     */
    public String toString() {
        return "HELP";
    }
    
}
