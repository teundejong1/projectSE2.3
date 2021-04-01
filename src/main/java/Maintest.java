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
        FxmlTest ding = new FxmlTest();
        Thread thread = new Thread(ding);
        thread.start();

//        do {
//            //  Scanner inputScanner = new Scanner(System.in);
////        System.out.println("Name player1");
////        // Naam van player 1 doorgegeven uit GUI
////        String nameplayer1 = inputScanner.next();
////        System.out.println("Name player2");
////        // Naam player 2 doorgegeven
////        String nameplayer2 = inputScanner.next();
////        Player player1 = new Player('x', nameplayer1);
////        Player player2 = new Player('o', nameplayer2);
////        System.out.println("WANNA PLAY VS AI?  Y/N");
//            // Check of tegen AI doorgegeven
//            // als wel tegen ai dan andere constructor
//
////        answerToAI = answerToAI.toUpperCase();
////        if (answerToAI.equals("Y")) {
////            AI ai = new AI('o', nameplayer2);
////            ticTacToe = new Maintest(player1, ai);
////            ticTacToe.ticTacToeManager.start(player1, ai);
////        } else if (answerToAI.equals("N")) {
////            ticTacToe = new Maintest(player1, player2);
////            ticTacToe.ticTacToeManager.start(player1, player2);
////        } else {
////            System.out.println("geef normale waarde draak");
////        }
////        Maintest ticTacToe = new Maintest(player1, player2);
//
//
//            System.out.println("Tic-Tac-Toe!");
//
//
//            System.out.println("Current board layout:");
//            // current board moet omgezet worden naar scherm in gui.
//            //ticTacToe.ticTacToeManager.printBoard(ticTacToe.board);
//            int row;
//            int col;
//            // deze twee (row en col) moeten coordinaten uit gui worden op mouseclick
//
//                // if player is AI
//                if (ticTacToe.ticTacToeManager.getCurrentPlayer() instanceof AI) {
//                    System.out.println("AI TURN");
//                    TimeUnit.SECONDS.sleep(1);
//
//
//                    ticTacToe.ticTacToeManager.placeAIMove();
//                    // hier moet ipv printboard de view in gui geupdate worden
//                    ticTacToe.ticTacToeManager.printBoard(ticTacToe.board);
//                    ticTacToe.ticTacToeManager.changePlayer();
//                }
//                // If player is Player
//
//                System.out.println("Player " + ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName() + ", enter an empty row and column to place your mark!");
//
////                if (row > 3 || row < 0) {
////                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName());
////                }
////                col = inputScanner.nextInt() - 1;
////                if (col > 3 || col < 0) {
////                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.ticTacToeManager.getCurrentPlayer().getPlayerName());
////                }
//            }
////            while (!ticTacToe.ticTacToeManager.checkMove(row, col));
////            // hier voert 'ie de 2 coordinaten uit de gui uit en plaatst ze
////            //ticTacToe.ticTacToeManager.placeMove(row, col);
////            //ticTacToe.ticTacToeManager.changePlayer();
////        }
//            while (!ticTacToe.ticTacToeManager.checkForWin() && !ticTacToe.ticTacToeManager.isBoardFull(ticTacToe.board));
//            if (ticTacToe.ticTacToeManager.isBoardFull(ticTacToe.board) && !ticTacToe.ticTacToeManager.checkForWin()) {
//                System.out.println("The game was a tie!");
//            } else {
//                System.out.println("Current board layout:");
//                // hier weer view.update
//                //ticTacToe.ticTacToeManager.printBoard(ticTacToe.board);
//                if (!(ticTacToe.ticTacToeManager.getCurrentPlayer() instanceof AI)) {
//                    ticTacToe.ticTacToeManager.changePlayer();
//
//                }
//                System.out.println(Character.toUpperCase(ticTacToe.ticTacToeManager.getCurrentPlayerMark()) + " Wins!");
//            }
//        }
    }
}

