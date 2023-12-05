package se.timotej.set.player;

public record Card(Shape shape, Colour colour, Number number, Shading shading) {
    public enum Shape {OVAL, SQUIGGLY, DIAMOND}

    public enum Colour {RED, PURPLE, GREEN}

    public enum Number {ONE, TWO, THREE}

    public enum Shading {SOLID, STRIPED, OUTLINED}
}
