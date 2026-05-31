package domain;

import java.util.Scanner;

public final class InputReader {
    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                System.out.println("Erro: digite apenas numeros inteiros.");
                scanner.next();
                continue;
            }

            var value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.printf("Opcao invalida. Digite um numero entre %d e %d.%n", min, max);
                continue;
            }
            return value;
        }
    }

    public String readToken(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}

