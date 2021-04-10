package networking_old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import networking_old.commands.*;

/**
 * haalt commando's uit inputbuffer (om te verwerken
 */

public class Parser{

    private Command command;
    private Connection connection;

    public Parser() {

    }

    public Parser(Connection connection){
        this.connection = connection;
    }

    /**
     * go through commands comming in
     * @param input
     * @return
     */
    public void parseIn(String input){
        // get the first line of input
        input = input.replace("[", " ");
        input = input.replace("]", " ");
        String[] lines = input.split(System.getProperty("line.separator"));
        String line = lines[0];
        // get fist word of te line
        String[] words = line.split(" ");
        String word = words[0];

        switch (word.toLowerCase()){
            case "ok":
                // check if next line. --> handle next line with parser
                for(int i = 1; i < lines.length; i++){
                    // send every line on their own
                    parseIn(lines[i]);
                }
                break;
            case "svr":

                for(int i = 0; i < lines.length; i++){
                    String p = lines[i].replace("SVR ", "");
                    svrParser(p);
                }

                break;
            case "err":
                // print input
//                System.out.println(input);
                break;
            default:
//                System.out.println("not going well");
//                System.out.println(word.toLowerCase());
                // handle exception no know servercommand;

        }
    }

    public void svrParser(String input){
        // get the first line of input
//        String[] lines = input.split(System.getProperty("line.separator"));
//        String line = lines[0];
        // get fist word of te line
        String[] words = input.split(" ");
        String word = words[0];
        System.out.println(input);

        switch(word.toLowerCase()) {
            case "game":
                String p = input.replace("GAME ", "");
                gameCmd(p);
                break;
            case "help":
                System.out.println(input);
                break;
        }
    }

    /**
     * go through commands going out
     * @param input
     * @return command gebasseerd op input
     */
    public void parseOut(String input){

        // get the first line of input
        String[] lines = input.split(System.getProperty("line.separator"));
        String line = lines[0];
        // get fist word of te line
        String[] words = line.split(" ");
        String word = words[0];

        // login, logout, getGamelist, getPlayerlist, subscribe, move, challenge, forfeit, askHelp;
        switch (word.toLowerCase()){
            case "login":
                break;
            case "logout":
                break;
            case "get":
                // playerlist or gamelist
                break;
            case "subscribe":
                break;
            case "move":
                break;
            case "challenge":
                break;
            case "forfeit":
                break;
            case "help":
                break;
            default:
                System.out.println(word + " is not a word");
        }

    }



    public void gameCmd(String input){
        // get fist word of te line
        String[] words = input.split(" ");
        String word = words[0];

        switch(word.toLowerCase()){
            case "match":
//                command = new MatchCommand();
                break;
            case "yourturn":
//                command = new TurnCommand();
                break;
            case "move":
                // MOVE {PLAYER: "<speler>", DETAILS: "<reactie spel op zet>", MOVE: "<zet>"}
                HashMap details = parseMap(input.replace("MOVE ", ""));
                // TODO separate hashmap to provide required information
//                command = new MoveCommand();
                break;
            case "challenge":
                // CHALLENGE {CHALLENGER: "Sjors", GAMETYPE: "Guess Game", CHALLENGENUMBER: "1"}
                HashMap message = parseMap(input.replace("CHALLENGE ", ""));
                //TODO start a new game
                break;
            case "win":
            case "loss":
            case "draw":
                //TODO change game status
                break;
            default:
                System.out.println("none of these");
        }
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

    public static void main(String[] args) {
        Parser parser = new Parser();

        String games = "[\"Reversi\", \"Tic-tac-toe\"]";
        ArrayList<String> list = parser.parseList(games);
        System.out.println(list);

        String keyvalues = "GET GAMELIST {GAMTYPE: \"<speltype>\", PLAYERTOMOVE: \"<naam speler1>\", OPPONENT: \"<naam tegenstander>\"}";
        HashMap<String, String> map = parser.parseMap(keyvalues);
        System.out.println(map);
    }

}
