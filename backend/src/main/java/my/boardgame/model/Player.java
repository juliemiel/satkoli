package my.boardgame.model;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Player {
    private final int playerNumber;

    private boolean isCurrentPlayer;

    private Pair<Integer, Integer> pitBoundaries;

    private int playerKalaha;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public void setCurrentPlayer(boolean flag) {
        isCurrentPlayer = flag;
    }

    /**
     * Set the pit boundaries for this player
     * @param start is where the player's first pit starts on the board
     * @param end is where the player's last pit ends on the board. This includes their Kalaha pit
     */
    public void setPlayerPits(int start, int end) {
        this.pitBoundaries = new MutablePair<>(start, end);
        this.playerKalaha = end;
    }

    public int getPlayerKalaha() {
        return playerKalaha;
    }

    /**
     * A player's valid pitIndex lies within the boundaries of its's pitBoundaries, excluding the Kalaha pits
     */
    public boolean isPlayerPit(int pitIndex) {
        return pitIndex >= pitBoundaries.getLeft() && pitIndex <= pitBoundaries.getRight();
    }
}
