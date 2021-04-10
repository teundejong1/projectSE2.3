package networking.commands;

public class ChallengeAcceptCommand implements Command {

    private int challengeNumber;

    public ChallengeAcceptCommand(int challengeNumber) {
        this.challengeNumber = challengeNumber;
    }

    @Override
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        return String.format("CHALLENGE ACCEPT %d", challengeNumber);
    }
    
}
