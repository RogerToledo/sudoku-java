package domain;

public enum MoveResult {
    PLACED("Boa jogada!"),
    FIXED_POSITION("Voce nao pode alterar os numeros originais do tabuleiro."),
    CONFLICT("Jogada invalida! Esse numero conflita com a linha, coluna ou quadrante.");

    private final String message;

    MoveResult(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}

