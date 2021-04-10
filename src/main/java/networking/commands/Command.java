package networking.commands;
/**
 * interface for all the commands
 * @author Jeroen Lammersma
 */
public interface Command {

    /**
     * Confirmation that the command has been received by the server
     * required for every command
     */
    void validateResponse();

}
