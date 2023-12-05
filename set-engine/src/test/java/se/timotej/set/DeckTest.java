package se.timotej.set;

import org.junit.jupiter.api.Test;
import se.timotej.set.player.Card;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeckTest {
    @Test
    public void newDeckContains81Cards() {
        Deck deck = new Deck();
        int cardCount = 0;
        while (!deck.isEmpty()) {
            deck.take();
            cardCount++;
        }
        assertEquals(81, cardCount);
    }

    @Test
    public void newDeckContainsUniqueCards() {
        Deck deck = new Deck();
        Set<Card> cards = new HashSet<>();
        while (!deck.isEmpty()) {
            Card card = deck.take();
            if (!cards.add(card)) {
                fail("Duplicate card: " + card);
            }
        }
    }
}