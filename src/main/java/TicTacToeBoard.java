public class TicTacToeBoard implements Board{

    private char[][] TTTboard;
    private char currentPlayerMark;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    public TicTacToeBoard(Player player1, Player player2) {
        TTTboard = new char[3][3];
        this.TTTboard = createBoard();
        currentPlayerMark = 'x';
        currentPlayer = player1;
        this.player1 = player1;
        System.out.println(player1.getPlayerName());
        this.player2 = player2;
        System.out.println(player2.getPlayerName());
    }

    public char getCurrentPlayerMark()
    {
        return currentPlayerMark;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }


@Override
    public char[][] createBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TTTboard[i][j] = '-';
            }
        }
        return TTTboard;
    }

    public boolean placeMark() {
        return false;
    }



@Override
    public void printBoard() {
        System.out.println("++++++++++");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(TTTboard[i][j] + " | ");
            }
            System.out.println();
            System.out.println("++++++++++");
        }
    }

    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TTTboard[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }


    private boolean checkHorizonForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(TTTboard[i][0], TTTboard[i][1], TTTboard[i][2]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(TTTboard[0][i], TTTboard[1][i], TTTboard[2][i]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalForWin() {
        return ((checkRowCol(TTTboard[0][0], TTTboard[1][1], TTTboard[2][2]) == true) || (checkRowCol(TTTboard[0][2], TTTboard[1][1], TTTboard[2][0]) == true));
    }

    public boolean checkForWin() {
        return (checkHorizonForWin() || checkVerticalForWin() || checkDiagonalForWin());
    }


    public void changePlayer() {
        if (currentPlayer.getPlayerName() == "joep") {
            currentPlayer= player2;

        }
        else {
            currentPlayer = player1;

        }
    }

    @Override
    public boolean placeMark(int row, int col) {
        if ((row >= 0) && (row < 3)) {
            if ((col >= 0) && (col < 3)) {
                if (TTTboard[row][col] == '-') {
                    TTTboard[row][col] = currentPlayer.getPlayerMark();
                    System.out.println(currentPlayer.getPlayerName());
                    return true;
                }
            }
        }

        return false;
    }
}
