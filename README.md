# Sudoku Java (DIO Challenge)

Projeto de Sudoku em Java desenvolvido como parte de um desafio prático da [DIO (Digital Innovation One)](https://www.dio.me/).

## Visão geral

Aplicação de terminal (CLI) para jogar Sudoku, com:
- seleção de dificuldade
- validação de jogadas (linha, coluna e quadrante)
- proteção de posições fixas do tabuleiro
- marcação de jogadas incertas
- opção de apagar jogadas do usuário
- validação de solução final (`isSolved`)

## Stack

- **Java 25**
- Paradigma orientado a objetos
- Uso de recursos modernos da linguagem (`record`, `enum`, `var`, streams)

## Estrutura do projeto

```text
src/
  Main.java
  Sudoku.java
  domain/
    Difficulty.java
    InputReader.java
    Move.java
    MoveResult.java
    Position.java
    SudokuBoard.java
    SudokuGame.java