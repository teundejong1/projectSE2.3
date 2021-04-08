package networking.commands;

import networking.Parser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CommandHandler implements Runnable{

    private BlockingQueue<String> inputBuffer;
    private BlockingQueue<String> outputBuffer;
    private networking.Parser parser;


    public CommandHandler(BlockingQueue<String> inputBuffer, BlockingQueue<String> outputBuffer) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
        parser = new Parser();

    }

    private String poll() throws InterruptedException {
        return inputBuffer.poll(50, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {

        StringBuilder sb;

        while (true) {

            try {
                // krijg input van Server
                sb = new StringBuilder(inputBuffer.take());
                String moreInput;

                // Check of er meer input is
                while ((moreInput = poll()) != null) {

                    sb.append(System.getProperty("line.separator") + moreInput);
                }

                System.out.println(sb.toString());
                parser.parse(sb.toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } //while

    }

}
