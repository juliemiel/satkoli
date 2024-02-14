/*
package my.boardgame.service;

import my.boardgame.move.MoveCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameServiceTest {

    private GameService gameService;
    private final int player1 = 1;
    private final int player2 = 2;

    @Before
    public void setup() {
        gameService = new GameService(player1, player2);
    }

    @Test
    public void testPlayers() {
        Pair<Integer, Integer> gamePlayers = gameService.getPlayers();
        assertEquals(gamePlayers.getLeft().intValue(), player1);
        assertEquals(gamePlayers.getRight().intValue(), player2);
    }

    @Test
    public void testMove() {
        int currentPlayer = gameService.getCurrentPlayer();
        gameService.makeMove(new MoveCommand(player1, 3));
        assertNotEquals(gameService.getCurrentPlayer(), currentPlayer);
    }
}*/
