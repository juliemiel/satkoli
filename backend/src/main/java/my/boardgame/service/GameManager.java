/*
package my.boardgame.service;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
        private final Map<Integer, GameService> gameInstances;

        public GameManager() {
            gameInstances = new HashMap<>();
        }

        public int startNewGame(int player1, int player2) {
            GameService boardGame = new GameService(player1, player2);
            int existingSessionId = boardGame.getBoardId();
            if (gameInstances.containsKey(existingSessionId)) {
                throw new IllegalStateException("Session already exists: " + existingSessionId);
            }
            gameInstances.put(existingSessionId, boardGame);
            return existingSessionId;
        }
        public void endGame(String sessionId) {
            gameInstances.remove(sessionId);
        }
}
*/
