import java.util.Scanner;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {
            Sudoku.executar(scanner);
        }
    }
}