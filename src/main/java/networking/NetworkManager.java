package networking;

import threadpool.ThreadPool;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class NetworkManager {

    public static void main(String[] args) {

        // create the buffers
        LinkedBlockingQueue<String> inputBuffer = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<String> outputBuffer = new LinkedBlockingQueue<>();

        try (
                Connection connection = new Connection( inputBuffer, outputBuffer);
                Scanner scanner = new Scanner(System.in)
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

}
