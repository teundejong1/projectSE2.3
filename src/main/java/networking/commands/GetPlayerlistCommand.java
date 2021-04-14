package networking.commands;
/**
 * Commandclass to receive a list of the current players in the room
 * @author Jeroen Lammersma
 */
public class GetPlayerlistCommand implements Command {

    public GetPlayerlistCommand() {}

    @Override
    public boolean isReponseTwoLines() {
        return true;
    }

    @Override
    public boolean isValidResponse(String response) {
        String[] lines = response.split(System.lineSeparator());
        if (lines.length < 2) return false;
        return (lines[0].equalsIgnoreCase("OK") && lines[1].startsWith("SVR PLAYERLIST"));
    }

    /**
     * writes the command to be send into string form
     * @Override
     */
    public String toString() {
        return "get playerlist";
    }

}
