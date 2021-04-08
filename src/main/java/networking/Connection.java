package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import threadpool.ThreadPool;

class Connection implements AutoCloseable {

     public static void main(String[] args) {

         LinkedBlockingQueue<String> inputBuffer = new LinkedBlockingQueue<>();
         LinkedBlockingQueue<String> outputBuffer = new LinkedBlockingQueue<>();

         try (
             Connection connection = new Connection(inputBuffer, outputBuffer);
             Scanner scanner = new Scanner(System.in);
         ) {

             ThreadPoolExecutor tp = ThreadPool.getInstance();

             //tp.submit(new Parser(inputBuffer));

             while(connection.isConnected() && !connection.isClosed()) {
                 connection.write(scanner.nextLine());
             }

         } catch (ConnectionFailedException cfe) {
             cfe.printStackTrace();
         }

         ThreadPool.shutdown();
         System.out.println("Shutdown");
     }

    private static final String CHECK_ONE = "Strategic Game Server Fixed [Version 1.1.0]";
    private static final String CHECK_TWO = "(C) Copyright 2015 Hanzehogeschool Groningen";

    private static final int DEFAULT_PORT = 7789;
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private BlockingQueue<String> inputBuffer;
    private BlockingQueue<String> outputBuffer;

    public Connection(BlockingQueue<String> inputBuffer, BlockingQueue<String> outputBuffer)
            throws ConnectionFailedException {
        
        this(DEFAULT_PORT, inputBuffer, outputBuffer);
    }

    public Connection(int port, BlockingQueue<String> inputBuffer,
            BlockingQueue<String> outputBuffer) throws ConnectionFailedException {

        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;

        try {
            initSocket(port);
            initInputListener();
            initOutputListener();
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

    public void write(String line) {
        outputBuffer.add(line);
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

    private void initSocket(int port) throws IOException {
        this.socket = new Socket("localhost", port); // TODO IP adres niet hard coded
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        if (!isValidServer()) throw new InvalidServerException("Server is not Strategic Game Server");
    }

    private void initInputListener() {
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(new InputListener());
    }

    private void initOutputListener() {
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(new OutputListener());
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

    /**
     * Innerclass outputlistener
     */
    class OutputListener implements Runnable {

        public OutputListener() {}
    
        @Override
        public void run() {
            String line;

            while(socket.isConnected() && !socket.isClosed()) {
                try {
                    line = outputBuffer.poll(50, TimeUnit.MILLISECONDS);
                    if (line != null) write(line);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            };
        }
        
        private void write(String line) {
            if (line.equals("bye")) close(); // TODO move logout logic 
            out.println(line);
        }
    
    }

}