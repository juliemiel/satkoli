package my.boardgame.game;

import my.boardgame.model.Board;
import my.boardgame.model.Player;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void testBoardSetup() {
        int seed = 6; int pits = 7; int totalPits = pits * 2;
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Board board = new Board(seed, pits, player1, player2);
        player1.setCurrentPlayer(true);
        int[] boardPits = board.getBoardPits();

        // Assert Board setup
        assertEquals(totalPits, boardPits.length);
        assertFalse(Arrays.stream(boardPits).allMatch(p -> seed == p));
        assertEquals(0, boardPits[pits - 1]);
        assertEquals(0, boardPits[boardPits.length - 1]);

        // Assert player setup on Board
        assertEquals(pits - 1, board.getPlayer1().getPlayerKalaha());
        assertEquals(totalPits - 1, board.getPlayer2().getPlayerKalaha());

        // Assert capturing
        assertEquals(seed, board.captureStones(seed));
    }
}