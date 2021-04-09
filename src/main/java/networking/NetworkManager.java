package networking;

import networking.commands.CommandHandler;
import threadpool.ThreadPool;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class NetworkManager{

    private final LinkedBlockingQueue<String> inputBuffer = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<String> outputBuffer = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        // create the buffers
        LinkedBlockingQueue<String> inputBuffer = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<String> outputBuffer = new LinkedBlockingQueue<>();

        try (
                // create connection and scanner
                Connection connection = new Connection( inputBuffer, outputBuffer);
                Scanner scanner = new Scanner(System.in)
        ) {
            // get threadpool and add CommandHandler into it
            ThreadPoolExecutor tp = ThreadPool.getInstance();

            tp.submit(new CommandHandler(inputBuffer));

            while(connection.isConnected() && !connection.isClosed()) {
                connection.write(scanner.nextLine());
            }

        } catch (ConnectionFailedException cfe) {
            cfe.printStackTrace();
        }

        ThreadPool.shutdown();
        System.out.println("Shutdown");
    }

}
