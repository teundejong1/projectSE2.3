package networking;

import networking.commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * haalt commando's uit inputbuffer (om te verwerken
 */

public class Parser{

    private BlockingQueue<String> inputBuffer;

    public Parser() {}

    //public Parser(BlockingQueue<String> inputBuffer) {
//        this.inputBuffer = inputBuffer;
//    }

    public Command parse(String input){
        // change string to Command


        //TODO link commandos


        return null;
    }


//    @Override
//    public void run() {
//
//        // sb bestaat alleen in run?
//
//        StringBuilder sb;
//
//        while (true) {
//            try {
//                // krijg input van Server
//                sb = new StringBuilder(inputBuffer.take());
//                String moreInput;
//
//                // Check of er meer input is
//                while ((moreInput = poll()) != null) {
//                    sb.append(System.getProperty("line.separator") + moreInput);
//                }
//
//                System.out.println(sb.toString());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    /**
     * Checkt voor input
     * @return nieuwe input
     * @throws InterruptedException
     */
    private String poll() throws InterruptedException {
        return inputBuffer.poll(50, TimeUnit.MILLISECONDS);
    }

    /**
     * zet String om tot lijst
     * @param input
     * @return
     */
    private ArrayList<String> parseList(String input) {
        ArrayList<String> list = new ArrayList<>();

        final Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(input);
        
        while (m.find()) {
            list.add(m.group(1));
        }

        return list;
    }

    /**
     * zet string om tot HashMap
     * @param input
     * @return
     */
    private HashMap<String, String> parseMap(String input) {
        HashMap<String, String> map = new HashMap<>();

        final Pattern p = Pattern.compile("(\\w+):\\s\"([^\"]*)\"");
        Matcher m = p.matcher(input);
        
        while (m.find()) {
            map.put(m.group(1), m.group(2));
        }

        return map;
    }

//    public static void main(String[] args) {
//        Parser parser = new Parser();
//
//        String games = "[\"Reversi\", \"Tic-tac-toe\"]";
//        ArrayList<String> list = parser.parseList(games);
//        System.out.println(list);
//
//        String keyvalues = "GET GAMELIST {GAMTYPE: \"<speltype>\", PLAYERTOMOVE: \"<naam speler1>\", OPPONENT: \"<naam tegenstander>\"}";
//        HashMap<String, String> map = parser.parseMap(keyvalues);
//        System.out.println(map);
//    }

}
