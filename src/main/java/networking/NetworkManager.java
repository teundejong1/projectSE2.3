package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import games.GameEnum;
import games.Move;
import networking.commands.Command;
import networking.commands.GetGamelistCommand;
import networking.commands.LoginCommand;
import networking.connection.Connection;
import networking.connection.ConnectionFailedException;
import networking.connection.SocketFactory;
import networking.states.LoggedOutState;
import networking.states.DisconnectedState;
import networking.states.IllegalStateException;
import networking.states.State;
import threadpool.ThreadPool;

public class NetworkManager {

    private ThreadPoolExecutor executor;
    private Connection connection;
    private State currentState;
    private BlockingQueue<String> inputBuffer;
    private ResponseHandler responseHandler;

    public NetworkManager() throws ConnectionFailedException {
        this("localhost", 7789);
    }

    public NetworkManager(String ip, int port) throws ConnectionFailedException {
        executor = ThreadPool.getInstance();
        inputBuffer = new LinkedBlockingQueue<>();
        currentState = new DisconnectedState();
        initConnection(ip, port);
        responseHandler = new ResponseHandler(this, inputBuffer);
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getState() {
        return currentState;
    }

    public boolean isConnected() {
        return !(currentState instanceof DisconnectedState);
    }

    public void sendCommand(Command command) {
        responseHandler.setLastCommand(command);
        executor.submit(() -> connection.write(command));
    }

    public void acceptChallenge(int challengeNumber) throws IllegalStateException {
        currentState.acceptChallenge(this, challengeNumber);
    }

    public void challengePlayer(String opponent, GameEnum game) throws IllegalStateException {
        currentState.challengePlayer(this, opponent, game);
    }

    public void forfeit() throws IllegalStateException {
        currentState.forfeit(this);
    }

    public void getGameList() throws IllegalStateException {
        currentState.getGameList(this);
    }

    public void getPlayerList() throws IllegalStateException {
        currentState.getPlayerList(this);
    }

    public void help() throws IllegalStateException {
        currentState.help(this);
    }

    public void login(String name) throws IllegalStateException {
        currentState.login(this, name);
    }

    public void logout() throws IllegalStateException {
        currentState.logout(this);
        connection.close();
        setState(new DisconnectedState());
    }

    public void sendMove(Move move, int boardSize) throws IllegalStateException {
        currentState.sendMove(this, move, boardSize);
    }

    public void subscribe(GameEnum game) throws IllegalStateException {
        currentState.subscribe(this, game);
    }

    private void initConnection(String ip, int port) throws ConnectionFailedException {
        try {
            Socket socket = SocketFactory.createSocket(ip, port);
            connection = new Connection(socket, inputBuffer);
            currentState = new LoggedOutState();
        } catch (IOException ioe) {
            throw new ConnectionFailedException(ioe);
        }
    }


    public static void main(String[] args) throws Exception {
        NetworkManager manager = new NetworkManager();
        // TODO zorgen dat je wacht totdat lastCommand weer null is.
        // OF maak gebruik van een queue, dat is beter denk ik?

        manager.login("jeroen");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        manager.getGameList();
    }

}
