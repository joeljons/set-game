package se.timotej.set.player;

import java.util.Objects;

public class Card {
    public enum Shape {OVAL, SQUIGGLY, DIAMOND}

    public enum Colour {RED, PURPLE, GREEN}

    public enum Number {ONE, TWO, THREE}

    public enum Shading {SOLID, STRIPED, OUTLINED}

    private final Shape shape;
    private final Colour colour;
    private final Number number;
    private final Shading shading;

    public Card(Shape shape, Colour colour, Number number, Shading shading) {
        this.shape = shape;
        this.colour = colour;
        this.number = number;
        this.shading = shading;
    }

    public Shape getShape() {
        return shape;
    }

    public Colour getColour() {
        return colour;
    }

    public Number getNumber() {
        return number;
    }

    public Shading getShading() {
        return shading;
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", colour=" + colour +
                ", number=" + number +
                ", shading=" + shading +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape &&
                colour == card.colour &&
                number == card.number &&
                shading == card.shading;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, colour, number, shading);
    }
}
