import java.util.Scanner;

public class TicTacToe  {

    private TicTacToeBoard board;

    public TicTacToe() {
        Player player1 = new Player('x', "joep");
        Player player2 = new Player('o', "henk");

        this.board = new TicTacToeBoard(player1, player2);

    }


    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        //ticTacToe.board.printBoard();
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe!");
        do
        {
            System.out.println("Current board layout:");
            ticTacToe.board.printBoard();
            int row;
            int col;
            do
            {
                System.out.println("Player " + ticTacToe.board.getCurrentPlayerMark() + ", enter an empty row and column to place your mark!");
                row = inputScanner.nextInt()-1;
                if (row > 3 || row < 0) {
                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.board.getCurrentPlayer().getPlayerName());
                }
                col = inputScanner.nextInt()-1;
                if (col > 3 || col < 0) {
                    System.out.println("Doe een geldige waarde joh, " + ticTacToe.board.getCurrentPlayer().getPlayerName());
                }
            }
            while (!ticTacToe.board.placeMark(row, col));
            ticTacToe.board.changePlayer();
        }
        while(!ticTacToe.board.checkForWin() && !ticTacToe.board.isBoardFull());
        if (ticTacToe.board.isBoardFull() && !ticTacToe.board.checkForWin())
        {
            System.out.println("The game was a tie!");
        }
        else
        {
            System.out.println("Current board layout:");
            ticTacToe.board.printBoard();
            ticTacToe.board.changePlayer();
            System.out.println(Character.toUpperCase(ticTacToe.board.getCurrentPlayerMark()) + " Wins!");
        }
    }
}
