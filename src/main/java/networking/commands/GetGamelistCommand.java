package networking.commands;

/**
 * Commandclass to ask for a list of games available to play on the server
 * @author Jeroen Lammersma
 */
public class GetGamelistCommand implements Command {
    
    public GetGamelistCommand() {}

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
        return "GET GAMELIST";
    }
    
}
