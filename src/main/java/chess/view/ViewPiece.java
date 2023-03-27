package chess.view;

import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceType;
import java.util.Arrays;

enum ViewPiece {
    PAWN(PieceType.PAWN, "p"),
    ROOK(PieceType.ROOK, "r"),
    KNIGHT(PieceType.Knight, "n"),
    BISHOP(PieceType.Bishop, "b"),
    QUEEN(PieceType.QUEEN, "q"),
    KING(PieceType.KING, "k"),
    EMPTY(PieceType.EMPTY, ".");;

    final PieceType pieceType;
    final String name;

    ViewPiece(final PieceType pieceType, final String name) {
        this.pieceType = pieceType;
        this.name = name;
    }

    public static String getName(Piece piece) {
        ViewPiece viewPiece = findPiece(piece);
        return viewPiece.name;
    }

    private static ViewPiece findPiece(Piece piece) {
        return Arrays.stream(ViewPiece.values())
            .filter(p -> p.pieceType.equals(piece.getPieceType()))
            .findFirst()
            .get();
    }
}
