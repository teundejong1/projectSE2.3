public class TicTacToeManager extends GameManager {
    private char[][] board;
    private char currentPlayerMark;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    public TicTacToeManager(Player player1, Player player2) {

        this.board = createBoard(3);
        currentPlayerMark = 'x';
        currentPlayer = player1;
        this.player1 = player1;
        System.out.println("Naam van player 1 : " + player1.getPlayerName());
        this.player2 = player2;
        System.out.println("Naam van player 2 : " + player2.getPlayerName());
    }

    public char[][] getBoard(){
        return this.board;
    }

        public char getCurrentPlayerMark()
    {
        return currentPlayerMark;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

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


    @Override
    public void printBoard(char[][] board) {
        super.printBoard(board);
    }

    @Override
    public void start(Player player1, Player player2) {
        super.start(player1, player2);
    }

    @Override
    public void changePlayer() {
        if (currentPlayer.equals(player1)) {
            currentPlayer= player2;
        }
        else {
            currentPlayer = player1;
        }
    }

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

