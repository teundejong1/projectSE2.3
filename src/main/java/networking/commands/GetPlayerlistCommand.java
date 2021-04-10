package networking.commands;
/**
 * Commandclass to receive a list of the current players in the room
 * @author Jeroen Lammersma
 */
public class GetPlayerlistCommand implements Command {

    //NOTE This list might have to be called on more than once to keep an updated list of players in the lobby

    public GetPlayerlistCommand() {}

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
        return "GET PLAYERLIST";
    }

}
