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
        return "LOGOUT";
    }

}
