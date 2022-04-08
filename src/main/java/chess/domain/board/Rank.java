package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import java.util.Arrays;

public enum Rank {

    RANK_8("8", 8),
    RANK_7("7", 7),
    RANK_6("6", 6),
    RANK_5("5", 5),
    RANK_4("4", 4),
    RANK_3("3", 3),
    RANK_2("2", 2),
    RANK_1("1", 1),
    ;

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 8;
    private static final Rank BLACK_START_RANK = Rank.RANK_7;
    private static final Rank WHITE_START_RANK = Rank.RANK_2;

    private final String value;
    private final int y;

    Rank(String value, int y) {
        this.value = value;
        this.y = y;
    }

    public static Rank of(String input) {
        return Arrays.stream(Rank.values())
                .filter(row -> row.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }

    public static Rank getStartRankOfPawn(Color color) {
        if (color == Color.BLACK) {
            return BLACK_START_RANK;

        }
        return WHITE_START_RANK;
    }
    public Rank move(int y) {
        return findByY(this.y + y);
    }


    public boolean canMove(int y) {
        int nextY = this.y + y;
        return (MINIMUM <= nextY) && (nextY <= MAXIMUM);
    }

    private Rank findByY(int input) {
        return Arrays.stream(Rank.values())
                .filter(row -> row.y == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Row{" +
                "y=" + y +
                '}';
    }
}
