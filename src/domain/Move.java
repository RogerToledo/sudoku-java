package domain;

public record Move(Position position, int value, boolean uncertain) {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 9;

    public Move {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("Numero fora do limite: " + value);
        }
    }
}

