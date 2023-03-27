package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.direction.PawnDirection;
import chess.domain.direction.Route;
import java.util.List;

public class Pawn extends Piece {

    private static final Row INIT_BLACK_PAWN_ROW_POSITION = Row.SEVEN;
    private static final Row INIT_WHITE_PAWN_ROW_POSITION = Row.TWO;

    private static final List<PawnDirection> WHITE_PAWN_FIRST_MOVE_DIRECTION = List.of(
        PawnDirection.WHITE_PAWN_MOVE,
        PawnDirection.WHITE_PAWN_DOUBLE_MOVE,
        PawnDirection.WHITE_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.WHITE_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> WHITE_PAWN_MOVE_DIRECTION = List.of(
        PawnDirection.WHITE_PAWN_MOVE,
        PawnDirection.WHITE_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.WHITE_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> BLACK_PAWN_FIRST_MOVE_DIRECTION = List.of(
        PawnDirection.BLACK_PAWN_MOVE,
        PawnDirection.BLACK_PAWN_DOUBLE_MOVE,
        PawnDirection.BLACK_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.BLACK_PAWN_LEFT_DIAGONAL_ATTACK
    );

    private static final List<PawnDirection> BLACK_PAWN_MOVE_DIRECTION = List.of(
        PawnDirection.BLACK_PAWN_MOVE,
        PawnDirection.BLACK_PAWN_RIGHT_DIAGONAL_ATTACK,
        PawnDirection.BLACK_PAWN_LEFT_DIAGONAL_ATTACK
    );


    private boolean isFirstMove;

    public Pawn(final Team team) {
        super(team, PieceType.PAWN);
        this.isFirstMove = true;
    }

    @Override
    public void canMove(final Position source, final Position destination, final boolean isAttack) {
        if (!isFirstMove(source)) {
            validateAfterMove(source, destination, isAttack);
        }
        if (isFirstMove(source)) {
            validateFirstMove(source, destination, isAttack);
            this.isFirstMove = false;
        }
    }

    @Override
    public Route generateRoute(final Position source, final Position destination) {
        PawnDirection direction = findDirection(source, destination);
        if (direction == PawnDirection.WHITE_PAWN_DOUBLE_MOVE) {
            return Route.generateRouteFromWhitePawnDoubleMove(direction, source, destination);
        }
        if (direction == PawnDirection.BLACK_PAWN_DOUBLE_MOVE) {
            return Route.generateRouteFromBlackPawnDoubleMove(direction, source, destination);
        }
        return Route.generateRouteFromPawn(direction, source, destination);
    }

    private void validateFirstMove(final Position source, final Position destination, final boolean isAttack) {
        if (isWhiteTeam()) {
            validateWhiteTeamFirstMoveDirection(source, destination, isAttack);
        }
        if (isBlackTeam()) {
            validateBlackTeamFirstMoveDirection(source, destination, isAttack);
        }
    }

    private void validateAfterMove(final Position source, final Position destination, final boolean isAttack) {
        if (isWhiteTeam()) {
            validateWhiteTeamMoveDirectionAfterFirst(source, destination, isAttack);
        }
        if (isBlackTeam()) {
            validateBlackTeamMoveDirectionAfterFirst(source, destination, isAttack);
        }
    }

    private void validateWhiteTeamFirstMoveDirection(final Position source, final Position destination, final boolean isAttack) {
        WHITE_PAWN_FIRST_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("White Pawn의 첫 움직임으로 올바르지 않습니다."));
    }

    private void validateWhiteTeamMoveDirectionAfterFirst(final Position source, final Position destination, final boolean isAttack) {
        WHITE_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("White Pawn의 움직임으로 올바르지 않습니다."));
    }

    private void validateBlackTeamFirstMoveDirection(final Position source, final Position destination, final boolean isAttack) {
        BLACK_PAWN_FIRST_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Black Pawn의 첫 움직임으로 올바르지 않습니다."));
    }

    private void validateBlackTeamMoveDirectionAfterFirst(final Position source, final Position destination, final boolean isAttack) {
        BLACK_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination) && vector.isAttackMove(isAttack))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Black Pawn의 움직임으로 올바르지 않습니다."));
    }

    private PawnDirection findDirection(final Position source, final Position destiantion) {
        if (isWhiteTeam()) {
            return findWhitePawnDirection(source, destiantion);
        }
        return findBlackPawnDirection(source, destiantion);
    }

    private PawnDirection findWhitePawnDirection(final Position source, final Position destination) {
        if (isFirstMove(source)) {
            return WHITE_PAWN_FIRST_MOVE_DIRECTION.stream()
                .filter(vector -> vector.isSameDirection(source, destination))
                .findFirst()
                .get();
        }
        return WHITE_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }

    private PawnDirection findBlackPawnDirection(final Position source, final Position destination) {
        if (isFirstMove(source)) {
            return BLACK_PAWN_FIRST_MOVE_DIRECTION.stream()
                .filter(vector -> vector.isSameDirection(source, destination))
                .findFirst()
                .get();
        }
        return BLACK_PAWN_MOVE_DIRECTION.stream()
            .filter(vector -> vector.isSameDirection(source, destination))
            .findFirst()
            .get();
    }

    private boolean isFirstMove(Position source) {
        return source.isSameRow(INIT_BLACK_PAWN_ROW_POSITION)
            || source.isSameRow(INIT_WHITE_PAWN_ROW_POSITION);
    }
}
