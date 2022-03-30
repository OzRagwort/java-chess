package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.movepattern.AbstractStraightMovePattern;
import chess.domain.piece.movepattern.BishopMovePattern;

public final class Bishop extends Piece {

    private static final double POINT = 3.0;

    private final AbstractStraightMovePattern pattern = new BishopMovePattern();

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position destination) {
        return pattern.canMove(source, destination);
    }

    @Override
    public Direction findDirection(Position source, Position destination) {
        return pattern.findDirection(source, destination);
    }

    @Override
    public double getPoint() {
        return POINT;
    }
}
