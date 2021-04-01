import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToeManager extends GameManager {
    private char[][] board;
    private char currentPlayerMark;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    /**
     * Constructor for TicTacToe Manager
     * @param player1
     * @param player2
     */
    public TicTacToeManager(Player player1, Player player2) {

        this.board = createBoard(3);
        currentPlayerMark = 'x';
        currentPlayer = player1;
        this.player1 = player1;
        System.out.println("Naam van player 1 : " + player1.getPlayerName());
        this.player2 = player2;
        System.out.println("Naam van player 2 : " + player2.getPlayerName());
    }

    /**
     * getter for board
     * @return board
     */
    public char[][] getBoard(){
        return this.board;
    }

    /**
     * getter for current player mark
     * @return
     */
        public char getCurrentPlayerMark()
    {
        return this.currentPlayerMark;
    }

    /**
     * getter for current player
     * @return currentplayer
     */
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * Creates board
     * @param size
     * @return board
     */
    @Override
    public char[][] createBoard(int size) {
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
        return board;
    }

    /**
     * Places player mark on the board
     * @param row
     * @param col
     * @return true if turn was correct
     */
    @Override
    public boolean checkMove(int row, int col) {
        if ((row >= 0) && (row < board.length)) {
            if ((col >= 0) && (col < board.length)) {
                if (board[row][col] == '-') {
                    System.out.println(currentPlayer.getPlayerName());
                    return true;
                }
            }
        }
        if(!(currentPlayer instanceof AI)){
            System.out.println("Hier staat al iets DRAAK!");
        }
        return false;
    }

    @Override
    public void placeMove(int row, int col) {
        board[row][col] = currentPlayer.getPlayerMark();
    }

    public void placeAIMove() {
        if (!(isBoardFull(board))){
            Random random = new Random();
            int r1 = random.nextInt(3);
            int r2 = random.nextInt(3);
            if (checkMove(r1, r2)) {
                // String string = "button" + r1 + r2;
                // System.out.println(string);

                placeMove(r1, r2);
            }
            else {
              placeAIMove();
            }
        }
    }

    /**
     * Method to print board
     * @param board
     */
    @Override
    public void printBoard(char[][] board) {
        super.printBoard(board);
    }

    /**
     * Method to start game with 2 players
     * @param player1
     * @param player2
     */
    @Override
    public void start(Player player1, Player player2) throws InterruptedException {
        System.out.println("Tic-Tac-Toe!");
        Scanner inputScanner = new Scanner(System.in);
        do {
            System.out.println("Current board layout:");
            // current board moet omgezet worden naar scherm in gui.
            printBoard(board);
            int row;
            int col;
            // deze twee (row en col) moeten coordinaten uit gui worden op mouseclick
            do {
                // if player is AI
                if (getCurrentPlayer() instanceof AI) {
                    System.out.println("AI TURN");
                    TimeUnit.SECONDS.sleep(1);

                    placeAIMove();
                    // hier moet ipv printboard de view in gui geupdate worden
                    printBoard(board);
                    changePlayer();
                }
                // If player is Player

                System.out.println("Player " + getCurrentPlayer().getPlayerName() + ", enter an empty row and column to place your mark!");
                row = inputScanner.nextInt() ;
                if (row > board.length || row < 0) {
                    System.out.println("Doe een geldige waarde joh, " + getCurrentPlayer().getPlayerName());
                }
                col = inputScanner.nextInt() ;
                if (col > board.length || col < 0) {
                    System.out.println("Doe een geldige waarde joh, " + getCurrentPlayer().getPlayerName());
                }
            }
            while (!checkMove(row, col));
            // hier voert 'ie de 2 coordinaten uit de gui uit en plaatst ze
            placeMove(row, col);
            changePlayer();
        }
        while (!checkForWin() && !isBoardFull(board));
        if (isBoardFull(board) && !checkForWin()) {
            System.out.println("The game was a tie!");
        } else {
            System.out.println("Current board layout:");
            // hier weer view.update
            printBoard(board);
            if (!(getCurrentPlayer() instanceof AI)) {
                changePlayer();
            }
            System.out.println(Character.toUpperCase(getCurrentPlayerMark()) + " Wins!");
        }
    }


//        super.start(player1, player2);


    /**
     * Change current player
     */
    @Override
    public void changePlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer= player2;
        }
        else {
            currentPlayer = player1;
        }
    }

    /**
     * Method to check if someone has won the game
     * @return
     */
    @Override
    public boolean checkForWin() {
        return (checkHorizonForWin() || checkVerticalForWin() || checkDiagonalForWin());
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    private boolean checkHorizonForWin() {
        for (int i = 0; i < board.length; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalForWin() {
        for (int i = 0; i < board.length; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }

}



