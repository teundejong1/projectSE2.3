import com.sun.glass.ui.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Maintest {
    public static Maintest ticTacToe;
    public final TicTacToeManager ticTacToeManager;
    private final char[][] board;


    /**
     * Constructor for MAINTEST.
     */
    public Maintest(Player player1, Player player2) {
        this.ticTacToeManager = new TicTacToeManager(player1, player2);
        this.board = ticTacToeManager.getBoard();

    }

    public Maintest(Player player, AI AI) {
        this.ticTacToeManager = new TicTacToeManager(player, AI);
        this.board = ticTacToeManager.getBoard();


    }

    /**
     * The main method used for playing the game.
     * Creates two players to play TicTacToe.
     * Creates a scanner to read the player input.
     * Mainly uses methods from the TicTacToeBoard class.
     */
    public static void main(String[] args) throws InterruptedException {

        // Open Menu
        FxmlTest fxml = new FxmlTest();
        Thread fxmlThread = new Thread(fxml);
        fxmlThread.start();

//        Scanner inputScanner = new Scanner(System.in);
//        System.out.println("Name player1");
//        // Naam van player 1 doorgegeven uit GUI
//        String nameplayer1 = inputScanner.next();
//        System.out.println("Name player2");
//        // Naam player 2 doorgegeven
//        String nameplayer2 = inputScanner.next();
//        Player player1 = new Player('x', nameplayer1);
//        Player player2 = new Player('o', nameplayer2);
//        System.out.println("WANNA PLAY VS AI?  Y/N");
//        // Check of tegen AI doorgegeven
//        // als wel tegen ai dan andere constructor
//        String answerToAI = inputScanner.next();
//        answerToAI = answerToAI.toUpperCase();
//        if (answerToAI.equals("Y")) {
//            AI ai = new AI('o', nameplayer2);
//            ticTacToe = new Maintest(player1, ai);
//            ticTacToe.ticTacToeManager.start(player1, ai);
//        } else if (answerToAI.equals("N")) {
//            ticTacToe = new Maintest(player1, player2);
//            ticTacToe.ticTacToeManager.start(player1, player2);
//        } else {
//            System.out.println("geef normale waarde draak");
//        }
        //Maintest ticTacToe = new Maintest(player1, player2);
    }
}