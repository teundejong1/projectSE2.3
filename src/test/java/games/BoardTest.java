package games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import games.board.Board;
import games.board.Mark;
import games.board.SetOutOfBoundsException;
import games.board.TicTacToeBoard;

public class BoardTest {

    private static Board board;
    
    @BeforeAll
    static void beforeAll() {}

    @BeforeEach
    void BeforeEach() {}

    @AfterAll
    static void afterAll() {}

    @AfterEach
    void afterEach() {}

    @Test
    void setMoveTest() {
        board = new TicTacToeBoard(3);

        try {
            board.setMove(0, 1, Mark.ONE);
        } catch (SetOutOfBoundsException ime) {}

        assertEquals('X', board.getCell(0, 1));
        
        assertThrows(SetOutOfBoundsException.class, () -> { board.setMove(0, 3, Mark.ONE); });
    }
}
