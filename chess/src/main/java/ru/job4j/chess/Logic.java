package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.util.Arrays;
import java.util.Objects;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        free(steps);
        figures[index] = figures[index].copy(dest);
    }

    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Figure figure : figures) {
            for (Cell step : steps) {
                if (figure.position().equals(step)) {
                    throw new OccupiedCellException(String.format("Cell %s is occupied", step));
                }
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found on the board.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Logic logic = (Logic) o;
        return index == logic.index && Arrays.equals(figures, logic.figures);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(index);
        result = 31 * result + Arrays.hashCode(figures);
        return result;
    }
}
