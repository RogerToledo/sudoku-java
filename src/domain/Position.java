package domain;

public record Position(int row, int column) {
    public static final int SIZE = 9;

    public Position {
        if (row < 0 || row >= SIZE || column < 0 || column >= SIZE) {
            throw new IllegalArgumentException("Posicao fora do tabuleiro: (%d,%d)".formatted(row, column));
        }
    }
}

