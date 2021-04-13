<<<<<<< HEAD
//package networking;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import games.GameEnum;
//import games.Move;
//import networking.commands.Command;
//import networking.connection.Connection;
//import networking.connection.ConnectionFailedException;
//import networking.connection.SocketFactory;
//import networking.states.LoggedOutState;
//import networking.states.DisconnectedState;
//import networking.states.IllegalStateException;
//import networking.states.State;
//import threadpool.ThreadPool;
//
//public class NetworkManager {
//
//    private volatile static NetworkManager manager;
//
//    private ThreadPoolExecutor executor;
//    private Connection connection;
//    private State currentState;
//    private BlockingQueue<String> inputBuffer;
//    private ResponseHandler responseHandler;
//    private Object lock = new Object();
//    private boolean ready;
//
//    public static NetworkManager getInstance(String ip, int port) {
//        if (manager == null) {
//            synchronized (ThreadPool.class) {
//                if (manager == null) {
//                    try {
//                        manager = new NetworkManager(ip, port);
//                    } catch (ConnectionFailedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        return manager;
//    }
//
//    public static void deleteInstance() {
//        synchronized (ThreadPool.class) {
//            manager = null;
//        }
//    }
//
//    private NetworkManager(String ip, int port) throws ConnectionFailedException {
//        executor = ThreadPool.getInstance();
//        inputBuffer = new LinkedBlockingQueue<>();
//        currentState = new DisconnectedState();
//        initConnection(ip, port);
//        ready = true;
//        responseHandler = new ResponseHandler(this, inputBuffer, lock);
//    }
//
//    public void setState(State state) {
//        this.currentState = state;
//    }
//
//    public State getState() {
//        return currentState;
//    }
//
//    public void setReady() {
//        ready = true;
//    }
//
//    public boolean isConnected() {
//        return !(currentState instanceof DisconnectedState);
//    }
//
//    public void sendCommand(Command command) {
//        executor.submit(new CommandTask(command));
//    }
//
//    public void acceptChallenge(int challengeNumber) throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.acceptChallenge(this, challengeNumber);
//    }
//
//    public void challengePlayer(String opponent, GameEnum game) throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.challengePlayer(this, opponent, game);
//    }
//
//    public void forfeit() throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.forfeit(this);
//    }
//
//    public void getGameList() throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//        synchronized(lock2) {
//            while (!isReady) {
//                try {
//                    System.out.println("wait");
//                    lock2.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            isReady = false;
//        }
//        System.out.println("getgamelist");
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.getGameList(this);
//    }
//
//    public void getPlayerList() throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.getPlayerList(this);
//    }
//
//    public void help() throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.help(this);
//    }
//
//    public void login(String name) throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//        synchronized(lock2) {
//            while (!isReady) {
//                try {
//                    System.out.println("wait");
//                    lock2.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            isReady = false;
//        }
//        System.out.println("logging in");
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.login(this, name);
//    }
//
//    public void logout() throws IllegalStateException {
//        currentState.logout(this);
//        connection.close();
//        setState(new DisconnectedState());
//    }
//
//    public void sendMove(Move move, int boardSize) throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.sendMove(this, move, boardSize);
//    }
//
//    public void subscribe(GameEnum game) throws IllegalStateException {
//<<<<<<< HEAD
//        syncWait();
//=======
//>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
//        currentState.subscribe(this, game);
//    }
//
//    private void initConnection(String ip, int port) throws ConnectionFailedException {
//        try {
//            Socket socket = SocketFactory.createSocket(ip, port);
//            connection = new Connection(socket, inputBuffer);
//            currentState = new LoggedOutState();
//        } catch (IOException ioe) {
//            throw new ConnectionFailedException(ioe);
//        }
//    }
//
//    /**
//     * Method for waiting for feedback before sending a new command
//     * required for every command send
//     */
//    private void syncWait() {
//        synchronized(lock) {
//            while (!ready) {
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            ready = false;
//        }
//    }
//
//    /**
//     * Inner class CommandTask
//     */
//    class CommandTask implements Runnable {
//
//        private Command command;
//
//        public CommandTask(Command command) {
//            this.command = command;
//        }
//
//        @Override
//        public void run() {
//            responseHandler.setLastCommand(command);
//            connection.write(command);
//        }
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        NetworkManager manager = NetworkManager.getInstance("localhost", 7789);
//        manager.login("jeroenn");
//        manager.getGameList();
//        manager.getPlayerList();
//        TimeUnit.SECONDS.sleep(1);
//        manager.logout();
//    }
//
//}
=======
package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
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
    private ReentrantLock rlock = new ReentrantLock();
    private Object lock2 = new Object();
    private boolean isReady = true;

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
        responseHandler = new ResponseHandler(this, inputBuffer, lock2);
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getState() {
        return currentState;
    }

    public void setReady() {
        isReady = true;
    }

    public boolean isConnected() {
        return !(currentState instanceof DisconnectedState);
    }

    public void sendCommand(Command command) {
        executor.submit(new CommandTask(command));
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
        synchronized(lock2) {
            while (!isReady) {
                try {
                    System.out.println("wait");
                    lock2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isReady = false;
        }
        System.out.println("getgamelist");
        currentState.getGameList(this);
    }

    public void getPlayerList() throws IllegalStateException {
        currentState.getPlayerList(this);
    }

    public void help() throws IllegalStateException {
        currentState.help(this);
    }

    public void login(String name) throws IllegalStateException {
        synchronized(lock2) {
            while (!isReady) {
                try {
                    System.out.println("wait");
                    lock2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isReady = false;
        }
        System.out.println("logging in");
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

    class CommandTask implements Runnable {

        private Command command;

        public CommandTask(Command command) {
            this.command = command;
        }

        @Override
        public void run() {
            // synchronized(lock) {
            //     while (responseHandler.isCommandSet()) {
            //         try {
            //             System.out.println("wait");
            //             lock.wait();
            //         } catch (InterruptedException e) {
            //             e.printStackTrace();
            //         }
            //     }
            // }
            responseHandler.setLastCommand(command);
            connection.write(command);
        }
        
    }

    public static void main(String[] args) throws Exception {
        NetworkManager manager = NetworkManager.getInstance("localhost", 7789);
        // TODO zorgen dat je wacht totdat lastCommand weer null is.
        // OF maak gebruik van een queue, dat is beter denk ik?

        manager.login("jeroen");
        manager.getGameList();
    }

}
>>>>>>> parent of 968efb5 (Merge branch 'main' into fxml)
