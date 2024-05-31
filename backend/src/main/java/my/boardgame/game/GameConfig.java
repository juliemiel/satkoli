package my.boardgame.game;

import my.boardgame.model.Board;
import my.boardgame.model.Player;

public class GameConfig {
    private final Board board;

    private boolean gameOver;

    public GameConfig(int seed, int playerPits) {
        gameOver = false;
        board = new Board(seed, playerPits, new Player(1), new Player(2));
        // Pick player1 to start the game
        board.setCurrentPlayer(board.getPlayer1());
        System.out.println("Player " + board.getCurrentPlayer() + " begins in board " + board.getBoardId());
    }

    // Make a move on the specified pit for currentPlayer
    public boolean makeMove(int pitIndex) {
        System.out.println("Player " + getCurrentPlayerId() + " makes move on " + pitIndex);
        if (!isValidMove(pitIndex)) {
            System.out.println("Invalid move on " + pitIndex);
            return false;
        }
        int[] pits = board.getBoardPits();

        // 1. Move stones from the selected pit to other pits, except the opponent's Kalaha
        moveStones(pits, pitIndex);

        // 2. Perform capture if last stone lands in player's empty pit
        capturePits(pits, pitIndex);

        // 3. Check for game end conditions
        if (board.hasRun()) {
            System.out.println("Winner is - " + endGameWithWinner().getPlayerNumber());
        }

        // Allow a repeat turn if the last stone lands in the player's Kalaha
        boolean repeatMove = board.isPlayersKalahaPit(pitIndex);
        if (repeatMove) {
            System.out.println("Player " + board.getCurrentPlayer() + " repeats turn!");
        } else {
            board.turnCurrentPlayer(); // Pick next player as current player
        }
        return repeatMove;
    }

    private void moveStones(int[] pits, int pitIndex) {
        int stones = pits[pitIndex];
        pits[pitIndex] = 0;

        while (stones > 0) {
            pitIndex = (pitIndex + 1) % pits.length;
            if (board.isOpponentsKahalaPit(pitIndex)) {
                // Skip opponent's Kalaha pit
                pitIndex = (pitIndex + 1) % pits.length;
            }
            pits[pitIndex]++; // Drop a stone in the pit
            stones--;
        }
    }

    /**
     * Capture pit if on moving, the last stone lands in player's Kalaha
     */
    private void capturePits(int[] pits, int pitIndex) {
        if (pits[pitIndex] == 1 && board.getCurrentPlayer().isPlayerPit(pitIndex)) {
            System.out.println("Capturing stones from player's pit at " + pitIndex);
            board.captureStones(pits[pitIndex]); // Capture stones in player's pit
            pits[pitIndex] = 0;
            // Capture stones from the opponent's pit
            int opponentsIndex = board.getPitFromAcross(pitIndex);
            System.out.println("Capturing stones from opponent's pit at " + opponentsIndex);
            int opponentsStones = pits[opponentsIndex];
            board.captureStones(opponentsStones);
        }
    }

    /*
     * A valid board move is -
     * 1. when the game is still live, and
     * 2. when the pitIndex is not one of the Kalaha pits, and
     * 3. the pitIndex has seeds to play, and
     * 4. pitIndex is within the array bounds of the player
     */

    private boolean isValidMove(int pitIndex) {
        return (!gameOver && board.isNotKalaha(pitIndex) && board.isPitNotEmpty(pitIndex) && isPlayerPit(pitIndex));
    }

    public boolean isPlayerPit(int pitIndex) {
        return board.getCurrentPlayer().isPlayerPit(pitIndex);
    }

    public int getCurrentPlayerId() {
        return board.getCurrentPlayer().getPlayerNumber();
    }

    // Print board snapshot for debugging
    public void printPitsSnapshot() {
        board.prettyPrint();
    }

    public int peekAt(int pitIndex) {
        return board.peekAt(pitIndex);
    }

    // Ends the game and determine the winner
    private Player endGameWithWinner() {
        gameOver = true;
        if (board.getCurrentPlayerScore() > board.getOpponentScore()) {
            return board.getCurrentPlayer();
        } else {
            return board.getOpponentPlayer();
        }
    }
}
