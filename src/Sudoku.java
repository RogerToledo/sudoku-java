import domain.Difficulty;
import domain.InputReader;
import domain.SudokuBoard;
import domain.SudokuGame;

import java.util.Scanner;

public final class Sudoku {
    private Sudoku() {
    }

    public static void executar(Scanner scanner) {
        var input = new InputReader(scanner);

        System.out.println("=====================================");
        System.out.println("      BEM-VINDO AO COPS SUDOKU       ");
        System.out.println("=====================================");

        var dificuldade = Difficulty.fromOption(input.readIntInRange(
                "\nEscolha o nivel de dificuldade [1=Facil, 2=Medio, 3=Dificil]: ",
                1,
                3
        ));

        var board = SudokuBoard.fromTemplate(dificuldade.template());
        board.shuffleNumbers();

        System.out.println("\nJogo iniciado com sucesso!");
        new SudokuGame(input, board).run();
    }
}
