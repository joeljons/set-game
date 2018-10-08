package se.timotej.set.player;

import org.apache.commons.lang3.tuple.Triple;

import java.util.function.Function;

public class SetUtil {
    private SetUtil() {
    }

    public static boolean isSet(Triple<Card, Card, Card> cards) {
        if (cards.getLeft().equals(cards.getMiddle())
                || cards.getLeft().equals(cards.getRight())
                || cards.getMiddle().equals(cards.getRight())) {
            return false;
        }
        return sameOrDifferent(cards, Card::getShape)
                && sameOrDifferent(cards, Card::getColour)
                && sameOrDifferent(cards, Card::getNumber)
                && sameOrDifferent(cards, Card::getShading);
    }

    private static boolean sameOrDifferent(Triple<Card, Card, Card> cards, Function<Card, Enum<?>> propertyGetter) {
        Enum<?> leftProperty = propertyGetter.apply(cards.getLeft());
        Enum<?> middleProperty = propertyGetter.apply(cards.getMiddle());
        Enum<?> rightProperty = propertyGetter.apply(cards.getRight());
        return (leftProperty == middleProperty && middleProperty == rightProperty)
                || (leftProperty != middleProperty && middleProperty != rightProperty && leftProperty != rightProperty);
    }

}
