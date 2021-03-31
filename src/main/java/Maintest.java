import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class Maintest {
    private TicTacToeManager ticTacToeManager;
    private char[][] board;

    public Maintest() {

    }

    /**
     * Constructor for MAINTEST.
     */
    public Maintest(Player player1, Player player2) {
        this.ticTacToeManager = new TicTacToeManager(player1, player2);
        this.board = ticTacToeManager.getBoard();
    }

    /**
     * The main method used for playing the game.
     * Creates two players to play TicTacToe.
     * Creates a scanner to read the player input.
     * Mainly uses methods from the TicTacToeBoard class.
     *
     */
    public static void main(String[] args) throws IOException {
        Runnable fxml = new FxmlTest();
        Thread ding = new Thread(fxml);
        ding.start();

        //spelers aanmaken
        rotspel();
    }
    public static void rotspel() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Name player1");
        String nameplayer1 = inputScanner.next();
        System.out.println("Name player2");
        String nameplayer2 = inputScanner.next();
        Player player1 = new Player('x', nameplayer1);
        Player player2 = new Player('o', nameplayer2);
        Maintest ticTacToe = new Maintest(player1, player2);
        System.out.println("Tic-Tac-Toe!");
        do {
            System.out.println("Current board layout:");
            ticTacToe.ticTacToeManager.printBoard(ticTacToe.board);
            int row;
            int col;
            System.out.println("JOEHOE");
            do {
                System.out.println("Player " + ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName() + ", enter an empty row and column to place your mark!");
                row = inputScanner.nextInt() - 1;
                if (row > 3 || row < 0) {
                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName());
                }
                col = inputScanner.nextInt() - 1;
                if (col > 3 || col < 0) {
                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName());
                }
            }
            while (!ticTacToe.ticTacToeManager.doMove(row, col));
            ticTacToe.ticTacToeManager.changePlayer();
        }
        while (!ticTacToe.ticTacToeManager.checkForWin() && !ticTacToe.ticTacToeManager.isBoardFull(ticTacToe.board));
        if (ticTacToe.ticTacToeManager.isBoardFull(ticTacToe.board) && !ticTacToe.ticTacToeManager.checkForWin()) {
            System.out.println("The game was a tie!");
        } else {
            System.out.println("Current board layout:");
            ticTacToe.ticTacToeManager.printBoard(ticTacToe.board);
            ticTacToe.ticTacToeManager.changePlayer();
            System.out.println(Character.toUpperCase(ticTacToe.ticTacToeManager.getCurrentPlayerMark()) + " Wins!");
        }
    }
}
