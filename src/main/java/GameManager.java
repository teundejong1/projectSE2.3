public abstract class GameManager implements Board{
//    private Game game;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private char[][] board;

    @Override
    public char[][] createBoard(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
        return board;
    }

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

    public void printBoard(char[][] board) {
        System.out.println("++++++++++++++++++++");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j <board.length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("++++++++++++++++++++");
        }
    }

    public boolean isBoardFull(char[][] board) {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }


    public void start(Player player1, Player player2) {
    }

    public void changePlayer(){
    }

    public boolean checkForWin(){
        return false;
    }
}

