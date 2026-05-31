package domain;

public final class SudokuGame {
    private final InputReader input;
    private final SudokuBoard board;

    public SudokuGame(InputReader input, SudokuBoard board) {
        this.input = input;
        this.board = board;
    }

    public void run() {
        while (true) {
            System.out.println(board.render());
            if (board.isComplete()) {
                System.out.println("Parabens! Voce completou o tabuleiro.");
                return;
            }

            System.out.println("--- NOVA JOGADA ---");
            System.out.println("(Digite 110 em linha, coluna ou numero para sair)");

            var row = input.readIntInRange("Linha (1-9 ou 0): ", 0, SudokuBoard.SIZE);
            if (row == 10) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                return;
            }

            var column = input.readIntInRange("Coluna (1-9 ou 0): ", 0, SudokuBoard.SIZE);
            if (column == 10) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                return;
            }

            var value = input.readIntInRange("Numero (1-9 ou 0): ", 0, SudokuBoard.SIZE);
            if (value == 10) {
                System.out.println("Jogo encerrado. Obrigado por jogar!");
                return;
            }

            var answer = input.readToken("Tem certeza dessa jogada? (S/N): ");
            var move = new Move(new Position(row - 1, column - 1), value, answer.equalsIgnoreCase("N"));
            var result = board.applyMove(move);
            System.out.println(result.message());
        }
    }
}

