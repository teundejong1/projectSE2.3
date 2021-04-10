package networking.commands;
/**
 * Commandclass to send the forfeit command to the server
 * @author Jeroen Lammersma
 */
public class ForfeitCommand implements Command {

    public ForfeitCommand() {}

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
        return "FORFEIT";
    }

    
}
