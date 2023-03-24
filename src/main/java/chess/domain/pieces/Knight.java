package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.Route;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> KNIGHT_DIRECTIONS = List.of(
        Direction.NORTH_NORTH_EAST,
        Direction.NORTH_NORTH_WEST,
        Direction.EAST_EAST_NORTH,
        Direction.EAST_EAST_SOUTH,
        Direction.SOUTH_SOUTH_EAST,
        Direction.SOUTH_SOUTH_WEST,
        Direction.WEST_WEST_NORTH,
        Direction.WEST_WEST_SOUTH
    );

    public Knight(final Team team) {
        super(team, PieceType.Knight);
    }

    @Override
    public void canMove(final Position source, final Position destination, boolean isAttack) {
        validateMoveDirection(source, destination);
        validateRangeOfMove(source, destination);
    }

    @Override
    public Route generateRoute(final Position source, final Position destination) {
        Direction direction = findDirection(source, destination);
        return Route.generateRouteFromOtherPiece(direction, source, destination);
    }

    private void validateMoveDirection(final Position source, final Position destination) {
        KNIGHT_DIRECTIONS.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Knight는 (2,1) (1,2)와 같은 방식으로 움직여야 합니다."));
    }

    private void validateRangeOfMove(final Position source, final Position destination) {
        int absSubOfCol = Math.abs(destination.calculateDistanceOfCol(source));
        int absSubOfRow = Math.abs(destination.calculateDistanceOfRow(source));
        if ((absSubOfRow == 2 && absSubOfCol != 1) || (absSubOfRow == 1 && absSubOfCol != 2)) {
            throw new IllegalArgumentException("Knight의 올바른 이동범위가 아닙니다.");
        }
    }

    private Direction findDirection(final Position source, final Position destination) {
        return KNIGHT_DIRECTIONS.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }
}
