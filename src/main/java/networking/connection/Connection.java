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

public class Connection implements AutoCloseable {

    private static final String CHECK_ONE = "Strategic Game Server Fixed [Version 1.1.0]";
    private static final String CHECK_TWO = "(C) Copyright 2015 Hanzehogeschool Groningen";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private final BlockingQueue<String> inputBuffer;

    public Connection(Socket socket, BlockingQueue<String> inputBuffer)
            throws ConnectionFailedException {

        this.socket = socket;
        this.inputBuffer = inputBuffer;

        try {
            initIO();
            initInputListener();
        } catch (IOException e) {
            throw new ConnectionFailedException("Connection cannot be established", e);
        }

    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public void write(Command command) { // TODO must throw custom exception when not connected
        if (socket.isConnected() && !socket.isClosed()) {
            out.println(command);
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidServer() throws IOException {
        return (in.readLine().equals(CHECK_ONE) && in.readLine().equals(CHECK_TWO));
    }

    private void initIO() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        in.readLine();
        in.readLine();

        // if (!isValidServer()) throw new InvalidServerException("Server is not Strategic Game Server");
    }

    private void initInputListener() {
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(new InputListener());
    }

    /**
     * Innerclass Inputlistener
     */
    class InputListener implements Runnable {

        public InputListener() {}

        @Override
        public void run() {
            while(socket.isConnected() && !socket.isClosed()) {
                try {
                    inputBuffer.add(in.readLine());
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            };
        }

    }

}