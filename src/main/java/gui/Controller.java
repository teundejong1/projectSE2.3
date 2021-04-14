package gui;

import games.*;
import games.board.Mark;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import networking.NetworkManager;
import networking.commands.GetPlayerlistCommand;
import networking.states.IllegalStateException;
import player.PlayEnum;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;
import threadpool.ThreadPool;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


public class Controller {
    NetworkManager networkManager;
    Game game;
    Thread gameThread;
    String spelernaam;

    @FXML
    public AnchorPane gameAnchor;
    public Button forfeit;
    public BorderPane mainWindow;


    FXMLLoader menuLoader;
    Parent menu;
    MenuController menuController;

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void setMenu() throws IOException {
        forfeit.setVisible(false);
        menuLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        menu = menuLoader.load();
        menuController = menuLoader.getController();
        menuController.setRoot(this);

        gameAnchor.getChildren().clear();
        gameAnchor.getChildren().add(menu);
    }

    public void TTTInfo(ActionEvent actionEvent) {
        //TODO
    }

    public void OthelloInfo(ActionEvent actionEvent) {
        //TODO
    }

    public void howToTTT(ActionEvent actionEvent) {
    }

    public void howToOThello(ActionEvent actionEvent) {
    }

    public void setOthello(PlayEnum playType) {
        System.out.println("sets Othello");

        GameManager gm = GameManager.getInstance();
        ThreadPoolExecutor tpe = ThreadPool.getInstance();

        forfeit.setVisible(true);
        gameAnchor.getChildren().clear();
        Othello othello = null;
        if(playType == PlayEnum.ONLINEAI) {
            othello = (Othello) gm.getGame();
        }
        else if(playType == PlayEnum.ONLINEPLAYER){
            othello = (Othello) gm.getGame();
        }
        else if(playType == PlayEnum.PVE){
            Player p1 = PlayerFactory.createAIPlayer("AI", GameEnum.OTHELLO, PlayerType.ONE, Mark.ONE);
            Player p2 = PlayerFactory.createGUIPlayer("Player1", GameEnum.OTHELLO, PlayerType.TWO, Mark.TWO);
            Game game = gm.createGame(PlayerType.ONE, GameEnum.OTHELLO,  p1, p2, PlayEnum.PVE);
            othello = (Othello) game;
            gameAnchor.getChildren().add(View.setOthello(othello));
            try {
                gm.start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (playType == PlayEnum.PVP){
            Player p1 = PlayerFactory.createGUIPlayer("Player1", GameEnum.OTHELLO, PlayerType.ONE, Mark.ONE);
            Player p2 = PlayerFactory.createGUIPlayer("Player2", GameEnum.OTHELLO, PlayerType.TWO, Mark.TWO);
            Game game = gm.createGame(PlayerType.ONE, GameEnum.OTHELLO, p1, p2, PlayEnum.PVP );
            othello = (Othello) game;
            gameAnchor.getChildren().add(View.setOthello(othello));
            try {
                gm.start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("HIER MAG JE NIET KOMEN");
        }

        // gameAnchor.getChildren().add(View.setOthello(othello));
//        gameThread = new Thread(othello);
//        gameThread.start();
    }

    public void setTTT(PlayEnum playType) {
        GameManager gm = GameManager.getInstance();
        ThreadPoolExecutor tpe = ThreadPool.getInstance();

        forfeit.setVisible(true);
        gameAnchor.getChildren().clear();
        TicTacToe ticTacToe = null;
        if(playType == PlayEnum.ONLINEAI) {
            ticTacToe = new TicTacToe(PlayerType.ONE, playType, networkManager);
        }
        else if(playType == PlayEnum.ONLINEPLAYER){
            ticTacToe = new TicTacToe(PlayerType.ONE, playType, networkManager);
        }
        else if(playType == PlayEnum.PVE){
            Player p1 = PlayerFactory.createAIPlayer("AI", GameEnum.TTT, PlayerType.ONE, Mark.ONE);
            Player p2 = PlayerFactory.createGUIPlayer("Player1", GameEnum.TTT, PlayerType.TWO, Mark.TWO);
            Game game = gm.createGame(PlayerType.ONE, GameEnum.TTT,  p1, p2, PlayEnum.PVE);
            try {
                gm.start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (playType == PlayEnum.PVP){
            Player p1 = PlayerFactory.createGUIPlayer("Player1", GameEnum.TTT, PlayerType.ONE, Mark.ONE);
            Player p2 = PlayerFactory.createGUIPlayer("Player2", GameEnum.TTT, PlayerType.TWO, Mark.TWO);
            Game game = gm.createGame(PlayerType.ONE, GameEnum.TTT, p1, p2, PlayEnum.PVP );
        }
        else {
            System.out.println("HIER MAG JE NIET KOMEN");
        }

        gameAnchor.getChildren().add(View.setTTT(ticTacToe));

    }

    public void forfeit(ActionEvent actionEvent) throws IOException {
        /*
        TODO
         rekening houden met mogelijke andere spelers, verbinding verbreken
         mischien een popup maken die om bevestiging vraagt?
         */
        game.setRunning(false);
        System.out.println(gameThread);
        gameThread.interrupt();
        setMenu();
        Platform.runLater( () -> {
            mainWindow.setTop(new Label(" "));
        });
    }

//    public void initLoggedInStatus() throws IOException{
//        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxml/Inlogstatus.fxml"));
//        Parent status = fxmlLoader.load();
//
//        //TODO Networkmanager moet de challenges kunnen updaten, hiervoor moet de networkmanager bij de inlogstatuscontroller kunnen komen
//        InlogstatusController inlogstatusController = fxmlLoader.getController();
//        inlogstatusController.setController(this);
//
//        mainWindow.setRight(status);
//    }

    public void initLobby() throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("/fxml/Lobby.fxml"));
        Parent lobby = fxmlLoader.load();

        LobbyController lobbyController = fxmlLoader.getController();
        lobbyController.setController(this);

        mainWindow.setLeft(lobby);

        VBox vbox = (VBox) mainWindow.getLeft();
        VBox spelerLijst = new VBox();

        spelerLijst.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        vbox.getChildren().add(spelerLijst);
        View.spelerLijst = spelerLijst;
    }

    public void lobbyRefresh() {
        try {
            networkManager.getPlayerList();
        } catch (IllegalStateException e) {
            View.illegalStateException();
        }
    }

    public void challengeConfirmation(String spelernaam) {
        VBox vBox = (VBox) mainWindow.getLeft();
        Button button = new Button();
        button.setText("Klik om " + spelernaam + "\r\nuit te dagen.");
        Label label = new Label("Klik om " + spelernaam + " uit te dagen.");
        //deze actie moet challengen
        button.setOnAction(e -> {
            try {
                networkManager.challengePlayer(spelernaam, GameEnum.OTHELLO);
            } catch (IllegalStateException ex) {
                View.illegalStateException();
            }
        });

        vBox.getChildren().add(button);
    }

    public void setSpelerNaam(String spelernaam) {
        this.spelernaam = spelernaam;
        View.spelernaam = spelernaam;
    }

    public Game getGame() {
        return game;
    }

    public void showWinner(String winner){
        Label label = new Label("Player " + winner + " has won");
        label.setStyle("-fx-font-weight: bold");
        label.setStyle("-fx-font: normal bold 30px 'serif' ");
        Platform.runLater( () -> {
            mainWindow.setTop(label);
        }
        );
    }
}