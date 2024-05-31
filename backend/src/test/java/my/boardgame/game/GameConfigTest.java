package my.boardgame.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameConfigTest {

    @Test
    public void testGameMode() {
        int seed = 6;
        GameConfig game = new GameConfig(seed, 7);
        System.out.println("Initial view");
        game.printPitsSnapshot();
        assertEquals(seed, game.peekAt(0));
        assertEquals(seed, game.peekAt(9));

        boolean repeatMove = game.makeMove(0);
        assertFalse(repeatMove);
        game.printPitsSnapshot();
        assertEquals(0, game.peekAt(0));
        assertEquals(1, game.peekAt(6));

        game.makeMove(9);
        game.printPitsSnapshot();
        assertEquals(8, game.peekAt(1));
        assertEquals(1, game.peekAt(13));
    }
}
