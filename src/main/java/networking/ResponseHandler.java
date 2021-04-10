package networking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import networking.commands.Command;

public class ResponseHandler {

    private NetworkManager manager;
    private BlockingQueue<String> inputBuffer;
    private Command lastCommand;

    public ResponseHandler(NetworkManager manager,
            BlockingQueue<String> inputBuffer) {
        
        this.manager = manager;
        this.inputBuffer = inputBuffer;
    }

    public void setLastCommand(Command command) {
        lastCommand = command;
    }

    class ResponseListener implements Runnable {

        @Override
        public void run() {
            String line;
            
            while(manager.isConnected()) {
                try {
                    if ((line = poll()) != null) {
                        if (!isOkResponse(line)) {
                            parseResponse();
                        } else {

                        }
                        // kijken of line == OK
                        // poll
                        // als line != OK, dan niet nog keer pollen
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }

        private String poll() throws InterruptedException {
            return inputBuffer.poll(50, TimeUnit.MILLISECONDS);
        }

        private void parseResponse() {}

        private boolean isOkResponse(String line) {
            return line.equalsIgnoreCase("OK");
        }

    }
    
}
