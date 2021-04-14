package networking.states;

import games.GameEnum;
import games.Move;
import networking.NetworkManager;

public abstract class State {

    public void acceptChallenge(NetworkManager manager, int challengeNumber) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void challengePlayer(NetworkManager manager, String opponent, GameEnum game) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void forfeit(NetworkManager manager) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void getGameList(NetworkManager manager) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void getPlayerList(NetworkManager manager) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void help(NetworkManager manager) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void login(NetworkManager manager, String name) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void logout(NetworkManager manager) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void sendMove(NetworkManager manager, Move move, int boardSize) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }

    public void subscribe(NetworkManager manager, GameEnum game) throws IllegalStateException {
        throw new IllegalStateException(String.format("Command illegal in current state: %s", this));
    }
}
