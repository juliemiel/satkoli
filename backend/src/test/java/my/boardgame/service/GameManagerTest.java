/*
package my.boardgame.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerTest {
    private GameManager gameManager;
    private String sessionId1;
    private String sessionId2;

    @Before
    public void setUp() {
        gameManager = new GameManager();
        sessionId1 = "game1";
        sessionId2 = "game2";
    }

    @Test
    public void testStartNewGame() {
        GameManager game1 = gameManager.startNewGame(sessionId1);
        GameManager game2 = gameManager.startNewGame(sessionId2);

        assertNotNull(game1);
        assertNotNull(game2);
        assertNotSame("Same sessions - check implementation ", game1, game2);
    }

    @Test
    public void testGetGameBySessionId() {
        GameManager game1 = gameManager.startNewGame(sessionId1);
        GameManager game2 = gameManager.startNewGame(sessionId2);

        assertEquals(game1, gameManager.getGameBySessionId(sessionId1));
        assertEquals(game2, gameManager.getGameBySessionId(sessionId2));
    }

    @Test
    public void testEndGame() {
        GameManager game1 = gameManager.startNewGame(sessionId1);
        gameManager.startNewGame(sessionId2);

        gameManager.endGame(sessionId1);
        assertNull("Game1 should have ended", gameManager.getGameBySessionId(sessionId1));
        assertNotNull("Game2 should still be running", gameManager.getGameBySessionId(sessionId2));
    }

}*/
