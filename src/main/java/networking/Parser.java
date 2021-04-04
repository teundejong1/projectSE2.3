package networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser implements Runnable {

    private BlockingQueue<String> inputQueue;

    public Parser() {}

    public Parser(BlockingQueue<String> inputQueue) {
        this.inputQueue = inputQueue;
    }

    @Override
    public void run() {

        StringBuilder sb;

        while (true) {
            try {
                sb = new StringBuilder(inputQueue.take());
                String moreInput;

                while ((moreInput = poll()) != null) {
                    sb.append(System.getProperty("line.separator") + moreInput);
                }

                System.out.println(sb.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

    private String poll() throws InterruptedException {
        return inputQueue.poll(50, TimeUnit.MILLISECONDS);
    }

    private ArrayList<String> parseList(String input) {
        ArrayList<String> list = new ArrayList<>();

        final Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(input);
        
        while (m.find()) {
            list.add(m.group(1));
        }

        return list;
    }

    private HashMap<String, String> parseMap(String input) {
        HashMap<String, String> map = new HashMap<>();

        final Pattern p = Pattern.compile("(\\w+):\\s\"([^\"]*)\"");
        Matcher m = p.matcher(input);
        
        while (m.find()) {
            map.put(m.group(1), m.group(2));
        }

        return map;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();

        String games = "[\"Reversi\", \"Tic-tac-toe\"]";
        ArrayList<String> list = parser.parseList(games);
        System.out.println(list);

        String keyvalues = "{GAMTYPE: \"<speltype>\", PLAYERTOMOVE: \"<naam speler1>\", OPPONENT: \"<naam tegenstander>\"}";
        HashMap<String, String> map = parser.parseMap(keyvalues);
        System.out.println(map);
    }

}
