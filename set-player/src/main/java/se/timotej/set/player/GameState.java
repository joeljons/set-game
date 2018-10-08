package se.timotej.set.player;

import java.util.List;

public class GameState {
    private List<Card> cardsOnTable;
    private List<Integer> scores;

    public GameState(List<Card> cardsOnTable, List<Integer> scores) {
        this.cardsOnTable = List.copyOf(cardsOnTable);
        this.scores = List.copyOf(scores);
    }

    /**
     * Gives all cards on the table. Any newly added cards are in the end of the list.
     */
    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    /**
     * Gives the scores for all players.
     */
    public List<Integer> getScores() {
        return scores;
    }
}
