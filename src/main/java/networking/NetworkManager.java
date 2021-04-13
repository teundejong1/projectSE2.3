package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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

public class NetworkManager {

    private volatile static NetworkManager manager;

    private ThreadPoolExecutor executor;
    private Connection connection;
    private State currentState;
    private BlockingQueue<String> inputBuffer;
    private ResponseHandler responseHandler;
    private Object lock = new Object();
    private boolean ready;

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

    public static void deleteInstance() {
        synchronized (ThreadPool.class) {
            manager = null;
        }
    }

    private NetworkManager(String ip, int port) throws ConnectionFailedException {
        executor = ThreadPool.getInstance();
        inputBuffer = new LinkedBlockingQueue<>();
        currentState = new DisconnectedState();
        initConnection(ip, port);
        ready = true;
        responseHandler = new ResponseHandler(this, inputBuffer, lock);
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

    public void setReady() {
        ready = true;
    }

    public void sendCommand(Command command) {
        executor.submit(new CommandTask(command));
    }

    public void acceptChallenge(int challengeNumber) throws IllegalStateException {
        lock();
        currentState.acceptChallenge(this, challengeNumber);
    }

    public void challengePlayer(String opponent, GameEnum game) throws IllegalStateException {
        lock();
        currentState.challengePlayer(this, opponent, game);
    }

    public void forfeit() throws IllegalStateException {
        lock();
        currentState.forfeit(this);
    }

    public void getGameList() throws IllegalStateException {
        lock();
        System.out.println("getgamelist");
        currentState.getGameList(this);
    }

    public void getPlayerList() throws IllegalStateException {
        lock();
        currentState.getPlayerList(this);
    }

    public void help() throws IllegalStateException {
        lock();
        currentState.help(this);
    }

    public void login(String name) throws IllegalStateException {
        lock();
        System.out.println("logging in");
        currentState.login(this, name);
    }

    public void logout() throws IllegalStateException {
        currentState.logout(this);
        connection.close();
        setState(new DisconnectedState());
    }

    public void sendMove(Move move, int boardSize) throws IllegalStateException {
        lock();
        currentState.sendMove(this, move, boardSize);
    }

    public void subscribe(GameEnum game) throws IllegalStateException {
        lock();
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

    private void lock() {
        synchronized(lock) {
            while (!ready) {
                try {
                    System.out.println("wait");
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
