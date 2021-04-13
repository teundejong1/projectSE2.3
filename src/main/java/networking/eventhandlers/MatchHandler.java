package networking.eventhandlers;

import java.util.Map;

import games.GameEnum;
import gui.View;
import networking.NetworkManager;
import networking.Parser;
import networking.states.PlayingState;
import player.PlayEnum;

public class MatchHandler implements Handler {

    NetworkManager manager = NetworkManager.getInstance("", 0);
    
    public void handle(String response) {
        manager.setState(new PlayingState());
        MatchHash match = new MatchHash(response);
        // {GAMTYPE: "<speltype>", PLAYERTOMOVE: "<naam speler1>", OPPONENT: "<naam tegenstander>"}
        // krijgen command binnen. wordt nog niks mee gedaan
        View.startOnlineMatch(match.getGame());
    }

    /**
     * Inner class to handle the Hashmap
     */
    class MatchHash{
        // {GAMTYPE: "<speltype>", PLAYERTOMOVE: "<naam speler1>", OPPONENT: "<naam tegenstander>"}
        private String speltype, playerToMove, opponent;

        public MatchHash(String details){
            new MatchHash(Parser.parseMap(details));
        }

        public MatchHash(Map<String, String> map){
            speltype = map.get("GAMETYPE");
            playerToMove = map.get("PLAYERTOMOVE");
            opponent = map.get("OPPONENT");
        }

        public String getSpeltype(){
            return speltype;
        }

        public GameEnum getGame(){
            switch(speltype){
                case "Tic-tac-toe":
                    return GameEnum.TTT;
                case "Othello":
                case "Reversi":
                    return GameEnum.OTHELLO;
                default: return null;
            }
        }

        public String getOpponent(){
            return opponent;
        }

        public String getPlayerToMove(){
            return playerToMove;
        }
    }
}
