package networking.eventhandlers;

import java.util.Map;

import games.GameEnum;
import games.GameManager;
import networking.NetworkManager;
import networking.Parser;
import networking.states.PlayingState;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

public class MatchHandler implements Handler {

    NetworkManager networkManager = NetworkManager.getInstance("", 0);
    GameManager gameManager = GameManager.getInstance();
    Map<String, String> map;
    
    public void handle(String response) {
        networkManager.setState(new PlayingState());
        map = Parser.parseMap(response);

        String username = networkManager.getUsername();
        String localUsername = (username == null) ? "ai" : username;

        Player localAI = PlayerFactory.createAIPlayer(localUsername, getGame());
        Player remotePlayer = PlayerFactory.createRemotePlayer(getOpponent());

        Player playerOne;
        Player playerTwo;
        
        if (isRemoteStartingPlayer()) {
            playerOne = remotePlayer;
            playerTwo = localAI;
        } else {
            playerOne = localAI;
            playerTwo = remotePlayer;
        }

        gameManager.createGame(PlayerType.ONE, getGame(), playerOne, playerTwo);
        try {
            gameManager.start();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private GameEnum getGame() {
        switch (map.get("GAMETYPE")) {
            case "Tic-tac-toe": return GameEnum.TTT;
            case "Reversi": return GameEnum.OTHELLO;
            default: return null;
        }
    }

    private boolean isRemoteStartingPlayer() {
        String playerToMove = map.get("PLAYERTOMOVE");
        return playerToMove.equals(getOpponent());
    }

    private String getOpponent() {
        return map.get("OPPONENT");
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
