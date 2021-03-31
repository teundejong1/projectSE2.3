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
    public boolean doMove(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (board[row][col] == '-') {
                    board[row][col] = currentPlayer.getPlayerMark();
                    System.out.println(currentPlayer.getPlayerName());
                    return true;
                }
            }
        }
        return false;
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
    public void start(Player player1, Player player2) {
        super.start(player1, player2);
    }

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
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }
    private boolean checkVerticalForWin() {
        for (int i = 0; i < 3; i++) {
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

