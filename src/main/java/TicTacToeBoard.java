///**
// * TicTacToeBoard class, used to initialise the board used for playing the game.
// * Keeps track of player turns, checks for a winner and updates the board after a player turn.
// * @author Eva Jakobs, Teun de Jong
// */
//public class TicTacToeBoard implements Board {
//
//    private char[][] TTTboard;
//    private char currentPlayerMark;
//    private Player currentPlayer;
//    private Player player1;
//    private Player player2;
//
//    /**
//     * Concstructor for TicTacToeBoard
//     * @param player1 Player
//     * @param player2 Player
//     */
//    public TicTacToeBoard(Player player1, Player player2) {
//        TTTboard = new char[3][3];
//        this.TTTboard = createBoard(3);
//        currentPlayerMark = 'x';
//        currentPlayer = player1;
//        this.player1 = player1;
//        System.out.println("Naam van player 1 : " + player1.getPlayerName());
//        this.player2 = player2;
//        System.out.println("Naam van player 2 : " + player2.getPlayerName());
//    }
//
//    /**
//     * Method to return PlayerMark
//     * @return Playermark
//     */
//    public char getCurrentPlayerMark()
//    {
//        return currentPlayerMark;
//    }
//
//    /**
//     * Method to return current player
//     * @return currentPlayer
//     */
//    public Player getCurrentPlayer()
//    {
//        return currentPlayer;
//    }
//
//    /**
//     * Method to create the board and return it.
//     * @return TicTacToeBoard
//     */
//    @Override
//    public char[][] createBoard(int size) {
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                TTTboard[i][j] = '-';
//            }
//        }
//        return TTTboard;
//    }
//
//    /**
//     * Method used to print the board using two for-loops.
//     * One used for the rows and one used for the columns.
//     */
//    @Override
//    public void printBoard() {
//        System.out.println("++++++++++");
//        for (int i = 0; i < 3; i++) {
//            System.out.print("| ");
//            for (int j = 0; j < 3; j++) {
//                System.out.print(TTTboard[i][j] + " | ");
//            }
//            System.out.println();
//            System.out.println("++++++++++");
//        }
//    }
//
//    /**
//     * Method to check whether the board is full and thus no more moves can be made by the players.
//     * @return Boolean True if the board is full, False if the board is not full.
//     */
//    public boolean isBoardFull() {
//        boolean isFull = true;
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (TTTboard[i][j] == '-') {
//                    isFull = false;
//                }
//            }
//        }
//        return isFull;
//    }
//
//    /**
//     * Method used to check rows and columns for a win.
//     * @param c1 char
//     * @param c2 char
//     * @param c3 char
//     * @return True if all three characters are equal to a players' mark, otherwise return False.
//     */
//    private boolean checkRowCol(char c1, char c2, char c3) {
//        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
//    }
//
//    /**
//     * Method used to check if a player has three of his or her marks horizontally next to each other.
//     * @return true if the conditions are met.
//     */
//    private boolean checkHorizonForWin() {
//        for (int i = 0; i < 3; i++) {
//            if (checkRowCol(TTTboard[i][0], TTTboard[i][1], TTTboard[i][2]) == true) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Method used to check if a player has three of his or her marks vertically next to each other.
//     * @return true if the conditions are met.
//     */
//    private boolean checkVerticalForWin() {
//        for (int i = 0; i < 3; i++) {
//            if (checkRowCol(TTTboard[0][i], TTTboard[1][i], TTTboard[2][i]) == true) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Method used to check if a player has three of his or her marks diagonally next to each other.
//     * @return true if the conditions are met.
//     */
//    private boolean checkDiagonalForWin() {
//        return ((checkRowCol(TTTboard[0][0], TTTboard[1][1], TTTboard[2][2]) == true) || (checkRowCol(TTTboard[0][2], TTTboard[1][1], TTTboard[2][0]) == true));
//    }
//
//    /**
//     * Method used to check if a player has met the win conditions for the game.
//     * @return true if Horizontal, Vertical or Diagonal win.
//     */
//    public boolean checkForWin() {
//        return (checkHorizonForWin() || checkVerticalForWin() || checkDiagonalForWin());
//    }
//
//    /**
//     * Method used to change who's turn it is.
//     * Compares Players using the .equals() method.
//     */
//    public void changePlayer() {
//        if (currentPlayer.equals(player1)) {
//            currentPlayer= player2;
//        }
//        else {
//            currentPlayer = player1;
//        }
//    }
//
//    /**
//     * Method used to place a player's mark on the board.
//     * @param row int for row.
//     * @param col int for column.
//     * @return True if mark placed on board, otherwise False.
//     */
//    @Override
//    public boolean doMove(int row, int col) {
//        if ((row >= 0) && (row < 3)) {
//            if ((col >= 0) && (col < 3)) {
//                if (TTTboard[row][col] == '-') {
//                    TTTboard[row][col] = currentPlayer.getPlayerMark();
//                    System.out.println(currentPlayer.getPlayerName());
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}
