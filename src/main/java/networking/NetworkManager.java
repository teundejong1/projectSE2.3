package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import games.GameEnum;
import games.Move;
import networking.commands.Command;
import networking.connection.Connection;
import networking.connection.ConnectionFailedException;
import networking.connection.SocketFactory;
import networking.states.LoggedOutState;
import networking.states.DisconnectedState;
import networking.states.IllegalStateException;
import networking.states.State;
import threadpool.ThreadPool;

/**
 * Manages the Networkconnection, provides commands to be send
 * and keeps track of the state it's in compared to the server
 * @author Jeroen Lammersma, Esther Zigterman Rustenburg
 */
public class NetworkManager {

    private volatile static NetworkManager manager;

    private ThreadPoolExecutor executor;
    private Connection connection;
    private State currentState;
    private BlockingQueue<String> inputBuffer;
    private ResponseHandler responseHandler;
    private Object lock = new Object();
    private boolean ready;

    /**
     * Singleton for the Networkmanager
     * @param ip address of server
     * @param port value
     * @return a manager
     */
    public static NetworkManager getInstance(String ip, int port) {
        if (manager == null) {
            synchronized (ThreadPool.class) {
                if (manager == null) {
                    try {
                        manager = new NetworkManager(ip, port);
                    } catch (ConnectionFailedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        return manager;
    }

    /**
     * Method for deleting the instance of NetworkManager,
     * so a new one can be created
     */
    public static void deleteInstance() {
        synchronized (ThreadPool.class) {
            manager = null;
        }
    }

    /**
     * Private constructor for NetworkManager
     * @param ip address of the server
     * @param port value
     * @throws ConnectionFailedException when connection could not be established
     */
    private NetworkManager(String ip, int port) throws ConnectionFailedException {
        executor = ThreadPool.getInstance();
        inputBuffer = new LinkedBlockingQueue<>();
        currentState = new DisconnectedState();
        initConnection(ip, port);
        ready = true;
        responseHandler = new ResponseHandler(this, inputBuffer, lock);
    }

    /**
     * changes the state. Different states allow different commandt to be send
     * @param state new state to change into
     */
    public void setState(State state) {
        this.currentState = state;
    }

    /**
     * @return currrent state it is in
     */
    public State getState() {
        return currentState;
    }

    /**
     * sets ready to true. So the next command can be send
     */
    public void setReady() {
        ready = true;
    }

    /**
     * @return true if current state is not in disconnected state
     */
    public boolean isConnected() {
        return !(currentState instanceof DisconnectedState);
    }

    /**
     *
     * @param command to be send to the server
     */
    public void sendCommand(Command command) {
        executor.submit(new CommandTask(command));
    }

    /**
     * the send a command to accept a challenge
     * @param challengeNumber of the challenge to be accepted
     * @throws IllegalStateException if you're not in a state where you should be able to accept a challenge
     */
    public void acceptChallenge(int challengeNumber) throws IllegalStateException {
        syncWait();
        currentState.acceptChallenge(this, challengeNumber);
    }

    public void challengePlayer(String opponent, GameEnum game) throws IllegalStateException {
        syncWait();
        currentState.challengePlayer(this, opponent, game);
    }

    public void forfeit() throws IllegalStateException {
        syncWait();
        currentState.forfeit(this);
    }

    public void getGameList() throws IllegalStateException {
        syncWait();
        currentState.getGameList(this);
    }

    public void getPlayerList() throws IllegalStateException {
        syncWait();
        currentState.getPlayerList(this);
    }

    public void help() throws IllegalStateException {
        syncWait();
        currentState.help(this);
    }

    public void login(String name) throws IllegalStateException {
        syncWait();
        currentState.login(this, name);
    }

    public void logout() throws IllegalStateException {
        currentState.logout(this);
        connection.close();
        setState(new DisconnectedState());
    }

    public void sendMove(Move move, int boardSize) throws IllegalStateException {
        syncWait();
        currentState.sendMove(this, move, boardSize);
    }

    public void subscribe(GameEnum game) throws IllegalStateException {
        syncWait();
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

    /**
     * Method for waiting for feedback before sending a new command
     * required for every command send
     */
    private void syncWait() {
        synchronized(lock) {
            while (!ready) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ready = false;
        }
    }

    /**
     * Inner class CommandTask
     */
    class CommandTask implements Runnable {

        private Command command;

        public CommandTask(Command command) {
            this.command = command;
        }

        @Override
        public void run() {
            responseHandler.setLastCommand(command);
            connection.write(command);
        }
        
    }

    public static void main(String[] args) throws Exception {
        NetworkManager manager = NetworkManager.getInstance("localhost", 7789);
        manager.login("jeroen");
        manager.getGameList();
        // TimeUnit.SECONDS.sleep(1);
        // manager.logout();
    }

}
