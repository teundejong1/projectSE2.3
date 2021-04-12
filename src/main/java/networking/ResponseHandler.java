package networking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import networking.commands.Command;
import threadpool.ThreadPool;

public class ResponseHandler {

    private NetworkManager manager;
    private BlockingQueue<String> inputBuffer;
    private Command lastCommand;

    public ResponseHandler(NetworkManager manager,
            BlockingQueue<String> inputBuffer) {
        
        this.manager = manager;
        this.inputBuffer = inputBuffer;

        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(new ResponseListener());
    }

    public void setLastCommand(Command command) {
        lastCommand = command;
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
                            System.out.println("Last command: " + lastCommand);
                            lastCommand = null;
                        } else if (isOkResponse(response)) {
                            if (lastCommand.isReponseTwoLines()) {
                                response += System.lineSeparator() + poll(200);
                            }

                            if (lastCommand.isValidResponse(response)) {
                                Parser.parseResponse(response, lastCommand);
                                System.out.println("Last command: " + lastCommand);
                                lastCommand = null;
                            }
                        } else if (response.startsWith("SVR GAME")) Parser.parseResponse(response);
                        
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }

        private String poll(int miliseconds) throws InterruptedException {
            return inputBuffer.poll(miliseconds, TimeUnit.MILLISECONDS);
        }

        private String poll() throws InterruptedException {
            return poll(50);
        }

        private boolean isOkResponse(String line) {
            return line.equalsIgnoreCase("OK");
        }

        private boolean isErrorResponse(String line) {
            return line.toUpperCase().startsWith("ERR");
        }

    }
    
}
