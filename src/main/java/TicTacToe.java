//import java.util.Scanner;
//
///**
// * @author Eva Jakobs, Teun de Jong
// * Class TicTacToe
// * Creates the TicTacToe board and two players.
// */
//public class TicTacToe  {
//    private TicTacToeBoard board;
//
//    /**
//     * Constructor for TicTacToe.
//     */
//    public TicTacToe(Player player1, Player player2) {
//
//        this.board = new TicTacToeBoard(player1, player2);
//    }
//
//    /**
//     * The main method used for playing the game.
//     * Creates two players to play TicTacToe.
//     * Creates a scanner to read the player input.
//     * Mainly uses methods from the TicTacToeBoard class.
//    */
//    public static void main(String[] args) {
//        Scanner inputScanner = new Scanner(System.in);
//        System.out.println("Name player1");
//        String nameplayer1 = inputScanner.next();
//        System.out.println("Name player2");
//        String nameplayer2 = inputScanner.next();
//        Player player1 = new Player('x', nameplayer1);
//        Player player2 = new Player('o', nameplayer2);
//        TicTacToe ticTacToe = new TicTacToe(player1, player2);
//        System.out.println("Tic-Tac-Toe!");
//        do
//        {
//            System.out.println("Current board layout:");
//            ticTacToe.board.printBoard();
//            int row;
//            int col;
//            do
//            {
//                System.out.println("Player " + ticTacToe.board.getCurrentPlayer().getPlayerName() + ", enter an empty row and column to place your mark!");
//                row = inputScanner.nextInt()-1;
//                if (row > 3 || row < 0) {
//                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.board.getCurrentPlayer().getPlayerName());
//                }
//                col = inputScanner.nextInt()-1;
//                if (col > 3 || col < 0) {
//                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.board.getCurrentPlayer().getPlayerName());
//                }
//            }
//            while (!ticTacToe.board.doMove(row, col));
//            ticTacToe.board.changePlayer();
//        }
//        while(!ticTacToe.board.checkForWin() && !ticTacToe.board.isBoardFull());
//        if (ticTacToe.board.isBoardFull() && !ticTacToe.board.checkForWin())
//        {
//            System.out.println("The game was a tie!");
//        }
//        else
//        {
//            System.out.println("Current board layout:");
//            ticTacToe.board.printBoard();
//            ticTacToe.board.changePlayer();
//            System.out.println(Character.toUpperCase(ticTacToe.board.getCurrentPlayerMark()) + " Wins!");
//        }
//    }
//}
