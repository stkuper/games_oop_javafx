package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BishopBlackTest {
    @Test
    public void whenTheSamePosition() {
        BishopBlack bishop = new BishopBlack(Cell.A3);
        Cell result = bishop.position();
        Cell expected = Cell.A3;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenCopy() {
        BishopBlack bishop = new BishopBlack(Cell.A3);
        BishopBlack bishopMove = (BishopBlack) bishop.copy(Cell.C5);
        Cell result = bishopMove.position();
        Cell expected = Cell.C5;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenWayC1ToG5() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] result = bishop.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenIsNotDiagonal() {
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    BishopBlack bishop = new BishopBlack(Cell.C1);
                    bishop.way(Cell.G4);
                });
        assertThat(exception.getMessage()).isEqualTo("Could not way by diagonal from C1 to G4");
    }
}