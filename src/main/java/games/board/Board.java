package games.board;

public abstract class Board {

    private Mark[][] board;
    private int size;
    
    public Board(int size) {
        this.size = size;
        board = new Mark[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    public abstract String getMark(Mark mark);

    public void setMove(int x, int y, Mark marker) throws SetOutOfBoundsException {
        if (!isInBounds(x, y)) throw new SetOutOfBoundsException("Move on board is out of bounds");
        
        board[x][y] = marker;
    }

    public Mark getCell(int x, int y) {
        return board[x][y];
    }

    public int getSize() {
        return size;
    }

    public boolean isInBounds(int x, int y) {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }

    public boolean isFull() {
        boolean isFull = true;

        outerloop:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == Mark.EMPTY) {
                    isFull = false;
                    break outerloop;
                }
            }
        }
        return isFull;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Mark mark;

        for (int i = 0; i < size; i++) {
            sb.append("|");
            for (int j = 0; j < size; j++) {
                mark = board[i][j];
                sb.append(getMark(mark) + "|");
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
        
    }

}
