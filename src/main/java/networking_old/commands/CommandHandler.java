package networking_old.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import networking_old.Parser;

public class CommandHandler implements Runnable{

    private BlockingQueue<String> inputBuffer;
    private BlockingQueue<String> outputBuffer;
    private networking_old.Parser parser;


//    public CommandHandler(BlockingQueue<String> inputBuffer, BlockingQueue<String> outputBuffer) {
//        this.inputBuffer = inputBuffer;
//        this.outputBuffer = outputBuffer;
//        parser = new Parser();
//
//    }

    public CommandHandler(LinkedBlockingQueue<String> inputBuffer) {
        this.inputBuffer = inputBuffer;
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
//                System.out.println(sb);
                String moreInput;

                // Check of er meer input is
                while ((moreInput  = poll())!= null){

                    sb.append(System.getProperty("line.separator") + moreInput);
                }

                parser.parseIn(sb.toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } // try/catch

        } //while

    }

}
