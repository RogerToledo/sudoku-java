# sudoku-java

Jogo de Sudoku em Java com arquitetura separada entre entrada, regras de dominio e orquestracao do jogo.

## Estrutura

- `src/Main.java`: ponto de entrada da aplicacao.
- `src/Sudoku.java`: fachada que inicializa o jogo.
- `src/domain/Difficulty.java`: enum com tabuleiros base por dificuldade.
- `src/domain/SudokuBoard.java`: estado do tabuleiro e regras de validacao.
- `src/domain/SudokuGame.java`: loop da partida.
- `src/domain/InputReader.java`: leitura segura de entrada no terminal.
- `src/domain/Position.java` e `src/domain/Move.java`: modelos imutaveis com `record`.

## Rodar

```bash
cd /Users/rogertoledo/me/cursos/dio/sudoku-java/src
javac Main.java Sudoku.java domain/*.java
java Main
```
