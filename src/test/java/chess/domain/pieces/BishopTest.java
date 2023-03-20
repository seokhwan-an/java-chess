package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3:a1", "c3:b2", "c3:d4", "c3:e5", "c3:f6", "c3:g7", "c3:a5", "c3:d2",
        "c3:e1"}, delimiter = ':')
    @DisplayName("bishop은 대각선으로만 움직인다.")
    void move_success(final String start, final String end) {
        // given
        Bishop bishop = new Bishop(Team.WHITE);

        // when & then
        assertDoesNotThrow(() -> bishop.canMove(start, end));
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3:a7", "c3:b3", "c3:c5", "c3:d7"}, delimiter = ':')
    @DisplayName("bishop이 대각선으로 움직이지 않으면 예외를 발생시킨다.")
    void throws_exception_when_move_invalid(final String start, final String end) {
        // given
        Bishop bishop = new Bishop(Team.WHITE);

        // when & then
        assertThatThrownBy(
            () -> bishop.canMove(start, end)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
