package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SudokuBoard {
    public static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;

    private final int[][] grid;
    private final Set<Position> fixedPositions;
    private final Set<Position> uncertainPositions;

    private SudokuBoard(int[][] grid, Set<Position> fixedPositions) {
        this.grid = grid;
        this.fixedPositions = Set.copyOf(fixedPositions);
        this.uncertainPositions = new HashSet<>();
    }

    public static SudokuBoard fromTemplate(int[][] template) {
        var copiedGrid = Arrays.stream(template)
                .map(int[]::clone)
                .toArray(int[][]::new);

        var fixed = IntStream.range(0, SIZE)
                .boxed()
                .flatMap(row -> IntStream.range(0, SIZE)
                        .filter(column -> copiedGrid[row][column] != 0)
                        .mapToObj(column -> new Position(row, column)))
                .collect(Collectors.toSet());

        return new SudokuBoard(copiedGrid, fixed);
    }

    public void shuffleNumbers() {
        var shuffledNumbers = IntStream.rangeClosed(1, SIZE)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(shuffledNumbers);

        var map = new int[SIZE + 1];
        IntStream.rangeClosed(1, SIZE)
                .forEach(number -> map[number] = shuffledNumbers.get(number - 1));

        IntStream.range(0, SIZE)
                .forEach(row -> IntStream.range(0, SIZE)
                        .filter(column -> grid[row][column] != 0)
                        .forEach(column -> grid[row][column] = map[grid[row][column]]));
    }

    public MoveResult applyMove(Move move) {
        if (fixedPositions.contains(move.position())) {
            return MoveResult.FIXED_POSITION;
        }

        if (!isValidPlacement(move.position(), move.value())) {
            return MoveResult.CONFLICT;
        }

        grid[move.position().row()][move.position().column()] = move.value();
        uncertainPositions.remove(move.position());
        if (move.uncertain()) {
            uncertainPositions.add(move.position());
        }

        return MoveResult.PLACED;
    }

    public boolean isComplete() {
        return IntStream.range(0, SIZE)
                .allMatch(row -> IntStream.range(0, SIZE)
                        .noneMatch(column -> grid[row][column] == 0));
    }

    public String render() {
        var builder = new StringBuilder();
        builder.append("    1  2  3   4  5  6   7  8  9\n");
        builder.append("  +---------+---------+---------+\n");

        IntStream.range(0, SIZE).forEach(row -> {
            var values = IntStream.range(0, SIZE)
                    .mapToObj(column -> cellValue(row, column))
                    .toList();

            builder.append("%d | %s  %s  %s | %s  %s  %s | %s  %s  %s |%n".formatted(
                    row + 1,
                    values.get(0), values.get(1), values.get(2),
                    values.get(3), values.get(4), values.get(5),
                    values.get(6), values.get(7), values.get(8)
            ));

            if ((row + 1) % SUBGRID_SIZE == 0) {
                builder.append("  +---------+---------+---------+\n");
            }
        });

        return builder.toString();
    }

    private String cellValue(int row, int column) {
        var number = grid[row][column];
        if (number == 0) {
            return ".";
        }

        var position = new Position(row, column);
        if (fixedPositions.contains(position)) {
            return "\033[1;3m\033[34m" + number + "\033[0m";
        }
        if (uncertainPositions.contains(position)) {
            return "\033[33m" + number + "\033[0m";
        }
        return Integer.toString(number);
    }

    private boolean isValidPlacement(Position position, int value) {
        return rowValues(position.row(), position).stream().noneMatch(current -> current == value)
                && columnValues(position.column(), position).stream().noneMatch(current -> current == value)
                && subgridValues(position).stream().noneMatch(current -> current == value);
    }

    private Set<Integer> rowValues(int row, Position ignored) {
        return IntStream.range(0, SIZE)
                .filter(column -> column != ignored.column())
                .map(column -> grid[row][column])
                .filter(value -> value != 0)
                .boxed()
                .collect(Collectors.toSet());
    }

    private Set<Integer> columnValues(int column, Position ignored) {
        return IntStream.range(0, SIZE)
                .filter(row -> row != ignored.row())
                .map(row -> grid[row][column])
                .filter(value -> value != 0)
                .boxed()
                .collect(Collectors.toSet());
    }

    private Set<Integer> subgridValues(Position ignored) {
        var startRow = (ignored.row() / SUBGRID_SIZE) * SUBGRID_SIZE;
        var startColumn = (ignored.column() / SUBGRID_SIZE) * SUBGRID_SIZE;

        return IntStream.range(startRow, startRow + SUBGRID_SIZE)
                .boxed()
                .flatMap(row -> IntStream.range(startColumn, startColumn + SUBGRID_SIZE)
                        .filter(column -> row != ignored.row() || column != ignored.column())
                        .map(column -> grid[row][column])
                        .filter(value -> value != 0)
                        .boxed())
                .collect(Collectors.toSet());
    }
}

