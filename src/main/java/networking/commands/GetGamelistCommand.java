package networking.commands;

/**
 * Commandclass to ask for a list of games available to play on the server
 * @author Jeroen Lammersma
 */
public class GetGamelistCommand implements Command {
    
    public GetGamelistCommand() {}

    @Override
    public boolean isReponseTwoLines() {
        return true;
    }

    @Override
    public boolean isValidResponse(String response) {
        String[] lines = response.split(System.lineSeparator());
        if (lines.length < 2) return false;
        return (lines[0].equalsIgnoreCase("OK") && lines[1].startsWith("SVR GAMELIST"));
    }

    /**
     * writes the command to be send into string form
     * @Override
     */
    public String toString() {
        return "GET GAMELIST";
    }
    
}
