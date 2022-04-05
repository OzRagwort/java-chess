package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("넘어온 파라미터의 색과 피스의 색이 같은지 확인한다")
    void colorTest() {
        Color color = Color.BLACK;
        Piece piece = new Rook(color);

        assertThat(piece.isSameColor(color)).isTrue();
    }

    @Test
    @DisplayName("넘어온 파라미터의 색과 피스의 색이 다른지 확인한다")
    void colorTestFalse() {
        Color color = Color.BLACK;
        Piece piece = new Rook(color);

        assertThat(piece.isSameColor(Color.WHITE)).isFalse();
    }
}
