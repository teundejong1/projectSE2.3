package networking.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import networking.commands.Command;
import threadpool.ThreadPool;

/***
 * Class for setting up the connection with the server
 * @author Jeroen Lammersma
 */
public class Connection implements AutoCloseable {

    private static final String CHECK_ONE = "Strategic Game Server Fixed [Version 1.1.0]";
    private static final String CHECK_TWO = "(C) Copyright 2015 Hanzehogeschool Groningen";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private BlockingQueue<String> inputBuffer;

    /**
     * Constructor
     * @param socket for the connection
     * @param inputBuffer to receive all data the server sends
     * @throws ConnectionFailedException when connection can't be established
     */
    public Connection(Socket socket, BlockingQueue<String> inputBuffer)
            throws ConnectionFailedException {

        this.socket = socket;
        this.inputBuffer = inputBuffer;

        // try setting up a connection, then initiate the InputListener
        try {
            initIO();
            initInputListener();
        } catch (IOException e) {
            throw new ConnectionFailedException("Connection cannot be established", e);
        }

    }

    /**
     * @return boolean wether a connection has been established with the server
     */
    public boolean isConnected() {
        return socket.isConnected();
    }

    /**
     * @return boolean wether socket has been closed
     */
    public boolean isClosed() {
        return socket.isClosed();
    }

    /**
     * @param command to be send to the server
     */
    public void write(Command command) { // TODO must throw custom exception when not connected
        if (socket.isConnected() && !socket.isClosed()) {
            out.println(command);
        }
    }

    public void writeUTF(String command) {
        if (socket.isConnected() && !socket.isClosed()) {
            out.println(command);
        }
    }

    /**
     * Method to close the connection with the server
     * @Override
     */
    public void close() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return wether connected server is the server we want to connect to
     * @throws IOException
     */
    private boolean isValidServer() throws IOException {
        return (in.readLine().equals(CHECK_ONE) && in.readLine().equals(CHECK_TWO));
    }

    /**
     * initiates the IO. creates a bufferedReader and a PrintWriter 
     * for receiving and sending data respectively
     * @throws IOException
     */
    private void initIO() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        in.readLine();
        in.readLine();

        // if (!isValidServer()) throw new InvalidServerException("Server is not Strategic Game Server");
    }

    /**
     * initiates a new inputlistener. Using a ThreadPoolExecutor from a ThreadPool instance
     * then submits a new inputlistener to the executor
     */
    private void initInputListener() {
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(new InputListener());
    }

    /**
     * Innerclass Inputlistener
     * uses a separate ThreadPool for receiving data
     */
    class InputListener implements Runnable {

        public InputListener() {}

        @Override
        public void run() {
            while(socket.isConnected() && !socket.isClosed()) {
                try {
                    if (in.ready()) {
                        inputBuffer.add(in.readLine());
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            };
        }

    }

}