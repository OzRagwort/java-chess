package chess.domain.board;

import chess.domain.piece.*;

import java.util.*;

public class Board {

    private final Map<Position, Piece> value;

    public Board(Map<Position, Piece> value) {
        this.value = value;
    }

    public static Board getInstance() {
        Map<Position, Piece> board = new HashMap<>();

        board.put(Position.of("a8"), new Rook(Color.BLACK));
        board.put(Position.of("b8"), new Knight(Color.BLACK));
        board.put(Position.of("c8"), new Bishop(Color.BLACK));
        board.put(Position.of("d8"), new Queen(Color.BLACK));
        board.put(Position.of("e8"), new King(Color.BLACK));
        board.put(Position.of("f8"), new Bishop(Color.BLACK));
        board.put(Position.of("g8"), new Knight(Color.BLACK));
        board.put(Position.of("h8"), new Rook(Color.BLACK));

        board.put(Position.of("a7"), new Pawn(Color.BLACK));
        board.put(Position.of("b7"), new Pawn(Color.BLACK));
        board.put(Position.of("c7"), new Pawn(Color.BLACK));
        board.put(Position.of("d7"), new Pawn(Color.BLACK));
        board.put(Position.of("e7"), new Pawn(Color.BLACK));
        board.put(Position.of("f7"), new Pawn(Color.BLACK));
        board.put(Position.of("g7"), new Pawn(Color.BLACK));
        board.put(Position.of("h7"), new Pawn(Color.BLACK));

        board.put(Position.of("a1"), new Rook(Color.WHITE));
        board.put(Position.of("b1"), new Knight(Color.WHITE));
        board.put(Position.of("c1"), new Bishop(Color.WHITE));
        board.put(Position.of("d1"), new Queen(Color.WHITE));
        board.put(Position.of("e1"), new King(Color.WHITE));
        board.put(Position.of("f1"), new Bishop(Color.WHITE));
        board.put(Position.of("g1"), new Knight(Color.WHITE));
        board.put(Position.of("h1"), new Rook(Color.WHITE));

        board.put(Position.of("a2"), new Pawn(Color.WHITE));
        board.put(Position.of("b2"), new Pawn(Color.WHITE));
        board.put(Position.of("c2"), new Pawn(Color.WHITE));
        board.put(Position.of("d2"), new Pawn(Color.WHITE));
        board.put(Position.of("e2"), new Pawn(Color.WHITE));
        board.put(Position.of("f2"), new Pawn(Color.WHITE));
        board.put(Position.of("g2"), new Pawn(Color.WHITE));
        board.put(Position.of("h2"), new Pawn(Color.WHITE));

        return new Board(board);
    }

    public boolean exist(Position position, Class<? extends Piece> type, Color color) {
        Piece piece = value.get(position);
        return piece.isSameType(type) && piece.isSameColor(color);
    }

    public Piece get(Position position) {
        return value.get(position);
    }

    public Optional<Piece> findPieceBy(Position position) {
        Piece piece = value.get(position);
        if (piece == null) {
            return Optional.empty();
        }
        return Optional.of(piece);
    }

    public void move(Position src, Position dest) {
        Piece piece = findPieceBy(src)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));
        // 기물의 행마법 체크
        if (!piece.canMove(src, dest)) {
            throw new IllegalArgumentException("못갑니다");
        }
        // 도착지 아군 체크
        checkSameColorInDestination(piece, dest);
        // 장애물/아군 체크
        checkObstacleInPath(src, dest);
        // 이동 또는 먹힘
        value.put(dest, piece);
        // 기존 위치 기물 제거
        value.remove(src);
    }

    private void checkSameColorInDestination(Piece piece, Position dest) {
        Optional<Piece> optionalPiece = findPieceBy(dest);
        if (optionalPiece.isPresent() && piece.isSameColor(optionalPiece.get())) {
            throw new IllegalArgumentException("도착 위치에 아군이 있어 이동할 수 없습니다.");
        }
    }

    private void checkObstacleInPath(Position src, Position dest) {
        Piece piece = findPieceBy(src)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다"));

        Direction direction = piece.findDirection(src, dest);
        checkObstacle(src, dest, direction);

        // 직선인경우 로직
        // 대각선인 경우 로직
        // 이동경로에 장애물

        // 가는 경로를 알아야한다.
        // Piece가 행마법을 안다.
    }

    private void checkObstacle(Position src, Position dest, Direction direction) {
        while (!src.equals(dest)) {
            src = src.move(direction.getX(), direction.getY());
            if (findPieceBy(src).isPresent()) {
                throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
            }
        }
    }
}
