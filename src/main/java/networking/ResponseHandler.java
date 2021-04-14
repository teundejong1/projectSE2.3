package networking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import networking.commands.Command;
import threadpool.ThreadPool;

/**
 * 
 * @author Jeroen Lammersma, Esther Zigterman Rustenburg
 */
public class ResponseHandler {

    private NetworkManager manager;
    private BlockingQueue<String> inputBuffer;
    private Command lastCommand;
    private Object lock;

    public ResponseHandler(NetworkManager manager,
            BlockingQueue<String> inputBuffer, Object lock) {
        
        this.manager = manager;
        this.inputBuffer = inputBuffer;
        this.lock = lock;

        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(new ResponseListener());
    }

    public void setLastCommand(Command command) {
        lastCommand = command;
    }

    public Command getLastComand() {
        return lastCommand;
    }

    public boolean isCommandSet() {
        return lastCommand != null;
    }

    class ResponseListener implements Runnable {

        @Override
        public void run() {
            String response;
            
            while(manager.isConnected()) {
                try {
                    if ((response = poll()) != null) {
                        if (isErrorResponse(response)) {
                            Parser.parseError(response, lastCommand);
                            ready();
                        } else if (isOkResponse(response)) {
                            if (lastCommand.isReponseTwoLines()) {
                                response += System.lineSeparator() + poll(1000);
                                System.out.println(response);
                            }

                            if (lastCommand.isValidResponse(response)) {
                                Parser.parseResponse(response, lastCommand);
                                ready();
                            }
                        } else if (response.startsWith("SVR GAME")) Parser.parseResponse(response);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }

        private void ready() {
            synchronized(lock) {
                lastCommand = null;
                manager.setReady();
                lock.notify();
            }
        }

        private String poll(int miliseconds) throws InterruptedException {
            return inputBuffer.poll(miliseconds, TimeUnit.MILLISECONDS);
        }

        private String poll() throws InterruptedException {
            return poll(500);
        }

        private boolean isOkResponse(String line) {
            return line.equalsIgnoreCase("OK");
        }

        private boolean isErrorResponse(String line) {
            return line.toUpperCase().startsWith("ERR");
        }

    }
    
}
