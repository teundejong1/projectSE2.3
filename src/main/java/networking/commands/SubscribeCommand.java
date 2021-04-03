package networking.commands;

public class SubscribeCommand {

    private String game;

    public SubscribeCommand(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return String.format("SUBSCRIBE %s", game);
    }
    
}
