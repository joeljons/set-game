package se.timotej.set;

import se.timotej.set.player.Card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck() {
        List<Card> shufflingCards = createSortedCards();
        Collections.shuffle(shufflingCards);
        this.cards = new ArrayDeque<>(shufflingCards);
    }

    public static List<Card> createSortedCards() {
        List<Card> shufflingCards = new ArrayList<>();
        for (Card.Shape shape : Card.Shape.values()) {
            for (Card.Colour colour : Card.Colour.values()) {
                for (Card.Number number : Card.Number.values()) {
                    for (Card.Shading shading : Card.Shading.values()) {
                        shufflingCards.add(new Card(shape, colour, number, shading));
                    }
                }
            }
        }
        return shufflingCards;
    }

    public Card take() throws NoSuchElementException {
        return cards.remove();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
