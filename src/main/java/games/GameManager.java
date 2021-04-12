package games;

import games.board.SetOutOfBoundsException;
import gui.Controller;
import networking.NetworkManager;
import networking.eventhandlers.Handler;
import networking.states.IllegalStateException;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

import java.util.HashMap;
import java.util.List;

/**
 * Class as Director between te Network, Game, and GUI
 * It will always be considered that we are player one. 
 * @author Esther Zigterman Rustenburg
 */
public class GameManager {

    private NetworkManager networkManager;
    private Controller controller;
    private GameEnum subscribed;
    private Handler handler;
    private GameStatus status;
    private Game game;
    private Player player1;
    private Player player2;
    private String localPlayer;

    public void GameManager(NetworkManager networkManager, Controller controller){
        this.networkManager = networkManager;
        this.controller = controller;
    }

    /**
     * Method to log in
     * @param name that will be shown on the server
     * @throws IllegalStateException
     */
    public void logIn(String name) throws IllegalStateException {
        localPlayer = name;
        networkManager.login(name);
    }

    /**
     * Method for when a new game is started from the server.
     * @param details hashmap from server with all details
     * @param asAI true if we play as an AI;
     */
    public void startGame(HashMap<String, String> details, boolean asAI)
            throws SetOutOfBoundsException {
        // {GAMETYPE: "<speltype>", PLAYERTOMOVE: "<naam speler1>", OPPONENT: "<naam tegenstander>"}

        // create opponent
        player2 = PlayerFactory.createRemotePlayer(details.get("OPPONENT"));

        // get the GameEnum
        GameEnum type = getGameEnum(details.get("GAMETYPE"));

        // create our player
        if(asAI) {
            player1 = PlayerFactory.createAIPlayer(localPlayer, type);
        } else {
            player1 = PlayerFactory.createGUIPlayer(localPlayer);
        }

        PlayerType playerToMove = (player2.getName().equals(details.get("PLAYERTOMOVE"))) ? PlayerType.TWO : PlayerType.ONE;

        // create the game
        if(type == GameEnum.OTHELLO){
            game = GameFactory.createOthelloGame(playerToMove);
        } else if(type == GameEnum.TTT) {
            game = GameFactory.createTicTacToeGame(playerToMove);
        }

        game.start(player1, player2);
    }

    /**
     * method for when a game is started locally
     * @param name1 name of the first player.
     * @param name2 name of the second player.
     * @param againstAI true if playing against AI
     * @param p1Starts true if p1 starts, else False
     */
    public void startGame(String name1, String name2, boolean againstAI, boolean p1Starts, GameEnum gameType)
            throws SetOutOfBoundsException {
        player1 = PlayerFactory.createGUIPlayer(name1);

        if(againstAI){
            player2 = PlayerFactory.createAIPlayer(name2, gameType);
        } else {
            player2 = PlayerFactory.createGUIPlayer(name2);
        }

        PlayerType playerToMove = (p1Starts) ? PlayerType.ONE : PlayerType.TWO;

        // create the game
        if(gameType == GameEnum.OTHELLO){
            game = GameFactory.createOthelloGame(playerToMove);
        } else if(gameType == GameEnum.TTT) {
            game = GameFactory.createTicTacToeGame(playerToMove);
        }

        game.start(player1, player2);

    }

    /**
     * If game changes the status, we change it as well
     * @param status status of the game
     */
    public void updateGameStatus(GameStatus status){
        game.setStatus(status);
    }

    /**
     * Call when you send a challenge to another player
     * @param name name of the player to be challenged
     * @param type game you want to play against them
     * @throws IllegalStateException if you're not in a state allowed to challenge from
     */
    public void sendChallenge(String name, GameEnum type)
            throws IllegalStateException {
        networkManager.challengePlayer(name, type);
    }

    /**
     * TODO
     * receives challenge and updates list of challengers in GUI
     */
    public void getChallenge(HashMap<String, String> details){
        // updates list to GUI
        // CHALLENGE {CHALLENGER: "Sjors", GAMETYPE: "Guess Game", CHALLENGENUMBER: "1"}

    }

    /**
     *
     * @param challengeNr nr related to the challenge
     * @throws IllegalStateException in case you're not in a position to accept a challenge
     */
    public void acceptChallenge(int challengeNr) throws IllegalStateException {
        networkManager.acceptChallenge(challengeNr);
    }

    /**
     * subscribe to a game using String
     * @param gameType name of the game as a string
     */
    public void subscribe(String gameType)
            throws IllegalStateException {
        networkManager.subscribe(getGameEnum(gameType));
        subscribed = getGameEnum(gameType);
    }

    /**
     * subscribe to a game using Enum
     * @param gameEnum enum to subscribe to
     */
    public void subscribe(GameEnum gameEnum)
            throws IllegalStateException {
        networkManager.subscribe(gameEnum);
        subscribed = gameEnum;
    }

    /**
     * make a request for the gamelist
     * @throws IllegalStateException if you're not in a state to get a playerlist
     */
    public void requestPlayerList()
            throws IllegalStateException {
        networkManager.getGameList();
    }

    /**
     * might still need work. The Idea is that it updates the GUI lobby playerlist
     * TODO
     */
    public void updatePlayerList(List playerList){
        // TODO
        // call method to update playerList in the lobby from LobbyController
        // update Playerlist
    }

    /**
     * send a move to the server
     * @param move with x and y locations of the move
     * @param boardSize size of the board
     * @throws IllegalStateException can't use this method when in a game
     */
    public void sendMove(Move move, int boardSize)
            throws IllegalStateException {
        networkManager.sendMove(move, boardSize);

        // if we want to send the location from here. Not sure if x and y are correctly placed
//        int location = move.getX()/boardSize + move.getY();
//        networkManager.sendMove(location);
    }

    /**
     * receive a move from the server
     * TODO send to controller
     * @param location placement on the grid
     */
    public void receiveMove(int location){
        int size = game.board.getSize();
        int row = location / size;
        int column = location % size;
//        controller.
    }

    /**
     * send forfeit message
     * @throws IllegalStateException cant use method if not in a game
     */
    public void sendForfeit() throws IllegalStateException {
        networkManager.forfeit();
    }

    /**
     * asks server for gamelist
     * @throws IllegalStateException if you're not in a state where you can get the gamelist
     */
    public void getGamelist() throws IllegalStateException {
        networkManager.getGameList();
    }

    /**
     * updates the gamelist in the GUI (if there was one, currently has no use)
     */
    public void updateGamelist(){
        //TODO
    }

    /**
     * gives Player 1 the turn
     */
    public void getTurn(){
        game.setCurrentTurn(PlayerType.ONE);
    }
    
    /**
     * returns GameEnum
     * @param game name in String of the game
     * @return GameEnum or null if gametype does not exist
     */
    private GameEnum getGameEnum(String game){
        switch(game.toLowerCase()){
            case "tic-tac-toe":
            case "ttt":
            case "tictactoe":
                return GameEnum.TTT;
            case "othello":
            case "reversi":
                return GameEnum.OTHELLO;
            default: return null;
        }
    }

}
