package networking.eventhandlers;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import games.Game;
import games.GameEnum;
import games.GameManager;
import gui.Controller;
import gui.View;
import networking.NetworkManager;
import networking.Parser;
import networking.states.PlayingState;
import player.PlayEnum;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;
import threadpool.ThreadPool;

public class MatchHandler implements Handler {

    NetworkManager networkManager = NetworkManager.getInstance("", 0);
    GameManager gameManager = GameManager.getInstance();
    Game game;
    Map<String, String> map;
    
    public void handle(String response) {
        System.out.println(response);

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        networkManager.setState(new PlayingState());
        System.out.println(networkManager.getState());
        map = Parser.parseMap(response);

        String username = networkManager.getUsername();
        String localUsername = (username == null) ? "ai" : username;

//        Player localAI = PlayerFactory.createAIPlayer(localUsername, getGame(), PlayerType.ONE);
//        Player remotePlayer = PlayerFactory.createRemotePlayer(getOpponent(), PlayerType.TWO);

        PlayerType startingPlayer = PlayerType.ONE;
//        if (isRemoteStartingPlayer()){
//            startingPlayer = PlayerType.TWO;
//        }
        Player localAI;
        Player remotePlayer;
        if (!isRemoteStartingPlayer()){
            localAI = PlayerFactory.createAIPlayer(localUsername, getGame(), PlayerType.ONE);
            remotePlayer = PlayerFactory.createRemotePlayer(getOpponent(), PlayerType.TWO);
        }
        else {
            localAI = PlayerFactory.createAIPlayer(localUsername, getGame(), PlayerType.TWO);
            remotePlayer = PlayerFactory.createRemotePlayer(getOpponent(), PlayerType.ONE);
        }
        /*
         if (playertomove = localai) {
               player1 = localai
               localai mark = X
               player 2 = remote player
               remote player mark = 'O'
               }

          if (playerto move = remote player) {
                player1 = remote player
                remote player mark = X
                player 2 = local ai
                local ai mark = O
         */
        game = gameManager.createGame(startingPlayer, getGame(), localAI, remotePlayer, PlayEnum.ONLINEAI);

        Controller controller = View.controller;
        //Setting the view
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        executor.submit(() -> {
            if(getGame() == GameEnum.OTHELLO){
                controller.setOthello(PlayEnum.ONLINEAI);
            } else {
                controller.setTTT(PlayEnum.ONLINEAI);
            }});

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

}
