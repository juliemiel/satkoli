package my.boardgame.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A Board represents a layout with the specified number of pits and seeds, and two players
 */
public class Board {
    private static final AtomicInteger atomicBoardId = new AtomicInteger(1000);
    private final int boardId;
    private final int[] pits;
    private Player player1;
    private Player player2;

    private Player currentPlayer;

    public Board(int seed, int playerPits, Player player1, Player player2) {
        final int TOTAL_PLAYERS = 2;
        pits = new int[playerPits * TOTAL_PLAYERS];
        for (int i = 0; i < pits.length; i++) {
            // Initialise all player pits to seed, except for accumulator pits/Kalahas (0)
            pits[i] = i != playerPits - 1  && i != pits.length - 1 ? seed : 0;
        }
        // Initialise players
        this.player1 = player1;
        this.player1.setPlayerPits(0, playerPits - 1);
        this.player2 = player2;
        this.player2.setPlayerPits(playerPits, pits.length - 1);
        this.boardId = atomicBoardId.incrementAndGet();
    }

    public int getBoardId() {
        return boardId;
    }

    public synchronized int[] getBoardPits() {
        return pits;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        if (null == currentPlayer) {
            throw new IllegalStateException("Current player is not yet set!");
        }

        if (player1.isCurrentPlayer()) {
            return player1;
        } else {
            return player2;
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.currentPlayer.setCurrentPlayer(true);
    }

    public Player turnCurrentPlayer() {
        synchronized (this) {
            if (player1.isCurrentPlayer()) {
                player2.setCurrentPlayer(true);
                player1.setCurrentPlayer(false);
                currentPlayer = player2;
            } else {
                player1.setCurrentPlayer(true);
                player2.setCurrentPlayer(false);
                currentPlayer = player1;
            }
        }
        return currentPlayer;
    }

    public int getCurrentPlayerScore() {
        return pits[getCurrentPlayer().getPlayerKalaha()];
    }

    public Player getOpponentPlayer() {
        if (null == currentPlayer) {
            throw new IllegalStateException("Current player is not yet set!");
        }

        if (player1.isCurrentPlayer()) {
            return player2;
        } else {
            return player1;
        }
    }

    public int getOpponentScore() {
        return pits[getOpponentPlayer().getPlayerKalaha()];
    }

    /**
     * A valid Kalaha pit belongs to the current player, and not to the opponent
     * */
    public boolean isPlayersKalahaPit(int pitIndex) {
        return getCurrentPlayer().getPlayerKalaha() == pitIndex;
    }

    /**
     * Capture stones in the player's Kalaha pit
     */
    public int captureStones(int weight) {
        int index = getCurrentPlayer().getPlayerKalaha();
        pits[index] += weight;
        return pits[index];
    }

    public boolean isOpponentsKahalaPit(int pitIndex) {
        return getOpponentPlayer().getPlayerKalaha() == pitIndex;
    }

    public boolean isNotKalaha(int pitIndex) {
        return player1.getPlayerKalaha() != pitIndex && player2.getPlayerKalaha() != pitIndex;
    }

    public boolean isPitNotEmpty(int pitIndex) {
        return pits[pitIndex] > 0;
    }

    public int getPitFromAcross(int pitIndex) {
        if (pitIndex < pits.length/2) {
            return pits.length/2 + pitIndex;
        } else {
            return pits.length/2 - pitIndex;
        }
    }

    // An indication that the game has run to a finish
    public boolean hasRun() {
        for (int i = 0; i < pits.length; i ++) {
            if (player1.getPlayerKalaha() == i  || player2.getPlayerKalaha() == i) {
                continue;
            }
            if (pits[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public void prettyPrint() {
        System.out.println("Player 1's board");
        for(int i = 0; i < player1.getPlayerKalaha() + 1; i ++) {
            System.out.print(pits[i] + "\t");
        }
        System.out.println("\nPlayer 2's board");
        for(int i = player1.getPlayerKalaha() + 1; i < pits.length; i ++) {
            System.out.print(pits[i] + "\t");
        }
        System.out.println(System.lineSeparator());
    }

    public int peekAt(int pitIndex) {
        return pits[pitIndex];
    }

}
