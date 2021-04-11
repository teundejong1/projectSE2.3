package networking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import networking.commands.*;
import networking.eventhandlers.*;

public class Parser {

    public static void parseResponse(String response) {
        Handler handler = getHandler(response);
        if (handler != null) handler.handle(response);
        
    }

    public static void parseResponse(String response, Command command) {
        CommandHandler handler = getCommandHandler(command);
        if (handler != null) handler.handle(response);
    }

    public static void parseError(String response, Command command) {
        CommandHandler handler = getCommandHandler(command);
        if (handler != null) handler.handleError(response);
    }

    private static Handler getHandler(String response) {
        Handler handler = null;
        String[] words = response.split(" ");
        String first = words[2];
        String second = words[3];

        if (first.equalsIgnoreCase("MATCH")) handler = new MatchHandler();
        else if (first.equalsIgnoreCase("YOURTURN")) handler = new TurnHandler();
        else if (first.equalsIgnoreCase("MOVE")) handler = new ReceivedMoveHandler();
        else if (first.equalsIgnoreCase("CHALLENGE") &&
                second.equalsIgnoreCase("CANCELLED")) handler = new ChallengeCancelledHandler();
        else if (first.equalsIgnoreCase("CHALLENGE")) handler = new ReceivedChallengeHandler();
        else handler = new ResultHandler();

        return handler;
    }

    private static CommandHandler getCommandHandler(Command command) {
        CommandHandler handler = null;

        if (command instanceof ChallengeAcceptCommand) handler = new ChallengeAcceptHandler();
        if (command instanceof ChallengePlayerCommand) handler = new ChallengeHandler();
        if (command instanceof ForfeitCommand) handler = new Forfeithandler();
        if (command instanceof GetGamelistCommand) handler = new GameListHandler();
        if (command instanceof GetPlayerlistCommand) handler = new PlayerListHandler();
        if (command instanceof LoginCommand) handler = new LoginHandler();
        if (command instanceof MoveCommand) handler = new MoveHandler();
        if (command instanceof SubscribeCommand) handler = new SubscribeHandler();

        return handler;
    }
    
    /**
     * zet String om tot lijst
     * @param input
     * @return
     */
    public static List<String> parseList(String input) {
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
    public static Map<String, String> parseMap(String input) {
        HashMap<String, String> map = new HashMap<>();

        final Pattern p = Pattern.compile("(\\w+):\\s\"([^\"]*)\"");
        Matcher m = p.matcher(input);
        
        while (m.find()) {
            map.put(m.group(1), m.group(2));
        }

        return map;
    }

}
