package se.timotej.set.player;

import org.apache.commons.lang3.tuple.Triple;

public interface Player {
    /**
     * Called before start of the game. Corresponds to the scores list in {@link GameState#getScores()}
     *
     * @param playerNumber your number
     */
    default void initGame(int playerNumber) {
    }

    /**
     * @return Your player name
     */
    default String getName() {
        return getClass().getSimpleName();
    }

    /**
     * Play as fast as you can!
     *
     * @param state current game state
     * @return a valid player, or null if you can't find any valid player
     */
    Triple<Card, Card, Card> play(GameState state);
}
