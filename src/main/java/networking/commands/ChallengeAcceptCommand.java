package networking.commands;
/**
 * CommandClass to accept a challenge 
 * @author Jeroen Lammersma
 */
public class ChallengeAcceptCommand implements Command {

    private int challengeNumber;

    /**
     * Contstructor
     * @param challengeNumber value related to the challenge to be accepted
     */
    public ChallengeAcceptCommand(int challengeNumber) {
        this.challengeNumber = challengeNumber;
    }
    
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
        return String.format("CHALLENGE ACCEPT %d", challengeNumber);
    }
    
}
