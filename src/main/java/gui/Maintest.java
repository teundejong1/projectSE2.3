package gui;

import games.Game;
import games.GameEnum;
import games.TicTacToe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;
import player.PlayerFactory;
import player.PlayerType;

import java.io.IOException;

public class Maintest{

    FXMLLoader loader;
    Parent root;
    Controller controller;

    public static void main(String[] args) throws Exception {
        //FXMLLoader instance


        FxmlTest fxmlTest = new FxmlTest();
        Thread thread =  new Thread(fxmlTest);

        thread.start();

        Player p1 = PlayerFactory.createGUIPlayer("Teun");
        Player p2 = PlayerFactory.createGUIPlayer("Esther");

        Game game = new TicTacToe(PlayerType.ONE);
        game.start(p1, p2);
    }
}