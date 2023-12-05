package se.timotej.set.player;

import java.util.List;

public record GameState(List<Card> cardsOnTable, List<Integer> scores) {
    /**
     * @param cardsOnTable All cards on the table. Any newly added cards are in the end of the list.
     * @param scores       The scores for all players.
     */
    public GameState(List<Card> cardsOnTable, List<Integer> scores) {
        this.cardsOnTable = List.copyOf(cardsOnTable);
        this.scores = List.copyOf(scores);
    }
}
