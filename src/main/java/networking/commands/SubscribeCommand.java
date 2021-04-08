package networking.commands;

public class SubscribeCommand{

    private String game;

    /**
     * subscribe bij een game
     * @param game ttt of othello
     */
    public SubscribeCommand(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return String.format("SUBSCRIBE %s", game);
    }

}
