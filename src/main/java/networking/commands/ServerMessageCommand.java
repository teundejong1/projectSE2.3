package networking.commands;

import java.util.Locale;

public class ServerMessageCommand implements Command {



    public ServerMessageCommand(){
    }

    //gevolgd door HELP, GAME
    public void firstCmd(String cmd){

        String first = cmd.split(" ")[1].toLowerCase();

        switch(first){
            case "help":
                //TODO
                break;
            case "game":
                //TODO
                gameCommand(cmd);
                break;
            default: //exception
                System.out.println("geen case: " + cmd);
        }

    }

    public void gameCommand(String cmd){
        String command = cmd.split(" ")[2].toLowerCase();

        //MATCH | YOURTURN | MOVE | CHALLENGE | [WIN | LOSS | DRAW
        // wordt gevolgd door HashMap
        switch(command){
            case "match":
                break;
            case "yourturn":
                break;
            case "move":
                break;
            case "challenge":
                break;
            case "win":
                break;
            case "loss":
                break;
            case "draw":
                break;
            default: // exception
                System.out.println("geen case: " + cmd);
        }
    }

    @Override
    public void execute() {

    }
}
