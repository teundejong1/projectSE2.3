package networking.commands;
/**
 * Commandclass to send the forfeit command to the server
 * @author Jeroen Lammersma
 */
public class ForfeitCommand implements Command {

    public ForfeitCommand() {}

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
        return "FORFEIT";
    }

    
}
