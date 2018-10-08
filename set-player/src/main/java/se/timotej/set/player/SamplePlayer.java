package se.timotej.set.player;

import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Random;

public class SamplePlayer implements Player {
    private Random r = new Random();

    @Override
    public void initGame(int playerNumber) {
    }

    @Override
    public String getName() {
        return "TestPlayer";
    }

    @Override
    public Triple<Card, Card, Card> play(GameState state) {
        List<Card> cards = state.getCardsOnTable();
        int cardCount = cards.size();
        for (int i = 0; i < 1000000; i++) {
            int a = r.nextInt(cardCount);
            int b = r.nextInt(cardCount);
            int c = r.nextInt(cardCount);
            if (a == b || a == c || b == c) {
                continue;
            }
            Triple<Card, Card, Card> possibleSet = Triple.of(cards.get(a), cards.get(b), cards.get(c));
            if (SetUtil.isSet(possibleSet)) {
                return possibleSet;
            }
        }
        return null;
    }
}
